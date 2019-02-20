package travellingBuddy.util;
import java.util.Map;
import java.util.List;

public interface ResultsI {
	public void addBuddies(String people, String cities);
	public void setDistanceMapping(Map<String,Double> distanceMapping);
	public void printToScreen();
	public void cleanup(List<String> arli);

}