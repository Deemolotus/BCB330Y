class Motif {

    String motif(String miRNA, String dotForm) {

        StringBuilder mot = new StringBuilder();
        StringBuilder up = new StringBuilder();
        StringBuilder down = new StringBuilder();
        StringBuilder middle = new StringBuilder();
        int num = findMiddle(dotForm);
        for (int i = 0; i<num; i++) {
            if (dotForm.charAt(i) == '.'){
                up.append(miRNA.charAt(i));
            }
            if (dotForm.charAt(i) == '.' && dotForm.charAt(i + 1) == '('){
                up.append(miRNA.charAt(i));
                mot.append(up);
                up.setLength(0);
            }
            if ()

        }
        for(int j = miRNA.length() -  1; j > num; j--){
            if (dotForm.charAt(j) == '.'){
                down.append(miRNA.charAt(j));
            }
            if (dotForm.charAt(j) == '.' && dotForm.charAt(j - 1) == ')'){
                down.append(miRNA.charAt(j));
                mot.append(down);
                down.setLength(0);
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
