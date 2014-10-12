package mago;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import com.mysql.jdbc.PreparedStatement;

public class Main {

	
	
	public static void main(String[] args) throws Exception{
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root",null);
		
		PreparedStatement statement = (PreparedStatement) con.prepareStatement("select * from UTENTI");
		
		ResultSet result = statement.executeQuery();
		
		while(result.next())
			System.out.println(result.getString(1) + " " + result.getString(2) + " "+result.getString(3) + " " + result.getString(4));
		
		String Q1 = "INSERT INTO `test`.`UTENTI`"
					+"(`nome`,`cognome`,`password`)"
					+" VALUES "
					+"('Valentina',"
					+"'Boni',"
					+"'Bella')";
		
		statement.execute(Q1);
		
		
	}
	

}	
	
	
	
	
	
