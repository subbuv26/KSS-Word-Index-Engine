//////////////////////////////////////////////////////////////////////////////
// Parser function Implementation for KSS Word Index Engine                 //
// Implementation : Parser for the fel                                      //
// Author : V. Subba Reddy                                                  //
// Reg NO : 11MCMT03                                                        //
//////////////////////////////////////////////////////////////////////////////

import java.util.*;
import java.lang.*;
import java.io.*;

public class Parse {
   public Item item;
   public KSSDictionary dictionary;
   
   Parse(KSSDictionary dictionary) {      //constructor
   this.dictionary = dictionary;
   }
   
   
   public int parse(String fileName) {
      item = new Item();
      FileInputStream fis;
      DataInputStream dis;
      InputStreamReader isr;
      BufferedReader br;
      
      String line;
      String[] tokens;
      String delemeter = " ";
      int i = 0, flag = -1;
      
      try {
         fis = new FileInputStream(fileName);
         dis = new DataInputStream(fis);
         isr = new InputStreamReader(dis);
         br = new BufferedReader(isr);
         
         while((line = br.readLine()) != null) {
            if(!line.equals("")) {            //skipping empty lines
               line = line + " \0";
               tokens = line.split(delemeter); //splitting words into tokens
               
               for(i = 0; i < tokens.length; i++) {
                  while(tokens[i].equals(""))
                     i++;
                  tokens[i] = tokens[i].replace(".","");
                  tokens[i] = tokens[i].replace(",","");
                  tokens[i] = tokens[i].replace("?","");
                  tokens[i] = tokens[i].replace("!","");
                  tokens[i] = tokens[i].replace("\'","");
                  tokens[i] = tokens[i].replace("\"","");
                  if(isString(tokens[i]) == true) {
                     item.word = tokens[i];
                     item.frequency = 1;
                     dictionary.addItem(item);
                  //   item.print(item);
                     flag = 0;
                  }
               }
            }
         }
         dis.close();
      }
      
      catch(Exception e) {
         System.out.println("Error: " + e.getMessage());
      }
      return flag;
   }
   
   boolean isString(String str) {
      str = str.toUpperCase();
      int i, c;
      if(str.equals("") == true || str.equals("\0") == true)
         return false;
      for(i = 0; i < str.length(); i++) {
         c = (int) str.charAt(i);
         if(c < 65 || c > 90)
            return false;
      }
      return true;
   }

   //for unit testing
/*   public static void main(String args[]) {
      Parse p = new Parse();
      p.parse(args[0]);
      return;
   } */
}
