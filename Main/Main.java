import java.util.HashMap;
import java.util.Map;

public class Main {

    private static final Map<String, String> RNA = ReadFile.readRead();
    private static final Map<String, String> dot = ReadFile.readDot();
    private static Map<String, String> result = new HashMap<>();
    private static Motif a = new Motif();

    public static void main(String[] args){
        for (String key: RNA.keySet()) {
            result.put(key, a.motifMaker(a.motif(RNA.get(key),dot.get(key))));
        }
        System.out.println(result);
    }

}