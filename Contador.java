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
 
public class Contador
{
    public static void main(String[] args) throws SQLException 
    {
        String filePath = "C:\\\\Users\\\\Carolina Oliveira\\\\OneDrive\\\\Área de Trabalho\\\\ux\\\\dados.txt";
        
 
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

            System.out.println("Oracle JDBC Driver Registered!");

            Connection connection = null;
        	Connection conn =
     			   DriverManager.getConnection ("jdbc:oracle:thin:@localHost:1521:XE", "system", "teste");

     	 
     	   Statement stmt = conn.createStatement ();
     	  System.out.println("n abri o arquivo");
        String input=usingBufferedReader( filePath ) ;  //Input String 
        System.out.println("abri o arquivo");
        String[] words=input.split(" ");  //Split the word from String
        int wrc=1;    //Variable for getting Repeated word count
        String query2 = " Select NAOCONTAVEIS FROM PALAVRAS";
       
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
         
           ResultSet rset = stmt.executeQuery (query2);

        	   boolean inserir = true;
        	   // Iterate through the result and print the employee names
        	   String pala=words[i];
        	   TestePalavaraNaoUsada Palavra = new TestePalavaraNaoUsada();
        	 inserir= Palavra.testar(pala, inserir);
        	     //System.out.println("quantas vezes eu entro aqui antes do if  " + i);
        	   if(((words[i]!="0"&& (wrc>8)))&&(Preposicao.contains(words[i])!=true)) 
        	   {
                  // System.out.println(words[i]+"--"+wrc); //Printing the word along with count
                 //  System.out.println("quantas vezes eu entro aqui  " + i);
            	   String word1= "";
            	   String cat= "zap";
        	   
        	   if (inserir==true)
        	   {
        		  // System.out.println(words[i]+"-eu posso inserir-"+wrc);   
        		   //System.out.println("A palavra  " + words[i] + "  é diferente da palavra  " + palavranaousada + "   então posso inserir. ");
                     String query = "INSERT INTO PALAVRASMAISUSADAS(PALAVRA,CATEGORIA, TOTAL)VALUES (?, ?, ?)";
        	         PreparedStatement preparedStmt = conn.prepareStatement(query);
        	         preparedStmt.setString (1,words[i]);
        	         preparedStmt.setString (2,cat);
        	         preparedStmt.setInt (3, wrc);
        	       
        	         preparedStmt.executeUpdate();
        	         
        		   
        	   }
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