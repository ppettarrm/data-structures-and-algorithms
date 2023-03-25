package dynamicProgramming;

import backtrack.knapSack.Item;

import java.util.LinkedList;

public class KnapsackDP {

    private int knapsackWeight;
    private Item[] items;
    private int[][] mp; //tabela maksimalnog profita

    public KnapsackDP(int w, Item[] items){
        this.knapsackWeight = w;
        this.items = items;
        mp = new int[items.length][knapsackWeight + 1];
    }

    public int findMaxProfit(){
        //trivijalna instanca - ruksak kapaciteta 0
        for(int i = 0; i < items.length; i++){
            mp[i][0] = 0;
        }

        for(int j = 0; j <= knapsackWeight; j++){
            if(items[0].getWeight() <= j)
                mp[0][j] = items[0].getProfit();
            else
                mp[0][j] = 0;
        }

        for(int i = 1; i < items.length; i++){
            for(int j = 1; j <= knapsackWeight; j++){
                int profitAdd = 0;
                if(items[i].getWeight() <= j)
                    profitAdd = items[i].getProfit() + mp[i - 1][j - items[i].getWeight()];

                int profitSkip = mp[i - 1][j];
                mp[i][j] = Math.max(profitAdd, profitSkip);
            }
        }
        return mp[items.length - 1][knapsackWeight];
    }

    public LinkedList<Item> getAddedItems() {
        LinkedList<Item> l = new LinkedList<Item>();
        int i = items.length - 1;
        int j = knapsackWeight;
        while (i > 0) {
            if (mp[i][j] != mp[i - 1][j]) {
                l.addFirst(items[i]);
                j -= items[i].getWeight();
            }
            i--;
        }
        if (j > 0 && items[0].getWeight() <= j)
            l.addFirst(items[0]);
        return l;
    }

    public static void main(String[] args) {
        int[] weight = {7, 6, 5, 2, 1};
        int[] profit = {28, 22, 18, 6, 1};
        Item[] items = new Item[weight.length];
        for (int i = 0; i < items.length; i++) {
            items[i] = new Item(weight[i], profit[i]);
        }
        KnapsackDP k = new KnapsackDP(11, items);
        System.out.println("Maks profit = " + k.findMaxProfit());
        LinkedList<Item> addedItems = k.getAddedItems();
        for (int i = 0; i < addedItems.size(); i++) {
            System.out.println(addedItems.get(i));
        }
    }
}
