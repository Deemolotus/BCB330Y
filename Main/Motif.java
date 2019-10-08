import java.util.regex.Pattern;

class Motif {

    String allMotif(String miRNA, String dotForm){
        StringBuilder allMot = new StringBuilder();
        while(miRNA.length() != 0) {
            allMot.append(motif(miRNA, dotForm));
        }
        return allMot.toString();
    }

    private String motif(String miRNA, String dotForm) {

        StringBuilder mot = new StringBuilder();
        StringBuilder up = new StringBuilder();
        StringBuilder down = new StringBuilder();
        StringBuilder middle = new StringBuilder();
        int num = findMiddle(dotForm);
        for (int i = 0; i<num; i++) {
            if (dotForm.charAt(i) == '.'){
                up.append(miRNA.charAt(i));
                dotForm = dotForm.replace(dotForm.charAt(i), ' ');
            }
            if (dotForm.charAt(i) == '.' && dotForm.charAt(i + 1) == '('){
                up.append(miRNA.charAt(i));
                dotForm = dotForm.replace(dotForm.charAt(i), ' ');
                miRNA = miRNA.replaceFirst(Pattern.quote(up.toString()), "");
                mot.append(up);
                up.setLength(0);
            }
        }
        for(int j = miRNA.length() -  1; j > num; j--){
            if (dotForm.charAt(j) == '.'){
                down.append(miRNA.charAt(j));
                dotForm = dotForm.replace(dotForm.charAt(j), ' ');
            }
            if (dotForm.charAt(j) == '.' && dotForm.charAt(j - 1) == ')'){
                down.append(miRNA.charAt(j));
                dotForm = dotForm.replace(dotForm.charAt(j), ' ');
                miRNA = miRNA.replaceFirst(Pattern.quote(down.toString()), "");
                mot.append(down);
                down.setLength(0);
            }
        }
        for (int i = 0; i<num; i++) {
            for (int j = miRNA.length() - 1; j > num; j--) {
                if(dotForm.charAt(i) == '('){
                    if(dotForm.charAt(j) == ')'){
                        middle.append('('+ miRNA.charAt(i) + miRNA.charAt(j) + ')');
                        dotForm = dotForm.replace(dotForm.charAt(i), ' ');
                        dotForm = dotForm.replace(dotForm.charAt(j), ' ');
                        miRNA = miRNA.replaceFirst(Pattern.quote(middle.toString()), "");
                        mot.append(middle);
                        middle.setLength(0);
                    }
                }
            }
        }
        return mot.toString();
    }

    private int findMiddle(String dotForm){
        for (int i = 0; i < dotForm.length(); i++) {
            if (dotForm.charAt(i) == ')') {
                return i;
            }
        }
        return 0;
    }
}
