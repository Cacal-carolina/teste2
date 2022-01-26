import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
 
public class CarregarPalavrasNaoUsadas
{
    public static void main(String[] args) throws SQLException 
    {
        String filePath = "C:\\Users\\Carolina Oliveira\\OneDrive\\Área de Trabalho\\ux\\dados6.txt";
 
    
            
            try {

                Class.forName("oracle.jdbc.driver.OracleDriver");

            } catch (ClassNotFoundException e) {

                System.out.println("Where is your Oracle JDBC Driver?");
                e.printStackTrace();
                return;

            }

            System.out.println("Oracle JDBC Driver Registered!");

            Connection connection = null;
        	Connection conn =
     			   DriverManager.getConnection ("jdbc:oracle:thin:@localHost:1521:XE", "system", "teste");

     	 
     	   Statement stmt = conn.createStatement ();
            
        String input=usingBufferedReader( filePath ) ;  //Input String 
        String[] words=input.split(" ");  //Split the word from String
        int wrc=1;    //Variable for getting Repeated word count
      
       
        for(int i=0;i<words.length;i++) //Outer loop for Comparison       
        {  
        	//System.out.println(" QUANTAS VEZES VC ENTROU AQUi  a palavra é " + words[i]);
           for(int j=i+1;j<words.length;j++) //Inner loop for Comparison
           {
              
           if(words[i].equals(words[j]))  //Checking for both strings are equal
              {
                 wrc=wrc+1;    //if equal increment the count
                 words[j]="0"; //Replace repeated words by zero
              }
           }
         
         

        
        	   
        	     //System.out.println("quantas vezes eu entro aqui antes do if  " + i);
        	   if((words[i]!="0"))
        	   {
                   System.out.println(words[i]+"--"+wrc); //Printing the word along with count
                 //  System.out.println("quantas vezes eu entro aqui  " + i);
            	   String word1= "";
        	   
        	  
        		      System.out.println("A palavra  " + words[i]  );
                     String query = "INSERT INTO  CARREGARPALAVRASNAOUSADAS(PALAVRA)VALUES (?)";
        	         PreparedStatement preparedStmt = conn.prepareStatement(query);
        	         preparedStmt.setString (1,words[i]);
        	         
        	         preparedStmt.executeUpdate();
        	         
        		   
        	 
               }
               wrc=1;
           
          }  
        // Close the Statement
 	   stmt.close();

 	   // Close the connection
 	   conn.close(); 
    }
 
    //Read file content into the string with - using BufferedReader and FileReader
    //You can use this if you are still not using Java 8
 
    private static String usingBufferedReader(String filePath) 
    {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) 
        {
 
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) 
            {
                contentBuilder.append(sCurrentLine).append("\n");
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }
}