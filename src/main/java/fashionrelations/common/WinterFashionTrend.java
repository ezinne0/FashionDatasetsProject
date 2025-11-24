package fashionrelations.common;

public class WinterFashionTrend {

    private String brand;
    private String category;
    private String color;
    private String material;
    private String style;
    private String gender;
    private String season;
    private double price;
    private double popularity;

    // constructor
    public WinterFashionTrend(String brand, String category, String color, String material,
                              String style, String gender, String season) {
        this.brand = brand;
        this.category = category;
        this.color = color;
        this.material = material;
        this.style = style;
        this.gender = gender;
        this.season = season;
        this.price = price;
        this.popularity = popularity;
    }

    // getter methods

    public String getBrand() {
        return brand;
    }
    public String getCategory() {
        return category;
    }
    public String getColor() {
        return color;
    }
    public String getMaterial() {
        return material;
    }
    public String getStyle() {
        return style;
    }
    public String getGender() {
        return gender;
    }
    public String getSeason() {
        return season;
    }
    public double getPrice() {
        return price;
    }
    public double getPopularity() {
        return popularity;
    }
}
