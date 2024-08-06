package repository;

import common.IOFromFile;
import entities.BenhAn;

import java.util.List;

public class BenhAnRepoImpl implements IBenhAnRepo {

    @Override
    public List<BenhAn> getAllBenhAn() {
        return IOFromFile.convertFileToList();
    }

    @Override
    public boolean findByMaBenhAn(String maBenhAn) {
        List<BenhAn> list = IOFromFile.convertFileToList();
        for (BenhAn benhAn : list) {
            if (benhAn.getMaBenhAn().equals(maBenhAn)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void deleteBenhAn(String maBenhAn) {
        List<BenhAn> list = IOFromFile.convertFileToList();
        for (BenhAn benhAn : list) {
            if (benhAn.getMaBenhAn().equals(maBenhAn)) {
                list.remove(benhAn);
                break;
            }
        }
        IOFromFile.saveToFile(list);
    }

    @Override
    public void addBenhAn(BenhAn benhAn) {
        List<BenhAn> list = IOFromFile.convertFileToList();
        list.add(benhAn);
        IOFromFile.saveToFile(list);
    }
}
