/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robotapplication;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Math.abs;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toRadians;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author aditya
 */
public  class CustomJPanel extends JPanel implements ActionListener{

    Double panelWidth;
    Double panelHeight;
    
    Double length;
    
    
    Double circleX;
    Double circleY;
    
    Double endX;
    Double endY;
    
    Double centerX;
    Double centerY;
    Double radius;

    
    public static int speed;
    public static int angle;    
    public static int clockwise;
    public static int anticlockwise;
    public static int counter;
    
    
    Timer timer;
    
    public  static Long startTime;
    public static Long endTime;

    public CustomJPanel(int speed,int angle) {
    this.setVisible(true);
    this.setSize(363,250);
    this.setBackground(Color.WHITE);
    
    this.angle=angle;
    this.speed=speed;
    
    length=80.0;
    
    counter=0;
    radius=0.0;

    
    
    Dimension d=this.getSize();
    centerX=d.getWidth()/2;
    centerY=d.getHeight()/2;
    
    circleX=centerX;
    circleY=centerY;
    
    panelHeight=d.getHeight();
    panelWidth=d.getWidth();
    
    timer=new Timer(1000/speed, this);
    timer.start();
    }
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        repaint();
    }
    
    @Override
      public void paintComponent( Graphics g )
        {         
         super.paintComponent(g);
         //Create a Graphics2D object from g
         Graphics2D graphics2D = (Graphics2D)g;

         setEndCoordinates();
             
        //Antialiasing ON
         graphics2D.setRenderingHint(
         RenderingHints.KEY_ANTIALIASING, 
         RenderingHints.VALUE_ANTIALIAS_ON);
         
         graphics2D.drawLine(centerX.intValue(), 0, centerX.intValue(), panelHeight.intValue());
         graphics2D.drawLine(0,centerY.intValue(),  panelWidth.intValue(),centerY.intValue());
         
         g.setColor(Color.RED);
   
         RobotScreen.jLabel5.setText(String.valueOf(speed*Integer.signum(counter)));
         RobotScreen.jLabel6.setText(String.valueOf(angle%360));
         if(clockwise!=0)
         {
             startTime=System.currentTimeMillis();
             if(abs(startTime%1000-endTime%1000)>200)
             {
                 angle=angle+10;
                 endTime=startTime;
             }

         }
         
         if(anticlockwise!=0)
         {
             startTime=System.currentTimeMillis();
             if(abs(startTime%1000-endTime%1000)>100)
             {
                 angle=angle-10;
                 endTime=startTime;
             }

         }
         getCircleCoordinates();
         graphics2D.drawLine(centerX.intValue(),centerY.intValue(),endX.intValue(),endY.intValue());
         drawCenteredCircle(graphics2D, circleX.intValue(), circleY.intValue(), 10);
         setTimerSpeed();
        }    
      
      
      public void setEndCoordinates(){
      
          endX=centerX+length*cos(toRadians(angle));
          endY=centerY+length*sin(toRadians(angle));
      
      }
      
      
      public void drawCenteredCircle(Graphics2D g, int x, int y, int r) {
        x = x-(r/2);
        y = y-(r/2);
        g.fillOval(x,y,r,r);
    }
      
      public void getCircleCoordinates(){
      
          if(counter>0)
          {
              if((circleX-centerX)*(circleX-centerX)+(circleY-centerY)*(circleY-centerY)>80*80)
          {
              circleX=centerX;
              circleY=centerY;
          }
          else{
              circleX=circleX+counter*cos(toRadians(angle));
              circleY=circleY+counter*sin(toRadians(angle));
          }
          }
          
          else if(counter<0){
            if((circleX-centerX)*(endX-centerX)<=0 || (circleY-centerY)*(endY-centerY)<=0)
          {
              circleX=endX;
              circleY=endY;
          }
          else{
              circleX=circleX+counter*cos(toRadians(angle));
              circleY=circleY+counter*sin(toRadians(angle));
          }
          }
          else
          {
              circleX=centerX;

              circleY=centerY;
          }
      
      }
      
      
     public void setTimerSpeed(){
         timer.setDelay(1000/speed);
     }
      
}
