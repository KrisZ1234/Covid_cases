import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Dynamic_Median {
    public static void main(String[] args) throws IOException {
    	
    	Scanner in = new Scanner(System.in);
    	
    	//will read from cities.txt
    	String line = null;
		BufferedReader br = new BufferedReader(new FileReader("cities.txt"));
		
		//our queues
	    PQ pqA = new PQ();
	    ReversePQ pqB = new ReversePQ();
        
	    //i-> number of cities in the file
	    int i = 1;
	    double median = 0;
	    
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
	    	for (int j=1; j <= splitline.length; j++) {
	    		//read and add to cityname until you find numbers
	    		if (!Character.isDigit(splitline[j].charAt(0))){
	    			cityname += splitline[j] + " ";
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
	    			population = Integer.parseInt(splitline[j]);
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
	    	    	city.setCovidCases(Integer.parseInt(splitline[j+1]));	    	    	
	    			break;
	    		}
	    	}//for
	    	//calculate City's density for each one
	    	city.calculateDensity();
	    	
	    	if (i == 1) {
	    		//first city to be added in the A-queue
	    		pqA.insert(city);
	    		//median = pqA.max().getDensity();
	    	}
	    	else {
	    		//algorithm exchanging data between two queues
	    		if (i % 2 == 0){
	    			if (city.getDensity() >= pqA.max().getDensity()) {
	    				pqB.insert(city);
	    			}
	    			else {
	    				pqA.insert(city);
	    				pqB.insert( pqA.getMax() );
	    			}
	    		}
	    		else {
	    			if (city.getDensity() >= pqA.max().getDensity()) {
	    				pqB.insert(city);
	    				pqA.insert( pqB.getMin() );
	    			} 
	    			else {
	    				pqA.insert(city);
	    			}	
	    		}			
	    	}//else
	    	if (i % 5 == 0) {
    			median = pqA.max().getDensity();
    			System.out.println("after " + i +" cities->" + " median: " + median);
			}
	    	i++;	   	
	    	
		}//while

	    br.close();
	    in.close();
    }//main
    
}//Dynamic_median
