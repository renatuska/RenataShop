import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class RenataShop {

	private static final String NEATPAZINTA_IVESTIS = "Neatpažinta įvestis";

	private static final Scanner SC = new Scanner(System.in);


	//static UserService userService = new UserService(new FileUserStorage("src/com/company/userdata"));
	//static StockService stockService = new StockService(new FileStockStorage("src/com/company/stockdata"));
	//static ReportService reportService = new ReportService(new FileReportStorage("src/com/company/reportdata"));

	static SqlLiteStorage db = new SqlLiteStorage();
	static UserService userService = new UserService(db);
	static StockService stockService = new StockService(db);
	static ReportService reportService = new ReportService(db);

	public static void main(String[] args) throws Exception {
		User loggedUser = null;
		try {
			while (true) {
				if (loggedUser == null) {
					printLoginMenu();
				} else {
					try {
						userMenu(loggedUser);
					} catch (UserException e) {
						System.out.println(e.getMessage());
					} catch (UserLogOutException e) {
						loggedUser = null;
						continue;
					}
				}

				String loginChoice = SC.nextLine();
				switch (loginChoice) {
					case "1": //Prisijungti
						System.out.print("Įveskite prisijungimo vardą: ");
						String username = SC.nextLine();
						System.out.print("Įveskite slaptažodį: ");
						String password = SC.nextLine();

						try {
							loggedUser = userService.getUser(username, password);
							userMenu(loggedUser);
						} catch (UserException e) {
							System.out.println(e.getMessage());
						} catch (UserLogOutException e) {
							loggedUser = null;
							continue;
						}
						break;
					case "2": //Registruotis
						registerNewUser();
						break;
					case "3": //Prekių peržiūra
						printAllStocks(stockService.getAllStocks());
						break;
					case "4": //Atsijungti
						return;
					default:
						System.out.println(NEATPAZINTA_IVESTIS);
						break;
				}
			}
		} catch (IOException e) {
			System.out.println("ERROR nerastas duomenu failas");
		}

	}

	private static void userMenu(User user) throws Exception {
		if (user.getRole().equals(Role.CUSTOMER)) {

			customerUserMenu(user);
		} else if (user.getRole().equals(Role.ADMIN)) {

			adminUserMenu(user);
		}
	}

	private static void adminUserMenu(User adminUser) throws Exception {
		while (true) {
			printAdminUserMenu();
			String choice = SC.nextLine();

			switch (choice) {
				case "1": //Peržiurėti savo informaciją (mato savo rolę, slaptažodžio - ne)
					printUserForAdmin(adminUser);
					break;
				case "2": //Peržiūrėti visų vartotojų informaciją (mato visų vartotojų roles, slaptažodžių - ne)
					ArrayList<User> allUsers = userService.getAllUsers();
					printAllUsers(allUsers);
					break;
				case "3": //Pridėti naują vartotoją
					addNewUser();
					break;
				case "4": //Ištrinti egzistuojantį vartotoją
					System.out.println("--------Vartotojo trynimas-------------");
					System.out.print("Iveskite trinamo vartotojo prisijungimo varda: ");
					String usernameToDelete = SC.nextLine();

					try {
						deleteUser(adminUser, usernameToDelete);
						System.out.printf("Vartotojas '%s' sekmingai ištrintas\n", usernameToDelete);
						return;
					} catch (AdminDeletionException | UserException e) {
						System.out.println(e.getMessage());
					}
					break;
				case "5": // Ataskaitos:
					System.out.println("************************************************************************************************************");
					System.out.println("Ataskaitos: ");
					System.out.printf("Pajamos per laikotarpį Eur: %.2f", reportService.getRevenue());
					System.out.println();
					System.out.printf("Kaštai per laikotarpį Eur: %.2f", reportService.getCosts());
					System.out.println();
					System.out.printf("Pelnas per laikotarpį Eur: %.2f", reportService.getProfit());
					System.out.println();
					System.out.printf("Pelningumas per laikotarpį %.2f%%", reportService.getMargin()*100);
					System.out.println();

					break;
				case "6": //Papildyti prekių likučius
					addNewStock();
					break;
				case "7":
					throw new UserLogOutException("Atsijungte");
				default:
					System.out.println(NEATPAZINTA_IVESTIS);
					break;
			}
		}
	}

	private static void deleteUser(User adminUser, String usernameToDelete) throws Exception {
		if (!adminUser.getUsername().equalsIgnoreCase(usernameToDelete)) {
			userService.deleteUserByUsername(usernameToDelete);
			return;
		}

		System.out.println("Jūs bandote ištrinti save, ar tikrai to norite [T/N]");
		String deleteChoice = SC.nextLine();

		if (!(deleteChoice.equalsIgnoreCase("T") || deleteChoice.equalsIgnoreCase("taip"))) {
			System.out.println("Vartotojas netrinamas");
			return;
		}
		userService.deleteAdmin(adminUser);
	}

	private static void addNewUser() throws Exception {
		System.out.println("------------Naujo vartotojo kūrimas------------");
		String username = getValidUsername();
		System.out.print("Įveskite slpatažodį: ");
		String password = SC.nextLine();
		System.out.print("Įveskite vardą: ");
		String name = SC.nextLine();
		System.out.print("Įveskite pavardę: ");
		String surname = SC.nextLine();
		System.out.print("Įveskite pristatymo adresą: ");
		String address = SC.nextLine();
		int age = getAge();
		Role role = getRoleForNewUser();
		userService.addUser(new User(username, password, role, name, surname, address, age));
		System.out.println("--------------Vartotojas sėkmingai užregistruotas------------------");
	}

	private static void registerNewUser() throws Exception {
		System.out.println("------------Naujo vartotojo registracija------------");
		String username = getValidUsername();
		System.out.print("Įveskite slpatažodį: ");
		String password = SC.nextLine();
		System.out.print("Įveskite vardą: ");
		String name = SC.nextLine();
		System.out.print("Įveskite pavardę: ");
		String surname = SC.nextLine();
		System.out.print("Įveskite pristatymo adresą: ");
		String address = SC.nextLine();
		int age = getAge();
		User user = new User(username, password, Role.CUSTOMER, name, surname, address, age);
		userService.addUser(user);
		System.out.println("Vartotojas sėkmingai užregistruotas");
		System.out.println();
		try {
			customerUserMenu(user);
		} catch(UserLogOutException e) {

		}
	}

	private static int getAge() {
		while (true) {
			try {
				System.out.print("Įveskite amzių: ");
				int age = SC.nextInt();
				if (!(age <= 99 && age >= 1)) {
					throw new InputMismatchException();
				}
				SC.nextLine();
				return age;
			} catch (InputMismatchException e) {
				System.out.println("Amžius turi būti skaičius 1 - 99");
				SC.nextLine();
			}

		}
	}

	private static String getValidUsername() throws Exception {
		String username;
		while (true) {
			System.out.println("************************************************************************************************************");
			System.out.print("Įveskite prisijungimo vardą: ");
			username = SC.nextLine();
			if (!userService.isUserExists(username)) {
				return username;
			}
			System.out.printf("Vartotojo vardas '%s' užimtas\n", username);
		}
	}

	private static Role getRoleForNewUser() {
		System.out.println("Pasirinkite rolę:");

		while (true) {
			System.out.println("[1] Custumer");
			System.out.println("[2] Admin");
			String choice = SC.nextLine();

			switch (choice) {
				case "1":
					return Role.CUSTOMER;
				case "2":
					return Role.ADMIN;
				default:
					System.out.println(NEATPAZINTA_IVESTIS);
			}
		}
	}

	private static void printAllUsers(ArrayList<User> users) {
		for (User user : users) {
			printUserForAdmin(user);
		}
	}

	private static void printUserForAdmin(User adminUser) {
		System.out.println("************************************************************************************************************");
		System.out.println("Prisijungimo vardas: " + adminUser.getUsername());
		System.out.println("Vardas: " + adminUser.getName());
		System.out.println("Pavardė: " + adminUser.getSurname());
		System.out.println("Adresas: " + adminUser.getAddress());
		System.out.println("Amžius: " + adminUser.getAge());
		System.out.println("Rolė: " + adminUser.getRole());
		System.out.println();
	}

	private static void customerUserMenu(User customerUser) throws Exception {
		while (true) {
			printCustumerMenu();
			String choice = SC.nextLine();

			switch (choice) {
				case "1": //Peržiurėti savo informaciją (savo rolės ir slaptažodžio nemato
					printCustumer(customerUser);
					break;
				case "2": //Prekių peržiūra
					printAllStocks(stockService.getAllStocks());
					break;
				case "3": //Įdėti prekes į krepšelį
					addItemsToBasket(customerUser);
					break;
				case "4": //Pirkimas
					buy(customerUser);
					break;
				case "5":
					throw new UserLogOutException("Atsijunge");
				default:
					System.out.println(NEATPAZINTA_IVESTIS);
					break;
			}
		}
	}

	private static void addItemsToBasket(User loggedUser) throws Exception {
		System.out.println("************************************************************************************************************");
		boolean finished = false;
		while (!finished) {
			System.out.println("Įveskite perkamos prekės ID: ");
			String itemID = SC.nextLine();
			System.out.print("Įveskite perkamos prekės kiekį: ");
			int itemQt = Integer.parseInt(SC.nextLine());

			Stock stock = stockService.getStockbyId(itemID);
			if (stock != null) {
				int alreadyAddedStockCount = 0;
				Stock stockFromBasket = loggedUser.getStockFromBasket(stock.getItemId());
				if (stockFromBasket != null) {
					alreadyAddedStockCount = stockFromBasket.getItemQt();
				}

				if (stock.getItemQt() >= itemQt + alreadyAddedStockCount) {
					Stock basketStock = new Stock(stock.getItemId(), stock.getItemName(), stock.getItemCosts(), itemQt + alreadyAddedStockCount);
					loggedUser.addToBasket(basketStock);
				} else {
					System.out.println("Nepakankamas prekių kiekis sandėlyje:" + stock.getItemQt());
				}
			} else {
				System.out.println("Preke kurios kodas: " + itemID + " neegzistuoja");
			}
			System.out.println("Ar norite tęsti apsipirkimą:");
			System.out.println("[1] Taip");
			System.out.println("[2] Ne");
			String choice = SC.nextLine();
			switch (choice) {
				case "1":
					finished = false;
					break;
				case "2":
					finished = true;
					System.out.println("Tęskite pirkimą, pasirinkdami meniu 4 punktą");
					break;
				default:
					finished = true;
					System.out.println("Tęskite pirkimą, pasirinkdami meniu 4 punktą");
					break;
			}
		}
	}

	public static void buy(User loggedUser) throws Exception {
		ArrayList<Stock> stocks = loggedUser.getStocksFromBasket();
		for (Stock stock : stocks) {
			Stock stockFromDb = stockService.getStockbyId(stock.getItemId());
			if(stockFromDb == null) {
				loggedUser.removeFromBasket(stock.getItemId());
				System.out.println("Preke nerasta sandalyje: "+ stock.getItemId());
			}

			if(stockFromDb != null && stockFromDb.getItemQt() >= stock.getItemQt()) {
				stockFromDb.setQt(stockFromDb.getItemQt() - stock.getItemQt());
				stockService.updateStock(stockFromDb);
				reportService.addDataToReport(new Log(stock));
				System.out.println("Pirkimo procesas sėkmingai užbaigtas");

			}  else {
				System.out.println("Nepakankamas prekių kiekis sandėlyje:"+ stockFromDb.getItemName());

			}
		}

		loggedUser.clearBasket();
	}


	public static void printAllStocks(ArrayList<Stock> stocks) {
		for (Stock stock : stocks) {
			printStock(stock);
		}
	}

	public static void printStock(Stock stock) {
		System.out.println("************************************************************************************************************");
		System.out.println("Prekės pavadinimas: " + stock.getItemName());
		System.out.printf("Prekės kaina: %.2f", stock.getItemPrice());
		System.out.println();
		System.out.println("Prekių kiekis sandėlyje: " + stock.getItemQt());
		System.out.println();
	}

	private static void addNewStock() throws Exception {
		System.out.println("************************************************************************************************************");
		System.out.print("Įveskite prekės ID: ");
		String itemId = SC.nextLine();
		System.out.print("Įveskite prekės pavadinimą: ");
		String itemName = SC.nextLine();

		float itemCosts = 0;
		boolean succes = false;
		while(!succes) {

			try {
				System.out.print("Įveskite prekės savikainą: ");
				itemCosts = SC.nextFloat();
			} catch (Exception ex) {
				System.out.println("Neteisingai ivestas savikaina");
				SC.nextLine();
				continue;
			}
			succes = true;
		}

		Stock existingStock = stockService.getStockbyId(itemId);
		int itemQt = 0;
		boolean qt = false;
		while(!qt) {
			try {
				System.out.print("Įveskite prekės kiekį: ");
				itemQt = SC.nextInt();
			} catch (Exception ex) {
				System.out.println("Neteisingai įvestas kiekis");
				SC.nextLine();
				continue;
			}
			qt = true;
		}
		SC.nextLine();
		if(existingStock != null) {
			itemQt += existingStock.getItemQt();
		}
		stockService.addStock(new Stock(itemId, itemName, itemCosts, itemQt));
		System.out.println("----------Prekės atsargos sėkmingai patalpintos į prekybos sandėlį----------");
	}

	private static void printCustumer(User user) {
		System.out.println("************************************************************************************************************");
		System.out.println("Prisijungimo vardas: " + user.getUsername());
		System.out.println("Vardas: " + user.getName());
		System.out.println("Pavardė: " + user.getSurname());
		System.out.println("Adresas: " + user.getAddress());
		System.out.println("Amžius: " + user.getAge());
		System.out.println("--------------------------------------");
	}

	//-------------------------------------------------------------------------------MENIU------------------------------------------------------------------------------
	private static void printAdminUserMenu() {
		System.out.println("************************************************************************************************************");
		System.out.println("Administratoriaus meniu:");
		System.out.println("[1] Peržiurėti savo informaciją");
		System.out.println("[2] Peržiūrėti visų vartotojų informaciją");
		System.out.println("[3] Pridėti naują vartotoją");
		System.out.println("[4] Ištrinti egzistuojantį vartotoją");
		System.out.println("[5] Ataskaitos");
		System.out.println("[6] Papildyti prekių likučius");
		System.out.println("[7] Atsijungti");
		System.out.println("************************************************************************************************************");
	}

	private static void printCustumerMenu() {
		System.out.println("************************************************************************************************************");
		System.out.println("Pirkėjo meniu:");
		System.out.println("[1] Peržiūrėti savo informaciją");
		System.out.println("[2] Prekių peržiūra");
		System.out.println("[3] Įdėti prekę į krepšelį");
		System.out.println("[4] Pirkimas");
		System.out.println("[5] Atsijungti");
		System.out.println("************************************************************************************************************");
	}

	private static void printLoginMenu() {
		System.out.println("************************************************************************************************************");
		System.out.println("Meniu:");
		System.out.println("[1] Prisijungti");
		System.out.println("[2] Registruotis");
		System.out.println("[3] Prekių peržiūra");
		System.out.println("[4] Atsijungti");
		System.out.println("************************************************************************************************************");
	}
}