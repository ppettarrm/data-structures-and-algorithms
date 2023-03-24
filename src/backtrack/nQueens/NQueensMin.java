package backtrack.nQueens;


public class NQueensMin {

    private int n;
    private int slCounter;
    private int[] table;

    private int[] firstAvailableCell;

    public NQueensMin(int n){
        this.n = n;
        this.slCounter = 0;
        this.table = new int[n];
        this.firstAvailableCell = new int[n];
    }

    public void find(){
        firstAvailableCell[0] = 0;
        int cr = 0;

        while(cr >= 0){
            while (firstAvailableCell[cr] < n){
                table[cr] = firstAvailableCell[cr];
                firstAvailableCell[cr]++;
                updateFirstAvailableCell(cr);

                if(cr == n - 1)
                    print();
                else{
                    cr++;
                    firstAvailableCell[cr] = 0;
                    updateFirstAvailableCell(cr);
                }
            }
            cr--;
        }
    }

    private void print() {
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

    private void updateFirstAvailableCell(int cr) {
        for(int j = firstAvailableCell[cr]; j < n; j++){
            boolean available = true;
            for(int i = 0; i < cr; i++){
                if(table[i] == j || Math.abs(i - cr) == Math.abs(table[i] - j)){
                    available = false; break;
                }
            }
            if(available){
                firstAvailableCell[cr] = j;
                return;
            }
        }
        firstAvailableCell[cr] = n;
    }

    public static void main(String[] args) {
        NQueensMin nq = new NQueensMin(8);
        nq.find();
    }


}
