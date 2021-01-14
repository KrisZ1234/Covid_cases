
public class City implements Comparable<City>{
	
	private int id = 0;
	private String name = null;
	private int population = 0;
	private int covidCases = 0;
	public double density = 0;
	
	//getters
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public int getPopulation() {
		return population;
	}
	public int getCovidCases() {
		return covidCases;
	}
	public double getDensity() {
		return density;
	}
	
	//setters
	public void setID(int ID) {
		this.id = ID;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPopulation(int population) {
		this.population = population;
	}
	public void setCovidCases(int covidCases) {
		this.covidCases = covidCases;
	}
	
	//calculates Covid_cases per 50.000 residents
	public double calculateDensity() {
		double d = (double)covidCases*50000/(double)population;		
		this.density = Math.round(d*100.0)/100.0;
		return density;
	}
	
	//prints info
	public String toString() {
		//no space after cityname because it is placed after reading the file
		return id +" "+ this.name + this.population +" "+ this.covidCases + " "+this.density+'\n'; 
	}
    
	//compares cities via density. If tie, compares cities via name
	//if tie, compare ids0
	public int compareTo(City o) {
		
			if (this.density < o.getDensity()) { return -1; }
			if (this.density > o.getDensity()) { return 1; }	
			else {
				if ( this.getName().compareTo(o.getName()) > 0 ) { return 1; }
				if (this.getName().compareTo(o.getName()) > 0 ) { return -1; }
				else {
					if (this.id < o.getId()) { return 1; }
					else return -1; 
				}
	        }//else
	}//compareTo
	
}//city
