/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robotapplication;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;


/**
 *
 * @author aditya
 */
public class Arm extends JPanel {
    
    Double endX1;//x coordinate of the ending point of left arm
    Double endX2;//x coordinate of the ending point of right arm
    Double endY;//y coordinate of the ending point arms
    
    Double startX1;//x coordinate of the starting point of left arm
    Double startX2;//x coordinate of the starting point of right arm
    Double startY;//y coordinate of the starting  point of arms
    
    int armangle;
    
    int armaction;
    int clawaction;
        
    Arm(){
        this.setVisible(true);
        this.setSize(345,228);
        this.setBackground(Color.WHITE);
        armangle=0;
        armaction=1;
        clawaction=3;
    }
    
    public void up(){
            armaction=2;
            repaint();
    }
    
    public void down(){
            armaction=1;
            repaint();
    }
    
    public void openclaw(){
            clawaction=4;
            repaint();
    }
        
    public void closeclaw(){
            clawaction=3;
            repaint();
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
         
         startX1=(double)(this.getWidth()/2-10);
         startX2=(double)(this.getWidth()/2+10);
         startY=(double)(this.getHeight()/2);
         
//         endX1=this.getWidth()/2-40*Math.cos(Math.toRadians(armangle));
//         endX2=this.getWidth()/2+40*Math.cos(Math.toRadians(armangle));
//         endY=this.getHeight()/2-40*Math.sin(Math.toRadians(armangle));
            setEndCoordinates(40,armangle);
         
         switch(armaction){
         case 1: showarms(graphics2D);break;
         case 2:armup(graphics2D);break;
         }
         
         switch(clawaction){
          case 3: clawclosed(graphics2D);break;
          case 4: clawopen(graphics2D);break;
                 
         }
         
        }
      
      public void armup(Graphics2D graphics2D){
          if(armangle<60)
            armangle+=10;
//          endX1=this.getWidth()/2-40*Math.cos(Math.toRadians(armangle));
//          endX2=this.getWidth()/2+40*Math.cos(Math.toRadians(armangle));         
//          endY=this.getHeight()/2-40*Math.sin(Math.toRadians(armangle));
          setEndCoordinates(40,armangle);
         graphics2D.drawLine(startX1.intValue(), startY.intValue(), endX1.intValue(), endY.intValue());
         graphics2D.drawLine(startX2.intValue(), startY.intValue(), endX2.intValue(), endY.intValue());
      }

    private void clawclosed(Graphics2D graphics2D) {
        graphics2D.setColor(Color.BLUE); 
        int angle=0;
        if(armangle==60)
         angle=75;
        else if(armangle==0) 
            angle=armangle;
        else 
            angle=armangle+7;
        Double tempx1=endX1-10*Math.cos(Math.toRadians(angle));
        Double tempx2=endX2+10*Math.cos(Math.toRadians(angle));
        Double tempy=endY-10*Math.sin(Math.toRadians(angle));
        
        graphics2D.drawLine(endX1.intValue(), endY.intValue(),tempx1.intValue(),tempy.intValue());
        graphics2D.drawLine(endX2.intValue(), endY.intValue(),tempx2.intValue(),tempy.intValue());
    }

    private void clawopen(Graphics2D graphics2D) {
        graphics2D.setColor(Color.BLUE); 
        Double tempx1;
        Double tempx2;
        Double tempy1;
        Double tempy2;
        if(armangle==0)
        {
            //left hand
            tempx1=endX1-10*Math.cos(Math.toRadians(armangle+35));
            tempx2=endX1-10*Math.cos(Math.toRadians(armangle+35));
            tempy1=endY-10*Math.cos(Math.toRadians(armangle+35));
            tempy2=endY+10*Math.cos(Math.toRadians(armangle+35));
         
            graphics2D.drawLine(endX1.intValue(), endY.intValue(),tempx1.intValue(),tempy1.intValue());
            graphics2D.drawLine(endX1.intValue(), endY.intValue(),tempx2.intValue(),tempy2.intValue());

            //right hand
            tempx1=endX2+10*Math.cos(Math.toRadians(armangle+35));
            tempx2=endX2+10*Math.cos(Math.toRadians(armangle+35));
            tempy1=endY-10*Math.cos(Math.toRadians(armangle+35));
            tempy2=endY+10*Math.cos(Math.toRadians(armangle-35));
            
            graphics2D.drawLine(endX2.intValue(), endY.intValue(),tempx1.intValue(),tempy1.intValue());
            graphics2D.drawLine(endX2.intValue(), endY.intValue(),tempx2.intValue(),tempy2.intValue());
        }
        else{
            //left hand
//            tempx1=endX1-10*Math.cos(Math.toRadians(40));
//            tempx2=endX1-10*Math.cos(Math.toRadians(30));
//            tempy1=endY-10*Math.cos(Math.toRadians(40));
//            tempy2=endY+10*Math.cos(Math.toRadians(30));
            
            
            tempx1=endX1-10*Math.cos(Math.toRadians(armangle-5));
            tempx2=endX1-10*Math.cos(Math.toRadians(armangle-10));
            tempy1=endY-10*Math.cos(Math.toRadians(armangle-5));
            tempy2=endY+10*Math.cos(Math.toRadians(armangle-10));
            
            graphics2D.drawLine(endX1.intValue(), endY.intValue(),tempx1.intValue(),tempy1.intValue());
            graphics2D.drawLine(endX1.intValue(), endY.intValue(),tempx2.intValue(),tempy2.intValue());

            //right hand
//            tempx1=endX2+10*Math.cos(Math.toRadians(40));
//            tempx2=endX2+10*Math.cos(Math.toRadians(30));
//            tempy1=endY-10*Math.cos(Math.toRadians(40));
//            tempy2=endY+10*Math.cos(Math.toRadians(30));
            
            tempx1=endX2+10*Math.cos(Math.toRadians(armangle-5));
            tempx2=endX2+10*Math.cos(Math.toRadians(armangle-10));
            tempy1=endY-10*Math.cos(Math.toRadians(armangle-5));
            tempy2=endY+10*Math.cos(Math.toRadians(armangle-10));
            
            graphics2D.drawLine(endX2.intValue(), endY.intValue(),tempx1.intValue(),tempy1.intValue());
            graphics2D.drawLine(endX2.intValue(), endY.intValue(),tempx2.intValue(),tempy2.intValue());
   
        }
        
    }

    private void showarms(Graphics2D graphics2D) {
        if(armangle>0) 
            armangle-=10;
//         endX1=this.getWidth()/2-40*Math.cos(Math.toRadians(armangle));
//         endX2=this.getWidth()/2+40*Math.cos(Math.toRadians(armangle));         
//         endY=this.getHeight()/2-40*Math.sin(Math.toRadians(armangle));
         setEndCoordinates(40,armangle);
         graphics2D.drawLine(startX1.intValue(), startY.intValue(), endX1.intValue(), endY.intValue());
         graphics2D.drawLine(startX2.intValue(), startY.intValue(), endX2.intValue(), endY.intValue());         
    }

    
    private void setEndCoordinates(int length,int angle){
        endX1=this.getWidth()/2-length*Math.cos(Math.toRadians(angle));
        endX2=this.getWidth()/2+length*Math.cos(Math.toRadians(angle));
        endY=this.getHeight()/2-length*Math.sin(Math.toRadians(angle));
    }


}
