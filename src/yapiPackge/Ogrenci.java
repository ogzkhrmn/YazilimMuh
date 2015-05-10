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
public class Ogrenci {
    private String isim,soyisim,ev,cep;
    private int id;
  public Ogrenci (String isim,String soyisim, String ev, String cep){
       this.isim=isim;
       this.soyisim=soyisim;
       this.ev=ev;
       this.cep=cep;
   } 
  public Ogrenci (int id,String isim,String soyisim, String ev, String cep){
	  this.id = id;
      this.isim=isim;
      this.soyisim=soyisim;
      this.ev=ev;
      this.cep=cep;
  } 
  
  public static boolean OgrenciEkle(Ogrenci ogrenci,int OdemeId){
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver"); 
			con = Connections.getDatabaseConnectionPath();
			String query = "insert into Ogrenci (isim,soyisim,evTelefonu,cepTelefonu) VALUES(?,?,?,?)";
			PreparedStatement st = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			st.setString(1, ogrenci.getIsim());
			st.setString(2, ogrenci.getSoyisim());
			st.setString(3, ogrenci.getEv());
			st.setString(4, ogrenci.getCep());
			long num = st.executeUpdate();
			ResultSet generatedKeys = st.getGeneratedKeys(); 
	        if (generatedKeys.next()) {
	            num = generatedKeys.getLong(1);
	        }
	        st.clearBatch();
	        query = "insert into OdemeOgrenci(ogrenciId,odemeId) VALUES(?,?)";
	        st = con.prepareStatement(query);
	        st.setLong(1, num);
	        st.setInt(2, OdemeId);
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
public int getId() {
	return id;
}
public static String odemebilgi(int ogrenciid){
            Connection con = null;
             String odemem=null;
            try{
				Class.forName("com.mysql.jdbc.Driver"); 
				con = Connections.getDatabaseConnectionPath();
				String query = "select odemeTanim from Ogrenci o,OdemeOgrenci oo,OdemeBilgisi ob where o.id=oo.ogrenciId and oo.odemeId=ob.id and o.id="+ogrenciid;
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(query);
				if(rs.next()){
					odemem=rs.getString(1);
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
            return odemem;
        }
		public static boolean OgrenciOdemeYap(int ogrenciId,int miktar){
			Connection con = null;
			try{
				Class.forName("com.mysql.jdbc.Driver"); 
				con = Connections.getDatabaseConnectionPath();
				String query = "insert into odenenOdemeler(miktar,tarih,ogrenciId) VALUES(?,?,?)";
				PreparedStatement st = con.prepareStatement(query);
				st.setInt(1, miktar);
				st.setDate(2,new java.sql.Date( new java.util.Date().getTime()));
				st.executeQuery();
				return true;
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
			return false;
		}
    	public static  Ogrenci ogrenciBilgisiGetir(int ogrenciid){
			Ogrenci ogr = null;
			Connection con = null;
			try{
				String query = null;
				Class.forName("com.mysql.jdbc.Driver"); 
				
				con = Connections.getDatabaseConnectionPath();
				if(ogrenciid!=-1)
					query = "select * from Ogrenci where ID = "+ogrenciid;
				else
					query = "select * from Ogrenci ";
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(query);
				if(rs.next()){
					ogr = new Ogrenci(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)); 
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
			return ogr;
			
		}
    	public static  ArrayList<Ogrenci> ogrenciListesiGetir(){
    		ArrayList<Ogrenci> list =  new ArrayList<Ogrenci>();
			Connection con = null;
			try{
				String query = null;
				Class.forName("com.mysql.jdbc.Driver"); 
				
				con = Connections.getDatabaseConnectionPath();
				
					query = "select * from Ogrenci ";
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(query);
				while(rs.next()){
					list.add(new Ogrenci(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5))); 
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
			return list;
			
		}
}
