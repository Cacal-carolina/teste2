
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

public class CriarParagrafo
{
	public static void main(String[] args) throws SQLException 
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
   
     
     //private static final String PARAGRAPH_SPLIT_REGEX = "(^\\s{4})";
    // String[] paragrafo= input.split("\\r");
     CriarSentencas Sentencas=new CriarSentencas();
     String[] paragraphs = input.split("\\n\\n");
     System.out.println(paragraphs.length);
     for (int i = 0; i < paragraphs.length; i++) {
         System.out.println("Paragraph "+ i + paragraphs[i]);
         // criar sentenca com a maior frequencia para cada paragrafo. 
         String Sentencamaisusada =  Sentencas.Criar(paragraphs[i]);
         
       //  System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxx" + Sentencamaisusada);
      }
     System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxx" + Sentencamaisusada);
    }         
        
   
     // Close the Statement
	


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
	
	

