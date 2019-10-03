import java.io.FileNotFoundException;
import java.util.Map;

public class Main {

    private static final Map<String, String> mature = ReadFile.readMature();
    private static final Map<String, String> hairpin = ReadFile.readPin();
    private static Motif a = new Motif();

    public static void main(String[] args){
        System.out.println(mature);
        System.out.println(hairpin);
        a.motif("UUGGAGUACACUCUUUC", "..(((((..)))))...");
    }
}