package travellingBuddy.traveller;
import travellingBuddy.util.Results;
import travellingBuddy.util.ResultsI;
import travellingBuddy.util.DistanceApi;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Creation of adjadency matrix and processing the matrix then finding the buddies
 *
 */
public class Mapper implements MapperI {
	private Map<String,Integer> uniqueCities;
	private List<Traveller> travelData;
	private Map<Integer,String> keys;
	private Map<String,Double> distanceMapping;
	private HashMap<String,Double> special;
	private String[][] adjMatrix;
	private Double[][] matr;
	
	public Mapper(List<Traveller> travelData) {
		uniqueCities = new HashMap<String,Integer>();
		keys = new HashMap<Integer,String>();
		this.travelData = travelData; 
	}

	public void createAllCities() {
		int count = 0;
		for(int i=0; i<travelData.size(); i++){
			Traveller obj = travelData.get(i);
			for(int j=0; j<obj.getcities().size(); j++) {
				String city = obj.getcities().get(j);
				if(!uniqueCities.containsKey(city)) {
					uniqueCities.putIfAbsent(city, count);
					keys.putIfAbsent(count, city);
					count++;
				}
			}
		}
		//System.out.println(uniqueCities);
	}

	public void makeAdjadencyMatrix() {
 	adjMatrix = new String[uniqueCities.size()][uniqueCities.size()];
 	matr = new Double[uniqueCities.size()][uniqueCities.size()];

 	for (int i =0 ; i<uniqueCities.size() ; i++) {
 		for (int j =0 ; j<uniqueCities.size() ; j++) {
 			adjMatrix[i][j] = " ";
 			matr[i][j] = -1.0;
 		}
 	}
 
 	for(int i =0; i<travelData.size(); i++){
 		   Traveller obj = travelData.get(i);
 		   String person = obj.getfirstName() + " "+ obj.getlastName();
 		   String sourceCity = obj.getcities().get(0);
 		   for (int j =1; j<obj.getcities().size() ; j++) {
 		   	String desCity = obj.getcities().get(j);
 		   	int rowIndex = uniqueCities.get(sourceCity);
 		   	int colIndex = uniqueCities.get(desCity);
 		   	if(adjMatrix[rowIndex][colIndex].length() == 1) {
 		   		adjMatrix[rowIndex][colIndex] = person;
 		   	}
 		   	else {
 		   		adjMatrix[rowIndex][colIndex] = adjMatrix[rowIndex][colIndex] + ","+ person;
 		   	}
 		   	sourceCity = desCity;
 		   }
 	}
 }

	public ResultsI findBuddies() {
		ResultsI res = new Results();
		 distanceMapping = new HashMap<String,Double>();
		 special = new HashMap<String,Double>();
 	for (int i =0 ; i<uniqueCities.size() ; i++) {
 			for (int j =0 ; j<uniqueCities.size() ; j++) {
 				if(adjMatrix[i][j].length() > 1) {
 			Double distance = DistanceApi.getDistance(keys.get(i),keys.get(j));
 		   	matr[i][j] = distance/1.66;
 				}
 			}
 		}
 		List<String> arli = new ArrayList<String>();
		for (int i =0 ; i<uniqueCities.size() ; i++) {
 			for (int j =0 ; j<uniqueCities.size() ; j++) {
 			   if(adjMatrix[i][j].contains(",")) {
 			   	if(distanceMapping.containsKey(adjMatrix[i][j])) {
 			   		Double num = distanceMapping.get(adjMatrix[i][j]);
 			   		Double dist = num+ matr[i][j];
 			   		distanceMapping.put(adjMatrix[i][j],dist);
 			   		special.put(adjMatrix[i][j],2.0);
 			   	}
 			   	else {
 			   		distanceMapping.put(adjMatrix[i][j],matr[i][j]);
 			   		special.put(adjMatrix[i][j],1.0);
 			   	}
 			   	res.addBuddies(adjMatrix[i][j],keys.get(i)+"->"+keys.get(j));
 			   }
 			}
 		}

		for (int i =0 ; i<uniqueCities.size() ; i++) {
 			for (int j =0 ; j<uniqueCities.size() ; j++) {
 			 String str = adjMatrix[i][j];
 			 for(int h = 0; h< uniqueCities.size(); h++){
 			 	if(j!=h && matr[i][h] != -1.0 && str.length()>1){
 			 		Double x = DistanceApi.getDistance(keys.get(j),keys.get(h))/1.66;
 			   	Double dif = Math.abs(x);
 			 		if(dif<15){
 			 			//System.out.println(str+","+adjMatrix[i][h]);
 			 			//System.out.println("present already: "+str);
 			 			if(special.containsKey(str) || special.containsKey(adjMatrix[i][h])){
 			 				String prev = "";
 			 				int val = 0;
 			 				if(special.containsKey(str)) prev = str;
 			 				if(special.containsKey(adjMatrix[i][h])) prev = adjMatrix[i][h];
 			 				if(special.get(prev) == 1.0){
 			 					special.remove(prev);
 			 					arli.add(prev);
 			 					special.put(str+","+adjMatrix[i][h],1.0);
 			 					
 			 				}
 			 				else {
 			 					special.put(str+","+adjMatrix[i][h],1.0);
 			 				}
 			 			}
 			 			else {
 			 				special.put(str+","+adjMatrix[i][h],1.0);
 			 			}
 			 			distanceMapping.put(str+","+adjMatrix[i][h],matr[i][j]);
 			 			res.addBuddies(str+","+adjMatrix[i][h],keys.get(i)+"->"+keys.get(j));
 			 			adjMatrix[i][h] = " ";
 			 		}

 			 	}
 			 }
 				}


 			}
			res.cleanup(arli);
 	res.setDistanceMapping(distanceMapping);
 	return res;
	}

}