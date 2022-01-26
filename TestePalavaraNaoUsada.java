import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestePalavaraNaoUsada {

	public boolean testar( String palavra, boolean inserir) throws SQLException
	
	{    
		
		   try {

               Class.forName("oracle.jdbc.driver.OracleDriver");

           } catch (ClassNotFoundException e) {

               System.out.println("Where is your Oracle JDBC Driver?");
               e.printStackTrace();
               

           }

          // System.out.println("Oracle JDBC Driver Registered!");

           Connection connection = null;
       	Connection conn = null;
		try {
			conn = DriverManager.getConnection ("jdbc:oracle:thin:@localHost:1521:XE", "system", "teste");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	 
    	   Statement stmt = conn.createStatement ();
		  String query2 = " Select NAOCONTAVEIS FROM PALAVRAS";
		  ResultSet rset = stmt.executeQuery (query2);
		  
			
		   while (rset.next ())
    	   { 
			   System.out.println("a palavra " + palavra + " é igual a palavra que nao pode ser usada antes do if " );
	    		 
    		   String palavranaousada = rset.getString ("NAOCONTAVEIS");
  			 System.out.println("a palavra " + palavra + " é igual a palavra que nao pode ser usada antes do if " + palavranaousada);
    		 
    		   if ((palavra.equalsIgnoreCase(palavranaousada)) &&(inserir==true))
    		   {
    			 System.out.println("a palavra " + palavra + " é igual a palavra que nao pode ser usada " + palavranaousada);
   	    	      inserir=false;
   	        
    		   }
    
    	   }
		   
		  
		   stmt.close();

	 	   // Close the connection
	 	   conn.close();  
	 	  return inserir;
	}
	
	
}
