class Motif {

    String motif(String miRNA, String dotForm) {

        String mot = "";
        for (int i = 0; i < miRNA.length(); i++) {
            if (dotForm.charAt(i) == '.') {
                mot += miRNA.charAt(i);
            }
        }
        return mot;
    }
}
