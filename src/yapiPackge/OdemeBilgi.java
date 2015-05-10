package yapiPackge;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class OdemeBilgi {
	int id;
	String odemeTanim;
	public OdemeBilgi(int id, String odemeTanim) {
		super();
		this.id = id;
		this.odemeTanim = odemeTanim;
	}
	public int getId() {
		return id;
	}
	public String getOdemeTanim() {
		return odemeTanim;
	}
	public static ArrayList<OdemeBilgi> odemeBilgileriniGetir(){
		ArrayList<OdemeBilgi> list = new ArrayList<OdemeBilgi>();
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver"); 
			con = Connections.getDatabaseConnectionPath();
			String query = "select * from OdemeBilgi ";
			Statement st = con.createStatement();
			ResultSet rs=st.executeQuery(query);
			while(rs.next()) { 
				list.add(new OdemeBilgi(rs.getInt(1), rs.getString(2)));
			} 
		}catch(ClassNotFoundException ex){
			ex.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				con.close();
				
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
		return list;
	}
	
	
	public int ucretdondur(int kursid){
		Connection con = null;
		int ucret=0;
		try{
			Class.forName("com.mysql.jdbc.Driver"); 
			con = Connections.getDatabaseConnectionPath();
			String query = "select kursUcreti from Kurs where id= "+kursid;
			Statement st = con.createStatement();
			ResultSet rs=st.executeQuery(query);
			ucret=rs.getInt(1);
		}catch(ClassNotFoundException ex){
			ex.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				con.close();
				
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return ucret;
	}
}
