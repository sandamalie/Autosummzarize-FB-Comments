/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textsummariationapp;

import java.util.ArrayList;

public class  wlist
{

 private String word;
 private double cnt;
 private double scnt;
 private double wght; 
 
 private ArrayList spl;
 private ArrayList wpl;
 
 public wlist (String wd) {
    word= new String(wd);
    spl=new ArrayList();
    wpl=new ArrayList();
    cnt=0;scnt=0;
 }  

 public  void incrcount(int sp,int wp) { //To increase the count of the Sentences
   cnt++; //Increase the count value
   sentensepos(sp);
   wordpos(wp);
  } 

 public double getcount() 
         
 {  
     
     return cnt; 
 
 }
 public String getword() 
 {   
     
     return word; 
 
 }
 
 public void sentensepos(int sp) 
 
 { 
 if(! spl.contains(sp+""))  //checkes if the "Array List" contains the senetence position no - By creating new ArrayList
     scnt++; //then increment the sentence count value
  spl.add(sp+""); //Add to the arralist using Add() method
     
 }

 public void wordpos(int wp)  
 
 {  
     
     wpl.add(wp+""); //Add to the word position to the arralist using Add() method
     
 
 }
 public ArrayList getwordpos() //To get the Word position
 
 { return wpl; 
 
 };
 
 public ArrayList getsentensepos()  //To get the Sentence position
 { 
     return spl; 
 }  
 
 public void   weight(double wg) 
 { 
     wght=wg;
 }
 
 public double weight()
 { 
     return wght; 
 }
 
 public double sentensecount() 
 { 
     return scnt; 
 }
 

}