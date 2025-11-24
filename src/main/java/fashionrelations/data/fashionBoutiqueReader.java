package fashionrelations.data;

import fashionrelations.common.FashionBoutique;

import java.util.List;

public interface fashionBoutiqueReader {
        List<FashionBoutique> readBoutique(String filename);
}
