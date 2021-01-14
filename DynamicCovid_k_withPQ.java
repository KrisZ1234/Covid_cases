import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class DynamicCovid_k_withPQ {
	
	    public static void main(String[] args) throws IOException {
    	
    	    Scanner in = new Scanner(System.in);
    	
    	    //will read from cities.txt
    	    String line = null;
	    BufferedReader br = new BufferedReader(new FileReader("cities.txt"));
		
	    //our queue
	    ReversePQ pq = new ReversePQ();
	    
	    //user's input
	    System.out.println("Provide number of cities:");
	    int k = in.nextInt();
	    
	    //array for cities to-be-printed
	    City topCities[] = new City[k];
	    
	    while((line = br.readLine()) != null){   
	    	
		City city = new City(); 
			
	    	String[] splitline = line.split("\\s+"); 
	    	
	    	//create the City elements with their variables from the text file
	    	//check id
	    	int id = Integer.parseInt(splitline[0]);
	    	//id condition
	    	if (id > 999 || id < 0) {
	    		 System.out.println("Error while reading IDs. Correct the cities.txt");
	    		 System.exit(0);
	    	}
	    	//insert id 
	    	city.setID(id);	
	    	
	    	//check city name
	    	String cityname = "";
	    	for (int i=1; i <= splitline.length; i++) {
	    		//read and add to cityname until you find numbers
	    		if (!Character.isDigit(splitline[i].charAt(0))){
	    			cityname += splitline[i] + " ";
	    		}
	    		else {
	    			//cityname condition
	    			if (cityname.length() > 50) {
	    				System.out.println("Error while reading city names. Correct the cities.txt");
	   	    		 	System.exit(0);
	    			}
	    			//insert city name
	    			city.setName(cityname);
	    			
	    			//check population number format
	    			int population = 0;
	    			try { 
	    			population = Integer.parseInt(splitline[i]);
	    			} catch (NumberFormatException e) {
	    				System.out.println("Error while reading populations. Correct the cities.txt");
	    				System.exit(0);
	    			}
	    			//population condition
	    			if (population > 10000000 || population <= 0) {
	    				System.out.println("Error while reading populations. Correct the cities.txt");
	   	    		 	System.exit(0);
	    			}
	    			//insert population
	    			city.setPopulation(population);
	    			
	    			//insert covidcases
	    	    	city.setCovidCases(Integer.parseInt(splitline[i+1]));	    	    	
	    			break;
	    		}
	    	}//for
	    	
	    	//calculate City's density for each one
	    	city.calculateDensity();
			
	    	//add cities to the queue until size = k
	    	if (pq.size() < k) {
	    		pq.insert(city);
	    	}
	    	//compare each next city with the lowestDensity city of the queue
	    	//and replace if needed, so the queue has always k size
	    	else {
	    		if (pq.min().compareTo(city) == -1 ) {
		    		pq.getMin();
		    		pq.insert(city);
		    	}	
	    	}
		}//while
	    
	    br.close();
	    
 	    if (k <= pq.size()) {
	    	System.out.println("The top " + k + " cities are:");
	    	
		    //printing and removing the city with max-density from the queue
	    	for (int i = 0; i < k; i++) {
	    		topCities[i] =  pq.getMin();
	    	}
	    	//print the cities in the right order
	    	for (int i = k-1 ; i >= 0 ; i-- ) {
	    		System.out.println(topCities[i].getName());
	    	}		
	    }
	    else {
	    	System.out.println("Invalid number of cities");
	    }
	    in.close();   
    }//main
	
}//DynamicCovid_k_withPQ




