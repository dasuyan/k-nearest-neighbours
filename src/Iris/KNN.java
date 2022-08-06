package Iris;

import java.util.*;

import static Iris.Utilities.euclideanDistance;
import static Iris.Utilities.getMostCommonType;

public class KNN {
    public static double k;
    public static String train;
    public static String test;


    public static List<Record> trainSet;
    public static List<Record> testSet;
    public static double correct = 0;
    public static double incorrect = 0;

    public KNN(double k, String train, String test) {
        KNN.k = k;
        KNN.train = train;
        KNN.test = test;
        trainSet = Utilities.createListOfRecords(Objects.requireNonNull(Utilities.readFile(train)));
        testSet = Utilities.createListOfRecords(Objects.requireNonNull(Utilities.readFile(test)));
    }

    public static void Process() {
        for (Record r : testSet) {
            classify(r);
        }

        double accuracy = (correct/(correct+ incorrect)) * 100;
        System.out.println("Value of the K parameter: " + k);
        System.out.println("Correct: " + correct + ", Incorrect: " + incorrect);
        System.out.println("Accuracy: " + accuracy + "%");
        //getGraphData();
        System.out.println("****************************************************************************************");
        System.out.println("Type \"exit\" to stop the application");
        while (true) {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter your vector");
            String data = input.nextLine();

            if ("exit".equals(data)) {
                break;
            } else {
                Record inputRecord = new Record(data);
                classify(inputRecord);
            }
        }
    }

    public static void classify(Record record) {
        List<Record> recordsByDistance = new ArrayList<>();
        for (Record value : trainSet) {
            recordsByDistance.add(euclideanDistance(record, value));
        }
        Collections.sort(recordsByDistance);
        List<Record> firstKElementsList = recordsByDistance.stream().limit((long) k).toList();

        String assignedType = getMostCommonType(firstKElementsList);
        System.out.println(record + " => Classified as: " + assignedType);

        if (Objects.equals(assignedType, record.getType())) {
            correct++;
            //System.out.println("Correct");
        }
        else {
            incorrect++;
            //System.out.println("Incorrect");
        }
    }
}
