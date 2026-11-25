import javax.swing.*; //Swing provides components such as buttons and windows
import javax.swing.event.ChangeEvent; //represents what happened
import javax.swing.event.ChangeListener; //provides the behaviour that responds to what happened.
import java.awt.*; //AWT provides other GUI-related tools, including ways to arrange components, work with sizes and use colours.
import java.awt.event.ActionEvent; //if somebody clicks a button, that click is an ActionEvent
import java.awt.event.ActionListener;
//Clicking a button does not automatically tell Vehicle to accelerate.
//We still need code that responds to the click.
//That's where an ActionListener comes in.

/**
 * This class represents the full view of the MVC pattern of your car simulator.
 * It initializes with being center on the screen and attaching its controller in its state.
 * It communicates with the Controller by calling its methods when an action fires in
 * its components.
 * TODO: Write more actionListeners and wire the rest of the buttons
 **/
//THIS FILE IS RESPONSIBLE FOR CREATING BUTTONS AND WINDOWS, AND IT HAS A DRAWPANEL//
//CarView has the buttons.
//It creates the window containing the car pictures, Gas button, Brake button and other controls.


public class CarView extends JFrame {
    //extends JFrame means CarView inherits from JFrame.
    //But what is JFrame?
    //JFrame is a Java Swing class used for creating a top-level graphical window.
    public static final int X = 800;
    public static final int Y = 800;//Normally, fields belong to an object.But static means something different.
    //It means the field belongs to the class itself(CarView), rather than to each individual object.So we can call Car.X

    // The controller member
    CarController carC; //IMPORTANT:Every CarView object should have a field called carC that can hold a reference to a CarController object.

    DrawPanel drawPanel = new DrawPanel(X, Y-240);
    //JFrame = the main window.
    //DrawPanel = an area for drawing graphics.
    //JPanel = a general-purpose area that can contain buttons and other components

    JPanel controlPanel = new JPanel();
    //Create an empty panel called controlPanel. Later, we will put car-control buttons inside it.
    //Now, what is JPanel?
    //A JPanel is a container.
    //A container is a place where you can organise and display GUI components.
    //For example, one JPanel can contain several buttons.
    //Think of it like a box inside your window.

    JPanel gasPanel = new JPanel();
    //Another object from JPanel
    JSpinner gasSpinner;

    //What is JSpinner?
    //It is another class from Java Swing.
    //A JSpinner allows the user to choose a number.
    //For example, choosing how much gas to apply
    int gasAmount = 50;
    JLabel gasLabel = new JLabel("Amount of gas");
    //A JLabel displays text (or sometimes an image) in a GUI.
    //It does not normally behave like a button.
    //So the user sees:
    //Amount of gas
    //50

    JButton gasButton = new JButton("Gas");
    JButton brakeButton = new JButton("Brake");
    JButton leftButton = new JButton("← Turn left ←");
    JButton rightButton = new JButton("→ Turn right →");
    JButton turboOnButton = new JButton("Saab Turbo on");
    JButton turboOffButton = new JButton("Saab Turbo off");

    JButton startButton = new JButton("Start all cars");
    JButton stopButton = new JButton("Stop all cars");
    //Now we are creating the buttons here.
    //Creating the button does not make it move and function.

    // Constructor
    public CarView(String framename, CarController cc) {
        this.carC = cc;
        initComponents(framename);  //
        //Let's understand the word init.
        //It comes from initialize, meaning prepare something so it is ready to use.
    }

    // Sets everything in place and fits everything
    // TODO: Take a good look and make sure you understand (roughly) how these methods and components work
    private void initComponents(String title) {
        this.setTitle(title);
        //setTitle() is a method inherited from JFrame.
        //It sets the text shown in the window's title bar.
        this.setPreferredSize(new Dimension(X,Y));
        //Preferred size means requested size, not an absolute guarantee that the window will always have exactly those dimensions.
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
       // A layout controls how GUI components are arranged inside a container.
        // Imagine putting buttons inside a window.
           // Should they be:
        //Next to each other?
        //Above one another?
        // In a grid?
        // Starting from the left?
        //A layout manager decides how to arrange them.

        this.add(drawPanel);
        //IMPORTANT:
        //"Add this DrawPanel component to the current CarView window."

        SpinnerModel spinnerModel =
                new SpinnerNumberModel(gasAmount, //initial value
                        0, //min
                        100, //max
                        1);//step
        gasSpinner = new JSpinner(spinnerModel);
        gasSpinner.addChangeListener(e -> {
                gasAmount = (int) ((JSpinner)e.getSource()).getValue();
        });

        gasPanel.setLayout(new BorderLayout());
        gasPanel.add(gasLabel, BorderLayout.PAGE_START);
        gasPanel.add(gasSpinner, BorderLayout.PAGE_END);

        this.add(gasPanel);

        controlPanel.setLayout(new GridLayout(2,4));

        controlPanel.add(gasButton, 0);
        controlPanel.add(turboOnButton, 1);
        controlPanel.add(brakeButton, 2);
        controlPanel.add(turboOffButton, 3);
        controlPanel.add(leftButton, 4);
        controlPanel.add(rightButton, 5);
        controlPanel.setPreferredSize(new Dimension((X/2)+4, 200));
        this.add(controlPanel);
        controlPanel.setBackground(Color.CYAN);


        startButton.setBackground(Color.blue);
        startButton.setForeground(Color.green);
        startButton.setPreferredSize(new Dimension(X/5-15,200));
        this.add(startButton);

        stopButton.setBackground(Color.red);
        stopButton.setForeground(Color.black);
        stopButton.setPreferredSize(new Dimension(X/5-15,200));
        this.add(stopButton);

        // This actionListener is for the gas button only
        //We are creating buttons here
        gasButton.addActionListener(e -> {
            carC.gas(gasAmount);
        });
        brakeButton.addActionListener(e -> {
                    carC.brake(gasAmount);
        });//CHANGE: WHEN SOMEBODY BREAKS,ACTIONLISTENER REACTS TO IT.We are creating buttons here
        startButton.addActionListener(e -> {
            carC.startAllCars();
        });

        stopButton.addActionListener(e -> {
            carC.stopAllCars();
        });
        leftButton.addActionListener(e -> {
            carC.turnLeft();
        });

        rightButton.addActionListener(e -> {
            carC.turnRight();
        });

        // Make the panel layout more compact
        this.pack();

        // Get the screen resolution
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // Center the frame
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        // Make the frame visible
        this.setVisible(true);
        // Make sure the frame exits when "x" is pressed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}