package Iris;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Utilities {
    public static List<List<String>> readFile(String fileName)  {
        BufferedReader reader;
        List<List<String>> list = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            while (line != null) {
                List<String> row = List.of(line.split(","));
                list.add(row);
                line = reader.readLine();
            }
            reader.close();
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static List<Record> createListOfRecords (List<List<String>> data) {
        List<Record> records = new ArrayList<>();
        for (List<String> e : data) {
            Record record = new Record(e);
            records.add(record);
        }
        return records;
    }
    public static String getMostCommonType(List<Record> list) {
        List<String> types = new ArrayList<>();
        for (Record r : list) {
            types.add(r.getType());
        }
        Set<String> st = new HashSet<>(types);
        int max = 0;
        String mostCommon = null;

        for (String s : st) {
            int occurrence = Collections.frequency(types, s);
            if (occurrence > max) {
                mostCommon = s;
                max = occurrence;
            }
            else if (occurrence == max) {
                if (Math.random() >= 0.5) {
                    mostCommon = s;
                }
            }
        }
        return mostCommon;
    }
    public static Record euclideanDistance(Record x, Record y) {
        List<Double> listX = x.getList();
        List<Double> listY = y.getList();

        double sum = 0.0;
        for (int i = 0; i < listX.size(); i++) {
            sum += Math.pow(listX.get(i) - listY.get(i), 2.0);
        }
        double distance = Math.sqrt(sum);
        y.setDistance(distance);
        return y;
    }
}
