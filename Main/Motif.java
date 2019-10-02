

public class Motif {
    public String motif(String miRNA, String dotForm) {

        String mot = "";
        for (int i = 0; i < miRNA.length(); i++) {
            for (int j = miRNA.length() - 1; j > 0; j--){
                if (dotForm.charAt(i) == '(' && dotForm.charAt(j) ==')'){
                    mot += miRNA.charAt(i);
                    mot += miRNA.charAt(j);
                }
            }
        }
        return mot;
    }
}
