/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textsummariationapp;
import java.io.*;
import java.util.StringTokenizer;

public class Special 
{       
   
   char spch[];

   public Special()
    {
        int cnt=0,sz=0;char bt[]=null;
       try {
         File fp=new File("C:/Users/Compaq/Desktop/FacebookIntegration/TextSummariationApp/src/textsummariationapp/specials.txt");	
         FileReader fis=new FileReader(fp);
         sz=(int)fp.length();
         bt=new  char[sz];
         fis.read(bt);
         fis.close();	  		
          }
      catch(IOException ex) {}       
      spch=getTokens(new String(bt));		        	
   }


  public char[]  getTokens(String sen) 
      {
	int sz=0,cnt=0;char words[]=null;
	StringTokenizer  stk=new StringTokenizer(sen);
	sz=stk.countTokens();
     	words=new char[sz];	        	
     	while ( stk.hasMoreTokens()) 
	{
           	words[cnt]=new String(stk.nextToken()).charAt(0);	     				
           	cnt++;
       	}		
	return words;	        
    }
  
  public String remove(String sen) 
  {
     String  dsen=new String(sen);
     	
     for (int j=0;j<spch.length; j++) 			
	{

	  char csh[]=dsen.toCharArray();	    
	  dsen="";
	  for(int i=0;i<csh.length;i++ )
	   if(csh[i] != spch[j])	
		dsen=dsen + csh[i];		
	      	
	}
	  	
        return dsen;	         		
    }    

  public static void main(String str[])
   {
	Special sp=new Special();	
        System.out.println(sp.remove("Welcome to facebook Comments Summarization System"));	
   }
   
}