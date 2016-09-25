import java.io.*;
import ecs100.*;

public class oval{
    private int angle = 0;
    private int angleIncrement = 30;
    private double xOffset;
    private double yOffset;
    private double xRadius;
    private double yRadius;
    
    private double x;
    private double y;
    private double xNew;
    private double yNew;
    private PrintStream out;
    private boolean newShape;

    public oval(double x, double y, double xRad, double yRad, PrintStream out){
        this.out = out;
        this.xOffset = x;
        this.yOffset = y;
        this.xRadius = xRad;
        this.yRadius = yRad;
        newShape = true;
        calculate();
    }

    public void calculate(){
        x = (Math.sin(Math.toRadians(angle)))*xRadius;
        y = (Math.cos(Math.toRadians(angle)))*yRadius;
        while(angle <= 360){
            xNew = (Math.sin(Math.toRadians(angle)))*xRadius;
            yNew = (Math.cos(Math.toRadians(angle)))*yRadius;
            x = xNew;
            y = yNew;
            double xOut = ((Math.round(x*10))/10.0)+xOffset;
            double yOut = ((Math.round(y*10))/10.0)+yOffset;
            
            if(newShape){
                out.println(xOut+" "+yOut+" 2");
                newShape = false;
            }
            out.println(xOut+" "+yOut+" 1");

            angle = angle+angleIncrement;
        }
    }
}
