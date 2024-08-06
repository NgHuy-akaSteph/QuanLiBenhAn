package service;

import entities.BenhAn;
import repository.BenhAnRepoImpl;
import repository.IBenhAnRepo;

import java.util.List;
import java.util.Scanner;

public class BenhAnServiceImpl implements IBenhAnService {
    private static final Scanner scanner = new Scanner(System.in);
    private static final IBenhAnRepo benhAnRepo = new BenhAnRepoImpl();
    @Override
    public List<BenhAn> getAllBenhAn() {
        return benhAnRepo.getAllBenhAn();

    }
    @Override
    public boolean findByMaBenhAn(String maBenhAn) {
        return benhAnRepo.findByMaBenhAn(maBenhAn);
    }

    @Override
    public void deleteBenhAn(String maBenhAn) {
        if(benhAnRepo.findByMaBenhAn(maBenhAn)) {
            System.out.println("Bạn có chắc chắn muốn xóa bệnh án có mã " + maBenhAn + " không? (Y/N)");
            String choice = scanner.nextLine();
            if(choice.equals("Y")) {
                benhAnRepo.deleteBenhAn(maBenhAn);
                System.out.println("Xóa bệnh án có mã " + maBenhAn + " thành công!");
            }
        }
    }

    @Override
    public void addBenhAn(BenhAn benhAn) {
        List<BenhAn> list = benhAnRepo.getAllBenhAn();
        if(list.isEmpty()) {
            benhAn.setSttBenhAn(1);
        } else {
            benhAn.setSttBenhAn(list.get(list.size() - 1).getSttBenhAn() + 1);
        }
        benhAnRepo.addBenhAn(benhAn);
    }
}
