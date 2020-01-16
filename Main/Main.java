import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {

    private static final Map<String, String> RNA = ReadFile.readRNA();
    private static final Map<String, String> dot = ReadFile.readDot();
    private static Map<String, String> result = new HashMap<>();
    private static Map<String, Integer> status = new HashMap<>();
    private static Motif a = new Motif();

    private static void makeDictionary(){
        for (String key: RNA.keySet()) {
            result.put(key, a.motifMaker(a.motif(RNA.get(key),dot.get(key))));
        }

        result.forEach((k, v) -> {
            String[] token = v.split("\n");
            for (String s : token) {
                StringBuilder temp = new StringBuilder();
                temp.append(s);
                if (status.containsKey(s)){
                    int count = status.get(s);
                    count++;
                    status.replace(s, count);
                }
                else if (status.containsKey(temp.reverse().toString())){
                    int count = status.get(temp.toString());
                    count++;
                    status.replace(temp.toString(), count);
                    temp.setLength(0);
                }
                else{
                    status.put(s, 1);
                }
            }
        });

        //System.out.println(result);
    }
    private static void writeToFile() throws IOException {
        int num = result.size();
        int count = 0;
        FileWriter stream = new FileWriter("motif.txt");
        BufferedWriter out = new BufferedWriter(stream);
        Iterator<Map.Entry<String, String>> it = result.entrySet().iterator();
        while (it.hasNext() && count < num){
            Map.Entry<String, String> pairs = it.next();
            out.write(pairs.getKey() + "\n" + pairs.getValue()  + "\n");
            count++;
        }
        out.close();

        int nu = status.size();
        System.out.println(status.size());
        System.out.println(status);
        int cou = 0;
        FileWriter n_stream = new FileWriter("status.txt");
        BufferedWriter n_out = new BufferedWriter(n_stream);
        Iterator<Map.Entry<String, Integer>> n_it = status.entrySet().iterator();
        while (n_it.hasNext() && cou < nu){
            Map.Entry<String, Integer> pairs = n_it.next();
            n_out.write(pairs.getKey() + ":" + pairs.getValue()  + "\n");
            cou++;
        }
        n_out.close();
    }

    public static StringBuilder options(String type, String input){

        StringBuilder makeString = new StringBuilder();
        String fix = "Num motif in human miRNA: ";
        //Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        //System.out.println("Enter 1 for input motif，enter 2 for input miRNA name, or enter q to exit : ");
       // String number = myObj.nextLine();  // Read user input

        if (input.length() <= 0){ //If invalid input
            type = "null";
        }

        switch (type) {
            case "Motif":
                //System.out.println("Please Enter a motif : ");
                input = input.toUpperCase();

                for (String key : result.keySet()) {
                    if (result.get(key).contains(input)) {
                        makeString.append(key).append("\n");
                    }
                }

                for (String key : status.keySet()) {
                    if (key.equals(input)) {
                        makeString.append(fix).append(status.get(key));
                    }
                }

                if (!makeString.toString().contains(fix)){
                    makeString.setLength(0);
                }

                if (makeString.length() == 0) {
                    return null;
                }
                return makeString;

            case "mRNA":
                System.out.println("Please Enter a miRNA : ");

                input = input.toLowerCase();
                for (String key : result.keySet()) {
                    if (key.contains(input)) {
                        makeString.append(result.get(key)).append("\n");
                    }
                }
                if (makeString.length() == 0) {
                    return null;
                }
                return makeString;
            //case "q":
            //  break label;
            default:
                return null; //Invalid input
        }
    }


    public static void main(String[] args) throws IOException {
//        for (String key: RNA.keySet()) {
//            result.put(key, a.motifMaker(a.motif(RNA.get(key),dot.get(key))));
//        }
//        result.forEach((k, v) -> {
//            String[] token = v.split("\n");
//            for (String s : token) {
//                StringBuilder temp = new StringBuilder();
//                temp.append(s);
//                if (status.containsKey(s)){
//                    int count = status.get(s);
//                    count++;
//                    status.replace(s, count);
//                }
//                else if (status.containsKey(temp.reverse().toString())){
//                    int count = status.get(temp.toString());
//                    count++;
//                    status.replace(temp.toString(), count);
//                    temp.setLength(0);
//                }
//                else{
//                    status.put(s, 1);
//                }
//            }
//        });
//
//        //System.out.println(result);

        makeDictionary();
//        int num = result.size();
//        int count = 0;
//        FileWriter stream = new FileWriter("motif.txt");
//        BufferedWriter out = new BufferedWriter(stream);
//        Iterator<Map.Entry<String, String>> it = result.entrySet().iterator();
//        while (it.hasNext() && count < num){
//            Map.Entry<String, String> pairs = it.next();
//            out.write(pairs.getKey() + "\n" + pairs.getValue()  + "\n");
//            count++;
//        }
//        out.close();
//
//        int nu = status.size();
//        System.out.println(status.size());
//        System.out.println(status);
//        int cou = 0;
//        FileWriter n_stream = new FileWriter("status.txt");
//        BufferedWriter n_out = new BufferedWriter(n_stream);
//        Iterator<Map.Entry<String, Integer>> n_it = status.entrySet().iterator();
//        while (n_it.hasNext() && cou < nu){
//            Map.Entry<String, Integer> pairs = n_it.next();
//            n_out.write(pairs.getKey() + ":" + pairs.getValue()  + "\n");
//            cou++;
//        }
//        n_out.close();
        writeToFile();

//        StringBuilder makeString = new StringBuilder();
//        String fix = "Num motif in human miRNA: ";
        System.out.println("Enter 1 for input motif，enter 2 for input miRNA name, or enter q to exit : ");
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        String type = myObj.nextLine();  // Read user input
        System.out.println("Please Enter Sequence");
        String input = myObj.nextLine();  // Read user input
//
//        label:
//        while (true) {
//            System.out.println("Enter 1 for input motif，enter 2 for input miRNA name, or enter q to exit : ");
//            String number = myObj.nextLine();  // Read user input
//            switch (number) {
//                case "1":
//                    System.out.println("Please Enter a motif : ");
//                    number = myObj.nextLine();
//                    number = number.toUpperCase();
//                  System.out.println(fix);
//                    for (String key : result.keySet()) {
//                        if (result.get(key).contains(number)) {
//                            makeString.append(key).append("\n");
//                        }
//                    }
//
//                    for (String key : status.keySet()) {
//                        if (key.equals(number)) {
//                            makeString.append(fix).append(status.get(key));
//                        }
//                    }
//                    if (!makeString.toString().contains(fix)){
//                        makeString.setLength(0);
//                    }
//                    if (makeString.length() == 0) {
//                        System.out.println("No such motif");
//                    }
//                    System.out.println(makeString);
//                    makeString.setLength(0);
//                    break;
//                case "2":
//                    System.out.println("Please Enter a miRNA : ");
//                    number = myObj.nextLine();
//                    number = number.toLowerCase();
//                    for (String key : result.keySet()) {
//                        if (key.contains(number)) {
//                            makeString.append(result.get(key)).append("\n");
//                        }
//                    }
//                    if (makeString.length() == 0) {
//                        System.out.println("No such miRNA");
//                    }
//                    System.out.println(makeString);
//                    makeString.setLength(0);
//                    break;
//                case "q":
//                    break label;
//                default:
//                    System.out.println("Please enter a valid number");
//                    break;
//            }
//        }
        options(type, input);
    }
}