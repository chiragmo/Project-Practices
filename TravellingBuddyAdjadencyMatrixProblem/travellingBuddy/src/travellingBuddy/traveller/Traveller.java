package travellingBuddy.traveller;
import java.util.ArrayList;
import java.util.List;

/**
 * Object to store each traveller data
 *
 */
public class Traveller {
	private String firstName;
	private String lastName;
	private List<String> cities;

	public Traveller() {
		cities = new ArrayList<String>();
	}

	public String getfirstName() {
		return firstName;
	} 

	public void setfirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getlastName() {
		return lastName;
	} 

	public void setlastName(String lastName) {
		this.lastName = lastName;
	}

	public List<String> getcities() {
		return cities;
	} 

	public void setcities(List<String> cities) {
		this.cities = cities;
	}

}