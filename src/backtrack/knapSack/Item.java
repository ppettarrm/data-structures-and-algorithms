package backtrack.knapSack;

import org.jetbrains.annotations.NotNull;

public class Item implements Comparable<Item> {

    private int weight, profit;


    public Item(int w, int p){
        this.weight = w;
        this.profit = p;
    }

    public int getProfit() {
        return profit;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Item{" +
                "weight=" + weight +
                ", profit=" + profit +
                '}';
    }

    @Override
    public int compareTo(@NotNull Item o) {
        double pd = (double) profit / (double) weight;
        double opd = (double) o.getProfit() / (double) o.getWeight();
        if(pd > opd)
            return - 1;
        else if(pd < opd)
            return 1;
        else
            return 0;

    }
}

