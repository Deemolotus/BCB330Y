class Motif {

    StringBuilder motif(String miRNA, String dotForm) {

        StringBuilder mot = new StringBuilder();
        StringBuilder up = new StringBuilder();
        StringBuilder down = new StringBuilder();
        StringBuilder middle = new StringBuilder();

        while(miRNA.length() != 0) {

            int i = 0;
            int j = miRNA.length() - 1;
            if (dotForm.charAt(i) == '.') {
                while(dotForm.charAt(i) != '(') {
                    up.append(miRNA.charAt(i));
                    i++;
                    if(i == j + 1){
                        break;
                    }
                }
                if (miRNA.length() - i > 0) {
                    dotForm = dotForm.substring(i);
                    miRNA = miRNA.substring(i);
                }
                mot.append(up);
                up.setLength(0);
                if(miRNA.length() - i == 0){
                    return mot;
                }
            }

            i = 0;
            j = miRNA.length() - 1;
            if (dotForm.charAt(j) == '.') {
                while(dotForm.charAt(j) != ')' ) {
                    down.append(miRNA.charAt(j));
                    j--;
                    if(i == j - 1){
                        break;
                    }
                }
                if (miRNA.length() > 0) {
                    dotForm = dotForm.substring(0,j + 1);
                    miRNA = miRNA.substring(0,j + 1);
                }
                down.reverse();
                mot.append("/").append(down);
                down.setLength(0);
            }

            i = 0;
            j = dotForm.length() - 1;
            if (dotForm.charAt(i) == '(') {
                if (dotForm.charAt(j) == ')') {
                    middle.append("(").append(miRNA.charAt(i)).append(miRNA.charAt(j)).append(")");
                    dotForm = dotForm.substring(i + 1,j);
                    miRNA = miRNA.substring(i + 1, j);
                    mot.append(middle);
                    middle.setLength(0);
                }
            }
        }
        return mot;
    }

    String motifMaker(StringBuilder hairPin){

        int i = 0;
        while(i < hairPin.length()) {
            if (hairPin.charAt(i) == '(') {
                if (i != 0 && hairPin.charAt(i - 1) == ')'){
                    if (hairPin.charAt(i + 4) == '(') {
                        hairPin.replace(i, i + 4,"");
                        i = i -1;
                    }
                }
                if(i == 0){
                    if (hairPin.charAt(i + 4) == '(') {
                        hairPin.replace(i, i + 4,"");
                        i = i -1;
                    }
                }
            }
            i ++;
        }

        i = 0;
        while(i < hairPin.length()){
            if (i == 0 && hairPin.charAt(i) == '/'){
                hairPin.insert(i,'-');
            }
            else if (hairPin.charAt(i) == '/' && hairPin.charAt(i - 1) == ')'){
                hairPin.insert(i, '-');
            }
            if (hairPin.charAt(i) == ')' && hairPin.charAt(i + 1) != '('){
                int j = i + 1;
                int count  = 0;
                while(j < hairPin.length() && hairPin.charAt(j) != '('){
                    if (hairPin.charAt(j) == '/'){
                        count++;
                        j++;
                    }
                    j++;
                    if (j == hairPin.length() - 1){
                        count++;
                    }
                }
                if (count == 0) {
                    hairPin.insert(j, "/-");
                }
            }
            i ++;
        }
        StringBuilder hairPins = motifMakeHelper(hairPin);
        return hairPins.toString();
    }

    private StringBuilder motifMakeHelper(StringBuilder hairPin){

        for (int j = 0; j < hairPin.length(); j++) {
            if (hairPin.charAt(j) == ')'){
                if (j - 4 >= 0 && hairPin.charAt(j - 4) != ')' && hairPin.charAt(j + 1) != '('){
                    hairPin.insert(j + 1, hairPin.substring(j - 3, j + 1));
                }
            }
        }
        for (int j = 0; j < hairPin.length(); j++) {
            if (hairPin.charAt(j) == ')'){
                if (hairPin.charAt(j + 1) == '('){
                    hairPin.insert(j + 1, "\n");
                }
            }
        }
        return hairPin;
    }
}
