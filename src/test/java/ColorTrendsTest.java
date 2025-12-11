import fashionrelations.common.FashionBoutique;
import fashionrelations.common.Season;
import fashionrelations.processor.ColorTrends;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
public class ColorTrendsTest {

    private FashionBoutique item(Season s, String color) {
        return new FashionBoutique("dress", "BrandX", s, "M", color, 50, 50);
    }

    @Test
    public void testMostPopularColor_NormalCase() {
        ColorTrends trends = new ColorTrends();

        List<FashionBoutique> items = Arrays.asList(
                item(Season.WINTER, "Black"),
                item(Season.WINTER, "Black"),
                item(Season.WINTER, "Red")
        );

        Map<Season, String> result = trends.mostPopularColor(items);

        assertEquals("Black", result.get(Season.WINTER));
    }

    @Test
    public void testMostPopularColor_Tie() {
        ColorTrends trends = new ColorTrends();

        List<FashionBoutique> items = Arrays.asList(
                item(Season.SPRING, "Blue"),
                item(Season.SPRING, "Red")
        );

        Map<Season, String> result = trends.mostPopularColor(items);

        // Both counts = 1, so either answer is acceptable
        assertTrue(result.get(Season.SPRING).equals("Blue") ||
                result.get(Season.SPRING).equals("Red"));
    }

    @Test
    public void testMostPopularColor_EmptyList() {
        ColorTrends trends = new ColorTrends();

        Map<Season, String> result = trends.mostPopularColor(Collections.emptyList());

        assertTrue(result.isEmpty());
    }
}


