
package MainSystem.Test;

public class KhachHang {
     String MaKh;
     String hoTen;
     String cmnd;
     String diaChi;
     String sdt;

    public KhachHang() {
    }

    public KhachHang(String MaKh, String hoTen, String cmnd, String diaChi, String sdt) {
        this.MaKh = MaKh;
        this.hoTen = hoTen;
        this.cmnd = cmnd;
        this.diaChi = diaChi;
        this.sdt = sdt;
    }

    public String getMaKh() {
        return MaKh;
    }

    public void setMaKh(String MaKh) {
        this.MaKh = MaKh;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

}