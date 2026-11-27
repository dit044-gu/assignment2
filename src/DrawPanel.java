import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel {
    // Just a single image, TODO: Generalize
    BufferedImage volvoImage;
    BufferedImage SaabImage;

    CarController carC;


    // To keep track of a single car's position
    //Point volvoPoint = new Point();


    /*

    // TODO: Make this general for all cars
    void moveit(int x, int y) {
        volvoPoint.x = x;
        volvoPoint.y = y;
    }
    */
    // Initializes the panel and reads the images
    public DrawPanel(int x, int y, CarController carC) {
        this.carC = carC;
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        // Print an error message in case file is not found with a try/catch block
        try {

            volvoImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg"));
            SaabImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg"));
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    /*
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(volvoImage, volvoPoint.x, volvoPoint.y, null); // see javadoc for more info on the parameters
    }
    */
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        if (carC != null && carC.cars != null){
            for (Car car : carC.cars){
                int x = (int) Math.round(car.getX());
                int y = (int) Math.round(car.getY());

                if (car instanceof Volvo240){
                    g.drawImage(volvoImage, x ,y,null);
                } else if (car instanceof Saab95){
                    g.drawImage(SaabImage, x,y,null);
                }
            }
        }

    }
}
