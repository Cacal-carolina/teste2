
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class CriarSentenca
{
	public static void main(String[] args) throws SQLException, FileNotFoundException 
    {
        
	String filePath = "C:\\Users\\Carolina Oliveira\\OneDrive\\Área de Trabalho\\ux\\dados10.txt";
	 
   
         
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
  	   System.out.println(" filePath " +  filePath);   
     String input=usingBufferedReader( filePath );  //Input String 
  //  System.out.println("INPUT  " + input);
     String[] paragraphs = input.split("\\n\\n");
     System.out.println(paragraphs.length);
     int linha=0;
     for (int i = 0; i < paragraphs.length; i++) {
    
     String[] sentenca=paragraphs[i].split("\\.|\\?|\\!");  //Split the word from String
     
     System.out.println("words  " + sentenca[0]);
     for(int j=0;j<sentenca.length;j++) //Outer loop for Comparison       
     {  
     
    	        int total=0;
     	         String query = "INSERT INTO Sentencas(SENTENCA,TOTAL, PARAGRAFO, LINHA,CATEGORIA)VALUES (?, ?, ?,?,?)";
     	         PreparedStatement preparedStmt = conn.prepareStatement(query);
     	         preparedStmt.setString (1,sentenca[j]);
     	         
     	        String query2 = " Select Palavra, TOTAL FROM PALAVRASMAISUSADAS";
     			  ResultSet rset = stmt.executeQuery (query2);
     			  
     				linha++;
     			   while (rset.next ())
     	    	   { 
     				  
     	    		   String palavramaisusada = rset.getString ("Palavra");
     	    		   int frequencia=rset.getInt("TOTAL");
     	    		   String sent = sentenca[j];
     	    		 // boolean contains = Arrays.stream(sent).anyMatch(palavramaisusada ::equals);
     	    		 boolean contains= sent.contains(palavramaisusada);
     	  			System.out.println("o valor de contain" + contains + "a palavra mais usada é  " + palavramaisusada );
     	  			System.out.println("e a sentença é " + sentenca [j] );
     	  			if (contains==true)
     	    		   {
     	    			 System.out.println("adicionar valor de total de palavra mais usada, a total de sentenca");
     	   	    	     total=total+frequencia;
     	   	   	  System.out.println(total + "eu não chego aqui");
     	    		   }
     	    
     	    	   }
     		
     	          preparedStmt.setInt (2,total);
     	         preparedStmt.setInt (3,i);
     	        preparedStmt.setInt (4, linha);
     	        String CATEG="N CATEGORIZADO";
     	       preparedStmt.setString (5, CATEG);
                  preparedStmt.executeUpdate();
     	         
     		   
      }
	
		   
}
        /*
     String TextoSumarizado = " Select  SENTENCA, PARAGRAFO, TOTAL ,LINHA  FROM Sentencas\r\n"
     		+ "WHERE \r\n"
     		+ "(PARAGRAFO, TOTAL)\r\n"
     		+ "IN \r\n"
     		+ "(SELECT PARAGRAFO, MAX(TOTAL)\r\n"
     		+ "FROM\r\n"
     		+ "Sentencas\r\n"
     		+ "group by PARAGRAFO)\r\n"
     		+ "order by PARAGRAFO, LINHA";
	  ResultSet rset = stmt.executeQuery (TextoSumarizado);
	  System.out.println("--------------Texto Sumarizado-------------------");
	  String textoSumarizadoArquivo= "";
	   while (rset.next ())
 	   { 
			  String TextoSum=rset.getString("SENTENCA");
			  textoSumarizadoArquivo= textoSumarizadoArquivo + TextoSum + "\n";
	  System.out.println(TextoSum);
 	   }
	   
	   try (PrintStream out = new PrintStream(new FileOutputStream("C:\\\\Users\\\\Carolina Oliveira\\\\OneDrive\\\\Área de Trabalho\\\\ux\\\\dados10Sumarizado.txt"))) {
		    out.print(textoSumarizadoArquivo);
		}*/
     // Close the Statement
	   stmt.close();

	   // Close the connection
	   conn.close(); 
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
	
	

