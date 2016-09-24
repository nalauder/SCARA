import java.io.*;
import ecs100.*;

public class circle{
    private int angle;
    private int angleIncrement = 5;
    private double x = 0;
    private double y = 50;
    private double xNew;
    private double yNew;
    private double xOffset = 336;
    private double yOffset = 116;
    private double radius = 50;
    
    public circle(){
        UI.initialise();
        UI.setDivider(0.5);
        angle = 0;
        UI.addButton("Calculate", this::calculate);
    }

    public void calculate(){
        try{
            PrintStream out = new PrintStream(new File("circle.txt"));
            while(angle <= 360){
                UI.println("angle = "+angle);

                xNew = (Math.sin(Math.toRadians(angle)))*radius;
                yNew = (Math.cos(Math.toRadians(angle)))*radius;

                UI.drawLine(x+100, y+100, xNew+100, yNew+100);

                x = xNew;
                y = yNew;

                double xOut = ((Math.round(x*10))/10.0)+xOffset;
                double yOut = ((Math.round(y*10))/10.0)+yOffset;
                
                UI.println("x: "+xOut);
                UI.println("y: "+yOut);
                
                out.println(xOut+" "+yOut+" 1");

                angle = angle+angleIncrement;
            }
        }catch(FileNotFoundException e){}
    }
}
