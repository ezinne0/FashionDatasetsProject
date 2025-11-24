package fashionrelations.common;

public class FashionBoutique {
    private String category;
    private String brand;
    private String season;
    private String size;
    private String color;
    private double original_price;
    private double current_price;


    public FashionBoutique(String category, String brand, String season, String size,
                           String color, double original_price, double current_price){
        this.category = category;
        this.brand = brand;
        this.season = season;
        this.size = size;
        this.color = color;
        this.original_price = original_price;
        this.current_price = current_price;
    }



    public String getCategory(){
        return category;
    }

    public String getBrand(){
        return brand;
    }

    public String getSeason(){
        return season;
    }

    public String getSize(){
        return size;
    }

    public String getColor(){
        return color;
    }

    public double getOriginal_price(){
        return original_price;
    }

    public double getCurrent_price(){
        return current_price;
    }


}
