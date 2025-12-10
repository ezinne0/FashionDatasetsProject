package fashionrelations.data;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fashionrelations.common.ConsumerBehavior;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ConsumerBehaviorReader {

    public static List<ConsumerBehavior> readData(String filename)
            throws IOException, ParseException {

        List<ConsumerBehavior> records = new ArrayList<>();

        // parse file
        Object obj = new JSONParser().parse(new FileReader(filename));

        // casts element to JSONArray
        JSONArray jsonArray = (JSONArray) obj;

        // iterate through array
        for (Object o : jsonArray) {
            JSONObject jsonObject = (JSONObject) o;

            //int age = ((Integer) jsonObject.get("Age")).intValue();
            Number ageNumber = (Number) jsonObject.get("Age");
            int age = ageNumber.intValue();

            String gender = (String) jsonObject.get("Gender");
            String itemPurchased = (String) jsonObject.get("Item Purchased");
            String category = (String) jsonObject.get("Category");

            // int purchaseAmount = ((Integer) jsonObject.get("Purchase Amount")).intValue();
            Number purchaseAmountNumber = (Number) jsonObject.get("Purchase Amount (USD)");
            double purchaseAmount = purchaseAmountNumber.doubleValue();

            String location = (String) jsonObject.get("Location");
            String size = (String) jsonObject.get("Size");
            String color = (String) jsonObject.get("Color");
            String season = (String) jsonObject.get("Season");

            ConsumerBehavior record = new ConsumerBehavior(
                    age, gender, itemPurchased, category, purchaseAmount,
                    location, size, color, season);

            records.add(record);
        }

        return records;
    }
}
