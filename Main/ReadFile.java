import java.io.*;
import java.util.*;

class ReadFile {

    static Map<String, String> readMature(){

        Map<String, String> mature = new HashMap<>();

        try (Scanner sc = new Scanner(new File("mature.fa"))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.charAt(0) == '>') {
                    String name = line.replace('>',' ').trim();
                    String mirna = sc.nextLine().trim();
                    mature.put(name, mirna);
                }
            }
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
}
