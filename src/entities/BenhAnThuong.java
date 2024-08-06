package entities;

public class BenhAnThuong extends BenhAn {

    Integer vienPhi;

    public BenhAnThuong(Integer sttBenhAn, String maBenhAn, String maBenhNhan, String tenBenhNhan, String ngayNhapVien, String ngayRaVien, String lyDoNhapVien, Integer vienPhi) {
        super(sttBenhAn, maBenhAn, maBenhNhan, tenBenhNhan, ngayNhapVien, ngayRaVien, lyDoNhapVien);
        this.vienPhi = vienPhi;
    }

    public Integer getVienPhi() {
        return vienPhi;
    }

    public void setVienPhi(Integer vienPhi) {
        this.vienPhi = vienPhi;
    }



    public String toLine() {
        return super.toString() + "," + vienPhi;
    }



}
