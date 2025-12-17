package fashionrelations;

import fashionrelations.common.ConsumerBehavior;
import fashionrelations.common.FashionBoutique;
import fashionrelations.common.Season;
import fashionrelations.common.WinterFashionTrend;
import fashionrelations.data.ConsumerBehaviorReader;
import fashionrelations.data.WinterComparison;
import fashionrelations.data.WinterFashionTrendsDataReader;
import fashionrelations.data.fashionBoutiqueJSONReader;
import fashionrelations.processor.*;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException{
        Scanner scanner = new Scanner(System.in);

        fashionBoutiqueJSONReader fb = new fashionBoutiqueJSONReader();

        try {
            // Load datasets only once
            // don't have to instantiate static class
            List<WinterFashionTrend> WFTData =
                    WinterFashionTrendsDataReader.read("WinterFashionTrendsDS.json");

            // instantiating Valeria's things
            List<WinterFashionTrend> originalWinterData;
            List<WinterFashionTrend> winterData;

            try {
                winterData = WinterFashionTrendsDataReader.read("WinterFashionTrendsDS.json");
            } catch (Exception e) {
                System.out.println("Error reading JSON: " + e.getMessage());
                return;
            }
            originalWinterData = winterData;


            // this non-static class, we had to create an instance of it (above). Java then creates
            // a default constructor for us to use
            List<FashionBoutique> fashionBoutiqueData = // unsure ab this...
                    fb.readBoutique("FashionBoutiqueDS.json");

            List<ConsumerBehavior> consumerData = ConsumerBehaviorReader.readData("consumerdataset.json");
            ConsumerBehaviorProcessor processor = new ConsumerBehaviorProcessor();

            BrandPopularityProcessor brandProcessor = new BrandPopularityProcessor();

            while(true){
                // Display menu
                System.out.println("\nChoose an operation (1 - 7) \nEnter 8 to EXIT:");
                System.out.println("1. Sort Winter Fashion Trends by Most Popular Materials");
                System.out.println("2. Average Winter Color by Gender");
                System.out.println("3. Brands Comparison of Average Price for Winter vs. Non-Winter");
                System.out.println("4. Color Trends Across All Seasons");
                System.out.println("5. Filter Items Sold By Year and Brand");
                System.out.println("6. Average Age of Consumers");
                System.out.println("7. Most Popular Winter vs. Non-Winter Brands");
                System.out.println("8. EXIT");
                System.out.print("Enter your choice: ");

                String choice = scanner.nextLine().trim();

                if (choice.equals("8") || choice.equalsIgnoreCase("exit")) {
                    System.out.println("Goodbye!");
                    break;
                }

                switch (choice) {
                    case "1":
                        System.out.println("\nSorting by most popular materials...");
                        // get singleton instance and sort
                        SortWFT.getInstance().sortWFTByMaterials(WFTData);

                        // keep track of each material's frequency
                        Map<String, Integer> materialFrequency = new HashMap<>();
                        for (WinterFashionTrend w : WFTData) {
                            String material = w.getMaterial();
                            materialFrequency.put(material, materialFrequency.getOrDefault(material, 0) + 1);
                        }

                        // total number of items
                        int total = WFTData.size();

                        // Print unique materials with percentages
                        System.out.println("Material | Percentage");
                        for (Map.Entry<String, Integer> entry : materialFrequency.entrySet()) {
                            // recall that entrySet() returns a Set of all the key-value pairs in a Map; thus, I can iterate
                            String material = entry.getKey();
                            // save the material's percentage
                            double percentage = (entry.getValue() * 100.0) / total;
                            // print it
                            System.out.println(material + " — " +  percentage + "%");
                        }
                        break;

                    case "2":
                        // call the method from the processor; store in map
                        Map<String, String> colorResults =
                                WinterColorAnalysis.getAvgWinterColorByGender(WFTData, consumerData);

                        // print the results
                        System.out.println("Most common winter color worn by men: " + colorResults.get("Male"));
                        System.out.println("Most common winter color worn by women: " + colorResults.get("Female"));
                        break;

                    case "3":
                        System.out.print("\nEnter brand (Uniqlo, H&M, Mango): ");
                        String item = scanner.nextLine().trim();

                        WinterComparison comp =
                                AvgFashionPrices.computeWinterComparisonForItem(fashionBoutiqueData, item);

                        if (comp == null) {
                            System.out.println("No items found for: " + item);
                        } else {
                            System.out.println("\nAverage price comparison for '" + item + "':");
                            System.out.println("Winter Price average: " + comp.getWinterAvg());
                            System.out.println("Non-Winter Price average: " + comp.getNonWinterAvg());
                        }

                        break;

                    case "4":
                        System.out.println("\nColor trends:");
                        ColorTrends trends = new ColorTrends();
                        Map<Season, String> results = trends.mostPopularColor(fashionBoutiqueData);

                        for (Season s : results.keySet()) {
                            System.out.println(s + " → Most popular color: " + results.get(s));
                        }

                        break;

                    case "5":
                        boolean validInput = false;

                        // loop that keeps prompting the user until they enter a valid input
                        while (!validInput) {
                            try {
                                System.out.print("Enter year to filter (ex: 2023, 2024, 2025): ");
                                int year;

                                try {
                                    year = Integer.parseInt(scanner.nextLine().trim());
                                } catch (NumberFormatException e) { // throw this if they enter something that's not a number
                                    throw new IllegalArgumentException("Year must be a valid number.");
                                }

                                if (year != 2023 && year != 2024 && year != 2025) {
                                    throw new IllegalArgumentException(
                                            // throw this if they enter something that's a number, but not one of the year options
                                            "Invalid year. Year must be 2023, 2024, or 2025."
                                    );
                                }
                                // prompt user to enter brand
                                System.out.print("Enter brand to filter (ex: Adidas, H&M, Uniqlo, Gucci, Mango, North Face): ");
                                String brand = scanner.nextLine().trim();


                                if ( // if they DARE pass bad data... throw illegalArgs
                                        !brand.equalsIgnoreCase("Adidas") &&
                                                !brand.equalsIgnoreCase("H&M") &&
                                                !brand.equalsIgnoreCase("Uniqlo") &&
                                                !brand.equalsIgnoreCase("Gucci") &&
                                                !brand.equalsIgnoreCase("Mango") &&
                                                !brand.equalsIgnoreCase("North Face")
                                ) {
                                    throw new IllegalArgumentException(
                                            "Invalid brand. Allowed brands: Adidas, H&M, Uniqlo, Gucci, Mango, North Face."
                                    );
                                }
                                // save the number of times user's input brand came up based on user's input year
                                int count = BrandAndYear.countBrandByYear(originalWinterData, year, brand);

                                System.out.println("\nResults:");
                                System.out.println(
                                        "Items for brand '" + brand + "' in year " + year + ": " + count
                                );

                                validInput = true;
                            // catch anything that slipped thru the cracks
                            } catch (IllegalArgumentException e) {
                                // show error and loop again
                                System.out.println("Error: " + e.getMessage());
                                System.out.println("Please try again.\n");
                            }
                        }
                        break;

                    case "6":
                        double avgAge = processor.getAverageAge(consumerData);
                        System.out.println("Average Age of Consumers: " + avgAge);
                        break;

                    case "7":
                        String winterBrand = brandProcessor.getPopWinterBrand(WFTData);
                        String nonWinterBrand = brandProcessor.getPopNonWinterBrand(fashionBoutiqueData);
                        System.out.println("Most Popular Winter Brand: " + winterBrand);
                        System.out.println("Most Popular Non-Winter Brand: " + nonWinterBrand);
                        break;

                    default:
                        System.out.println("Invalid choice. Please enter a number 1–8.");
                }
            }
        } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
}