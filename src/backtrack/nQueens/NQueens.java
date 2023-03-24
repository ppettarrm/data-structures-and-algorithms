package backtrack.nQueens;

public class NQueens {
    private int n; //vel table
    private int slCounter; //brojac resenja
    private int[] table;

    private class AvailableCell{
        int y;
        AvailableCell next;

        public AvailableCell(int y) {
            this.y = y;
        }
    }

    private AvailableCell[] availableCells;

    public NQueens(int n) {
        this.n = n;
        this.slCounter = 0;
        this.table = new int[n];
        this.availableCells = new AvailableCell[n];
    }

    public void print(){
        ++slCounter;
        System.out.println("Solution " + slCounter);
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(j == table[i])
                    System.out.print("X ");
                else
                    System.out.print("- ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void find(){
        for(int i = n - 1; i >= 0; i--){
            AvailableCell c = new AvailableCell(i);
            c.next = availableCells[0];
            availableCells[0] = c;
        }

        int cr = 0;
        while(cr >= 0){
            while(availableCells[cr] != null){
                table[cr] = availableCells[cr].y;
                availableCells[cr] = availableCells[cr].next;

                if(cr == n -1)
                    print();
                else{
                    cr++;
                    findAvailableCells(cr);
                }
            }
            cr--;
        }
    }

    private void findAvailableCells(int cr){
        availableCells[cr] = null;
        for(int j = n - 1; j >= 0; j--){
            boolean available = true;
            for(int i = 0; i < cr; i++){
                if(table[i] == j || Math.abs(i - cr) == Math.abs(table[i] - j)){
                    available = false; break;
                }
            }
            if(available){
                AvailableCell c = new AvailableCell(j);
                c.next = availableCells[cr];
                availableCells[cr] = c;
            }
        }
    }

    public static void main(String[] args) {
        NQueens nq = new NQueens(8);
        nq.find();
    }

}
