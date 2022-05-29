import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Stock {

    @Id
    @Column(nullable = false)
    private String itemId;
    private String itemName;
    private  float itemCosts;
    private int itemQt;

    public Stock() {

    }

    public Stock(String itemId, String itemName, float itemCosts, int itemQt) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemCosts = itemCosts;
        this.itemQt = itemQt;
    }

    public String getItemId() {
        return itemId;
    }
    public String getItemName() {
        return itemName;
    }
    public float getItemCosts() {
        return itemCosts;
    }
    public  float getItemPrice() {
        return Stock.getItemPrice(itemCosts);
    }
    public static float getItemPrice(float itemCosts) {
        if(itemCosts < 20) {
            return itemCosts * (float)1.3;
        } else {
            return itemCosts * 2;
        }
    }

    public void setQt(int qt) {
        itemQt = qt;
    }
    public int getItemQt() {
        return itemQt;
    }
}
