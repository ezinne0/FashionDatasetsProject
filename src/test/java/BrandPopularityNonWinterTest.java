// Unit tests for operation that outputs most popular non-winter szn brand

import fashionrelations.common.FashionBoutique;
import fashionrelations.common.Season;
import fashionrelations.processor.BrandPopularityProcessor;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BrandPopularityNonWinterTest {

    @Test
    public void testGetPopNonWinterBrand() {

        BrandPopularityProcessor processor = new BrandPopularityProcessor();

        // null list = "No data"
        assertEquals("No data", processor.getPopNonWinterBrand(null));

        // empty list = "No data"
        assertEquals("No data", processor.getPopNonWinterBrand(new ArrayList<>()));

        // normal case: Uniqlo (Winter) is skipped
        // Mango (Spring and Summer) appears twice (returned)
        // Uniqlo (Fall) appears once

        List<FashionBoutique> data = new ArrayList<>();
        data.add(new FashionBoutique(null, "Uniqlo", Season.WINTER,
                null, null, 0.0, 0.0)); // skipped
        data.add(new FashionBoutique(null, "Mango", Season.SPRING,
                null, null, 0.0, 0.0));
        data.add(new FashionBoutique(null, "Mango", Season.SUMMER,
                null, null, 0.0, 0.0));
        data.add(new FashionBoutique(null, "Uniqlo", Season.FALL,
                null, null, 0.0, 0.0));

        String result = processor.getPopNonWinterBrand(data);
        assertEquals("Mango", result);
    }
}
