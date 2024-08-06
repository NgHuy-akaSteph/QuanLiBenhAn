package view;

import controller.BenhAnController;

import java.util.Scanner;

public class QuanLiBenhAn {

    private static final Scanner scanner = new Scanner(System.in);
    private static final BenhAnController benhAnController = new BenhAnController();

    public static void main(String[] args) {
        do {
            System.out.println("--- CHƯƠNG TRÌNH QUẢN LÍ BỆNH ÁN ---");
            System.out.println("1. Thêm bệnh án mới");
            System.out.println("2. Xóa bệnh án ");
            System.out.println("3. Xem danh sách bệnh án ");
            System.out.println("4. Thoát ");
            System.out.println("-------------------------------------");
            System.out.print("Chọn chức năng: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch(choice) {
                case 1:
                    benhAnController.themBenhAn();
                    System.out.println("-------------------------------------");
                    break;
                case 2:
                    benhAnController.xoaBenhAn();
                    System.out.println("-------------------------------------");
                    break;
                case 3:
                    benhAnController.xemDanhSachBenhAn();
                    System.out.println("-------------------------------------");
                    break;
                case 4:
                    System.exit(0);
                    break;
            }
        } while (true);
    }
}
