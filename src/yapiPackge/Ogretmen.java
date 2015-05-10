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
import java.util.Date;

/**
 *
 * @author OguzHero
 */
public class Ogretmen {
     private String isim,soyisim,ev,cep;
     private Date iseBasladigiTarih;
     private int dersVerebilecegiSubeId;
     private int dersVerebilecegiKursId;
	 public Ogretmen(String isim, String soyisim, String ev, String cep,
			Date iseBasladigiTarih, int dersVerebilecegiSubeId,int dersVerebilecegiKursId) {
		super();
		this.isim = isim;
		this.soyisim = soyisim;
		this.ev = ev;
		this.cep = cep;
		this.iseBasladigiTarih = iseBasladigiTarih;
		this.dersVerebilecegiSubeId = dersVerebilecegiSubeId;
		this.dersVerebilecegiKursId =dersVerebilecegiKursId;
	}
	
    public static int ogretmenEkle(Ogretmen ogretmen){
    	Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver"); 
			con = Connections.getDatabaseConnectionPath();
			String query = "insert into Ogretmen (isim,soyisim,evtelefonu,ceptelefonu,subeid,kursid,isebasladigitarih) VALUES(?,?,?,?,?,?,?)";
			PreparedStatement st = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			st.setString(1, ogretmen.getIsim());
			st.setString(2, ogretmen.getSoyisim());
			st.setString(3, ogretmen.getEv());
			st.setString(4, ogretmen.getCep());
			st.setInt(5, ogretmen.getDersVerebilecegiSubeId());
			java.sql.Date sqldate = new java.sql.Date(ogretmen.getIseBasladigiTarih().getTime());
			st.setInt(6, ogretmen.dersVerebilecegiKursId);
			st.setDate(7, sqldate);
			long num = st.executeUpdate();
			ResultSet generatedKeys = st.getGeneratedKeys(); 
	        if (generatedKeys.next()) {
	            num = generatedKeys.getLong(1);
	        }
	        
			return (int)num;
		}catch(ClassNotFoundException ex){
			ex.printStackTrace();
			return -1;
		} 
		catch(Exception e){
			e.printStackTrace();
			return -1;
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

	public static void ogretmeneKursAta(int ogretmenId,int kursid){
    	Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver"); 
			con = Connections.getDatabaseConnectionPath();
			String query = "Update Ogretmen Set dersverebilecegikursid = ? WHERE id = ? ";
			PreparedStatement st = con.prepareStatement(query);
			st.setInt(1, kursid);
			st.setInt(2,ogretmenId);
			st.executeUpdate();
		
			
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
	public String getIsim() {
		return isim;
	}
	public String getSoyisim() {
		return soyisim;
	}
	public String getEv() {
		return ev;
	}
	public String getCep() {
		return cep;
	}
	public Date getIseBasladigiTarih() {
		return iseBasladigiTarih;
	}
    public int getDersVerebilecegiSubeId() {
		return dersVerebilecegiSubeId;
	}
	public int getDersVerebilecegiKursId() {
		return dersVerebilecegiKursId;
	}
}
