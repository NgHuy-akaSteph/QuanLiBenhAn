package repository;

import entities.BenhAn;

import java.util.List;


public interface IBenhAnRepo {
    List<BenhAn> getAllBenhAn();

    boolean findByMaBenhAn(String maBenhAn);

    void deleteBenhAn(String maBenhAn);

    void addBenhAn(BenhAn benhAn);
}
