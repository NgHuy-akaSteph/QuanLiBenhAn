package common;

import entities.BenhAn;
import entities.BenhAnThuong;
import entities.BenhAnVip;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class IOFromFile {

    private static final String PATH = "src/data/medical_records.csv";

    public static List<BenhAn> convertFileToList() {
        List<BenhAn> list = new ArrayList<>();
        try (FileReader fileReader = new FileReader(PATH);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            String[] data;
            while ((line = bufferedReader.readLine()) != null) {
                data = line.split(",");
                if (data[7].matches("//d+")) {
                    list.add(new BenhAnThuong(Integer.parseInt(data[0]), data[1], data[2], data[3], data[4], data[5], data[6], Integer.parseInt(data[7])));
                } else {
                    list.add(new BenhAnVip(Integer.parseInt(data[0]), data[1], data[2], data[3], data[4], data[5], data[6], data[7]));
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return list;
    }

    public static void saveToFile(List<BenhAn> list) {
        try (FileWriter fileWriter = new FileWriter(PATH);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            for (BenhAn benhAn : list) {
                if(benhAn instanceof BenhAnThuong) {
                    bufferedWriter.write(((BenhAnThuong) benhAn).toLine());
                }
                if(benhAn instanceof BenhAnVip) {
                    bufferedWriter.write(((BenhAnVip) benhAn).toLine());
                }
                bufferedWriter.newLine();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
