import java.io.*;
import java.util.*;

class Dictionary {

    private Map<String, String> nameToRNA;
    private Map<String, String> nameToDot;
    private Map<String, String> nameToMotif;
    private Map<String, Integer> motifToNum;
    private Map<String, Integer> collection;

    Dictionary(String filePath){
        this(new File(filePath));
    }

    private Dictionary(File fileSource){

        nameToRNA = new HashMap<>();
        nameToDot = new HashMap<>();
        nameToMotif = new HashMap<>();
        motifToNum = new HashMap<>();

        try (Scanner sc = new Scanner(fileSource)) {
            while (sc.hasNextLine()) {
                //Get RNA Name
                String line = sc.nextLine().trim();
                if (line.charAt(0) == '>') {
                    String RNAName = line.replace('>', ' ').trim();

                    //read dot file
                    String sequenceRaw = sc.nextLine().trim();
                    String dotbracketRaw = sc.nextLine().trim();

                    //map things
                    nameToRNA.put(RNAName, sequenceRaw);
                    nameToDot.put(RNAName, dotbracketRaw);

                    String value = Motif.motifMaker(Motif.RNADecomposer(sequenceRaw,dotbracketRaw));
                    nameToMotif.put(RNAName, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        countMotif();
    }

    Map<String, String> getRNAMap (){
        return nameToRNA;
    }

    Map<String, String> getDotMap (){ return nameToDot; }

    Map<String, String> getMotifMap (){
        return nameToMotif;
    }

    Map<String, Integer> getMotifToNum (){
        return motifToNum;
    }

    Map<String, Integer> getCollection() {
        return collection(motifToNum);
    }

    private void countMotif(){

        nameToMotif.forEach((k, v) -> {
            String[] token = v.split("\n");
            for (String s : token) {
                StringBuilder temp = new StringBuilder();
                temp.append(s);
                if (motifToNum.containsKey(s)){
                    int count = motifToNum.get(s);
                    count++;
                    motifToNum.replace(s, count);
                }
                else if (motifToNum.containsKey(temp.reverse().toString())){
                    int count = motifToNum.get(temp.toString());
                    count++;
                    motifToNum.replace(temp.toString(), count);
                    temp.setLength(0);
                }
                else{
                    motifToNum.put(s, 1);
                }
            }
        });
    }

    private Map<String, Integer> collection(Map<String, Integer> status){
        collection = new HashMap<>();
        int count_3bulge, count_5bulge, count_inter, count_hairpin, free_end;
        count_3bulge = count_5bulge = count_hairpin = count_inter = free_end =  0;
        for (String key : status.keySet()) {
            if (key.charAt(0) == '(' && key.charAt(4) == '-') {
                count_3bulge += status.get(key);
            }
            else if (key.charAt(0) == '(' && key.charAt(6) == '-'){
                count_5bulge += status.get(key);
            }
            else if (key.charAt(0) == '(' && key.charAt(key.length()-1) != ')'){
                count_hairpin += status.get(key);
            }
            else if (key.charAt(0) != '('){
                free_end += status.get(key);
            }
            else{
                count_inter += status.get(key);
            }
        }
        collection.put("3_Bulge", count_3bulge);
        collection.put("5_Bulge", count_5bulge);
        collection.put("internal_loop", count_inter);
        collection.put("hairpin", count_hairpin);
        collection.put("free_end", free_end);
        return collection;
    }

}
