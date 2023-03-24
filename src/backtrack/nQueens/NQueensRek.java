package backtrack.nQueens;

public class NQueensRek {

    private int n, slCounter;
    private int[] table;

    public NQueensRek(int n){
        this.n = n;
        this.slCounter = 0;
        this.table = new int[n];
    }

    public void find(){
        find(0);
    }

    public void find(int cr){
        if(cr == n){
            print();
        }
        else{
            for(int j = 0; j < n; j++){
                if(availableCell(cr, j)){
                    table[cr] = j;
                    find(cr + 1);
                }
            }
        }
    }

    private boolean availableCell(int cr, int cc){
        for(int i = 0; i < cr; i++){
            if(table[i] == cc || Math.abs(i - cr) == Math.abs(table[i] - cc))
                return false;
        }
        return true;
    }

    private void print(){
        ++slCounter;
        System.out.println("Solution " + slCounter);
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(table[i] == j)
                    System.out.print("Q ");
                else
                    System.out.print("- ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        NQueensRek nq = new NQueensRek(8);
        nq.find();
    }
}
