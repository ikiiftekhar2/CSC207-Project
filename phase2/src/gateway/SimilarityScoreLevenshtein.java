package gateway;

import java.util.ArrayList;
import java.util.Collections;

public class SimilarityScoreLevenshtein implements ISimilarityScore{

    /*
      This function returns the similarity score between two strings using the Levenshtein distance method.
      This algorithm has shown to perform better in long text searches
      **/

    /** @return an array of adjacent letter pairs contained in the input string assisting the edit distance algorithm*/
    private String[] letterPairs(String str) {

        int numPairs = str.length()-1;

        String[] pairs = new String[numPairs];

        for (int i=0; i<numPairs; i++) {

            pairs[i] = str.substring(i,i+2);
        }
        return pairs;

    }
    /** @return an ArrayList of 2-character Strings. which assists the edit distance algorithm*/

    private ArrayList<String> wordLetterPairs(String str) {

        ArrayList<String> allPairs = new ArrayList<>();

        // Tokenize the string and put the tokens/words into an array

        String[] words = str.split("\\s");

        // For each word

        for (String word : words) {

            // Find the pairs of characters

            String[] pairsInWord = letterPairs(word);

            Collections.addAll(allPairs, pairsInWord);
        }

        return allPairs;

    }
    /** @return lexical similarity value in the range [0,1] which is used in other parts of the program*/

    public double getSimilarityScore(String str1, String str2) {

        ArrayList<String> pairs1 = wordLetterPairs(str1.toUpperCase());

        ArrayList<String> pairs2 = wordLetterPairs(str2.toUpperCase());

        int intersection = 0;

        int union = pairs1.size() + pairs2.size();

        for (Object pair1 : pairs1) {

            for (int j = 0; j < pairs2.size(); j++) {

                Object pair2 = pairs2.get(j);

                if (pair1.equals(pair2)) {

                    intersection++;

                    pairs2.remove(j);

                    break;
                }
            }
        }
        return (2.0*intersection)/union;
    }
}