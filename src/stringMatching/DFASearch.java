package stringMatching;

import java.util.HashSet;
import java.util.Iterator;

public class DFASearch {
    private String pattern;
    private int[][] dfa;
    public DFASearch(String pattern) {
        this.pattern = pattern;
        buildDFA();
    }
    private void buildDFA() {
        HashSet<Character> chars = new HashSet<Character>();
        for (int i = 0; i < pattern.length(); i++)
            chars.add(pattern.charAt(i));
        dfa = new int[Character.MAX_VALUE][pattern.length() + 1];
        dfa[pattern.charAt(0)][0] = 1;
        int prefixState = 0;
        for (int i = 1; i < pattern.length(); i++) {
            int c = pattern.charAt(i);
            dfa[c][i] = i + 1;
            Iterator<Character> it = chars.iterator();
            while (it.hasNext()) {
                char ch = it.next();
                if (ch != c) dfa[ch][i] = dfa[ch][prefixState];
            }
            prefixState = dfa[c][prefixState];
        }
    }

    public int searchIn(String text) {
        if (pattern.length() > text.length())
            throw new IllegalArgumentException("Patern veci od teksta");
        int state = 0;
        int finalState = pattern.length();
        for (int i = 0; i < text.length(); i++) {
            state = dfa[text.charAt(i)][state];
            if (state == finalState) {
                return i - pattern.length() + 1;
            }
        }
        return -1;
    }

}
