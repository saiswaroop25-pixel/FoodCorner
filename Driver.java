
import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

interface Login {
	void signup();
    void login();

}
interface mainMenu {
	void menu();
	void payment();
}

class Menu implements Login , mainMenu
{
	static LinkedList food = new LinkedList();
	private String name;
	private long contact = 9763341304l;
	private String address;
	private String password = "12345";
	static double totalBill;
	static int quantity;
	static char ch;
	

	public String getName() {
		return name;
	}
    public void setName() {

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your name:");
		name = sc.nextLine();
		if (name != null && name.length() >= 3 && name.length() <= 20) {
			if (!(name.contains("1")) && !(name.contains("2")) && !(name.contains("3")) && !(name.contains("4"))
					&& !(name.contains("5")) && !(name.contains("6")) && !(name.contains("7")) && !(name.contains("8"))
					&& !(name.contains("9")) && !(name.contains("0"))) {
				
					System.out.println("The name is valid");
			    }
			else {
				System.err.println("In name you can not pass Integer or special character.....");
				setName();
			}
		} else {
			System.err.println("Either You are entering null value or out of range or number");
            setName();

		}
	}

	public long getContact() {
		return contact;
	}

	public void setContact() {
   try {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your Mobile Number :");
		contact = sc.nextLong();
		if (contact > 999999999 && contact < 10000000000l) {
			System.out.println("Contact number is valid ");
		} else {
			System.err.println("Please enter 10 digit !! Try again ");
			setContact();
		}
   }
   catch(Exception e)
   {
	   System.err.println("Please enter integer ");
		setContact();
	   
   }

	}


	public String getAddress() {
		return address;
	}

