package localhost.employees;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
public class EmpolyeeGetSingle {

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
	protected static String verb = "GET";
	
	
		
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		try {
			
			//Request and assign the employee id to be viewed
			System.out.println("Please enter the employee ID number to be viewed: ");
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

			//read response into buffer
			BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
			
			//Loop of buffer line by line until it returns null meaning there are no more lines
			while (br.readLine() != null) {
				//print out each line to the screen
				System.out.println(br.readLine());
				
			}
			
			System.out.println("View single employee request sent.");
			
			
			//close connection to API
			connection.disconnect();
			
			
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
		
		
		
	}//main
	

	
	
}//class
