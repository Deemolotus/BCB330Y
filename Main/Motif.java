class Motif {

    static StringBuilder motif(String miRNA, String dotForm) {


        StringBuilder result = new StringBuilder();
        StringBuilder motif =  new StringBuilder();
        int motifNum = 1;
        motif.append('/');



        int front = 0;
        int back = miRNA.length() - 1;

        while (front < back){


            if (dotForm.charAt(front) == '(' & dotForm.charAt(back) == ')'){

                if (motif.length() != 1){ //Offload motif
                    result.append(motif);
                    motif.setLength(0);
                    motif.append('/');
                    motifNum = 1;
                }

                //Add RNA sequence
                result.append('(');
                result.append(miRNA.charAt(front));
                result.append(miRNA.charAt(back));
                result.append(')');

                front ++;
                back --;

            } else {//Build motif

                if (dotForm.charAt(front) == '.'){
                    motif.insert(0, miRNA.charAt(front));
                    front ++;
                    motifNum ++;
                }

                if (dotForm.charAt(back) == '.'){
                    motif.insert(motifNum,miRNA.charAt(back));
                    back --;
                }

            }

        }

        if (motif.length() != 0){ //Offload motif
            result.append(motif);
        }

        return result;

    }

    static String motifMaker(StringBuilder hairPin){

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

    static private StringBuilder motifMakeHelper(StringBuilder hairPin){

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
