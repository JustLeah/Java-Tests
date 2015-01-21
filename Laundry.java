import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;

//Create a class which holds the delay function based on the user input
class Delay {
    public static void waitX(int timeToWait) {
        try {
            Thread.sleep(timeToWait);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}

//-------------------------------------
//Create the main Laundry class
public class Laundry {
    public static void main(String[] args) {
//Find out how many timers the user wishes to create
        int amountOfTimers = getNumberOfTimersFromUserInput();
        Duration[] allTimers = getInitialDurationForEachTimer(amountOfTimers);
        int waitTimeFinal = findRunSpeed();


//-------------------------------------
//Go through the array and see if any of the values are greater than 0
//If so then output the time value that needs to be seen!
        int x = 0;
        NumberFormat nf2 = new DecimalFormat("00");
        double displayMins;
        double displaySecs;
        int endLoop = 0;
//Create an infinite loop to go through all the timers
        while (1 == 1) {
            displayMins = (int) allTimers[x].getSeconds() / 60;
            displaySecs = (int) allTimers[x].getSeconds() - ((int) displayMins * 60);
//Check if the timer has already finished and output 0 if so
            if (allTimers[x].getSeconds() < 0)
                System.out.print("Timer " + x + ": 0:00 ");
//Check if the timer is greater than 0 so output the timer display		
            if (allTimers[x].getSeconds() > 0) {
                System.out.print("Timer " + x + ": " +
                        (int) displayMins + ":" + nf2.format(displaySecs) + " ");
                allTimers[x].decrementBySeconds(1);
            }
//Check if that timer has just finished, if so add one to the end loop counter
            if (allTimers[x].getSeconds() == 0) {
                endLoop++;
                allTimers[x].decrementBySeconds(1);
            }
//If we're at the last timer output a line break and wait the delayed time
            if (x == (amountOfTimers - 1)) {
                System.out.println("");
                Delay.waitX(waitTimeFinal);
                x = 0;
//If every timer has finished break the loop!
                if (endLoop == amountOfTimers)
                    break;
            } else {
                x++;
            }
        }
    }

    private static int findRunSpeed() {
        //-------------------------------------
//Find the run speed the user wishes to run at
        Scanner speed = new Scanner(System.in);
        System.out.println("Please enter the speed you would wish to run " +
                "the simulation");
        double sysSpeed = Double.parseDouble(speed.next());
        double waitTime = 1000 / sysSpeed;
        return (int) waitTime;
    }

    private static Duration[] getInitialDurationForEachTimer(int amountOfTimers) {


        //-------------------------------------
//Create an array with the values for each timer as a double
        Duration[] allTimers = new Duration[amountOfTimers];
//-------------------------------------
//Go through each timer and find the timing value
        int x = 0;
        for (int i = 1; i <= amountOfTimers; i++) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Please enter the duration for timer " + i);
            double durationMins = scan.nextDouble();
            double durationSecs = durationMins * 60;
            allTimers[x] = new Duration(durationSecs);

            x++;
        }
        return allTimers;
    }

    private static int getNumberOfTimersFromUserInput() {
        Scanner amountOfTimersScan = new Scanner(System.in);
        System.out.println("Please enter the amount of timers you wish " +
                "to create...");
        return Integer.parseInt(amountOfTimersScan.next());
    }

    static class Duration {

        private double seconds;

        Duration(double seconds) {
            this.seconds = seconds;
        }

        public double getSeconds() {
            return seconds;
        }

        public void decrementBySeconds(double numberOfSeconds) {
            seconds = seconds - numberOfSeconds;
        }

        public double getMinutes() {
            return seconds / 60.0;
        }

    }

}
