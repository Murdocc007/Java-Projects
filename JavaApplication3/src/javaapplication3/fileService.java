/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import java.io.PrintStream;
import java.io.PrintWriter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import java.util.Map;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 *
 * @author aditya
 */
public class fileService {
    
    private ArrayList<info> inf=new ArrayList<info>();    
    
    public boolean equalName(String val,info obj){
        String temp=obj.getFirstName()+" "+obj.getMiddleInitial()+" "+obj.getLastName();
        if(temp.equals(val))
            return true;
        return false;
    }
    
    public boolean equalObject(info obj1,info obj2){
        if(obj1.getFirstName().equals(obj2.getFirstName()) && obj1.getMiddleInitial().equals(obj2.getMiddleInitial()) && obj1.getLastName().equals(obj2.getLastName()))
            return true;
        return false;                   
    }
    
    public info getInfoObject(String name,String phoneNumber){
        ArrayList<info> inf=new ArrayList<info>();
        inf=getInfoObject();
        for(info temp: inf){
            if(equalName(name,temp) && temp.getPhoneNumber().equals(phoneNumber))
                return temp;
        }
        return null;
    }
    
    public ArrayList<info> getInfoObject(){
        ArrayList<info> inf=new ArrayList<info>();
        String str;
        File foo=new File("foo.txt");
        if(!foo.exists())
            try {
                foo.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(fileService.class.getName()).log(Level.SEVERE, null, ex);
        }
        try{
            FileInputStream fis=new FileInputStream(foo);
            BufferedReader br=new BufferedReader(new InputStreamReader(fis));
            while((str=br.readLine())!=null)
            {
                inf.add(convertStringtoObject(str));
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        
        return inf;
    }
    
    private info convertStringtoObject(String s){
     HashMap<String,String> temp=convertStringtoMap(s);
     info inf=new info();
     for(Map.Entry<String, String> e : temp.entrySet()){

         
        if(e.getKey().equals("firstName")){
             inf.setFirstName(e.getValue());
         }
         
        if(e.getKey().equals("lastName")){
             inf.setLastName(e.getValue());
         }
         
        if(e.getKey().equals("addressLine1")){
             inf.setAddressLine1(e.getValue());
         }
         
        if(e.getKey().equals("addressLine2")){
             inf.setAddressLine2(e.getValue());
         }
        
        if(e.getKey().equals("middleInitial")){
             inf.setMiddleInitial(e.getValue());
         }
         
        if(e.getKey().equals("city")){
             inf.setCity(e.getValue());
         }
         
        if(e.getKey().equals("state")){
             inf.setState(e.getValue());
         }
         
        if(e.getKey().equals("zipCode")){
             inf.setZipCode(Integer.parseInt(e.getValue()));
         }
        
        if(e.getKey().equals("middleInitial")){
             inf.setMiddleInitial(e.getValue());
         }
         
        if(e.getKey().equals("phoneNumber")){
             inf.setPhoneNumber(e.getValue());
         }
         
        if(e.getKey().equals("emailAddress")){
             inf.setEmailAddress(e.getValue());
         }
         
        if(e.getKey().equals("proofOfPurchase")){
             inf.setProofOfPurchase(Boolean.parseBoolean(e.getValue()));
         }
        
        if(e.getKey().equals("dateReceived")){
             try {
                 Date date = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").parse(e.getValue());
                 inf.setDateReceived(date);
             } catch (ParseException ex) {
                 Logger.getLogger(fileService.class.getName()).log(Level.SEVERE, null, ex);
             }
            
         }

        }
     return inf;

    }
    
    private HashMap<String,String> convertStringtoMap(String s){
         HashMap<String,String> temp=new HashMap<String,String>();
         Matcher m = Pattern.compile("\\((.*?)\\)").matcher(s);
         while(m.find()) {
            String [] str=m.group(1).split(":",2);
            temp.put(str[0],str[1]);
        }
        return temp;
    }
    
    private void clearContents(){
        File file=new File("foo.txt");
        try{
        PrintWriter writer = new PrintWriter(file);
        writer.print("");
        writer.close();
        }catch(FileNotFoundException e) {
        System.out.println(e);
    }
    }
    
    public void setInfoObject(ArrayList<info> inf){ 
        clearContents();
        try {
            PrintStream pr = new PrintStream(new FileOutputStream("foo.txt"));
            for(info temp:inf){
                String str;
                
                str=temp.getFirstName();
                str="(firstName:"+str+")";
                pr.print(str);
                
                str=temp.getLastName();
                str="(lastName:"+str+")";
                pr.print(str);

                str=temp.getAddressLine1();
                str="(addressLine1:"+str+")";
                pr.print(str);
                
                str=temp.getAddressLine2();
                str="(addressLine2:"+str+")";
                pr.print(str);
                
                str=temp.getMiddleInitial();
                str="(middleInitial:"+str+")";
                pr.print(str);
                
                str=temp.getCity();
                str="(city:"+str+")";
                pr.print(str);
                
                str=temp.getState();
                str="(state:"+str+")";
                pr.print(str);
                
                str=String.valueOf(temp.getZipCode());
                str="(zipCode:"+str+")";
                pr.print(str);
                
                str=String.valueOf(temp.getPhoneNumber());
                str="(phoneNumber:"+str+")";
                pr.print(str);
                
                str=temp.getEmailAddress();
                str="(emailAddress:"+str+")";
                pr.print(str);
                
                str=String.valueOf(temp.getProofOfPurchase());
                str="(proofOfPurchase:"+str+")";
                pr.print(str);
                
                str=temp.getDateReceived().toString();
                str="(dateReceived:"+str+")";
                pr.print(str);
                
                pr.println("");
            }

        } catch(Exception ex) {
        ex.printStackTrace();
        }
    }
    
    
}
