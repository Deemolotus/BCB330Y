import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class Compare {

    void compareStatus(Map<String, Integer> status, Map<String, Integer> mutated_status) throws IOException {
        int size = mutated_status.size();
        int count = 0;
        FileWriter stream = new FileWriter("compare.txt");
        BufferedWriter out = new BufferedWriter(stream);
        Iterator<Map.Entry<String, Integer>> it = mutated_status.entrySet().iterator();
        while (it.hasNext() && count < size){
            Map.Entry<String, Integer> pairs = it.next();
            if(status.containsKey(pairs.getKey())){
                int temp;
                temp = mutated_status.get(pairs.getKey()) - status.get(pairs.getKey());
                if (temp != 0){
                    out.write(pairs.getKey() + ":" + temp + "\n");
                }
            }else {
                out.write(pairs.getKey() + ":" + mutated_status.get(pairs.getKey()) + "*" + "\n");
            }
            count++;
        }
        out.close();
    }

    void createDotMap(Map<String, String> normal_dot, Map<String, String> mutated_dot) throws IOException{
        int count = 0;
        for (Map.Entry<String, String> stringStringEntry : normal_dot.entrySet()) {
            FileWriter stream = new FileWriter("RNAdistance/RNA" + count + ".txt");
            BufferedWriter out = new BufferedWriter(stream);
            String name = stringStringEntry.getKey().split(" {2}")[1].split(" ")[0];
            out.write(">" + name + " [" + stringStringEntry.getValue() + "]" + "\n");
            out.write(stringStringEntry.getValue() + "\n");
            for (Map.Entry<String, String> m_pairs : mutated_dot.entrySet()) {
                if (m_pairs.getKey().contains(name)) {
                    out.write(">" + m_pairs.getKey().split(" {2}")[1].split("_")[1]
                            + " [" + m_pairs.getValue() + "] \n" + m_pairs.getValue() + "\n");
                }
            }
            out.close();
            count++;
        }
    }

}
