/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textsummariationapp;
public class slist // Java class for Sentence List
{

 String  rsen="",srsen="";
 private double wght;
 public slist(String sen) {
   rsen=new String(sen);
   wght=0.0;	
 }


 public void setrawsentense(String sen) { //Check sentences row by row basics
	rsen=new String(sen);      	
 }
 
 public void setsrsentence(String rsen) {
	srsen=new String(rsen);
 }  

 public String getrawsentense() {
     return rsen;  //return the Sentence with expected
 }   
 public String getsrsentence() {
	return srsen;
 }

 public void   weight(double wg) { wght=wght+wg;} 
 public double weight() { return wght; }  
 

}