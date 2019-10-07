import java.util.HashMap;
import java.util.Map;

public class Main {

//    private static final Map<String, String> mature = ReadFile.readMature();
    private static final Map<String, String> hairpin = ReadFile.readPin();
    private static Map<String, String> result = new HashMap<>();
    private static Motif a = new Motif();

    public static void main(String[] args){
        for (String key: hairpin.keySet()) {
//            result.put(key, a.motif(hairpin.get(key),get from software));
              result.put(key, a.motif("1234567890", "(((...)).)"));
        }
        System.out.println(result);
    }

}