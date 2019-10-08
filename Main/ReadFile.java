import java.io.*;
import java.util.*;

class ReadFile {

    static Map<String, String> readMature(){

        Map<String, String> mature = new HashMap<>();

        try (Scanner sc = new Scanner(new File("mature.fa"))) {
            allMap(mature, sc);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return mature;
    }

    static Map<String, String> readPin(){
        Map<String, String> hairpin = new HashMap<>();

        try (Scanner sc = new Scanner(new File("hairpin.fa"))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.charAt(0) == '>') {
                    String name = line.replace('>', ' ').trim();
                    String mirna = sc.nextLine().trim();
                    String mirna1 = sc.nextLine().trim();
                    String hairpinrna = mirna + mirna1;
                    hairpin.put(name, hairpinrna);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return hairpin;
    }
    static Map<String, String> readDot(){
        Map<String, String> dotDot = new HashMap<>();
        try (Scanner sc = new Scanner(new File("mature.dot"))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.charAt(0) == '>') {
                    String name = line.replace('>', ' ').trim();
                    sc.nextLine();
                    String real = sc.nextLine().trim();
                    dotDot.put(name, real);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return dotDot;
    }
    static Map<String, String> readRead(){
        Map<String, String> dotRna = new HashMap<>();
        try (Scanner sc = new Scanner(new File("mature.dot"))) {
            allMap(dotRna, sc);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return dotRna;
    }

    private static void allMap(Map<String, String> dotRna, Scanner sc) {
        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.charAt(0) == '>') {
                String name = line.replace('>', ' ').trim();
                String mirna = sc.nextLine().trim();
                dotRna.put(name, mirna);
            }
        }
    }

}
