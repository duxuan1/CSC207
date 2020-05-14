package guifx;

import java.util.Timer;
import java.util.TimerTask;

public class TimerDemo  {
	/**
	 * Might want to do something like this with a GUI, for example, 
	 * for a progress bar, or for an animation, or for animation.
	 * Problem is that the JavaFX code is not Thread Safe, that is, 
	 * it can get messed up if multiple threads are running through it.
	 * By default, the GUI already has a thread running through it, 
	 * managing the GUI.
	 * JavaFX does have a solution though...see TimerDemoFX.
	 */
	public static void main(String[] args) {
		
		TimerDemo td = new TimerDemo();
    }

	public TimerDemo() {
		
		Timer tickTimer = new Timer();
    	tickTimer.schedule(new TickTask(), 1000, 800);
    	
    	Timer tockTimer = new Timer();
    	tockTimer.schedule(new TockTask(), 1000, 1700);
	}
	
    private class TickTask extends TimerTask {

        @Override
        public void run() {
        	System.out.println("Tick!!");
        }
    }
    private class TockTask extends TimerTask {

        @Override
        public void run() {
        	System.out.println("Tock!!");
        }
    }
}



