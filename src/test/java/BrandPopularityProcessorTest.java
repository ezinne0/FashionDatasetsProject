// Unit tests for operation that outputs most popular non-winter szn brand

import fashionrelations.common.FashionBoutique;
import fashionrelations.common.Season;
import fashionrelations.common.WinterFashionTrend;
import fashionrelations.processor.BrandPopularityProcessor;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BrandPopularityProcessorTest {

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
    // Unit tests for operation that outputs most popular winter szn brand
        @Test
        public void testGetPopWinterBrand() {
            BrandPopularityProcessor processor = new BrandPopularityProcessor();

            // null list returns "No data"
            assertEquals("No data", processor.getPopWinterBrand(null));

            // empty list returns "No data"
            assertEquals("No data", processor.getPopWinterBrand(new ArrayList<>()));

            // normal case: North Face appears twice (returned)
            List<WinterFashionTrend> data = new ArrayList<>();

            data.add(new WinterFashionTrend("North Face", null, null,
                    null, null, null, "Winter", 50));
            data.add(new WinterFashionTrend("North Face", null, null,
                    null, null, null, "Winter", 50));
            data.add(new WinterFashionTrend("Fashion Nova", null, null,
                    null, null, null, "Winter", 50));

            String result = processor.getPopWinterBrand(data);
            assertEquals("North Face", result);
        }
    }
