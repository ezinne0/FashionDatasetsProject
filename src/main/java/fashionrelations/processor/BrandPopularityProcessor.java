package fashionrelations.processor;
import fashionrelations.common.FashionBoutique;
import fashionrelations.common.WinterFashionTrend;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BrandPopularityProcessor {

    public String getMostPopularWinterBrand(List<WinterFashionTrend> winterData) {

        if (winterData == null || winterData.isEmpty()) {
            return "No data";
        }

        Map<String, Integer> brandCount = new HashMap<>();

        for (WinterFashionTrend item : winterData) {
            String brand = item.getBrand();
            if (brand == null) continue;

            brandCount.put(brand, brandCount.getOrDefault(brand, 0) + 1);
        }

        // Find brand with max count
        String topBrand = null;
        int max = 0;

        for (String brand : brandCount.keySet()) {
            int count = brandCount.get(brand);
            if (count > max) {
                max = count;
                topBrand = brand;
            }
        }

        return topBrand;
    }


    public String getMostPopularNonWinterBrand(List<FashionBoutique> boutiqueData) {

        if (boutiqueData == null || boutiqueData.isEmpty()) {
            return "No data";
        }

        Map<String, Integer> brandCount = new HashMap<>();

        for (FashionBoutique item : boutiqueData) {

            // skip winter – we want other seasons
            if ("Winter".equalsIgnoreCase(item.getSeason())) {
                continue;
            }

            String brand = item.getBrand();
            if (brand == null) continue;

            brandCount.put(brand, brandCount.getOrDefault(brand, 0) + 1);
        }

        // Find brand with max count
        String topBrand = null;
        int max = 0;

        for (String brand : brandCount.keySet()) {
            int count = brandCount.get(brand);
            if (count > max) {
                max = count;
                topBrand = brand;
            }
        }

        return topBrand;
    }
}
