package fashionrelations;

import fashionrelations.common.ConsumerBehavior;
import fashionrelations.common.FashionBoutique;
import fashionrelations.common.WinterFashionTrend;
import fashionrelations.data.ConsumerBehaviorReader;
import fashionrelations.data.WinterFashionTrendsDataReader;
import fashionrelations.data.fashionBoutiqueJSONReader;
import fashionrelations.processor.SortWFT;
import fashionrelations.processor.WinterColorAnalysis;

import java.io.IOException;
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

            // this non-static class, we had to create an instance of it (above). Java then creates
            // a default constructor for us to use
            List<FashionBoutique> fashionBoutiqueData = // unsure ab this...
                    fb.readBoutique("FashionBoutiqueDS.json");

            List<ConsumerBehavior> consumerData = ConsumerBehaviorReader.readData("consumerdataset.json");

            // Display menu
            System.out.println("Choose an operation (1 - 7):");
            System.out.println("1. Sort Winter Fashion Trends by Most Popular Materials");
            System.out.println("2. Average Winter Color by Gender");
            System.out.println("3. Operation 3");
            System.out.println("4. Operation 4");
            System.out.println("5. Operation 5");
            System.out.println("6. Operation 6");
            System.out.println("7. Operation 7");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\nSorting by most popular materials...");
                    SortWFT.sortWFTByMaterials(WFTData);

                    // Print results
                    for (WinterFashionTrend w : WFTData) {
                        System.out.println(w.getMaterial() + " - " + w.getBrand());
                    }
                    break;

                case 2:
                    // call the method from the processor
                    Map<String, String> colorResults =
                            WinterColorAnalysis.getAvgWinterColorByGender(WFTData, consumerData);

                    // print the results
                    System.out.println("Most common winter color worn by men: " + colorResults.get("Male"));
                    System.out.println("Most common winter color worn by women: " + colorResults.get("Female"));
                    break;

                case 3:
                    System.out.println("You selected Operation 3.");
                    break;

                case 4:
                    System.out.println("You selected Operation 4.");
                    break;

                case 5:
                    System.out.println("You selected Operation 5.");
                    break;

                case 6:
                    System.out.println("You selected Operation 6.");
                    break;

                case 7:
                    System.out.println("You selected Operation 7.");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a number 1–7.");
            }

        } catch (Exception e) {
            System.out.println("Error loading datasets: " + e.getMessage());
        }
    }

//        fashionBoutiqueJSONReader reader = new fashionBoutiqueJSONReader();
//        List<FashionBoutique> items = reader.readBoutique("fashionBoutiqueDS.json");
//
//        for(FashionBoutique fb : items){
//            System.out.println("Category: " + fb.getCategory());
//            System.out.println("Brand: " + fb.getBrand());
//            System.out.println("Season: " + fb.getSeason());
//            System.out.println("Size: " + fb.getSize());
//            System.out.println("Color: " + fb.getColor());
//            System.out.println("Original_price: " + fb.getOriginal_price());
//            System.out.println("Current_price: " + fb.getCurrent_price());
//            //added a spacer to see if it prints
//            System.out.println("-------------------------------");
//
//        }

//        WinterFashionTrendsDataReader wftreader = new WinterFashionTrendsDataReader();
//        List<WinterFashionTrend> list = wftreader.read("WinterFashionTrendsDS.json");
//
//        for(WinterFashionTrend wft : list){
//            System.out.println("Category: " + wft.getCategory());
//            System.out.println("Brand: " + wft.getBrand());
//            System.out.println("Season: " + wft.getSeason());
//            System.out.println("Color: " + wft.getColor());
//            //added a spacer to see if it prints
//            System.out.println("-------------------------------");

        //}
    //}
}