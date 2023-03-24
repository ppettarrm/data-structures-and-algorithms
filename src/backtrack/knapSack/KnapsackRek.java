package backtrack.knapSack;

public class KnapsackRek {

    private int knapsackWeight;
    private Item[] items;
    private boolean[] in;

    private int maxProfit;
    private boolean[] maxProfitConf;


    public KnapsackRek(int knapsackWeight, Item[] items){
        this.items = items;
        this.knapsackWeight = knapsackWeight;
        this.in = new boolean[items.length];
        this.maxProfitConf = new boolean[items.length];
        this.maxProfit = Integer.MIN_VALUE;
    }

    public void find(){
        find(0, 0, 0);
    }

    private void find(int k, int cw, int cp){
        if(k == items.length){
            if(cp > maxProfit){
                maxProfit = cp;
                for(int i = 0; i < items.length; i++){
                    maxProfitConf[i] = in[i];
                }
            }
        } else {
            if(items[k].getWeight() + cw <= knapsackWeight){
                in[k] = true;
                find(k + 1, cw + items[k].getWeight(), cp + items[k].getProfit());
            }
            in[k] = false;
            find(k + 1, cw, cp);
        }
    }

    public void printSolution() {
        System.out.println("Max profit: " + maxProfit);
        System.out.println("Solution... ");
        for (int i = 0; i < items.length; i++) {
            if (maxProfitConf[i]) {
                System.out.println(items[i]);
            }
        }
    }

    public static void main(String[] args) {
        int[] weight = {1, 2, 5, 6, 7};
        int[] profit = {1, 6, 18, 22, 28};
        Item[] items = new Item[weight.length];
        for (int i = 0; i < items.length; i++) {
            items[i] = new Item(weight[i], profit[i]);
        }
        KnapsackRek kr = new KnapsackRek(11, items);
        kr.find();
        kr.printSolution();
    }

}
