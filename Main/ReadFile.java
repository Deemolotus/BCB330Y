import java.io.*;
import java.util.*;

class ReadFile {

    static Map<String, String> readDot(File dotFile){
        Map<String, String> dotDot = new HashMap<>();
        try (Scanner sc = new Scanner(dotFile)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.charAt(0) == '>') {
                    String name = line.replace('>', ' ').trim();
                    sc.nextLine();
                    String real = sc.nextLine().trim();
                    dotDot.put(name, real);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dotDot;
    }

    static Map<String, String> readRNA(File dotFile){
        Map<String, String> dotRna = new HashMap<>();
        try (Scanner sc = new Scanner(dotFile)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.charAt(0) == '>') {
                    String name = line.replace('>', ' ').trim();
                    String mirna = sc.nextLine().trim();
                    dotRna.put(name, mirna);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dotRna;
    }

}
