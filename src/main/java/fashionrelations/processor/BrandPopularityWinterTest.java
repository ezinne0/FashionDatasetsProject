package fashionrelations.processor;

import fashionrelations.common.WinterFashionTrend;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BrandPopularityWinterTest {

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
                null, null, null, "Winter"));
        data.add(new WinterFashionTrend("North Face", null, null,
                null, null, null, "Winter"));
        data.add(new WinterFashionTrend("Fashion Nova", null, null,
                null, null, null, "Winter"));

        String result = processor.getPopWinterBrand(data);
        assertEquals("North Face", result);
    }
}
