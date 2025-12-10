package fashionrelations.data;

import fashionrelations.common.FashionBoutique;

import java.util.List;
//my doc
public interface fashionBoutiqueReader {
        List<FashionBoutique> readBoutique(String filename);
}
