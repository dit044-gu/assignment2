void main() {
    // This class starts the program and it needs car controller class,CarView,
    //It prepares the different objects needed and starts it.
    CarController cc = new CarController();//CarApplication needs a controller to manage the actions of the cars.
    //that's why we are creating it//



    Vehicle volvo = new Volvo240();
    Vehicle saab = new Saab95();

    saab.setPosition(0, 100);

    cc.cars.add(volvo);
    cc.cars.add(saab);//CHANGE: I HAVE ADDED SAAB95 TO THE LIST
    //cc.cars.add-- means Access the field called ''cars'' inside the CarController object referenced by cc


    // Start a new view and send a reference to the controller
    cc.frame = new CarView("CarSim 1.0", cc);
    //The program needs a graphical window.
    // It will create a CarView object and give that object a reference to the controller.
    //CarView needs to draw the picture of that car.
    //CarView and CarController need to communicate between each other, so that when user presses a button(taken from CarView),the car need to move and do smth(taken from CarController)

    cc.startTimer();
    //starts the timer from CarController.
}