//ExifProcessor
//Andrew J. Holt
//2015

//This program will iterate through a user-defined target directory, searching for appropriate
//files, tokenize the text content of local files, search for, and printstream a stage  
//position's X and Y values with text processing to match a standard registered file's output.

//IMPORTANT: X and Y values are stored in original image based on a micron scale, you must convert
//them to pixel values to utilize ImageJ's stiching capabilities correctly.

import java.io.*;
import java.util.*;

public class Metadata_Image_Parse{
   public static void main(String[] args)throws FileNotFoundException, IOException {
    Double conversionFactor = (1/350.0);
//Prompt User for the target directory containing desired TIF Images
      System.out.println("Enter the path to your first file. Example: Right click on your file --> Properties --> Location");
      Scanner console = new Scanner(System.in);
      String pathName = console.next();
  
//Create folder content, create matrix to collect file names, create output file, initialize Printstream Name + Directory  
      File folder = new File(pathName);  
      File [] targetFolder = folder.listFiles();
      File outPut = new File(pathName+"\\ExifOutput.txt");
      PrintStream  print = new PrintStream(outPut);
    
//Create for loop to begin iterating through target folder from user 
      for(int a = 0; a < targetFolder.length; a++){
//Check if directory contains files, save file name as "getName" variable    
         if(targetFolder[a].isFile()){
            String getName = targetFolder[a].getName();
//Boolean check if getName file is TIF or TXT, if TXT continue processing
//If TIF, continue checking     
           if(getName.contains("txt")){
            FileReader fr = new FileReader(targetFolder[a]);            
            BufferedReader br = new BufferedReader(fr);    
            String data;                 
//While there is a line to read, do                   
               while((data = br.readLine()) != null){
//If the file contains "MetaData", begin to tokenize string, split where "<" present              
                   if(data.contains("MetaData")){               
                     String [] tokenMake = data.split("<");
//For loop initialized to length of "tokenMake"                  
                        for (int i=0; i < tokenMake.length; i++){
//If "stage position x" present, tokenize with splits after "\", printstream and replace .txt with .tif                    
                              if(tokenMake[i].contains("stage-position-x")){
                                 String[] positionX = tokenMake[i].split("\"");
                                 String xRaw = positionX[5].replace("-", "");
                                 Double X = (Double.valueOf(xRaw))*conversionFactor;
                                 print.print(getName.replace(".txt", "")+ ".tif; ; ("+ X +", ");  
                              }
//If "stage position y" present, tokenize with split "\"
//printstream y position to same line as x, then set a new line                        
                             else if(tokenMake[i].contains("stage-position-y")){
                                  if(tokenMake[i].contains("stage-position-y")){
                                     String[] positionY = tokenMake[i].split("\"");
                                     Double Y = Double.valueOf(positionY[5])*conversionFactor;
                                     print.print((Y+")"));
                                     print.println();
                                  }
                             }
                        }//close(for i)
                     }//close(if metadata)
//If no file found, then close program               
                   else{
                        System.out.println();
                  }
               }//close(while br.readLine)       
           }//close(if getName)
         }//close(if.isFolder)
      }//close(for int a)
    } 
}
