import java.io.*;
import ecs100.*;

public class circle{
    private int angle = 0;
    private int angleIncrement = 5;
    private double xOffset = 340;
    private double yOffset = 80;
    private double xRadius = 50;
    private double yRadius = 50;
    
    private double x;
    private double y;
    private double xNew;
    private double yNew;
    private PrintStream out;

    public circle(){
        UI.initialise();
        UI.setDivider(0.5);
        UI.addButton("Generate Circle", this::calculate);
        try{
            out = new PrintStream(new File("circle.txt"));
        }catch(FileNotFoundException e){}
    }

    public void calculate(){
        x = (Math.sin(Math.toRadians(angle)))*xRadius;
        y = (Math.cos(Math.toRadians(angle)))*yRadius;
        while(angle <= 360){
            UI.println("angle = "+angle);

            xNew = (Math.sin(Math.toRadians(angle)))*xRadius;
            yNew = (Math.cos(Math.toRadians(angle)))*yRadius;

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
    }
}
