package example.com.chartexample;

public class TempData {
    private double Mcu1_temp;
    private int Mcu1_cnt;

    public double getMcu1_temp() {
        return Mcu1_temp;
    }

    public int getMcu1_cnt() {
        return Mcu1_cnt;
    }

    public void setMcu1_temp(double mcu1_temp) {
        Mcu1_temp = mcu1_temp;
    }

    public void setMcu1_cnt(int mcu1_cnt) {
        Mcu1_cnt = mcu1_cnt;
    }

    public TempData(double mcu1_temp, int mcu1_cnt) {
        Mcu1_temp = mcu1_temp;
        Mcu1_cnt = mcu1_cnt;
    }

    @Override
    public String toString() {
        return "TempData{" +
                "Mcu1_temp=" + Mcu1_temp +
                ", Mcu1_cnt=" + Mcu1_cnt +
                '}';
    }
}
