package stringMatching;

public class KMPSearch {
    private String pat;
    private int[] prefix;
    public KMPSearch(String pat) {
        this.pat = pat;
        buildPrefixTable();
    }
    private void buildPrefixTable() {
        prefix = new int[pat.length() + 1];
        prefix[0] = -1;
        for (int i = 1; i <= pat.length(); i++) {
            int k = prefix[i - 1];
            char c = pat.charAt(i - 1);
            while (k >= 0 && c != pat.charAt(k)) {
                k = prefix[k];
            }
            prefix[i] = k + 1;
        }
    }

    public int searchIn(String txt) {
        int i = 0, j = 0;
        while (i + pat.length() - j <= txt.length()) {
            boolean match = true;
            while (match && j < pat.length()) {
                if (pat.charAt(j) != txt.charAt(i)) {
                    match = false;
                } else {
                    j++; i++;
                }
            }
            if (match) {
                return i - pat.length();
            }
            else {
                if (j == 0) i++;
                else j = prefix[j];
            }
        }
        return -1;
    }


}
