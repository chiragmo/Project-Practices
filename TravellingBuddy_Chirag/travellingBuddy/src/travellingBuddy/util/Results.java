package travellingBuddy.util;
import travellingBuddy.traveller.Traveller;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

/**
 * Storing the results
 *
 */
public class Results implements ResultsI {
 private Map<String,String> buddyData;
 private Map<String,Double> distanceMapping; 
 
  public Results(){
    buddyData = new HashMap<String,String>();
  }

  public void addBuddies(String people, String cities) {
    if(buddyData.containsKey(people)) {
          buddyData.put(people, buddyData.get(people)+","+cities);
        }
    else {
       buddyData.putIfAbsent(people,cities);
      }
  }

  public void setDistanceMapping(Map<String,Double> distanceMapping){
    this.distanceMapping = distanceMapping;
  }

  public void printToScreen() {
     System.out.println("*******************************");
    for (String key : buddyData.keySet()) {
      Double val = distanceMapping.get(key);
      String str = String.valueOf(val);
      int endV = str.length();
      if(str.contains(".")){
        endV = str.indexOf(".");
      }
      System.out.println("[ "+key+" ] : "+buddyData.get(key)+" TotalDistance(mi): "+str.substring(0,endV));
      System.out.println("*******************************");
    }
  }

  public void cleanup(List<String> arli){
    for (int i =0;i<arli.size() ; i++ ) {
      buddyData.remove(arli.get(i));
    }
  }
}
