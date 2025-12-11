package fashionrelations.data;

import fashionrelations.common.WinterFashionTrend;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WinterFashionTrendsDataReader {

    public static List<WinterFashionTrend> read(String filename) throws IOException, ParseException {
        // array of WinterFashionTrend objects we will return
        List<WinterFashionTrend> winterFashionTrends = new ArrayList<>();

        // step 1: parse the file
        Object obj = new JSONParser().parse(new FileReader(filename));

        // step 2: cast the element to JSONArray, instead of JSONObject (shown in the documentation's example)
        JSONArray jsonArray = (JSONArray) obj;

        // step 3: iterate through the array
        for (Object winterft : jsonArray) {
            // each WinterFashionTrend becomes a JSON object
            JSONObject jsonObject = (JSONObject) winterft;

            // extract the specific data (instance variables)
            String brand = (String) jsonObject.get("Brand");
            String color = (String) jsonObject.get("Color");
            String category = (String) jsonObject.get("Category");
            String material = (String) jsonObject.get("Material");
            String style = (String) jsonObject.get("Style");
            String gender = (String) jsonObject.get("Gender");
            String season = (String) jsonObject.get("Season");
            Number priceNumber = (Number) jsonObject.get("Price(USD)"); // Valeria changed this.
            double price = priceNumber.doubleValue();

//            double price = (double) jsonObject.get("Price(USD)");
//            double popularity = (double) jsonObject.get("Popularity_Score");


            // create WinterFashionTrend object
            WinterFashionTrend wft = new WinterFashionTrend(brand, category, color, material, style, gender, season, price);
            winterFashionTrends.add(wft);
        }
        return winterFashionTrends;
    }

}