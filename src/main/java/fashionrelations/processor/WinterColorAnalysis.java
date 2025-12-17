package fashionrelations.processor;

import fashionrelations.common.ConsumerBehavior;
import fashionrelations.common.WinterFashionTrend;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

    // most popular color in winter worn by men vs women
public class WinterColorAnalysis {
    // this operation aggregates the WinterFashionTrends and ConsumerBehaviors Datasets
    // gets average (across two datasets) of most common winter color worn by men vs. women
    public static Map<String, String> getAvgWinterColorByGender(List<WinterFashionTrend> wft, List<ConsumerBehavior> consumers) {

        // track male color frequencies
        Map<String, Integer> maleColorFreq = new HashMap<>();

        // track female color frequencies
        Map<String, Integer> femaleColorFreq = new HashMap<>();

        // PROCESS WINTER FASHION TRENDS DATASET
        for (WinterFashionTrend t : wft) {
            // Only include winter items
            if (t.getSeason() == null || !t.getSeason().toLowerCase().contains("winter")) continue;

            // extract color + gender
            String gender = t.getGender().toLowerCase(); // too late to check here!! move below
            String color = t.getColor();

            // any missing info? skip that iteration #errorHandled.
            if (gender == null || color == null) continue;

            // add counts in maps based on gender
            if (gender.equals("male") || gender.equals("man") || gender.equals("men")) {
                maleColorFreq.put(color, maleColorFreq.getOrDefault(color, 0) + 1);
            }
            if (gender.equals("female") || gender.equals("woman") || gender.equals("women")) {
                femaleColorFreq.put(color, femaleColorFreq.getOrDefault(color, 0) + 1);
            }
        }

        // PROCESS CONSUMER BEHAVIOR DATASET (same as above)
        for (ConsumerBehavior c : consumers) {
            // Only include winter items
            if (c.getSeason() == null || !c.getSeason().toLowerCase().contains("winter")) continue;

            // Extract color and gender
            String gender = c.getGender();
            String color = c.getColor();

            // skip missing data
            if (gender == null || color == null) continue;

            // add counts in maps based on gender
            if (gender.equals("male") || gender.equals("man") || gender.equals("men")) {
                maleColorFreq.put(color, maleColorFreq.getOrDefault(color, 0) + 1);
            }
            if (gender.equals("female") || gender.equals("woman") || gender.equals("women")) {
                femaleColorFreq.put(color, femaleColorFreq.getOrDefault(color, 0) + 1);
            }
        }

        // prepare the final output map
        Map<String, String> result = new HashMap<>();

        // Calculate most common color among women based on the map(using helper method below)
        result.put("Female", getMostCommonColor(femaleColorFreq));

        // Calculate most common color among men based on the map (using helper method below)
        result.put("Male", getMostCommonColor(maleColorFreq));

        return result;
    }

    // private helper method: return color with the highest frequency

    private static String getMostCommonColor(Map<String, Integer> freqMap) {

        return freqMap.entrySet()
                // convert to a stream
                .stream()
                // find entry with maximum value (most frequent color
                .max(Map.Entry.comparingByValue())
                // get that color
                .map(Map.Entry::getKey)
                // if map is empty (shouldn't happen), return N/A
                .orElse("N/A");
    }
}