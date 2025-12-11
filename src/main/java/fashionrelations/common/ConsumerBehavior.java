package fashionrelations.common;

public class ConsumerBehavior {
//testing
    private int age;
    private String gender;
    private String itemPurchased;
    private String category;
    private double purchaseAmount;
    private String location;
    private String size;
    private String color;
    private String season;

    public ConsumerBehavior(int age, String gender, String itemPurchased,
                            String category, double purchaseAmount,
                            String location, String size, String color,
                            String season) {

        this.age = age;
        this.gender = gender;
        this.itemPurchased = itemPurchased;
        this.category = category;
        this.purchaseAmount = purchaseAmount;
        this.location = location;
        this.size = size;
        this.color = color;
        this.season = season;
    }

    public String getSeason() {
        return season;
    }

    public String getGender() {
        return gender;
    }

    public String getColor() {
        return color;
    }

    public int getAge(){
        return age;
    }
}
