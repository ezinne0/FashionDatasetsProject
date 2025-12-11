
import fashionrelations.common.FashionBoutique;
import fashionrelations.common.Season;
import fashionrelations.data.WinterComparison;
import fashionrelations.processor.AvgFashionPrices;
import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AvgFashionPricesTest {
    private FashionBoutique item(String category, Season season, String color, double original_price, double current_price) {
        return new FashionBoutique(category, "DummyBrand", season, "DummySize", color, original_price, current_price);
    }

    @Test
    public void testComputeWinterComparison_Normal() {
        List<FashionBoutique> items = Arrays.asList(
                item("Mango", Season.WINTER, "Black", 120, 100),
                item("H&M", Season.SUMMER, "Blue", 70, 50));

        WinterComparison comp = AvgFashionPrices.computeWinterComparison(items);

        //originally had 100 as value
        //originally had 50 as value
        assertEquals(120.0, comp.getWinterAvg(), 0.0001);
        assertEquals(70.0, comp.getNonWinterAvg(), 0.0001);
    }


    @Test
    public void testComputeWinterComparison_OnlyWinter() {
        List<FashionBoutique> items = Arrays.asList(
                item("dress", Season.WINTER, "Black", 80,60)
        );

        WinterComparison comp = AvgFashionPrices.computeWinterComparison(items);

        assertEquals(80.0, comp.getWinterAvg(), 0.0001);
        assertEquals(0.0, comp.getNonWinterAvg(), 0.0001);
    }



    @Test
    public void testComputeWinterComparison_OnlyNonWinter() {
        List<FashionBoutique> items = Arrays.asList(
                item("dress", Season.FALL, "Red", 60,40)
        );

        WinterComparison comp = AvgFashionPrices.computeWinterComparison(items);

        assertEquals(0.0, comp.getWinterAvg(), 0.0001);
        assertEquals(60.0, comp.getNonWinterAvg(), 0.0001);
    }





    @Test
    public void testComputeWinterComparison_EmptyList() {
        WinterComparison comp = AvgFashionPrices.computeWinterComparison(Collections.emptyList());

        assertEquals(0.0, comp.getWinterAvg(), 0.0001);
        assertEquals(0.0, comp.getNonWinterAvg(), 0.0001);
    }



    //averageForEachCategory
    @Test
    public void testAverageForCategory_Found() {
        List<FashionBoutique> items = Arrays.asList(
                item("dress", Season.WINTER, "Black", 100,80),
                item("dress", Season.SPRING, "Blue", 50,40)
        );

        assertEquals(75.0, AvgFashionPrices.averageForCategory(items, "dress"), 0.0001);
    }

    @Test
    public void testAverageForCategory_NotFound() {
        List<FashionBoutique> items = Arrays.asList(
                item("hoodie", Season.WINTER, "Black", 80,60)
        );

        assertEquals(0.0, AvgFashionPrices.averageForCategory(items, "dress"), 0.0001);
    }

    @Test
    public void testAverageForCategory_CaseInsensitive() {
        List<FashionBoutique> items = Arrays.asList(
                item("Dress", Season.SUMMER, "Red", 70,55)
        );

        assertEquals(70.0, AvgFashionPrices.averageForCategory(items, "dress"), 0.0001);
    }
}









