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

    private Map<String, Integer> collection(Map<String, Integer> status) {
        collection = new HashMap<>();
        int count_3bulge, count_5bulge, count_inter, count_hairpin, free_end,
                b3nt1, b3nt2, b3nto, b5nt1, b5nt2, b5nto, h3, h4, h5, h6, ho, l11, l12, l22, lo;
        count_3bulge = count_5bulge = count_hairpin = count_inter = free_end =
                b3nt1 = b3nt2 = b3nto = b5nt1 = b5nt2 = b5nto =
                        h3 = h4 = h5 = h6 = ho = l11 = l12 = l22 = lo = 0;
        for (String key : status.keySet()) {
            if (key.charAt(0) == '(' && key.charAt(4) == '-') {
                if (key.charAt(7) == '(') {
                    b3nt1 += status.get(key);
                } else if (key.charAt(8) == '(') {
                    b3nt2 += status.get(key);
                } else {
                    b3nto += status.get(key);
                }
                count_3bulge += status.get(key);
            } else if (key.charAt(key.length() - 1) == ')' && key.charAt(key.length() - 5) == '-') {
                if (key.length() - 8 >= 0 && key.charAt(key.length() - 8) == ')') {
                    b5nt1 += status.get(key);
                } else if (key.length() - 9 >= 0 && key.charAt(key.length() - 9) == ')') {
                    b5nt2 += status.get(key);
                } else {
                    b5nto += status.get(key);
                }
                count_5bulge += status.get(key);
            } else if (key.charAt(0) == '(' && key.charAt(key.length() - 1) != ')') {
                if (key.length() - 5 == 3) {
                    h3 += status.get(key);
                } else if (key.length() - 5 == 4) {
                    h4 += status.get(key);
                } else if (key.length() - 5 == 5) {
                    h5 += status.get(key);
                } else if (key.length() - 5 == 6) {
                    h6 += status.get(key);
                } else {
                    ho += status.get(key);
                }
                count_hairpin += status.get(key);
            } else if (key.charAt(0) != '(') {
                free_end += status.get(key);
            } else {
                if (key.length() == 11) {
                    l11 += status.get(key);
                } else if (key.length() == 12) {
                    l12 += status.get(key);
                } else if (key.length() == 13 && key.charAt(6) == '/') {
                    l22 += status.get(key);
                } else {
                    lo += status.get(key);
                }
                count_inter += status.get(key);
            }
        }
        collection.put("3_Bulge", count_3bulge);
        collection.put("5_Bulge", count_5bulge);
        collection.put("internal_loop", count_inter);
        collection.put("hairpin", count_hairpin);
        collection.put("free_end", free_end);

        collection.put("3b_1nt", b3nt1);
        collection.put("3b_2nt", b3nt2);
        collection.put("3b_other", b3nto);
        collection.put("5b_1nt", b5nt1);
        collection.put("5b_2nt", b5nt2);
        collection.put("5b_other", b5nto);
        collection.put("loop11", l11);
        collection.put("loop12", l12);
        collection.put("loop22", l22);
        collection.put("loop_other", lo);
        collection.put("hairpin_3", h3);
        collection.put("hairpin_4", h4);
        collection.put("hairpin_5", h5);
        collection.put("hairpin_6", h6);
        collection.put("hairpin_other", ho);

        return collection;
    }
}
