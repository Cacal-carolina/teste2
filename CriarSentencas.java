

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class CriarSentencas
{
	public String Criar (String Sentenca) throws SQLException 
    {
        
	String filePath = "C:\\Users\\Carolina Oliveira\\OneDrive\\Área de Trabalho\\ux\\dados10.txt";
	 
   
         
         try {

             Class.forName("oracle.jdbc.driver.OracleDriver");

         } catch (ClassNotFoundException e) {

             System.out.println("Where is your Oracle JDBC Driver?");
             e.printStackTrace();
           
         }

         System.out.println("Oracle JDBC Driver Registered!");

         Connection connection = null;
     	Connection conn =
  			   DriverManager.getConnection ("jdbc:oracle:thin:@localHost:1521:XE", "system", "teste");

  	 
  	   Statement stmt = conn.createStatement ();
  	  // System.out.println(" filePath " +  filePath);   
  //   String input=usingBufferedReader( filePath );  //Input String 
  //  System.out.println("INPUT  " + input);
   
      String[] sentenca = Sentenca.split("\\.|\\?|\\!");  //Split the word from String
     
     System.out.print("fui chamado");
     System.out.println("words  " + sentenca[0]);
     for(int i=0;i<sentenca.length;i++) //Outer loop for Comparison       
     {  
     
    	        int total=0;
     	         String query = "INSERT INTO Sentencas(Sentenca,total)VALUES (?, ?)";
     	         PreparedStatement preparedStmt = conn.prepareStatement(query);
     	         preparedStmt.setString (1,sentenca[i]);
     	         
     	        String query2 = " Select Palavra, TOTAL FROM PALAVRASMAISUSADAS";
     			  ResultSet rset = stmt.executeQuery (query2);
     			  
     				
     			   while (rset.next ())
     	    	   { 
     				  
     	    		   String palavramaisusada = rset.getString ("Palavra");
     	    		   int frequencia=rset.getInt("TOTAL");
     	    		   String sent = sentenca[i];
     	    		 // boolean contains = Arrays.stream(sent).anyMatch(palavramaisusada ::equals);
     	    		 boolean contains= sent.contains(palavramaisusada);
     	  		//	System.out.println("o valor de contain" + contains + "a palavra mais usada é  " + palavramaisusada );
     	  		//	System.out.println("e a sentença é " + sentenca [i] );
     	  			if (contains==true)
     	    		   {
     	    			// System.out.println("adicionar valor de total de palavra mais usada, a total de sentenca");
     	   	    	     total=total+frequencia;
     	   	   	//  System.out.println(total + "eu não chego aqui");
     	    		   }
     	    
     	    	   }
     		
     	          preparedStmt.setInt (2,total);
                  preparedStmt.executeUpdate();
     	         
     		   
      }   
     String Sentencamaisusada ;
     String Sentcamaisusada =  " ";
	
     String query3 =  " Select   (Sentenca) FROM Sentencas  FETCH FIRST 3 ROWS ONLY ";
     ResultSet rset = stmt.executeQuery (query3);   
     while (rset.next ())
	   { 
		  
		  Sentencamaisusada = rset.getString ("Sentenca");
		  Sentcamaisusada = Sentcamaisusada + Sentencamaisusada ; 
	   }   
     // Close the Statement
	   stmt.close();

	   // Close the connection
	   conn.close(); 
	return  Sentcamaisusada;   
 }


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
    //    System.out.println(contentBuilder.toString());
        return contentBuilder.toString();
    }
}
	
	

