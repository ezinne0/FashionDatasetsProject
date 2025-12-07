package fashionrelations.processor;

import fashionrelations.common.WinterFashionTrend;

import java.util.*;

public class SortWFT {

    // method takes in list of WinterFashionTrendObjects
    public static void sortWFTByMaterials(List<WinterFashionTrend> trends) {

        // use Map to count occurrences of each material
        Map<String, Integer> materialFrequency = new HashMap<>();

        // loop through WFT Objects and count frequency of material used
        for (WinterFashionTrend wft : trends) {
            String material = wft.getMaterial();
            materialFrequency.put(material, materialFrequency.getOrDefault(material, 0) + 1);
        }

        // Sort using Comparator: materials with higher frequency come first
        // this compares two WFT Objects at a time (wft1, wft2)
        // materialFrequency.getorDefault gets material's frequency OR assigns it a default val of zero
        trends.sort((wft1, wft2) -> { // this is lambdaaa i think
            int freq1 = materialFrequency.getOrDefault(wft1.getMaterial(), 0);
            int freq2 = materialFrequency.getOrDefault(wft2.getMaterial(), 0);

            // Descending order (most common material first)
            // compare () method return values: 0 means a = b; -1 means a<b; 1 means a>b
            return Integer.compare(freq2, freq1);
        });
    }
}