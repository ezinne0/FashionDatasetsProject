package fashionrelations.data;
/*
this class converts a winterfashion trend item into a fashionboutique item
format, bc the winter dataset has different field names and formats.
so this class fixes the data so I can use both data sets together.
 */

import fashionrelations.common.FashionBoutique;
import fashionrelations.common.Season;
import fashionrelations.common.WinterFashionTrend;

public class WinterConverter {

        public static FashionBoutique convert(WinterFashionTrend t) {

            Season seasonEnum = Season.WINTER; // all winter dataset items are winter

            return new FashionBoutique(
                    t.getCategory(),
                    t.getBrand(),
                    seasonEnum,
                    "N/A",
                    t.getColor(),
                    t.getPrice(),
                    t.getPrice()
            );
        }
}
