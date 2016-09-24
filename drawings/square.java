import java.io.*;
import ecs100.*;

public class square{
    private double x = 0;
    private double y = 0;
    private double width = 50;
    private double xNew;
    private double yNew;
    private double xOffset = 336;
    private double yOffset = 116;
    private PrintStream out;
    public square(){
        UI.initialise();
        UI.setDivider(0.5);
        UI.addButton("Calculate", this::calculate);
        try{
            out = new PrintStream(new File("square.txt"));
        }catch(FileNotFoundException e){}
    }

    public void calculate(){
        print(x, y);
        print(x+width, y);
        print(x, y+width);
        print(x-width, y);
        print(x, y-width);
    }

    public void print(double xNew, double yNew){
        UI.drawLine(x+100, y+100, xNew+100, yNew+100);
        x = xNew;
        y = yNew;
        UI.println("x: "+(x+xOffset));
        UI.println("y: "+(y+yOffset));
        out.println((x+xOffset)+" "+(y+yOffset)+" 1");
    }
}
