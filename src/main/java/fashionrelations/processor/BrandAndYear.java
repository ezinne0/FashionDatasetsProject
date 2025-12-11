package fashionrelations.processor;

import fashionrelations.common.WinterFashionTrend;

import java.util.List;

public class BrandAndYear {

    /*
    Counts how many winterFashion items match a given brand and year
    Matching is case sensitive and year is extracted from season string.
     */

        public static int countBrandByYear(List<WinterFashionTrend> items, int year, String brand) {
            if (brand == null) {
                throw new NullPointerException("Brand cannot be null");
            }


            int count = 0;

            for (WinterFashionTrend item : items) {

                //String season = item.getSeason();  // e.g. "Winter 2025"

                // Extract year from the end of the string
                int extractedYear = extractYear(item.getSeason());

                if (extractedYear == year && item.getBrand() != null &&
                        item.getBrand().equalsIgnoreCase(brand)) {
                    count++;
                }


            }

            return count;
        }

        /*
        Extracts the year from the end of the season string
        the expected format is "Winter 2025"
        returns -1 if it extraction fails
         */
        private static int extractYear(String seasonString) {
            if (seasonString == null) return -1;

            // split on space → ["Winter", "2025"]
            String[] parts = seasonString.trim().split(" ");

            try {
                return Integer.parseInt(parts[parts.length - 1]);
            } catch (Exception e) {
                return -1;
            }
        }


}
