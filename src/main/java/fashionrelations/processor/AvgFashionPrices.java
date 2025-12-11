package fashionrelations.processor;

import fashionrelations.common.FashionBoutique;
import fashionrelations.common.Season;
import fashionrelations.data.WinterComparison;

import java.util.List;
import java.util.stream.Collectors;

//this is my first operation

public class AvgFashionPrices {

    /*
    Computes the average original price for winter items vs non winter items
    uses streams to filter by season and then averages the price lists
     */

    public static WinterComparison computeWinterComparison(List<FashionBoutique> data){
        //using streams and lambda expressions
        //winter prices
        List<Double> winterPrices = data.stream()
                .filter(item -> Season.WINTER.equals(item.getSeason()))
                .map(i -> i.getOriginal_price())
                .collect(Collectors.toList());

        //non winter prices
        List<Double> nonWinterPrice = data.stream()
                //Season is a Enum, thats why its Season
                .filter(item -> !Season.WINTER.equals(item.getSeason()))
                .map(i -> i.getOriginal_price())
                .collect(Collectors.toList());

       // double winterAvg = computeAverage(winterPrices);
        //double nonWinterAvg = computeAverage(nonWinterPrice);


        return new WinterComparison(computeAverage(winterPrices),computeAverage(nonWinterPrice));
    }
    /*
    Computes the numerical average of a list of doubles
    returns 0.0 if the list is null or empty
     */

    private static double computeAverage(List<Double> numbers){
        if(numbers == null || numbers.isEmpty()){
            return 0.0;
        }

        double sum = 0.0;
        for(double n : numbers ){
            sum += n;
        }
        return sum / numbers.size();
    }

    /*
    computes the average price for all items matching one category.
    Matching is also case sensitive
     */

    public static double averageForCategory(List<FashionBoutique> items, String category){
        List<Double> prices = items.stream()
                .filter(i -> i.getCategory().equalsIgnoreCase(category))
                .map(i -> i.getOriginal_price())
                .collect(Collectors.toList());

        //to avoid dividing by zero
        if(prices.isEmpty()){
            return 0.0;
        }
        return computeAverage(prices);
    }

    /*
    Computes winter vs non winter average prices for one brand
    Only items matching the brand also case sensitive are evaluated
    Returns null if the brand has no items at all.
     */
    public static WinterComparison computeWinterComparisonForItem(List<FashionBoutique> items, String brand){
        double winterTotal = 0;
        int winterCount = 0;

        double nonWinterTotal = 0;
        int nonWinterCount = 0;

        for (FashionBoutique item : items) {

            // Only evaluate the chosen category
            if (!item.getBrand().equalsIgnoreCase(brand)) {
                continue;
            }

            if (item.getSeason() == Season.WINTER) {
                winterTotal += item.getOriginal_price();
                winterCount++;
            } else {
                nonWinterTotal += item.getOriginal_price();
                nonWinterCount++;
            }
        }

        // No items found
        if (winterCount == 0 && nonWinterCount == 0) {
            return null;
        }

        double winterAvg;
        if (winterCount == 0) {
            winterAvg = 0.0;
        } else {
            winterAvg = winterTotal / winterCount;
        }

        double nonWinterAvg;
        if (nonWinterCount == 0) {
            nonWinterAvg = 0.0;
        } else {
            nonWinterAvg = nonWinterTotal / nonWinterCount;
        }

        return new WinterComparison(winterAvg, nonWinterAvg);
    }


}
