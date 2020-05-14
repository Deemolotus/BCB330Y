import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

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

    void compareMotifs(Map<String, String> result, Map<String, String> mutated_result) throws IOException {
        int size = mutated_result.size();
        int count = 0;
        FileWriter stream = new FileWriter("motif_result.txt");
        BufferedWriter out = new BufferedWriter(stream);
        Iterator<Map.Entry<String, String>> it = mutated_result.entrySet().iterator();
        while (it.hasNext() && count < size){
            Map.Entry<String, String> pairs = it.next();
            String name = pairs.getKey().split(" {2}")[1].split("_")[0];
            List motifs = new ArrayList<>(Arrays.asList(pairs.getValue().split("\n")));
            out.write(pairs.getKey() + "\n");

            for (Map.Entry<String, String> stringStringEntry : result.entrySet()){
                String original_name = stringStringEntry.getKey().split(" {2}")[1].split(" ")[0];
                List original_motifs = new ArrayList<>(Arrays.asList(stringStringEntry.getValue().split("\n")));
                if(original_name.equals(name)){
                    for (Object motif : motifs) {
                        String mot = motif.toString();
                        if (!result.get(stringStringEntry.getKey()).contains(mot)) {
                            out.write(mot + "(+) \n");
                        }
                    }
                    for (Object original_motif : original_motifs){
                        String or_mot = original_motif.toString();
                        if (!mutated_result.get(pairs.getKey()).contains(or_mot)) {
                            out.write(or_mot + "(-) \n");
                        }
                    }
                }
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
