import java.util.HashMap;
import java.util.Map;

public class Main {

    private static final Map<Character, Character> nucleotideMatcher;

    static {
        nucleotideMatcher = new HashMap<>();
        nucleotideMatcher.put('A', 'U');
        nucleotideMatcher.put('C', 'G');
        nucleotideMatcher.put('U', 'A');
        nucleotideMatcher.put('G', 'C');
    }

    private static boolean IsMatch(char n1, char n2) {

        return nucleotideMatcher.get(n1).equals(n2);

    }

    private static String Builder(String sequence) {

        String structure = "";

        if (sequence.length() % 2 != 0) {
            structure = ".";
        }

        int mid = sequence.length() / 2; //get the middle of the String
        String[] parts = {sequence.substring(0, mid), sequence.substring(mid)};

        char[] part1 = parts[0].toCharArray();
        char[] part2 = parts[1].toCharArray();

        for (int i = 0; i < mid; i++) {
            if (IsMatch(part1[i], part2[mid - i])) {
                structure = "(" + structure + ")";
            } else {
                structure = "." + structure + ".";
            }
        }
        return structure;
    }

    public static void main(String[] args) {

        System.out.println(Builder("CCCCGGG"));


    }
}