package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;

public class Program {

	public static void main(String[] args) throws ParseException{
		Locale.setDefault(Locale.US);
		Scanner scanner = new Scanner(System.in);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:ss");
			
		System.out.println("Enter rental data");
		
		System.out.println("Car model: ");
		String carModel = scanner.nextLine();
		
		System.out.println("Pickup (dd/MM/yyyy HH:ss): ");
		Date start = simpleDateFormat.parse(scanner.nextLine());
		
		System.out.println("Return (dd/MM/yyyy HH:ss): ");
		Date finish = simpleDateFormat.parse(scanner.nextLine());
		
		CarRental carRental = new CarRental(start, finish, new Vehicle(carModel));
		
		System.out.println("Enter price per hour: ");
		double pricePerHour = scanner.nextDouble();
		
		System.out.println("Enter price per day: ");
		double pricePerDay = scanner.nextDouble();
		
		RentalService rentalService = new RentalService(pricePerDay, pricePerHour, new BrazilTaxService());
		
		rentalService.processInvoice(carRental);
		
		System.out.println("INVOICE");
		System.out.println("Basic Payment: " + String.format("%.2f", carRental.getInvoice().getBasicPayment()));
		System.out.println("Tax: " + String.format("%.2f", carRental.getInvoice().getTax()));
		System.out.println("Total Payment: " + String.format("%.2f", carRental.getInvoice().getTotalPayment()));
		
		scanner.close();
	}

}
