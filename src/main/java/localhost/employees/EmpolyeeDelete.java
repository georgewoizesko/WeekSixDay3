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
public class EmpolyeeDelete {

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
	protected static String verb = "DELETE";
	
	
		
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		try {
			
			//Request and assign the employee id to be deleted
			System.out.println("Please enter the employee ID number to be deleted: ");
			String employeeId = sc.nextLine();
			
			
			
			//creats the url parameters as a string encoding them with the defined charset
			String queryString = String.format("id=%s",
					URLEncoder.encode(employeeId, charset)
					
					);
			
			//creates a new URL out of the endpoint, returnType and queryString
			URL employeeList = new URL(endpoint + "?" + queryString);
			HttpURLConnection connection = (HttpURLConnection) employeeList.openConnection();
			connection.setRequestMethod(verb);
			
			//if we did not get a 200 (success) throw an exception
			if (connection.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseCode());
			}
			
			System.out.println("Employee " + employeeId + " deleted.");
			
			
			//close connection to API
			connection.disconnect();
			
			
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
		
		
		
	}//main
	

	
	
}//class
