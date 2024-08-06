package controller;

import common.DuplicateMedicalRecordException;
import entities.BenhAn;
import entities.BenhAnThuong;
import entities.BenhAnVip;
import service.IBenhAnService;
import service.BenhAnServiceImpl;

import java.util.Scanner;

public class BenhAnController {
    private static final Scanner scanner = new Scanner(System.in);
    private static final IBenhAnService benhAnService = new BenhAnServiceImpl();
    private String maBenhAn, maBenhNhan, tenBenhNhan, ngayNhapVien, ngayRaVien, lyDoNhapVien;
    //Validate dữ liệu
    String MA_BENH_AN_REGEX = "^BA-[0-9]{3}$";
    String MA_BENH_NHAN_REGEX = "^BN-[0-9]{3}$";
    String NGAY_NHAP_VIEN_REGEX = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/((19|20)\\d\\d)$";
    String NGAY_RA_VIEN_REGEX = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/((19|20)\\d\\d)$";

    //So sánh ngày nhập viện và ngày ra viện
    private boolean compareDate(String date1, String date2) {
        String[] day1 = date1.split("/");
        String[] day2 = date2.split("/");
        if(Integer.parseInt(day1[2]) > Integer.parseInt(day2[2])) {
            return false;
        } else if(Integer.parseInt(day1[2]) == Integer.parseInt(day2[2])) {
            if(Integer.parseInt(day1[1]) > Integer.parseInt(day2[1])) {
                return false;
            } else if(Integer.parseInt(day1[1]) == Integer.parseInt(day2[1])) {
                return Integer.parseInt(day1[0]) <= Integer.parseInt(day2[0]);
            }
        }
        return true;
    }

    //Nhập thông tin bệnh án
    public void inputBenhAn() {

        do {
            try {
                System.out.println("Nhập mã bệnh án: ");
                maBenhAn = scanner.nextLine();
                if(benhAnService.findByMaBenhAn(maBenhAn)) {
                    throw new DuplicateMedicalRecordException("Bệnh án đã tồn tại!");
                }
            } catch (DuplicateMedicalRecordException e) {
                System.err.println(e.getMessage());
            }
        } while(benhAnService.findByMaBenhAn(maBenhAn));

        do {
            System.out.print("Nhập mã bệnh nhân: ");
            maBenhNhan = scanner.nextLine();
        } while(!maBenhNhan.matches(MA_BENH_NHAN_REGEX));

        System.out.print("Nhập tên bệnh nhân: ");
        tenBenhNhan = scanner.nextLine();

        do {
            System.out.print("Nhập ngày nhập viện(dd/mm/yyyy): ");
            ngayNhapVien = scanner.nextLine();
        } while(!ngayNhapVien.matches(NGAY_NHAP_VIEN_REGEX));

        do {
            System.out.print("Nhập ngày ra viện(dd/mm/yyyy): ");
            ngayRaVien = scanner.nextLine();
        } while(!ngayRaVien.matches(NGAY_RA_VIEN_REGEX) || !compareDate(ngayNhapVien, ngayRaVien));

        System.out.print("Nhập lý do nhập viện: ");
        lyDoNhapVien = scanner.nextLine();
    }

    public void xemDanhSachBenhAn() {
        benhAnService.getAllBenhAn().forEach(System.out::println);
    }

    public void xoaBenhAn() {
        String maBenhAn;
        do {
            System.out.print("Nhập mã bệnh án cần xóa: ");
            maBenhAn = scanner.nextLine();
        }while(!maBenhAn.matches(MA_BENH_AN_REGEX));
        benhAnService.deleteBenhAn(maBenhAn);
    }

    public void themBenhAn() {
        inputBenhAn();
        System.out.print("Chọn loại bệnh án (1: Bệnh án thường, 2: Bệnh án VIP): ");
        int loaiBenhAn = Integer.parseInt(scanner.nextLine());
        BenhAn benhAn;
        if(loaiBenhAn == 1) {
            String vienPhi;
            do {
                System.out.print("Nhập viện phí: ");
                vienPhi = scanner.nextLine();
            }while(vienPhi.matches("//d+"));
            benhAn = new BenhAnThuong(0, maBenhAn, maBenhNhan, tenBenhNhan, ngayNhapVien, ngayRaVien, lyDoNhapVien, Integer.parseInt(vienPhi));
        }
        else{
            String loaiVIP;
            do {
                System.out.println("Nhập loại VIP (VIP I, VIP II, VIP III): ");
                loaiVIP = scanner.nextLine();
            }while(loaiVIP.matches("VIP I") || loaiVIP.matches("VIP II") || loaiVIP.matches("VIP III"));
            benhAn = new BenhAnVip(0, maBenhAn, maBenhNhan, tenBenhNhan, ngayNhapVien, ngayRaVien, lyDoNhapVien, loaiVIP);
        }
        benhAnService.addBenhAn(benhAn);
        System.out.println("Thêm bệnh án mã " + maBenhAn + " thành công!");
    }
}
