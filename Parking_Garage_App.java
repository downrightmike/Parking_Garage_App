/*
 downrightmike
 Control Statements Parking Garage:
 The Safe Keeping Parking Garage 
 Determine the price they charge for parking 
 based on the day of the week and the time spent parked.
 */
import java.util.*;

public class Parking_Garage_App {
  public static void main(String[] args) {
    parkingGarage();
  }//end of main
  public static void parkingGarage(){
    final double WEEK_DAY_RATE = 1.25;  
    final double WEEK_DAY_MAX_FEE = 15.0; 
    final double WEEKEND_RATE = 0.5;    
    final double WEEKEND_MAX_FEE = 15.0;
    
    int sentinel = 0;
    int arrivalHours = 9;
    int arrivalMinutes = 59;
    int depatureHours = 9;
    int depatureMinutes = 59;
    int allMinutes = 0;
    int allArrivalMinutes = 0;
    int allDepartureMinutes = 0;
    int duration = 0;
    int intervals = 0;
    double amountCharged = 0.0;
    double rateType = 0.0;
    double chargeToday = 0.0;
    //Day of week
    String dayOfTheWeek = "";
    int dayInt = 0;
    //Times
    int arrivalTime = 0;
    int depatureTime = 0;
    int totalTime = 0;
    int timeCheck = 0;
    
    do{
      String localDay = IR4.getString("Please enter the day of the week (mon, tue, wed, thu, fri, sat, sun) or quit: ");
      
      if(localDay.equals("mon")){
        dayOfTheWeek = "Monday";
        dayInt = 1;
      }else if(localDay.equals("tue")){
        dayOfTheWeek = "Tuesday";
        dayInt = 2;
      }else if(localDay.equals("wed")){
        dayOfTheWeek = "Wednesday";
        dayInt = 3;
      }else if(localDay.equals("thu")){
        dayOfTheWeek = "Thursday";
        dayInt = 4;
      }else if(localDay.equals("fri")){
        dayOfTheWeek = "Friday";
        dayInt = 5;
      }else if(localDay.equals("sat")){
        dayOfTheWeek = "Saturday";
        dayInt = 6;
      }else if(localDay.equals("sun")){
        dayOfTheWeek = "Sunday";
        dayInt = 7;
      }else if(localDay.equals("quit")){
        sentinel = 1;
        break;
      }else{System.out.println("This system only accepts the specified inputs");continue;}
      

      do{ //This do controls the check for departure time earlier than arrival time
      do{arrivalTime = IR4.getIntegerBetweenLowAndHigh("Please enter the vehicle's arrival time (HHMM):",0,2400,"Error");
        //System.out.println("arrivalTime " + arrivalTime);
        arrivalHours = arrivalTime / 100;
        arrivalMinutes = arrivalTime % 100;
        //System.out.println("arrivalMinutes " + arrivalMinutes);
        //System.out.println("arrivalHours " + arrivalHours);
      }while((arrivalHours < 0 || arrivalHours > 23)|| (arrivalMinutes < 0 || arrivalMinutes > 59));
      
      do{
        depatureTime = IR4.getIntegerBetweenLowAndHigh("Please enter the vehicle's depature time (HHMM):",0,2400,"Error");
        //System.out.println("depatureTime " + depatureTime);     
        depatureHours = depatureTime / 100;
        //System.out.println("depatureHours " + depatureHours);
        depatureMinutes = depatureTime % 100;
        //System.out.println("depatureMinutes " + depatureMinutes);
        // Add an if statement to check if Depart time < arrival time, throw error and continue.
        if(depatureTime < arrivalTime){
            System.out.println("The time leaving may not be earlier than the arrival time.");
            System.out.println("");

        }
      } while((depatureHours < 0 || depatureHours > 23) || (depatureMinutes < 0 || depatureMinutes > 59));
    }while(depatureTime < arrivalTime); //end A/D input from customer once Arrival is earlier than departure.
      
    //Cost algorithm
    //in minutes is the number of hours in the arrival time multiplied by 60 plus the number of minutes
    //Arrival Minutes is calculated as 7 hours times 60 giving 420 plus 45 minutes resulting in a total of 465 minutes

   
    //Arrival
    allArrivalMinutes = arrivalHours * 60;
    allArrivalMinutes = allArrivalMinutes + arrivalMinutes;
    //System.out.println("allArrivalMinutes " + allArrivalMinutes);
    //Departure
    allDepartureMinutes = depatureHours * 60;
    allDepartureMinutes = allDepartureMinutes + depatureMinutes;
    //System.out.println("allDepartureMinutes " + allDepartureMinutes);
    //Duration in minutes
    duration = allDepartureMinutes - allArrivalMinutes;
    //System.out.println("duration " + duration);
    //15 minute Intervals
    //intervals = duration / 15;
    intervals = (int)Math.ceil(duration / 15.0);
    //System.out.println("intervals " + intervals);
    //
      if(duration < 15 && intervals == 0){
        amountCharged = 0.0;
      }else {
        if(dayInt == 1 | dayInt == 2 | dayInt == 3 | dayInt == 4 | dayInt == 5){
          chargeToday = WEEK_DAY_RATE * intervals;
          rateType = WEEK_DAY_RATE;
          if(chargeToday > WEEK_DAY_MAX_FEE){
            amountCharged = WEEK_DAY_MAX_FEE;
          }else{
            amountCharged = chargeToday;
          }
        }else{
          chargeToday = WEEKEND_RATE * intervals;
          rateType = WEEKEND_RATE;
          if(chargeToday > WEEKEND_MAX_FEE){
            amountCharged = WEEKEND_MAX_FEE;
          }else{
            amountCharged = chargeToday;
          }
        }
      }
     
      //print it up nicely
      System.out.println("*****************************");
      System.out.println(" Safe Keeping Parking Garage");
      System.out.println("      Sales Receipt");
      System.out.println("*****************************");
      System.out.println("Days of the Week: " + dayOfTheWeek);
      System.out.println("Rate: " + rateType);
      System.out.println();//nothing
      System.out.println("Arrival Time: " + arrivalTime);
      System.out.println("Departure Time: " + depatureTime);
      System.out.println("Parking Duration: " + duration + " minutes");
      System.out.println();//nothing   
      System.out.printf("%1s %1.2f", "Amount Charged: $", amountCharged);
      System.out.println();
      System.out.println("*****************************");
      
      
      
    }while( sentinel == 0);
  }//end of parkingGarage
}