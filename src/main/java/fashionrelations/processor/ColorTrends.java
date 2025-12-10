package fashionrelations.processor;

import fashionrelations.common.FashionBoutique;
import fashionrelations.common.Season;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

//second operation
public class ColorTrends {

    /*
    Determines the most popular color for each season
    process all boutique items counts color occurence per season
    returns a map : Season -> most common color
     */

    public Map<Season,String> mostPopularColor(List<FashionBoutique> items){

        Map<Season, Map<String,Integer>> counts = new HashMap<>();

        Iterator<FashionBoutique> it = items.iterator();
        while(it.hasNext()) {
            FashionBoutique item = it.next();
            Season s = item.getSeason();
            String c  = item.getColor();

            //if season is not in the map, add it
            if(!counts.containsKey(s)){
                counts.put(s,new HashMap<String, Integer>());
            }

            //count the color
            Map<String, Integer> seasonMap = counts.get(s);
            seasonMap.put(c, seasonMap.getOrDefault(c,0)+1);
        }

        Map<Season,String> result = new HashMap<>();


        for(Season s : counts.keySet()){

            Map<String,Integer> inner = counts.get(s);
            String best = null;
            int max = 0;

            for(String color : inner.keySet()){
                int count = inner.get(color);
                if(count > max){
                  max = count;
                  best = color;
                }
            }

            result.put(s,best);
        }

        return result;
    }


}