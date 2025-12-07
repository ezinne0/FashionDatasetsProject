import fashionrelations.common.WinterFashionTrend;
import fashionrelations.processor.SortWFT;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class SortWFTTest {

    // Helper method to build a WinterFashionTrend using only the material field
    private WinterFashionTrend wft(String material) {
        return new WinterFashionTrend(
                "brand", "category", "color", material, "style", "gender", "Winter"
        );
    }

    // Tests correct sorting based on material frequency (descending)
    @Test
    void testSortWFTByMaterials_sortingCorrectness() {
        List<WinterFashionTrend> trends = new ArrayList<>();
        trends.add(wft("Wool"));
        trends.add(wft("Cotton"));
        trends.add(wft("Wool"));
        trends.add(wft("Leather"));
        trends.add(wft("Cotton"));
        trends.add(wft("Wool"));

        SortWFT.sortWFTByMaterials(trends);

        assertEquals("Wool", trends.get(0).getMaterial());
        assertEquals("Wool", trends.get(1).getMaterial());
        assertEquals("Wool", trends.get(2).getMaterial());
        assertEquals("Cotton", trends.get(3).getMaterial());
        assertEquals("Cotton", trends.get(4).getMaterial());
        assertEquals("Leather", trends.get(5).getMaterial());
    }

    // Tests method behavior when given an empty list
    @Test
    void testSortWFTByMaterials_emptyList() {
        List<WinterFashionTrend> empty = new ArrayList<>();
        SortWFT.sortWFTByMaterials(empty);
        assertTrue(empty.isEmpty());
    }

    // Tests sorting when all items share the same material
    @Test
    void testSortWFTByMaterials_allSameMaterial() {
        List<WinterFashionTrend> trends = new ArrayList<>();
        trends.add(wft("Wool"));
        trends.add(wft("Wool"));
        trends.add(wft("Wool"));

        SortWFT.sortWFTByMaterials(trends);

        assertEquals("Wool", trends.get(0).getMaterial());
        assertEquals("Wool", trends.get(1).getMaterial());
        assertEquals("Wool", trends.get(2).getMaterial());
    }
}