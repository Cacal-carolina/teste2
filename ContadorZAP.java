import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
 
public class ContadorZAP
{
    public static void main(String[] args) throws SQLException 
    {
        String filePath = "C:\\Users\\Carolina Oliveira\\OneDrive\\Área de Trabalho\\ux\\dados8.txt";
 
       // System.out.println( usingBufferedReader( filePath ) );
        ArrayList<String> Preposicao = new ArrayList<String>();

        // Populate the ArrayList
            Preposicao.add("a");
            Preposicao.add("de");
            Preposicao.add("para");
            Preposicao.add("o");
            Preposicao.add("em");
            Preposicao.add("que");
            Preposicao.add("quando");
            Preposicao.add("embora");
            Preposicao.add("e");
            Preposicao.add("onde");
            Preposicao.add("que");
            Preposicao.add("do");
            Preposicao.add("por");
            Preposicao.add("dos");
            Preposicao.add("os");
            Preposicao.add("no");
            Preposicao.add("num");
            Preposicao.add("as");
            Preposicao.add("por");
            Preposicao.add("ou");
            Preposicao.add("com");
            
            try {

                Class.forName("oracle.jdbc.driver.OracleDriver");

            } catch (ClassNotFoundException e) {

                System.out.println("Where is your Oracle JDBC Driver?");
                e.printStackTrace();
                return;

            }

            System.out.println("Oracle JDBC Driver Registered! alterei");

            Connection connection = null;
        	Connection conn =
     			   DriverManager.getConnection ("jdbc:oracle:thin:@localHost:1521:XE", "system", "teste");

     	 
     	   Statement stmt = conn.createStatement ();
     	   
        String input=usingBufferedReader( filePath ) ;  //Input String 
        String[] words=input.split(".");  //Split the word from String
        int wrc=1;    //Variable for getting Repeated word count
        
        for(int i=0;i<words.length;i++) //Outer loop for Comparison       
        {
            String query = "INSERT INTO Sentencas(Sentenca)VALUES (?)";
	         PreparedStatement preparedStmt = conn.prepareStatement(query);
	         preparedStmt.setString (1,words[i]);
             preparedStmt.executeUpdate();
	         
           
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