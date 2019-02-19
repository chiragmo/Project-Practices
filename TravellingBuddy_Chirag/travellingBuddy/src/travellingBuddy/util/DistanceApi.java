package travellingBuddy.util;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL; 

/**
 * Get the distance data from https://www.distance24.org/route.json API
 *
 */
public class DistanceApi {
 
 public DistanceApi() {

 }

 public static Double getDistance(String source, String des) {
 	try {
	String api = "AIzaSyAzaQm3KIwPVxj7G7efGU-mf-BXTK1Y6Iw";
	String source1 = source.replaceAll(" ","+");
 	String des1 = des.replaceAll(" ","+");
	String url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins="+source1+"&destinations="+des1+"&key="+api;
     URL obj = new URL(url);
     HttpURLConnection con = (HttpURLConnection) obj.openConnection();
     int responseCode = con.getResponseCode();
     BufferedReader in = new BufferedReader(
             new InputStreamReader(con.getInputStream()));
     String inputLine;
     StringBuffer response = new StringBuffer();
     while ((inputLine = in.readLine()) != null) {
     	response.append(inputLine);
     }
     in.close();
     String answer = "";
     Double ans = -1.0;
	String str = response.toString();
     // System.out.println(response.toString());
     if(str.contains("text")){
     	int indexE = str.indexOf("km");
     	int s = str.indexOf("text");
     	String s1 = str.substring(s+9,indexE).trim();
     	 try {
     	 	ans = Double.valueOf(s1);
     	 
     	 }
     	 catch(Exception e){
     	 	System.err.println(e);
     	 }
     }
    return ans;
 }
 catch(Exception e){
 	System.err.println(e);
 }
 return -1.0;
}
}