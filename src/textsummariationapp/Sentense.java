/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textsummariationapp;

import java.io.*;
import java.util.StringTokenizer;
import java.util.AbstractList;

public class Sentense
{


String doc="";

public Sentense(File fp) 
 {     
    int sz; byte bt[];  
    try { 			            
    FileInputStream fis=new FileInputStream(fp);
    sz=(int)fp.length();	
    bt=new byte[sz];
    fis.read(bt);            
    this.doc=new String(bt);    	
    } 
   catch(IOException ex){   System.out.println(ex);}
 }


public Sentense(String  content) 
{
  doc=new String(content);   	
  doc=doc.replaceAll("\r\n"," ");//Replaces each substring of this string that matches the given regular expression with the given replacement
                                // \r\n is for windows format used for line separator
}


public AbstractList  separatesentense(AbstractList als)  //This method used for summary.java
{  
    int fs1=0,fs2=0;    	  
    int nx=0;
	
    while (  nx < (doc.length()-1) )
    {
        nx=doc.indexOf(".",fs2);
	if(nx==-1)	 	
	  break;						
	else if( nx==doc.lastIndexOf(".") )
	 {
	   String str=(doc.substring(fs1,nx+1)).toLowerCase();  
	   als.add(new slist(str));		
	   fs2=nx+1;  
	   fs1=fs2;	  
           break;
	 }	
        else if( doc.charAt(nx+1) ==' ' || doc.charAt(nx+1) =='\r' || doc.charAt(nx+1) =='\n') // \r - Carriage retrun(Make the cursor go to the left edge of the screen)
         {		        	     				
            String str=(doc.substring(fs1,nx+1).toLowerCase()).trim();		    
	    als.add(new slist(str));			
            fs2=nx+1; 	            
            fs1=fs2;  			
         } 	        
	else	  
	 fs2=nx+1;  	   	  		                   
     }

     return als;	
}


public void  separatesentense1()
{  
    int fs1=0,fs2=0;  String tokens[];    	  
    int cnt=0,nx=0;	
    while ( nx != -1  )
    {
        nx=doc.indexOf(".",fs2);		
        if(doc.charAt(nx+1) ==' ')
         {	    	     	

            System.out.println( cnt+ " "+doc.substring(fs1,nx) );	 
            fs2=nx+1; 	            
            fs1=fs2;  	
            cnt++;
         } 	
         else if( nx== -1 )
          {
            System.out.println( cnt+ " "+doc.substring(fs1,doc.length()-1));	 		
            break;	
          }	
          fs2=nx+1;          
     }
    
}


public static void main(String str[]) throws IOException
 {
   Sentense sc=new Sentense(new File("C:\\Users\\ADMIN\\Desktop\\TextSummariationApp\\Postcomments.txt"));
   sc.separatesentense1( );		 
 }


}
