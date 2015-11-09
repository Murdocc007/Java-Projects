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
public class customJpanel extends JPanel implements ActionListener{
    public static int speed;
    
    public static int angle=0;
    public static int directionX;
    public static int directionY;
    
    Double panelWidth;
    Double panelHeight;
    
    
    Double centerX;
    Double centerY;
    
    public static Double startX;
    public static Double startY;
    
    Double endX;
    Double endY;
    
    
    Double counterX;
    Double counterY;
    
    Double length=10.0;
    
    Timer timer;
    
    
    public customJpanel( int speed,int angle) {      
    this.setVisible(true);
    this.setSize(363,250);
    this.setBackground(Color.WHITE);
    this.speed=speed;
    Dimension d = this.getSize();
    centerX=d.getWidth()/2;
    centerY=d.getHeight()/2;
    startX=centerX;
    startY=centerY;
    this.angle=angle;
    
    panelWidth=this.getSize().getWidth();
    panelHeight=this.getSize().getHeight();
    
    counterX=length*cos(toRadians(angle));
    counterY=length*sin(toRadians(angle));
    
    directionX=1;
    directionY=1;

    
    timer=new Timer(1000/speed, this);
    timer.start();
    }

    @Override
      public void paintComponent( Graphics g )
        {         
         super.paintComponent(g);
         //Create a Graphics2D object from g
         Graphics2D graphics2D = (Graphics2D)g;
         
        //Antialiasing ON
         graphics2D.setRenderingHint(
         RenderingHints.KEY_ANTIALIASING, 
         RenderingHints.VALUE_ANTIALIAS_ON);
         g.setColor(Color.RED);
         setCounterX();
         setCounterY();
         if(speed!=0)
         {
             setSpeed();
         }

         endX=startX+counterX*RobotScreen.start;
         endY=startY+counterY*RobotScreen.start;
         graphics2D.drawLine(startX.intValue(),startY.intValue(),endX.intValue(),endY.intValue());
         if(speed!=0)
         {
             startX=endX;
             startY=endY;
         }
         process();
        }

      
     public  void setAngle(int angle){
         this.angle=angle;
     } 
     
     public void setSpeed(){
         timer.setDelay(1000/speed);
     }
     public void process(){         
         
         int angle1=angle%360;
         int tempx=hitBoundaryX(endX);
         int tempy=hitBoundaryY(endY);
         if(tempx!=0)
         {
             directionX=-1*directionX;    
             if(endX>0)
                 startX=panelWidth;
             else
                 startY=0.0;
             startY=endY;          
         }
         
         if(hitBoundaryY(endY)!=0){
             directionY=-1*directionY;
             if(endY>0)
                 startY=panelHeight;
             else
                 startX=0.0;
             startX=endX;

         }

                  
     } 
     
     
    @Override
    public void actionPerformed(ActionEvent ae) {
 
        repaint();
    }
    
    public int hitBoundaryX(double x){
    if(x<=0 )
        return -1;
    if(x>=panelWidth)
        return 1;
    return 0;
    }
    
    public void setCounterX(){
    counterX=directionX*length*(cos(toRadians(angle)));
    }
    
    
    public void setCounterY(){
    counterY=directionY*length*(sin(toRadians(angle)));;
    }
        
    public int hitBoundaryY(double y){
    if(y<=0 )
        return -1;
    if(y>=panelHeight)
        return 1;
    return 0;
    }
}
