import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Main {

    private static final Map<String, String> RNA = ReadFile.readRNA();
    private static final Map<String, String> dot = ReadFile.readDot();
    private static Map<String, String> result = new HashMap<>();
    private static Motif a = new Motif();

    public static void main(String[] args) throws IOException {
        for (String key: RNA.keySet()) {
            result.put(key, a.motifMaker(a.motif(RNA.get(key),dot.get(key))));
        }
        System.out.println(result);
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
    }

}