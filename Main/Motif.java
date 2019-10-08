class Motif {

    private StringBuilder mot = new StringBuilder();
    private StringBuilder up = new StringBuilder();
    private StringBuilder down = new StringBuilder();
    private StringBuilder middle = new StringBuilder();

    String motif(String miRNA, String dotForm) {

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
                    return mot.toString();
                }
            }
            j = miRNA.length() - 1;

            if (dotForm.charAt(j) == '.') {
                while(dotForm.charAt(j) != ')' ) {
                    down.append(miRNA.charAt(j));
                    j--;
                    if(i == j + 1){
                        break;
                    }
                }
                if (j - i > 0) {
                    dotForm = dotForm.substring(i,j);
                    miRNA = miRNA.substring(i,j);
                }
                mot.append("/").append(down);
                down.setLength(0);
            }

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
        return mot.toString();
    }
}
