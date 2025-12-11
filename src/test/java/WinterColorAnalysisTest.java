
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

    // Test the ideal scenario: both datasets have  clear male/female colors
    @Test
    void testAvgWinterColorNormal() {
        List<WinterFashionTrend> wft = new ArrayList<>();
        wft.add(new WinterFashionTrend("BrandA", "Coat", "Red", "Wool", "Casual", "Male", "Winter", 50));
        wft.add(new WinterFashionTrend("BrandB", "Jacket", "Blue", "Polyester", "Formal", "Female", "Winter", 50));

        List<ConsumerBehavior> consumers = new ArrayList<>();
        consumers.add(new ConsumerBehavior(25, "Male", "Coat", "Coats", 100, "NY", "M", "Red", "Winter"));
        consumers.add(new ConsumerBehavior(30, "Female", "Jacket", "Jackets", 150, "LA", "S", "Blue", "Winter"));

        Map<String, String> result = WinterColorAnalysis.getAvgWinterColorByGender(wft, consumers);

        assertEquals("Red", result.get("Male"));
        assertEquals("Blue", result.get("Female"));
    }

    // Test that empty datasets returns N/A for both genders
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
        wft.add(new WinterFashionTrend("BrandA", "Coat", "Red", "Wool", "Casual", "Male", "Summer", 40));
        List<ConsumerBehavior> consumers = new ArrayList<>();
        consumers.add(new ConsumerBehavior(25, "Male", "Coat", "Coats", 100, "NY", "M", "Red", "Fall"));

        Map<String, String> result = WinterColorAnalysis.getAvgWinterColorByGender(wft, consumers);

        assertEquals("N/A", result.get("Male"));
        assertEquals("N/A", result.get("Female"));
    }

    // Test different ways of specifying gender (man, men, woman, women)
    // program should be able to differentiate
    @Test
    void testGenderVariations() {
        List<WinterFashionTrend> wft = new ArrayList<>();
        wft.add(new WinterFashionTrend("BrandA", "Coat", "Black", "Wool", "Casual", "Man", "Winter", 40));
        wft.add(new WinterFashionTrend("BrandB", "Jacket", "White", "Polyester", "Formal", "Woman", "Winter", 40));

        List<ConsumerBehavior> consumers = new ArrayList<>();
        consumers.add(new ConsumerBehavior(25, "Men", "Coat", "Coats", 100, "NY", "M", "Black", "Winter"));
        consumers.add(new ConsumerBehavior(30, "Women", "Jacket", "Jackets", 150, "LA", "S", "White", "Winter"));

        Map<String, String> result = WinterColorAnalysis.getAvgWinterColorByGender(wft, consumers);

        assertEquals("Black", result.get("Male"));
        assertEquals("White", result.get("Female"));
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

    // Test a tie in colors (should pick either one of the top colors)
    @Test
    void testColorTie() {
        List<WinterFashionTrend> wft = new ArrayList<>();
        wft.add(new WinterFashionTrend("BrandA", "Coat", "Red", "Wool", "Casual", "Male", "Winter", 20));
        wft.add(new WinterFashionTrend("BrandB", "Coat", "Blue", "Wool", "Casual", "Male", "Winter", 20));

        Map<String, String> result = WinterColorAnalysis.getAvgWinterColorByGender(wft, new ArrayList<>());

        // Red OR Blue is valid
        assertTrue(result.get("Male").equals("Red") || result.get("Male").equals("Blue"));
        assertEquals("N/A", result.get("Female"));
    }
}