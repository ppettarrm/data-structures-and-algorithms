package stringMatching;

import java.util.HashMap;

public class QuickSearch {
    private String pat;
    // slucaj 2: largeShift je duzina pomeraja iza probnog znaka
    private int largeShift;
    // slucaj 1: mapa pomeraja
    private HashMap<Character, Integer> shiftMap =
            new HashMap<Character, Integer>();

    public QuickSearch(String pat) {
        this.pat = pat;
        largeShift = pat.length() + 1;
        int shift = 1;
        for (int i = pat.length() - 1; i >= 0; i--) {
            char c = pat.charAt(i);
            if (!shiftMap.containsKey(c))
                shiftMap.put(c, shift);
            shift++;
        }
    }

    private int shift(char c) {
        Integer s = shiftMap.get(c);
        return s == null ? largeShift : s;
    }

    public int searchIn(String txt) {
        if (pat.length() > txt.length())
            throw new IllegalArgumentException("Patern veci od teksta");
        int i = 0; // pomeraj
        do {
            boolean match = true;
            int j = 0;
            while (match && j < pat.length()) {
                if (pat.charAt(j) != txt.charAt(i + j))
                    match = false;
                else
                    j++;
            }
            if (match) {
                return i;
            } else {
// da li je tekuce poravnanje poslednje poravnanje?
                if (i == txt.length() - pat.length()) {
                    return -1;
                }
                char test = txt.charAt(i + pat.length());
                i += shift(test);
            }
        } while (i <= txt.length() - pat.length());
        return -1;
    }
}
