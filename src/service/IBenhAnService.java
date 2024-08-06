package service;

import entities.BenhAn;

import java.util.List;

public interface IBenhAnService {
    public List<BenhAn> getAllBenhAn();

    public boolean findByMaBenhAn(String maBenhAn);

    public void deleteBenhAn(String maBenhAn);

    public void addBenhAn(BenhAn benhAn);
}
