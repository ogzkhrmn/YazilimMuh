/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yapiPackge;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


/**
 *
 * @author OguzHero
 */
public class Sube {
    private int id;
    private String SubeAdi,Adres,TopluTasima,OzelOtomobil,SosyalOlanak;
    
    public Sube( String subeAdi, String adres, String topluTasima,
			String ozelOtomobil, String sosyalOlanak) {
		super();
		SubeAdi = subeAdi;
		Adres = adres;
		TopluTasima = topluTasima;
		OzelOtomobil = ozelOtomobil;
		SosyalOlanak = sosyalOlanak;
	}
    public Sube(int id, String subeAdi, String adres, String topluTasima,
			String ozelOtomobil, String sosyalOlanak) {
		super();
		this.id = id;
		SubeAdi = subeAdi;
		Adres = adres;
		TopluTasima = topluTasima;
		OzelOtomobil = ozelOtomobil;
		SosyalOlanak = sosyalOlanak;
	}

	public int getId() {
		return id;
	}

	public String getSubeAdi() {
		return SubeAdi;
	}

	public String getAdres() {
		return Adres;
	}

	public String getTopluTasima() {
		return TopluTasima;
	}

	public String getOzelOtomobil() {
		return OzelOtomobil;
	}

	public String getSosyalOlanak() {
		return SosyalOlanak;
	}
	public static boolean subeKaydet(Sube sube){
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver"); 
			con = Connections.getDatabaseConnectionPath();
			String query = "insert into Sube (SubeAd,Adres,TopluTasima,OzelOtomobil,SosyalOlanak) VALUES(?,?,?,?,?)";
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1, sube.getAdres());
			st.setString(2, sube.getAdres());
			st.setString(3, sube.getTopluTasima());
			st.setString(4, sube.getOzelOtomobil());
			st.setString(5, sube.getSosyalOlanak());
			st.executeUpdate();
			return true;
		}catch(ClassNotFoundException ex){
			ex.printStackTrace();
			return false;
		} 
		catch(Exception e){
			e.printStackTrace();
			return false;
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
	public static ArrayList<Sube> getSubeListesi(){
		Connection con = null;
		ArrayList<Sube> listSube = new ArrayList<Sube>();;
		try{
			Class.forName("com.mysql.jdbc.Driver"); 
			con = Connections.getDatabaseConnectionPath();
			String query = "select * from Sube";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next()){
				listSube.add( new Sube(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
			}
		}catch(ClassNotFoundException | SQLException e){
			e.getMessage();
		}
		finally{
			try {
				con.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return listSube;
	}
	public static Sube getSube(int subeid){
		Connection con = null;
		Sube sube = null;
		try{
			Class.forName("com.mysql.jdbc.Driver"); 
			con = Connections.getDatabaseConnectionPath();
			String query = "select * from Sube where id = "+subeid;
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
				if(rs.next()){
					sube = new Sube(subeid, rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
				}
		}catch(ClassNotFoundException | SQLException e){
			e.getMessage();
		}
		finally{
			try {
				con.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return sube;
	}
    public String toString(){
    	StringBuilder bl = new StringBuilder();
    	bl.append("<br>");
    	bl.append("Sube Adi : ");
    	bl.append(getSubeAdi());
    	bl.append("<hr>");
    	bl.append("Adresi : ");
    	bl.append(getAdres());
    	bl.append("<br>");
    	bl.append("Toplu Tasima Olanaklari : ");
    	bl.append(getTopluTasima());
    	bl.append("<br>");
    	bl.append("Ozel Otomobil Olanaklari : ");
    	bl.append(getOzelOtomobil());
    	bl.append("<br>");
    	bl.append("Sosyal Olanaklarimiz : ");
    	bl.append(getSosyalOlanak());
    	bl.append("<br>");
    	return bl.toString();
    }
    
}

