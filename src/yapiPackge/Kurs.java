package yapiPackge;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

public class Kurs {
	private int id;
	private String kursadi;
	private int kursucreti;
	public Kurs(int id, String kursadi, int kursucreti) {
		super();
		this.id = id;
		this.kursadi = kursadi;
		this.kursucreti = kursucreti;
	}
	public int getId() {
		return id;
	}
	public String getKursadi() {
		return kursadi;
	}
	public int getKursucreti() {
		return kursucreti;
	}
	public static Kurs kursGetir(int id){
		Connection con = null;
		Kurs kurs = null;
		try{
			Class.forName("com.mysql.jdbc.Driver"); 
			con = Connections.getDatabaseConnectionPath();
			String query = "Select * from Kurs where id = "+id;
			Statement st = (Statement) con.createStatement();
			ResultSet rs = st.executeQuery(query);
			if(rs.next()){
				kurs = new Kurs(rs.getInt(1),rs.getString(2),rs.getInt(3));
			}
			
		}
		catch(ClassNotFoundException | SQLException e){
			e.getMessage();
		}
		finally{
			try {
				con.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return kurs;
	}
	public static ArrayList<Kurs> kursListesiGetir(){
		Connection con = null;
		ArrayList<Kurs> lst = new ArrayList<Kurs>();
		try{
			Class.forName("com.mysql.jdbc.Driver"); 
			con = Connections.getDatabaseConnectionPath();
			String query = "Select * from Kurs";
			Statement st = (Statement) con.createStatement();
			
			ResultSet rs = st.executeQuery(query);
			while(rs.next()){
				lst.add(new Kurs(rs.getInt(1),rs.getString(2),rs.getInt(3)));
			}
			
		}
		catch(ClassNotFoundException | SQLException e){
			e.getMessage();
		}
		finally{
			try {
				con.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return lst;
	}
	public static void kursEkle(Kurs kurs){
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver"); 
			con = Connections.getDatabaseConnectionPath();
			String query = "insert into Kurs(kursadi,kursucreti) VALUES(?,?)";
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1,kurs.getKursadi() );
			st.setInt(2,kurs.getKursucreti());
			st.executeQuery();
			
		}
		catch(ClassNotFoundException | SQLException e){
			e.getMessage();
		}
		finally{
			try {
				con.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	public static void kursEkle(String kursadi,int kursucreti){
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver"); 
			con = Connections.getDatabaseConnectionPath();
			String query = "insert into Kurs(kursadi,kursucreti) VALUES(?,?)";
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1,kursadi);
			st.setInt(2,kursucreti);
			st.executeUpdate();
			
		}
		catch(ClassNotFoundException | SQLException e){
			e.printStackTrace();
		}
		finally{
			try {
				con.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	public String toString(){
		StringBuilder bl = new StringBuilder();
		bl.append("<br> Kurs Adı : ");
		bl.append(getKursadi());
		bl.append("<br> Kurs Ucreti : ");
		bl.append(getKursucreti());
		bl.append("<br>");
		return bl.toString();
	}
}
