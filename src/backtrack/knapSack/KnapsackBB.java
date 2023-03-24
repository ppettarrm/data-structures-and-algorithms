package backtrack.knapSack;

import java.util.Arrays;

public class KnapsackBB {

    private int knapsackWeight;
    private Item[] items;
    private boolean[] in;

    private boolean[] maxProfitConf;
    private int maxProfit;

    public KnapsackBB(int knapsackWeight, Item[] items) {
        this.knapsackWeight = knapsackWeight;
        this.items = items;
        this.in = new boolean[items.length];
        this.maxProfitConf = new boolean[items.length];
        this.maxProfit = Integer.MIN_VALUE;
    }

    public void find(){
        Arrays.sort(items);
        find(0, 0, 0);
    }

    private void find(int k, int cw, int cp){
        if(k == items.length){
            if(cp > maxProfit){
                updateConf(cp);
            }
        } else {
            if(items[k].getWeight() + cw <= knapsackWeight){
                int nextWeight = items[k].getWeight() + cw;
                int nextProfit = items[k].getProfit() + cp;
                double boundAdd = nextProfit + bound(k + 1, knapsackWeight - nextWeight);
                if(boundAdd > maxProfit){
                    in[k] = true;
                    find(k + 1, nextWeight, nextProfit);
                }
            }
            double boundSkip = cp + bound(k + 1, knapsackWeight - cw);
            if(boundSkip > maxProfit){
                in[k] = false;
                find(k + 1, cw, cp);
            }
        }
    }

    private double bound(int k, int w){
        int sumw = 0;
        double profitBound = 0.0;
        while(k < items.length && sumw + items[k].getWeight() <= w){
            sumw += items[k].getWeight();
            profitBound += items[k].getProfit();
            k++;
        }

        if(k < items.length){
            double fraction = (double)(w - sumw) / (double) items[k].getWeight();
            profitBound += items[k].getProfit() * fraction;
        }
        return profitBound;
    }


    private void updateConf(int cp){
        maxProfit = cp;
        for(int i = 0; i < in.length; i++){
            maxProfitConf[i] = in[i];
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
        KnapsackBB kr = new KnapsackBB(11, items);
        kr.find();
        kr.printSolution();
    }

}
