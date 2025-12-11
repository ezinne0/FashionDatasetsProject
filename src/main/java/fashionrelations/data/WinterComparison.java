package fashionrelations.data;
//my doc
/*
holds the average price comparison between winter items
and non winter items
 */
public class WinterComparison {
    private double winterAvg;
    private double nonWinterAvg;

    public WinterComparison(double winterAvg, double nonWinterAvg){
        this.winterAvg = winterAvg;
        this.nonWinterAvg = nonWinterAvg;
    }

    public double getWinterAvg(){
        return winterAvg;
    }

    public double getNonWinterAvg() {
        return nonWinterAvg;
    }
}
