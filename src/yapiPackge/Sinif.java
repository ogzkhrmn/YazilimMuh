package yapiPackge;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Sinif {
	private int id;
	private int subeId;
	private String sinifAdi;
	
	public Sinif(int id, int subeId, String sinifAdi) {
		super();
		this.id = id;
		this.subeId = subeId;
		this.sinifAdi = sinifAdi;
	}
	public Sinif( int subeId, String sinifAdi) {
		super();
		this.subeId = subeId;
		this.sinifAdi = sinifAdi;
	}
	public int getId() {
		return id;
	}

	public int getSubeId() {
		return subeId;
	}

	public String getSinifAdi() {
		return sinifAdi;
	}
	public static ArrayList<Sinif> sinifListesiniGetir(){
		ArrayList<Sinif> list = new ArrayList<Sinif>();
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver"); 
			con = Connections.getDatabaseConnectionPath();
			String query = "Select * from Sinif";
			Statement st = con.prepareStatement(query);
			ResultSet set = st.executeQuery(query);
			while(set.next()){
				list.add(new Sinif(set.getInt(1),set.getInt(2),set.getString(3)));
			}
	        st.clearBatch();
	        
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
		return list;
	}
	public static ArrayList<Sinif> sinifListesiniGetir(int subeid){
		ArrayList<Sinif> list = new ArrayList<Sinif>();
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver"); 
			con = Connections.getDatabaseConnectionPath();
			String query = "Select * from Sinif where subeId = "+subeid;
			Statement st = con.prepareStatement(query);
			ResultSet set = st.executeQuery(query);
			while(set.next()){
				list.add(new Sinif(set.getInt(1),set.getInt(2),set.getString(3)));
			}
	        st.clearBatch();
	        
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
		return list;
	}
	
	public static int sinifinSubesiniBul(Sinif sinif){
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver"); 
			con = Connections.getDatabaseConnectionPath();
			String query = "Select SubeId from Sinif where sinifId = "+sinif.getId();
			Statement st = con.prepareStatement(query);
			ResultSet set = st.executeQuery(query);
			if(set.next()){
				return set.getInt("SubeId");
			}
	        st.clearBatch();
	        
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
		return -1;
	}
	public static void sinifEkle(Sinif sinif){
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver"); 
			con = Connections.getDatabaseConnectionPath();
			String query = "insert into Sinif (SubeId,SinifAdi) VALUES(?,?)";
			PreparedStatement st = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, sinif.getSubeId());
			st.setString(2, sinif.getSinifAdi());
			st.executeUpdate();
	        st.clearBatch();
	        
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
