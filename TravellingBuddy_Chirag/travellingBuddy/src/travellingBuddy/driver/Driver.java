package travellingBuddy.driver;
import travellingBuddy.util.FileProcessor;
import travellingBuddy.util.FileProcessorI;
import travellingBuddy.util.Results;
import travellingBuddy.util.ResultsI;
import travellingBuddy.traveller.Mapper;
import travellingBuddy.traveller.MapperI;
import travellingBuddy.traveller.Traveller;
import java.util.List;

/**
 * @author Chirag M. Onkarappa
 *
 */
public class Driver {
	public static void main(String[] args) {

		/*
		 * As the build.xml specifies the arguments as argX, in case the
		 * argument value is not given java takes the default value specsified in
		 * build.xml. To avoid/check that, below condition is used
		 */
		if (args.length != 2 || args[0].equals("${arg0}")) {
        System.err.println(args.length);
			System.err.println("Error: Incorrect number of arguments. Program accepts only ONE argument i.e InputFileName.");
      System.exit(0);
		}		
     
     FileProcessorI objProcs = new FileProcessor(args[0]);
     List<Traveller> travelData = objProcs.getProcessedItinerary();
     MapperI map = new Mapper(travelData);
     map.createAllCities();
     map.makeAdjadencyMatrix();
     ResultsI res = map.findBuddies();
     res.printToScreen();
	}
}
