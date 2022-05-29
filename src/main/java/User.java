import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Hashtable;


@Entity
public class User  {

	@Id
	@Column(nullable = false)
	private String username;
	private String password;
	private Role role;
	private String name;
	private String surname;
	private String address;
	private int age;

	@Transient
	private Hashtable<String, Stock> basket;

	public User() {
		this.basket = new Hashtable<>();
	}

	public User(String username) {
		this.username = username;
		this.basket = new Hashtable<>();
	}

	public User(String username, String password, Role role, String name, String surname, String address, int age) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.address = address;
		this.role = role;
		this.basket = new Hashtable<>();
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public Role getRole() {
		return role;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getAddress() {
		return address;
	}

	public int getAge() {
		return age;
	}

	@Override
	public String toString() {
		return "User{" +
				"username='" + username + '\'' +
				", password='" + password + '\'' +
				", role='" + role + '\'' +
				", name='" + name + '\'' +
				", surname='" + surname + '\'' +
				", address='" + address + '\'' +
				", age=" + age +
				'}';
	}

	public void addToBasket(Stock stock){
		this.basket.put(stock.getItemId(), stock);
	}

	public void removeFromBasket(String id){
		this.basket.remove(id);
	}

	public Stock getStockFromBasket(String id) {
		if(this.basket.containsKey(id)) {
			return this.basket.get(id);
		}

		return null;
	}

	public ArrayList<Stock> getStocksFromBasket(){
		ArrayList<Stock> list = new ArrayList<>();
		for (Stock stock : basket.values()) {
			list.add(stock);
		}
		return list;
	}

	public void clearBasket(){
		this.basket.clear();
	}
}
