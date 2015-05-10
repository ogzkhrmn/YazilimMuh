package yapiPackge;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class Ciktilar {
	public static void Yazdir(String mesaj){
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver"); 
			con = Connections.getDatabaseConnectionPath();
			String query = "insert into Ciktilar (Log,Tarih) VALUES(?,?)";
			PreparedStatement st = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			st.setString(1, mesaj);
			st.setDate(2, new Date(new java.util.Date().getTime()));
			st.executeQuery();
		}catch(ClassNotFoundException ex){
			ex.printStackTrace();
			
		} 
		catch(Exception e){
			e.printStackTrace();
			
		}
		finally{
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
	}
}