	public void setAddress() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your Address:");
		address = sc.nextLine();
		if (address != null && address.length() >= 3 && address.length() <= 50) {
			System.out.println("The Address is valid");
		} else {
			System.err.println("Either You are entering null value or out of range");

			setAddress();

		}
	}

	public void setPassword() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your Strong Password:");
		password = sc.nextLine();
		if (password != null && password.length() == 8) {
			System.out.println("The Password is valid");
		} else {
			System.err.println("Either You are entering null value ");
			System.out.println("Password range must be 8 ");

			setPassword();

		}
	}

	public void signup() {
		Scanner sc = new Scanner(System.in);
		setName();
		setContact();
		verifyOTP();
		setPassword();
		setAddress();

		System.out.println("Registration is Succesfull");
		System.out.println("");
		System.out.println("Proceed with Login..");
		System.out.println("");
		login();

	}

	int otp;

	public void generateOTP() {
		Random r = new Random();
		int generatedOTP = r.nextInt(10000);
		if (generatedOTP > 999) {
			otp = generatedOTP;
			System.out.println("Generated OTP: " + otp);
		} else {
			generateOTP();
		}
	}

	public void verifyOTP() {
		Scanner sc = new Scanner(System.in);
		generateOTP();
		System.out.println("Enter OTP as same as above:");
		int userOTP = sc.nextInt();
		if (userOTP == otp) {
			System.out.println("OTP Verified Successfully.....");
		} else {
			System.err.println("Incorrect OTP. Try Again....");
			verifyOTP();
		}
	}

	public void login() {
	try {
		Scanner sc = new Scanner(System.in);
		System.out.println("Press 1 to use contact for login");
		int n = sc.nextInt();
		if (n == 1) {
			System.out.println("Enter The Contact Number");
			long contact = sc.nextLong();
			sc.nextLine();
			System.out.println("Enter the Password");
			String pass = sc.nextLine();
			if (this.contact == contact && this.password.equals(pass)) {
				System.out.println("Login Successful......");
			} else {
				System.err.println("User Credentials Incorrext");
				login();
			}
		} else {
			System.err.println("Wronng Choice!!! Please Try again");
			login();
		}
	}catch(Exception e)
	{
		System.err.println("Wronng Choice!!! Please Try again");
		login();
	}
	
}
	public void menu() {
		try {
		Scanner sc = new Scanner(System.in);
		System.out.println("Press 1 for  Starters");
		System.out.println("Press 2 for Main Course");
		System.out.println("Press 3 for Deserts");
		System.out.println("Press 5 for Exit");
		int x = sc.nextInt();
		switch (x) {
		case 1:
			Straters s = new Straters();
			s.startersMenu();
			break;
		case 2:
			Main_Course mc = new Main_Course();
			mc.Main_Courses_Menu();
			break;
		case 3:
			Desserts ds = new Desserts();
			ds.DessertsMenu();
			break;
		case 4:
			System.out.println("Thank You For Visisting ....");

			System.exit(0);

		default:
			System.err.print("Please Try Again");
		}
		}
		catch(Exception e)
		{
			System.err.print("Please Try Again");
			menu();
			
		}

	}public void payment() {
		System.out.println("Name: " + name);
		System.out.println("Contact: " + contact);
		for (Object obj : food) {

			System.err.println( obj);

		}
		System.out.println("Your Bill :" + totalBill);
		double gst = 0.18; // 18% GST
		System.out.println("Gst :" + gst);
		double totalBillWithGST = totalBill + (totalBill * gst);
		System.out.println("Total Bill With GST: " + totalBillWithGST);
		System.err.println("Maccha Your Order Has Been Placed ....");
		System.out.println("---- Thank You ----");
		
	}

}
class Straters extends Menu {
	public void startersMenu() {
		try {
		Scanner sc = new Scanner(System.in);
		System.out.println("Press 1 for Veg Starters :");
		System.out.println("Press 2 to go Back");
		int x = sc.nextInt();
		switch (x) {
		case 1:
			Veg_Straters vs = new Veg_Straters();
			vs.vegStarters();
			break;
		case 2:
			menu();
		default:
			System.err.print("Please Try Again !!!......");
		}
	}
		catch(Exception e)
		{
			System.err.print("Please Try Again !!!......");
			startersMenu();
		}
	}
}
class Veg_Straters extends Straters {
	public void vegStarters() {
		try {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Press 1 for South Indian Staters");
		System.out.println("Press 2 to go back");
		int n = sc.nextInt();
		switch (n) {
		
		case 1:
			southIndianStarters();
			break;
		case 2:
			startersMenu();
			break;
		default:
			System.err.println("Please choose correct option");
			vegStarters();
			break;
		}
		}
		catch(Exception e)
		{
			System.err.println("Please chose integer");
			vegStarters();
			
		}
	}
	class Main_Course extends Menu {
	public void Main_Courses_Menu() {
		try {
		Scanner sc = new Scanner(System.in);
		System.out.println("Press 1 for Veg Main_Courses :");
        System.out.println("Press 2 for Non Veg Main_Courses :");
		System.out.println("Press 3 to go Back");

		int x = sc.nextInt();
		switch (x) {
		case 1:
			Veg_Main_Courses vmc = new Veg_Main_Courses();
			vmc.vegMain_Courses();
			break;
		case 2:
			nonVeg_Main_Courses nvmc = new nonVeg_Main_Courses();
			nvmc.nonvegMain_Courses();

			break;
		case 3:
			menu();
		default:
			System.err.print("Please Try Again !!!......");

		}


		}
		catch(Exception e)
		{
			System.err.print("Please Try Again !!!......");
			Main_Courses_Menu();
		}
	}

}

class Veg_Main_Courses extends Main_Course {
	public void vegMain_Courses() 
	{
		try 
	{
		
	
		Scanner sc = new Scanner(System.in);


		System.out.println("Press 1 for South Indian Main_Course");
		System.out.println("Press 2 for Main_Courses");
		System.out.println("Press 3 to go back");
		int n = sc.nextInt();
		switch (n) {
		case 1:
			southindianmain_course();
			break;
		case 2:
			Main_Courses_Menu();
			break;
		default:
			System.err.println("Please choose correct option");
			vegMain_Courses();
			break;
		}
	}
		catch(Exception e)
		{
			System.err.println("Please choose correct option");
			vegMain_Courses();
		}
	}

	
	public void southindianmain_course() 
	{
       try {
		Scanner sc = new Scanner(System.in);
		System.out.println("Press 1 for Vegetable Biryani");
		System.out.println("Press 2 for Pongal");
		System.out.println("Press 3 to go back");
		int n = sc.nextInt();
		switch (n) {
		case 1:

			System.out.println("Price of Vegetable Biryani is Rs 150");
			System.out.println("Enter the quantity:");
			quantity = sc.nextInt();

			if (quantity == 0) {
				System.err.println("Quantity can not be Zero...");
				southindianmain_course();
			} else {

				System.out.println("Choose Y to order or N to go back");
				ch = sc.next().charAt(0);
				if (ch == 'y' || ch == 'Y') {
					System.out.println("Order has been confirmed");
					food.add("Item ::Vegetable Biryani && Price::150 ");
					totalBill = totalBill + (150 * quantity);
					food.add("Quantity :" + quantity);

					System.out.println("Do you want to order more?");
					System.out.println("Press 1 to order from same");
					System.out.println("Press 2 to go for Main Menu");
					System.out.println("Press 3 to proceed for bill");
					n = sc.nextInt();
					switch (n) {
					case 1:
						southindianmain_course();
						break;
					case 2:
						menu();
						break;
					case 3:
						System.out.println("Please pay the amount");
						break;
					}
				} else if (ch == 'n' || ch == 'N') {
					System.out.println("Choose another item");
					southindianmain_course();
				} else {
					System.err.println("Please choose correct option");
					southindianmain_course();
				}
			}
			break;
		case 2:
			System.out.println("Price of Pongal is Rs 120");
			System.out.println("Enter the quantity:");
			quantity = sc.nextInt();
			if (quantity == 0) {
				System.err.println("Quantity can not be Zero...");
				southindianmain_course();
			} else {
				System.out.println("Choose Y to order or N to go back");
				ch = sc.next().charAt(0);
				if (ch == 'y' || ch == 'Y') {
					System.out.println("Order has been confirmed");
					food.add("Item ::Pongal && Price::120 ");
					food.add("Quantity :" + quantity);

					totalBill = totalBill + (120 * quantity);
					System.out.println("Do you want to order more?");
					System.out.println("Press 1 to order from same");
					System.out.println("Press 2 to go for Main Menu");
					System.out.println("Press 3 to proceed for bill");
					n = sc.nextInt();
					switch (n) {
					case 1:
						southindianmain_course();
						break;
					case 2:
						menu();
						break;
					case 3:
						System.out.println("Please pay the amount");
						break;
					}
				} else if (ch == 'n' || ch == 'N') {
					System.out.println("Choose another item");
					southindianmain_course();
				} else {
					System.err.println("Please choose correct option");
					southindianmain_course();
				}
			}
			break;
		
		case 3:

			vegMain_Courses();
			break;

		default:
			System.err.println("Sahi sahi choose kar");
			southindianmain_course();
		}
       }
       catch(Exception e)
       {
    	   System.err.println("Sahi sahi choose kar");
			southindianmain_course();
       }

	}
}


}

