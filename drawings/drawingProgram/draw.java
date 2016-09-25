import ecs100.*;
import java.awt.Color;
import java.io.*;

public class draw{
    // Fields:
    private double lastX;
    private double lastY;
    private double x; 
    private double y;
    private String shape;
    private File file; 
    private PrintStream out;
    private boolean newShape;

    //Constructor
    public draw(){
        UI.addButton("Line", this::line);
        UI.addButton("Oval", this::oval);
        UI.addButton("New", this::canvas);
        UI.addButton("Quit", UI::quit);
        UI.setDivider(0.25);
        canvas();
    }

    public void canvas(){
        UI.clearGraphics();
        //draw outline of drawable area
        UI.drawLine(203,216,337,252);
        UI.drawLine(495,222,337,252);
        UI.drawLine(495,222,553,216);
        UI.drawLine(543,190,553,216);
        UI.drawLine(543,190,513,152);
        UI.drawLine(473,124,513,152);
        UI.drawLine(473,124,419,88);
        UI.drawLine(379,76,419,88);
        UI.drawLine(379,76,341,66);
        UI.drawLine(295,74,341,66);
        UI.drawLine(295,74,259,88);
        UI.drawLine(227,102,259,88);
        UI.drawLine(227,102,191,130);
        UI.drawLine(153,160,191,130);
        UI.drawLine(153,160,125,192);
        UI.drawLine(113,216,125,192);
        UI.drawLine(113,216,203,216);

        UI.drawImage("elf.jpg", 275, 100, 125, 125);

        String name = UI.askString("Enter file name");
        file = new File(name+".txt");
        try{
            out = new PrintStream(file);
        }catch(FileNotFoundException e){}
    }

    public void doMouse(String action, double x, double y) {
        if(shape.equals("oval")){
            if (action.equals("pressed")){
                lastX = x;
                lastY = y;
            }else if (action.equals("released")){
                //no coordinate method for drawing ovals, so had to create a drawOval for each possible diagonal line direction
                //necessary because width and height cannot be negative in the drawOval method
                if((lastX < x)&&(lastY < y)){
                    addOval(lastX, lastY, (x - lastX), (y - lastY));
                }else if((lastX > x)&&(lastY < y)){
                    addOval(x, lastY, (lastX - x), (y - lastY));
                }else if((lastX < x)&&(lastY > y)){
                    addOval(lastX, y, (x - lastX), (lastY - y));
                }else if((lastX > x)&&(lastY > y)){
                    addOval(x, y, (lastX - x), (lastY - y));
                }
            }
        }else if(shape.equals("line")){
            if (action.equals("pressed")){
                if(newShape){
                    out.println(x+" "+y+" 2");
                    lastX = x;
                    lastY = y;
                    newShape = false;
                }else if(lastX == x && lastY == y){ //double click to finish drawing line
                    shape = "none";
                    return;
                }
                UI.invertLine(x,y,lastX, lastY);
                UI.println("x: "+x+" lastX: "+lastX);
                UI.println("y: "+y+" lastY: "+lastY);
                lastX = x;
                lastY = y;
                out.println(x+" "+y+" 1");
            }
        }
    }

    public void addOval(double x, double y, double width, double height){
        UI.drawOval(x,y,width,height);
        new oval(x+(width/2), y+(height/2), width/2, height/2, out);
    }

    public void line(){
        newShape = true;
        shape = "line";
        UI.setMouseListener(this::doMouse);
    }         

    public void oval(){
        shape = "oval";
        UI.setMouseListener(this::doMouse);
    }      
}