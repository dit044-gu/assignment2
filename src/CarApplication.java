public class CarApplication {
  public static void main(String[] args) {
    // Instance of this class
    CarController cc = new CarController();

    // TODO uncomment, and adapt if necessary
    Volvo240 volvo = new Volvo240();
    Saab95 saab = new Saab95();

    volvo.setX(0);
    volvo.setY(0);

    saab.setX(0);
    saab.setY(100);
    
    cc.cars.add(volvo);
    cc.cars.add(saab);

    // Start a new view and send a reference to the controller
    cc.frame = new CarView("CarSim 1.0", cc);
    cc.startTimer();
    }
}