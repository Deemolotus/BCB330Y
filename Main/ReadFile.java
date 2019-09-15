import java.io.*;
import java.util.*;

public class ReadFile {

    public static void main(String[] args) throws FileNotFoundException {

        Map<String, String> map = new HashMap<String, String>();

        try (Scanner sc = new Scanner(new File("mature.fa"))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.charAt(0) == '>') {
                    String name = line;
                    String mirna = sc.nextLine().trim();
                    map.put(name, mirna);
                    System.out.println(Arrays.asList(map));
                }
            }
        }
    }
}
