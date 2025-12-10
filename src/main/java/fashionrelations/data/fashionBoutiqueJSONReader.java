package fashionrelations.data;

//my doc
import fashionrelations.common.FashionBoutique;
import fashionrelations.common.Season;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class fashionBoutiqueJSONReader implements fashionBoutiqueReader {

    @Override
    public List<FashionBoutique> readBoutique(String filename){
        //list to store all boutique items read from json file
        List<FashionBoutique> boutiqueData = new ArrayList<>();

        try{
            //create JSON parser and open the file
            JSONParser parser = new JSONParser();
            FileReader file = new FileReader(filename);
            //parse the entire file into a json array
            JSONArray array = (JSONArray) parser.parse(file);
            //file.close();

            //loop through each JSON object inside the array
            for(Object obj: array){
                //convert current element into a json object
                JSONObject item = (JSONObject) obj;

                //extract all string fields from json
                String category = (String) item.get("category");
                String brand = (String) item.get("brand");
                String seasonString = (String) item.get("season");
                //this does not work because it does not check null
                //Season season = Season.valueOf(seasonString.toUpperCase());
                Season season = null;

                //skip if null
                if(seasonString == null){
                    continue;
                }
                //fixing for invalid or null season
                try{
                    season = Season.valueOf(seasonString.toUpperCase());
                } catch (Exception e) {
                    //System.out.println("Skipping item with invalid season: " + seasonString);
                    continue; // skip this row safely
                }

                String size = (String) item.get("size");
                String color = (String) item.get("color");

                //extract num fields and convert them to double
                double original_price = ((Number) item.get("original_price")).doubleValue();
                double current_price = ((Number) item.get("current_price")).doubleValue();

                //create new fashionBoutique object
                FashionBoutique fb = new FashionBoutique(category,brand,season,size,color,original_price,current_price);

                //add the object to the list
                boutiqueData.add(fb);
            }
        }catch(Exception e){
            System.out.println("Error Reading JSON: " + e.getMessage());
            //e.printStackTrace();

        }

        //return the full list of boutique items
        return boutiqueData;
    }
}




