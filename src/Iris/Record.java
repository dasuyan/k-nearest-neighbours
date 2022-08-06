package Iris;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Record implements Comparable<Record> {
    protected List<Double> list;
    private String type;
    private double distance = 0.0;

    public Record(List<String> list) {
        List<String> org = new ArrayList<>(list);
        type = list.get(list.size() - 1);
        org.remove(list.size() - 1);
        this.list = convertStringListToDoubleList(org, Double::parseDouble);
    }

    public Record(String data) {
        String[] x = data.split(",");
        List<Double> list = new ArrayList<>();
        for (String s : x) {
            list.add(Double.parseDouble(s));
        }
        this.list = list;
    }

    public List<Double> getList() {
        return list;
    }

    public String getType() {
        return type;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        if (distance == 0)
            return  list + ", " + type;
        else
            return type + ", " + distance;
    }
    public static <T, U> List<U>  convertStringListToDoubleList(List<T> listOfString, Function<T, U> function) {
        return listOfString.stream().map(function).collect(Collectors.toList());
    }

    @Override
    public int compareTo(Record o) {
        return Double.compare(getDistance(), o.getDistance());
    }
}
