package dynamicProgramming;

import java.util.Scanner;
import java.util.Stack;

public class EditDistance {
    private String s1, s2;
    private int[][] d;

    public EditDistance(String s1, String s2){
        this.s1 = s1;
        this.s2 = s2;
        this.d = new int[s1.length() + 1][s2.length() + 1];
        computeDistance();
    }

    private void computeDistance(){
        for(int i = 0; i <= s1.length(); i++) d[i][0] = 0;
        for(int i = 0; i <= s2.length(); i++) d[0][i] = 0;

        for(int i = 1; i <= s1.length(); i++){
            for(int j = 1; j <= s2.length(); j++){
                char c1 = s1.charAt(i - 1);
                char c2 = s2.charAt(j - 1);
                if(c1 == c2) d[i][j] = d[i - 1][j - 1];
                else {
                    int dDel = d[i - 1][j];
                    int dAdd = d[i][j - 1];
                    int dSub = d[i - 1][j - 1];
                    d[i][j] = 1 + min3(dDel, dAdd, dSub);
                }
            }
        }

    }

    private int min3(int a, int b, int c) {
        int min = a;
        if(b < min) min = b;
        if(c < min) min = c;
        return min;
    }

    public int getDistance(){ return d[s1.length()][s2.length()]; }

    public void printDistanceMatrix(){
        System.out.print("      ");
        for(int j = 0; j < s2.length(); j++)
            System.out.print(s2.charAt(j) + " ");
        System.out.println();

        for(int i = 0; i <= s1.length(); i++){
            if(i > 0)
                System.out.print(s1.charAt(i - 1) + " ");
            else
                System.out.print("  ");
            for(int j = 0; j <= s2.length(); j++){
                System.out.print(d[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void getExplanation(){
        int i = s1.length();
        int j = s2.length();
        Stack<String> mess = new Stack<>();
        int[] di = {-1, -1, 0};
        int[] dj = {-1, 0, -1};

        while(d[i][j] > 0){
            int min = Integer.MAX_VALUE;
            int minIndex = -1;
            for(int k = 0; k < di.length; k++){
                if(i + di[k] >= 0 && j + dj[k] >= 0){
                    if (d[i + di[k]][j + dj[k]] < min) {
                        min = d[i + di[k]][j + dj[k]];
                        minIndex = k;
                    }
                }
            }

            if (minIndex == 0) {
                if (d[i][j] != min) {
                    mess.push(s1.charAt(i - 1) + " --> " +
                            s2.charAt(j - 1));
                }
                i--; j--;
            } else if (minIndex == 1) {
                i--; mess.push(s1.charAt(i) + " deleted");
            } else {
                j--; mess.push(s2.charAt(j) + " inserted");
            }
        }

        if (mess.isEmpty()) {
            System.out.println("Identical strings... ");
        } else {
            System.out.println("Transformations: ");
            while (!mess.isEmpty()) {
                System.out.println(mess.pop());
            }
        }

    }

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        System.out.println("First string: ");
        String s1 = reader.nextLine();
        System.out.println("Second string: ");
        String s2 = reader.nextLine();
        EditDistance ed = new EditDistance(s1, s2);
        System.out.println("Distanca: " + ed.getDistance());
        ed.printDistanceMatrix();
        ed.getExplanation();
        reader.close();
    }

}
