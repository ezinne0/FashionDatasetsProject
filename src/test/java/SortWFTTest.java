import fashionrelations.common.WinterFashionTrend;
import fashionrelations.processor.SortWFT;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import java.util.*;

class SortWFTTest {

    List<WinterFashionTrend> trends;

    // reset setup before each test
    @BeforeEach
    void setup() {
        trends = new ArrayList<>();
        trends.add(new WinterFashionTrend("A", "Coat", "Red", "Wool", "Casual", "Women", "Winter", 120.0));
        trends.add(new WinterFashionTrend("B", "Sweater", "Blue", "Cotton", "Casual", "Men", "Winter", 80.0));
        trends.add(new WinterFashionTrend("C", "Coat", "Black", "Wool", "Formal", "Women", "Winter", 200.0));
        trends.add(new WinterFashionTrend("D", "Pants", "Gray", "Polyester", "Casual", "Men", "Winter", 60.0));
        trends.add(new WinterFashionTrend("E", "Sweater", "White", "Cotton", "Casual", "Women", "Winter", 90.0));
    }

    // testing that the singleton behaves correctly
    @Test
    void testSingletonReturnsSameInstance() {
        SortWFT s1 = SortWFT.getInstance();
        SortWFT s2 = SortWFT.getInstance();
        assertSame(s1, s2, "getInstance() should always return the same singleton object");
    }

    // Testing the sorting behavior
    @Test
    void testSortWFTByMaterials_sortingCorrect() {
        // Wool = 2, Cotton = 2, Polyester = 1
        SortWFT.sortWFTByMaterials(trends);

        List<String> materials = trends.stream()
                .map(WinterFashionTrend::getMaterial)
                .toList();

        assertTrue(materials.indexOf("Polyester") > materials.indexOf("Wool"));
        assertTrue(materials.indexOf("Polyester") > materials.indexOf("Cotton"));
    }

    // Testing that it handles ties in material frequency

    @Test
    void testSortWFTByMaterials_tiedFrequenciesAllowed() {
        SortWFT.sortWFTByMaterials(trends);

        String first = trends.get(0).getMaterial();
        String second = trends.get(1).getMaterial();
        // this should NOT be true
        assertNotEquals("Polyester", first);
        assertNotEquals("Polyester", second);
    }

    // testing empty list

    @Test
    void testSortWFTByMaterials_emptyList_noError() {
        List<WinterFashionTrend> empty = new ArrayList<>();
        assertDoesNotThrow(() -> SortWFT.sortWFTByMaterials(empty));
        assertTrue(empty.isEmpty());
    }

    // Testing list w just one element; nothing should happen

    @Test
    void testSortWFTByMaterials_singleElement_noChange() {
        List<WinterFashionTrend> one = new ArrayList<>();
        one.add(new WinterFashionTrend("F", "Coat", "Red", "Wool", "Casual", "Women", "Winter", 150.0));

        SortWFT.sortWFTByMaterials(one);
        assertEquals("Wool", one.get(0).getMaterial());
    }

    // Testing when all entries have the same material

    @Test
    void testSortWFTByMaterials_allSameMaterial() {
        List<WinterFashionTrend> same = new ArrayList<>();
        same.add(new WinterFashionTrend("A", "Coat", "Blue", "Silk", "Casual", "Women", "Winter", 200));
        same.add(new WinterFashionTrend("B", "Sweater", "Green", "Silk", "Men", "Winter", "Winter", 180));
        same.add(new WinterFashionTrend("C", "Dress", "Red", "Silk", "Women", "Winter", "Winter", 250));

        SortWFT.sortWFTByMaterials(same);

        for (WinterFashionTrend w : same) {
            assertEquals("Silk", w.getMaterial());
        }
    }
}