class Desserts extends Menu {
	public void DessertsMenu() {
		try {
		Scanner sc = new Scanner(System.in);

		System.out.println("Press 1 for Ice Cream :");
		System.out.println("Press 2 to go Back");
		int x = sc.nextInt();
		switch (x) {
		case 1:
			icecream ic = new icecream();
			ic.icecream_menu();
			break;
		case 2:
			menu();
		default:
			System.err.print("Please Try Again !!!......");
			DessertsMenu();
		}
		}
		catch(Exception e)
		{
			System.err.print("Please Try Again !!!......");
			DessertsMenu();
		}
	}
}

class icecream extends Desserts{
	public void icecream_menu()
	{
		try
		{
		Scanner sc = new Scanner(System.in);
		System.out.println("Press 1 for Vanilla - Ice Cream :");
		System.out.println("Press 2 for Chocolate - Ice Cream :");
		
		System.out.println("Press 3 to go Back");
		
		
		
		int n = sc.nextInt();
		switch (n) {
		case 1:

			System.out.println("Price of Vanilla - Ice Cream is Rs 250");
			System.out.println("Enter the quantity:");
			quantity = sc.nextInt();

			if (quantity == 0)
			{
				System.out.println("Quantity can not be Zero...");
				icecream_menu();
			} 
			else 
			{

				System.out.println("Choose Y to order or N to go back");
				ch = sc.next().charAt(0);
				if (ch == 'y' || ch == 'Y') 
				{
					System.out.println("Order has been confirmed");
					food.add("Item ::Rasgulla  && Price::250 ");
					totalBill = totalBill + (250 * quantity);
					food.add("Quantity :" + quantity);

					System.out.println("Do you want to order more?");
					System.out.println("Press 1 to order from same");
					System.out.println("Press 2 to go for Main Menu");
					System.out.println("Press 3 to proceed for bill");
					n = sc.nextInt();
					switch (n) 
					{
					case 1:
						icecream_menu();
						break;
					case 2:
						menu();
						break;
					case 3:
						System.out.println("Please pay the amount");
						break;
					}
				} else if (ch == 'n' || ch == 'N') 
				{
					System.out.println("Choose another item");
					icecream_menu();
				} else 
				{
					System.err.println("Please choose correct option");
					icecream_menu();
				}
			}
			break;
		case 2:
			System.out.println("Price of Chocolate - Ice Cream is Rs  100");
			System.out.println("Enter the quantity:");
			quantity = sc.nextInt();
			if (quantity == 0)
			{
				System.err.println("Quantity can not be Zero...");
				icecream_menu();
				
			} else 
			{
				System.out.println("Choose Y to order or N to go back");
				ch = sc.next().charAt(0);
				if (ch == 'y' || ch == 'Y') 
				{
					System.out.println("Order has been confirmed");
					food.add("Item ::Jalebi && Price:: 100 ");
					food.add("Quantity :" + quantity);

					totalBill = totalBill + (100 * quantity);
					System.out.println("Do you want to order more?");
					System.out.println("Press 1 to order from same");
					System.out.println("Press 2 to go for Main Menu");
					System.out.println("Press 3 to proceed for bill");
					n = sc.nextInt();
					switch (n) 
					{
					case 1:
						icecream_menu();
						break;
					case 2:
						menu();
						break;
					case 3:
						System.out.println("Please pay the amount");
						break;
					}
				} 
				else if (ch == 'n' || ch == 'N') {
					System.out.println("Choose another item");
					icecream_menu();
				} else
				{
					System.err.println("Please choose correct option");
					icecream_menu();
				}
			}
			break;
		
		case 3:
			menu();
		default:
			System.err.print("Please Try Again !!!......");
			DessertsMenu();

		}		
	}
	catch(Exception e)
		{
			System.err.print("Please Try Again !!!......");
			icecream_menu();
		}	
	}	
}

public class Driver {

	static {

		System.out.println(" ----------------------------");
		System.out.println("|       * Fodd  Corner *     |");
		System.out.println("|    Eat                     |");
		System.out.println("|          &EAT              |");
		System.out.println("|                 &EAT       |");
		System.out.println(" ----------------------------");

	}
		public static void main(String[] args)
	{
		try
		{

			Scanner sc = new Scanner(System.in);
			System.out.println(" ----------------------------");
			System.out.println("| Press Enter 1 for Signup   |");
			System.out.println("| Press Enter 2 for Login    |");
			System.out.println("| Press 3 For exit           |");
			System.out.println("");
			System.out.println("Enter Your Input: ");
			int x = sc.nextInt();
			Menu m = new Menu();
			switch (x) 
			{
			case 1:
				m.signup();
				break;
			case 2:
				System.err.println("Machha First Register....");
				m.signup();
				break;
			case 3:
				System.err.println("Thank You For Visiting ..");
				System.exit(0);
			default:
				System.err.println("Invalid Input!! Please Try Again");
				main(null);

			}
			m.menu();
			m.payment();

		} 
		catch (Exception e)
		{
			System.err.println("Invalid Input!! Please Try Again");
			main(null);

		}

	}

}
