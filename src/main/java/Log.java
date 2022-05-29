import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Log  {

    @Id
    @Column(nullable = false)
    private String itemId;
    private String itemName;
    private  float itemCosts;
    private int itemQt;

    public Log() {

    }

    public Log(Stock stock) {
        this.itemId = stock.getItemId();
        this.itemName = stock.getItemName();
        this.itemCosts = stock.getItemCosts();
        this.itemQt = stock.getItemQt();
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
    public int getItemQt() {
        return itemQt;
    }
    public  float getItemPrice() {
        return Stock.getItemPrice(itemCosts);
    }
}
