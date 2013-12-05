import java.util.Scanner
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
//Create a class which holds the delay function based on the user input
class Delay{
	public static void waitX(int timeToWait){
		try {
   		Thread.sleep(timeToWait);
		} catch(InterruptedException ex) {
   		Thread.currentThread().interrupt();
		}
	}
}
//-------------------------------------
//Create the main Laundry class
public class Laundry{
	public static void main(String[] args){
//Find out how many timers the user wishes to create
		Scanner amountOfTimersScan = new Scanner( System.in );
		System.out.println("Please enter the amount of timers you wish " +
		"to create...");
		int amountOfTimers = Integer.parseInt(amountOfTimersScan.next());
//-------------------------------------
//Create an array with the values for each timer as a double
		double[][] allTimers = new double[amountOfTimers][3];
//-------------------------------------
//Go through each timer and find the timing value
		int x = 0;
		for(int i = 1; i <= amountOfTimers; i++){
			Scanner scan = new Scanner( System.in );
			System.out.println("Please enter the duration for timer " + i);
			double durationMins = scan.nextDouble();
			double durationSecs = durationMins * 60;
			allTimers[x][0] = i;
			allTimers[x][1] = durationMins;
			allTimers[x][2] = durationSecs;
			x++;
		}
//-------------------------------------
//Find the run speed the user wishes to run at
		Scanner speed = new Scanner(System.in);
		System.out.println("Please enter the speed you would wish to run " +
		"the simulation");
		double sysSpeed = Double.parseDouble(speed.next());
		double waitTime = 1000 / sysSpeed;
		int waitTimeFinal = (int) waitTime;
//-------------------------------------
//Go through the array and see if any of the values are greater than 0
//If so then output the time value that needs to be seen!
		x = 0;
		NumberFormat nf2 = new DecimalFormat("00");
		double displayMins;
		double displaySecs;	
		int endLoop = 0;
//Create an infinite loop to go through all the timers
		while(1==1){
			displayMins = (int)allTimers[x][2]/60;
			displaySecs = (int)allTimers[x][2] - ((int)displayMins*60);
//Check if the timer has already finished and output 0 if so
			if(allTimers[x][2] < 0)
				System.out.print("Timer " + (int)allTimers[x][0] + ": 0:00 ");
//Check if the timer is greater than 0 so output the timer display		
			if(allTimers[x][2] > 0){
				System.out.print("Timer " + (int)allTimers[x][0] + ": " +
				(int)displayMins + ":" + nf2.format(displaySecs) + " ");
				allTimers[x][2]--;
			}
//Check if that timer has just finished, if so add one to the end loop counter
			if(allTimers[x][2] == 0){
				endLoop++;
				allTimers[x][2]--;
			}
//If we're at the last timer output a line break and wait the delayed time
			if(x == (amountOfTimers - 1)){	
				System.out.println("");		
				Delay.waitX(waitTimeFinal);
				x = 0;
//If every timer has finished break the loop!
				if(endLoop == amountOfTimers)
					break;
			}
			else
			{
				x++;
			}
		}
	}
}
