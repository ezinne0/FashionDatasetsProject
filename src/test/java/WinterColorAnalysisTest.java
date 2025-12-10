
import fashionrelations.common.ConsumerBehavior;
import fashionrelations.common.WinterFashionTrend;
import fashionrelations.processor.WinterColorAnalysis;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class WinterColorAnalysisTest {

    // Test normal scenario with both datasets and clear male/female colors
    @Test
    void testAvgWinterColorNormal() {
        List<WinterFashionTrend> wft = new ArrayList<>();
        wft.add(new WinterFashionTrend("BrandA", "Coat", "Red", "Wool", "Casual", "Male", "Winter"));
        wft.add(new WinterFashionTrend("BrandB", "Jacket", "Blue", "Polyester", "Formal", "Female", "Winter"));

        List<ConsumerBehavior> consumers = new ArrayList<>();
        consumers.add(new ConsumerBehavior(25, "Male", "Coat", "Coats", 100, "NY", "M", "Red", "Winter"));
        consumers.add(new ConsumerBehavior(30, "Female", "Jacket", "Jackets", 150, "LA", "S", "Blue", "Winter"));

        Map<String, String> result = WinterColorAnalysis.getAvgWinterColorByGender(wft, consumers);

        assertEquals("Red", result.get("Male"));
        assertEquals("Blue", result.get("Female"));
    }

    // Test empty datasets returns N/A for both genders
    @Test
    void testEmptyDatasets() {
        Map<String, String> result = WinterColorAnalysis.getAvgWinterColorByGender(new ArrayList<>(), new ArrayList<>());
        assertEquals("N/A", result.get("Male"));
        assertEquals("N/A", result.get("Female"));
    }

    // Test skipping non-winter items
    @Test
    void testNonWinterItemsSkipped() {
        List<WinterFashionTrend> wft = new ArrayList<>();
        wft.add(new WinterFashionTrend("BrandA", "Coat", "Red", "Wool", "Casual", "Male", "Summer"));
        List<ConsumerBehavior> consumers = new ArrayList<>();
        consumers.add(new ConsumerBehavior(25, "Male", "Coat", "Coats", 100, "NY", "M", "Red", "Fall"));

        Map<String, String> result = WinterColorAnalysis.getAvgWinterColorByGender(wft, consumers);

        assertEquals("N/A", result.get("Male"));
        assertEquals("N/A", result.get("Female"));
    }

    // Test gender variations (man, men, woman, women)
    @Test
    void testGenderVariations() {
        List<WinterFashionTrend> wft = new ArrayList<>();
        wft.add(new WinterFashionTrend("BrandA", "Coat", "Black", "Wool", "Casual", "Man", "Winter"));
        wft.add(new WinterFashionTrend("BrandB", "Jacket", "White", "Polyester", "Formal", "Woman", "Winter"));

        List<ConsumerBehavior> consumers = new ArrayList<>();
        consumers.add(new ConsumerBehavior(25, "Men", "Coat", "Coats", 100, "NY", "M", "Black", "Winter"));
        consumers.add(new ConsumerBehavior(30, "Women", "Jacket", "Jackets", 150, "LA", "S", "White", "Winter"));

        Map<String, String> result = WinterColorAnalysis.getAvgWinterColorByGender(wft, consumers);

        assertEquals("Black", result.get("Male"));
        assertEquals("White", result.get("Female"));
    }

    // Test skipping null gender, null color, null season in WinterFashionTrend
    @Test
    void testNullFieldsInWFT() {
        List<WinterFashionTrend> wft = new ArrayList<>();
        wft.add(new WinterFashionTrend("BrandA", "Coat", null, "Wool", "Casual", "Male", "Winter"));
        wft.add(new WinterFashionTrend("BrandB", "Jacket", "Blue", "Polyester", "Formal", null, "Winter"));
        wft.add(new WinterFashionTrend("BrandC", "Scarf", "Red", "Cotton", "Casual", "Female", null));

        Map<String, String> result = WinterColorAnalysis.getAvgWinterColorByGender(wft, new ArrayList<>());

        assertEquals("N/A", result.get("Male"));
        assertEquals("N/A", result.get("Female"));
    }

    // Test skipping null gender, null color, null season in ConsumerBehavior
    @Test
    void testNullFieldsInConsumers() {
        List<ConsumerBehavior> consumers = new ArrayList<>();
        consumers.add(new ConsumerBehavior(25, null, "Coat", "Coats", 100, "NY", "M", "Red", "Winter"));
        consumers.add(new ConsumerBehavior(30, "Female", "Jacket", "Jackets", 150, "LA", "S", null, "Winter"));
        consumers.add(new ConsumerBehavior(40, "Male", "Coat", "Coats", 100, "NY", "M", "Blue", null));

        Map<String, String> result = WinterColorAnalysis.getAvgWinterColorByGender(new ArrayList<>(), consumers);

        assertEquals("N/A", result.get("Male"));
        assertEquals("N/A", result.get("Female"));
    }

    // Test tie in colors — should pick any max (deterministic in small maps)
    @Test
    void testColorTie() {
        List<WinterFashionTrend> wft = new ArrayList<>();
        wft.add(new WinterFashionTrend("BrandA", "Coat", "Red", "Wool", "Casual", "Male", "Winter"));
        wft.add(new WinterFashionTrend("BrandB", "Coat", "Blue", "Wool", "Casual", "Male", "Winter"));

        Map<String, String> result = WinterColorAnalysis.getAvgWinterColorByGender(wft, new ArrayList<>());

        // either Red or Blue is valid
        assertTrue(result.get("Male").equals("Red") || result.get("Male").equals("Blue"));
        assertEquals("N/A", result.get("Female"));
    }
}