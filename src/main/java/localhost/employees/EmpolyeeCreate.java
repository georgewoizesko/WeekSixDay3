package localhost.employees;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

/**
 * 
 * Sample Class that makes simple request to Google Maps Directions
 * 
 * @author George Woizesko
 * @since 2016/09/07
 * 
 *
 */
public class EmpolyeeCreate {

	/**
	 * The URL of the API we want to connect to
	 */
	protected static String endpoint = "http://localhost:1337/employee/";
	
	/**
	 * The character set to use when encoding URL parameters
	 * 
	 */
	protected static String charset = "UTF-8";
	
	/**
	 * Functionality verb for class task
	 */
	protected static String verb = "POST";
	
	
		
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		try {
			
			//Request and assign the First Name of the new employee
			System.out.println("Please enter the First name of the employee to be added: ");
			String firstName = sc.nextLine();
			
			//Request and assign the Last Name of the new employee
			System.out.println("Please enter the Last name of the employee: ");
			String lastName = sc.nextLine();
			
			//Request and assign the Email of the new employee
			System.out.println("Please enter the Email of the employee: ");
			String email = sc.nextLine();
			
			//Request and assign the Home Phone number of the new employee
			System.out.println("Please enter the Home phone number of the employee: ");
			String homePhone = sc.nextLine();
			
			//Request and assign the Cell Phone number of the new employee
			System.out.println("Please enter the Cell phone number of the employee: ");
			String cellPhone = sc.nextLine();
			
			//Request and assign the Password of the new employee
			System.out.println("Please enter a Password for the employee: ");
			String password = sc.nextLine();
			
			//Request if the employee's status is active or not
			System.out.println("Is this an active employee? (yes or no): ");
			String active = sc.nextLine();
			
			if (active.equalsIgnoreCase("yes")){
				active = "1";
			}else if (active.equalsIgnoreCase("no")){
				active = "0";
			}
			
			
			//creats the url parameters as a string encoding them with the defined charset
			String queryString = String.format("firstName=%s&lastName=%s&email=%s&homePhone=%s&cellPhone=%s&password=%s&active=%s",
					URLEncoder.encode(firstName, charset),
					URLEncoder.encode(lastName, charset),
					URLEncoder.encode(email, charset),
					URLEncoder.encode(homePhone, charset),
					URLEncoder.encode(cellPhone, charset),
					URLEncoder.encode(password, charset),
					URLEncoder.encode(active, charset)
					);
			
			//creates a new URL out of the endpoint, returnType and queryString
			URL employeeList = new URL(endpoint + "?" + queryString);
			HttpURLConnection connection = (HttpURLConnection) employeeList.openConnection();
			connection.setRequestMethod(verb);
			
			//if we did not get a 200 (success) throw an exception
			if (connection.getResponseCode() != 201) {
				throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseCode());
			}
			
			System.out.println("New employee created.");
			
			//close connection to API
			connection.disconnect();
			
			
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
		
		
		
	}//main
	

	
	
}//class
