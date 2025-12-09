package fashionrelations;

import fashionrelations.common.WinterFashionTrend;
import fashionrelations.data.WinterFashionTrendsDataReader;
import fashionrelations.common.ConsumerBehavior;
import fashionrelations.data.ConsumerBehaviorReader;
import fashionrelations.processor.ConsumerBehaviorProcessor;
import fashionrelations.processor.BrandPopularityProcessor;
import fashionrelations.data.fashionBoutiqueJSONReader;
import fashionrelations.common.FashionBoutique;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {
//        fashionBoutiqueJSONReader reader = new fashionBoutiqueJSONReader();
//        List<fashionBoutique> items = reader.readBoutique("fashionBoutiqueDS.json");
//
//        for(fashionBoutique fb : items){
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
/*
        WinterFashionTrendsDataReader wftreader = new WinterFashionTrendsDataReader();
        List<WinterFashionTrend> list = wftreader.read("WinterFashionTrendsDS.json");

        for(WinterFashionTrend wft : list){
            System.out.println("Category: " + wft.getCategory());
            System.out.println("Brand: " + wft.getBrand());
            System.out.println("Season: " + wft.getSeason());
            System.out.println("Color: " + wft.getColor());
            //added a spacer to see if it prints
            System.out.println("-------------------------------");

        }
    }

 */
    // Average Age Operation
    List<ConsumerBehavior> consumers = ConsumerBehaviorReader.readData("consumerdataset.json");
    ConsumerBehaviorProcessor processor = new ConsumerBehaviorProcessor();
    double avgAge = processor.getAverageAge(consumers);

    System.out.println("Average Age of Consumers: " + avgAge);

        //--For Comparing Most Popular Winter Brand vs Non-Winter Brand--

        // Reads Winter Dataset
        WinterFashionTrendsDataReader wftreader = new WinterFashionTrendsDataReader();
        List<WinterFashionTrend> winterList = wftreader.read("WinterFashionTrendsDS.json");

        // Reads Boutique Dataset
        fashionBoutiqueJSONReader boutiqueReader = new fashionBoutiqueJSONReader();
        List<FashionBoutique> boutiqueList = boutiqueReader.readBoutique("fashionBoutiqueDS.json");

        // Processor
        BrandPopularityProcessor brandProcessor = new BrandPopularityProcessor();

        String winterBrand = brandProcessor.getPopWinterBrand(winterList);
        String nonWinterBrand = brandProcessor.getPopNonWinterBrand(boutiqueList);
        System.out.println("Most Popular Winter Brand: " + winterBrand);
        System.out.println("Most Popular Non-Winter Brand: " + nonWinterBrand);
    }
}
