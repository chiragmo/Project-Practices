package travellingBuddy.traveller;
import travellingBuddy.util.ResultsI;

public interface MapperI {
	public void createAllCities();
	public void makeAdjadencyMatrix();
	public ResultsI findBuddies();
}