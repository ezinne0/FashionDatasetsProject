import fashionrelations.common.WinterFashionTrend;
import fashionrelations.processor.BrandAndYear;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;


public class BrandAndYearTest {


        // Helper to create WinterFashionTrend objects
        private WinterFashionTrend trend(String brand, int year) {
            WinterFashionTrend t = new WinterFashionTrend(
                    brand,
                    "Coats",
                    "Black",
                    "Wool",
                    "Modern",
                    "Unisex",
                    "Winter "+ year,
                    100.0
            );

            t.setYear(year);
            return t;
        }

        @Test
        void testCountBrandByYear_exactMatch() {
            List<WinterFashionTrend> data = Arrays.asList(
                    trend("H&M", 2023),
                    trend("H&M", 2023),
                    trend("Adidas", 2023),
                    trend("H&M", 2022)
            );

            int result = BrandAndYear.countBrandByYear(data, 2023, "H&M");

            assertEquals(2, result, "Should count only H&M items from 2023");
        }

        @Test
        void testCountBrandByYear_brandNotFound() {
            List<WinterFashionTrend> data = Arrays.asList(
                    trend("H&M", 2023),
                    trend("Mango", 2023)
            );

            int result = BrandAndYear.countBrandByYear(data, 2023, "Zara");

            assertEquals(0, result, "Brand Zara should not be found");
        }

        @Test
        void testCountBrandByYear_yearNotFound() {
            List<WinterFashionTrend> data = Arrays.asList(
                    trend("H&M", 2023),
                    trend("H&M", 2023)
            );

            int result = BrandAndYear.countBrandByYear(data, 2021, "H&M");

            assertEquals(0, result, "Year 2021 should not match any items");
        }

        @Test
        void testCountBrandByYear_caseInsensitiveBrand() {
            List<WinterFashionTrend> data = Arrays.asList(
                    trend("Adidas", 2023),
                    trend("ADIDAS", 2023),
                    trend("adidas", 2023)
            );

            int result = BrandAndYear.countBrandByYear(data, 2023, "adIDas");

            assertEquals(3, result, "Brand matching should be case-insensitive");
        }

        @Test
        void testCountBrandByYear_emptyList() {
            List<WinterFashionTrend> data = new ArrayList<>();

            int result = BrandAndYear.countBrandByYear(data, 2023, "H&M");

            assertEquals(0, result, "Should return 0 for empty dataset");
        }
}


