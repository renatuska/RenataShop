package com.company;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

public class User {
	private String username;
	private String password;
	private Role role;
	private String name;
	private String surname;
	private String address;
	private int age;
	private Hashtable<String, Stock> basket;

	public User(String username, String password, Role role, String name, String surname, String address, int age) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.address = address;
		this.role = role;
		this.basket = new Hashtable();
	}

	public String getUsername() {
		return this.username;
	}

	public String getPassword() {
		return this.password;
	}

	public Role getRole() {
		return this.role;
	}

	public String getName() {
		return this.name;
	}

	public String getSurname() {
		return this.surname;
	}

	public String getAddress() {
		return this.address;
	}

	public int getAge() {
		return this.age;
	}

	public String print() {
		return "User{username='" + this.username + '\'' + ", password='" + this.password + '\'' + ", role='" + this.role + '\'' + ", name='" + this.name + '\'' + ", surname='" + this.surname + '\'' + ", address='" + this.address + '\'' + ", age=" + this.age + '}';
	}

	public void addToBasket(Stock stock) {
		this.basket.put(stock.getItemId(), stock);
	}

	public void removeFromBasket(String id) {
		this.basket.remove(id);
	}

	public Stock getStockFromBasket(String id) {
		return this.basket.containsKey(id) ? (Stock)this.basket.get(id) : null;
	}

	public ArrayList<Stock> getStocksFromBasket() {
		ArrayList<Stock> list = new ArrayList();
		Iterator var2 = this.basket.values().iterator();

		while(var2.hasNext()) {
			Stock stock = (Stock)var2.next();
			list.add(stock);
		}

		return list;
	}

	public void clearBasket() {
		this.basket.clear();
	}
}
