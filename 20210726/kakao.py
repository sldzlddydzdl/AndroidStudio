from flask import Flask, request, jsonify
import json
import requests

application = Flask(__name__)

@application.route("/")
def hello():
    return "Hello goorm!"

@application.route("/kpu",methods=['POST'])
def animal():
    req = request.get_json()
    
    request_type = req["action"]["detailParams"]["Request_type"]["value"]	# json파일 읽기

    if request_type =="장치1 현재온도":
        print("됬다.")
        url = 'http://59.17.45.68/getjson.php'
        data = requests.get(url).json()
 
        print(len(data["dong1"]))
        for element in data["dong1"]:
          if element["cnt"] == str(len(data["dong1"])):
            print ("there")
            temp = round((float(element['temp1']) + float(element['temp2']) + float(element['temp3']))/3, 2)
            print (temp)
            break
          else:
            print ('not there')
            
    if request_type =="장치2 현재온도":
        print("됬다.")
        url = 'http://59.17.45.68/getjson2.php'
        data = requests.get(url).json()
        print(len(data["dong2"]))
        for element in data["dong2"]:
          if element["cnt"] == str(len(data["dong2"])):
            print ("there")
            temp = round((float(element['temp1']) + float(element['temp2']) + float(element['temp3']))/3, 2)
            print (temp)
            break
          else:
            print ('not there')
            
    if request_type =="장치3 현재온도":
        print("됬다.")
        url = 'http://59.17.45.68/getjson3.php'
        data = requests.get(url).json()
        print(len(data["dong3"]))
        for element in data["dong3"]:
          if element["cnt"] == str(len(data["dong3"])):
            print ("there")
            print (element['temp1'])
            temp = element['temp1']
            break
          else:
            print ('not there')

    if request_type =="장치4 현재온도":
        print("됬다.")
        url = 'http://59.17.45.68/getjson4.php'
        data = requests.get(url).json()

        print(len(data["dong4"]))
        for element in data["dong4"]:
          if element["cnt"] == str(len(data["dong4"])):
            print ("there")
            temp = round((float(element['temp1']) + float(element['temp2']) + float(element['temp3']))/3, 2)
            print (temp)
            break
          else:
            print ('not there')

    if request_type =="평균온도":
        print("됬다.")
        url = 'http://59.17.45.68/getjson.php'
        data = requests.get(url).json()
        url_2 = 'http://59.17.45.68/getjson2.php'
        data_2 = requests.get(url_2).json()
        url_3 = 'http://59.17.45.68/getjson3.php'
        data_3 = requests.get(url_3).json()
        url_4 = 'http://59.17.45.68/getjson4.php'
        data_4 = requests.get(url_4).json()
        
        

        print(len(data["dong1"]))
        for element in data["dong1"]:
          if element["cnt"] == str(len(data["dong1"])):
            print ("there")
          
            temp_1 = round((float(element['temp1']) + float(element['temp2']) + float(element['temp3']))/3, 2)
            break
          else:
            print ('not there')
            
        for element in data_2["dong2"]:
          if element["cnt"] == str(len(data_2["dong2"])):
            print ("there")
          
            temp_2 = round((float(element['temp1']) + float(element['temp2']) + float(element['temp3']))/3, 2)
            break
          else:
            print ('not there')
            
        for element in data_3["dong3"]:
          if element["cnt"] == str(len(data_3["dong3"])):
            print ("there")
      
            temp_3 = round((float(element['temp1']) + float(element['temp2']) + float(element['temp3']))/3, 2)
            break
          else:
            print ('not there')
            
        for element in data_4["dong4"]:
          if element["cnt"] == str(len(data_4["dong4"])):
            print ("there")
            
            temp_4 = round((float(element['temp1']) + float(element['temp2']) + float(element['temp3']))/3, 2)
            break
          else:
            print ('not there')

        temp = round((float(temp_1) + float(temp_2) + float(temp_3))/3, 2)
    
    # print(json.dumps(data))
    
    
    
    
    answer = temp
    
    # 답변 텍스트 설정
    res = {
        "version": "2.0",
        "template": {
            "outputs": [
                {
                    "simpleText": {
                        "text": answer
                    }
                }
            ]
        }
    }
    # 답변 전송
    return jsonify(res)

if __name__ == "__main__":
    application.run(host='0.0.0.0', port=5000, threaded=True)
