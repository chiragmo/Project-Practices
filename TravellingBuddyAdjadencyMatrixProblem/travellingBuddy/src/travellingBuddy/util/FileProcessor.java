package travellingBuddy.util;
import travellingBuddy.traveller.Traveller;
import java.io.File; 
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 * Reading the contents of the file and parsing the input
 *
 */
public class FileProcessor implements FileProcessorI {
  private List<Traveller> travelData;
  private String absPath;

  public FileProcessor(String absPath) {
  travelData = new ArrayList<Traveller>();
   this.absPath = absPath;
  }

	public List<Traveller> getProcessedItinerary() {
  File file = new File(absPath); 
	Scanner sc = null;
	try {
    sc = new Scanner(file); 
    }
    catch (Exception e) {
    	System.out.println(e);
    }
    while (sc.hasNextLine()) {
      Traveller objTrav = new Traveller();
      String content = sc.nextLine(); 
      String[] detailed = content.split("\t");
      objTrav.setfirstName(detailed[0]);
      objTrav.setlastName(detailed[1]);
      String[] city = detailed[2].split(",");
      for(int j =0; j<city.length; j++) {
        objTrav.getcities().add(city[j]);
      }
      travelData.add(objTrav);
    }
  return travelData;
	}



}
