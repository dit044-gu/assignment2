import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
 * This class represents the Controller part in the MVC pattern.
 * Its responsibilities are to listen to the View and responds in a appropriate manner by
 * modifying the model state and the updating the view.
 */

public class CarController {
    // The delay (ms) corresponds to 20 updates/second (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // TODO A list of cars, modify if needed
    ArrayList<Car> cars = new ArrayList<>();

    public void startTimer() {
        this.timer.start();
    }
    public void stopTimer(){this.timer.stop();}
    /* Each step, the TimerListener moves all cars in the list and tells the
     * view to update its images. TODO Change this method to your needs.
     */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            for (Car car : cars) {
                car.move(); //finns
                //int x = (int) Math.round(car.getX()); //no Method
                //int y = (int) Math.round(car.getY()); //no Method
                //int x = (int) Math.round(car.getPosition().getX()); //no Method
                //int y = (int) Math.round(car.getPosition().getY()); //no Method
                //frame.drawPanel.moveit(x, y);
                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.revalidate();
                frame.drawPanel.repaint();
            }
        }
    }

    void gas(int amount) {
        // TODO adapt to your needs
        double gas = ((double) amount) / 100;
        for (Car car : cars) {
            car.gas(gas);
        }
    }
    void brake(int amount){
        double brake = ((double) amount)/100;
        for (Car car : cars){
            car.brake(brake);
        }
    }

    void turnLeft(){
        for (Car car : cars){
            car.turnLeft();
            car.move();
        }
    }

    void turnRight(){
        for (Car car : cars){
            car.turnRight();
            car.move();
        }
    }

    void saabturboOn(){
        for (Car car : cars){
            if(car instanceof Saab95){
                ((Saab95) car).setTurboOn();
            }
        }
    }
    void saabturboOff(){
        for (Car car : cars){
            if(car instanceof Saab95){
                ((Saab95) car).setTurboOff();
            }
        }
    }



}
