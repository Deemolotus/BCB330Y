import java.util.ArrayList;
import java.util.List;

class Motif {

    static String RNADecomposer(String miRNA, String dotForm) {

        StringBuilder result = new StringBuilder();

        //Unclothing
        List<Integer> clothPoint = RNAUnclothHelper(dotForm);

        if (clothPoint.size() == 0){//Detect single motif
            return motifSorter(miRNA, dotForm).toString();
        }

        List<String> choppedList = RNAChopper(miRNA, dotForm, clothPoint);

        //Recursion
        result.append(RNADecomposer(choppedList.get(0), choppedList.get(1)));
        result.append(motifSorter(choppedList.get(2), choppedList.get(3)));

        return result.toString();

    }

    //Resolve single ring structure motif
    private static StringBuilder motifSorter(String miRNA, String dotForm) {


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


    //Chop RNA outer RNA & Dot, inner RNA & Dot
    private static List<String> RNAChopper (String miRNA, String dotForm, List<Integer> clothPoint){

        int length = dotForm.length();
        List<String> result = new ArrayList<>();

        int front = clothPoint.get(0);
        int back = clothPoint.get(1);

        String clothingRNA = miRNA.substring(0, front + 1) + miRNA.substring(back, length);
        String clothingDot = dotForm.substring(0, front + 1) + dotForm.substring(back, length);
        result.add(clothingRNA);
        result.add(clothingDot);

        result.add(miRNA.substring(front + 1, back));
        result.add(dotForm.substring(front  + 1, back));

        return result;
    }

    //Return Chopping point
    private static List<Integer> RNAUnclothHelper (String dotForm) {

        int front = 0;
        int back;
        List<Integer> result = new ArrayList<>();

        while (dotForm.charAt(front) != ')'){//Find the position of first )
            front ++;
        }

        back = front - 1;

        while (true){//If one of end are found

            //Toward front
            while (dotForm.charAt(front) == '.'){//Find the position of ()
                front --;
            }

            front --;


            //Toward back
            while (dotForm.charAt(back) == '.'){//Find the position of ()
                back ++;
                if (back >= dotForm.length() - 1) {//Reach the end
                    return result;
                }
            }

            if (back >= dotForm.length() - 2) {//Reach the end
                return result;
            } else if (dotForm.charAt(back) == '('){//Reach another (
                break;
            }

            back ++;

        }


        result.add(front);
        result.add(back);

        return result;

    }


    static String motifMaker(String hairPinRaw){

        StringBuilder hairPin = new StringBuilder();
        hairPin.append(hairPinRaw);

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
            else if (hairPin.charAt(i) == '/' && hairPin.charAt(i + 1) == '('){
                hairPin.insert(i + 1, '-');
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
