import java.io.*;
import java.util.*;

public class ReadFile {

    public static void main(String[] args) throws FileNotFoundException {

        Map<String, String> mature = new HashMap<String, String>();
        Map<String, String> hairpin = new HashMap<String, String>();

        try (Scanner sc = new Scanner(new File("mature.fa"))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.charAt(0) == '>') {
                    String name = line;
                    String mirna = sc.nextLine().trim();
                    mature.put(name, mirna);
                }
            }
            System.out.print(mature);
        }

        try (Scanner sc = new Scanner(new File("hairpin.fa"))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.charAt(0) == '>') {
                    String name = line;
                    String mirna = sc.nextLine().trim();
                    String mirna1 = sc.nextLine().trim();
                    String hairpinrna = mirna + mirna1;
                    hairpin.put(name, hairpinrna);
                }
            }
            System.out.print(hairpin);
        }
    }
}
