package yapiPackge;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Odeme {
    
    
    public void odemeYap(int miktar,int ogrenciid){
        Connection con = null;
        
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            con = Connections.getDatabaseConnectionPath();
            String query = "insert into odenenOdemeler (odenenMiktar,odenenTarih) VALUES(?,?)";
            PreparedStatement st = con.prepareStatement(query);
            java.util.Date utilDate = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            st.setInt(1, miktar);
            st.setDate(2, sqlDate);
                st.execute();        
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
    public void odemebilgigir(String odemetanim){
        Connection con = null;
        
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            con = Connections.getDatabaseConnectionPath();
            String query = "insert into odemeBilgisi (odemeTanim) VALUES(?)";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, odemetanim);
                st.execute();        
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
    
    public void odemeOgrenciOlustur(int ogrenciId,int odemeId,int odenenOdemeId){
         Connection con = null;
        
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            con = Connections.getDatabaseConnectionPath();
            String query = "insert into odemeOgrenci (ogrenciId,odemeId,odenenOdemeId) VALUES(?,?,?)";
            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, ogrenciId);
            st.setInt(2, odemeId);
            st.setInt(3, odenenOdemeId);
                st.execute();        
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
    
    public void odemetipli(String odemetip,int ogrenciid){
    	 Connection con = null;
    	try{
    		Class.forName("com.mysql.jdbc.Driver"); 
            con = Connections.getDatabaseConnectionPath();
            String query = "insert into odemeOgrenci (ogrenciId,odemeTanim) VALUES(?,?)";
			 PreparedStatement st = con.prepareStatement(query);
			 st.setInt(1,ogrenciid);
			 st.setString(2, odemetip);
			 st.execute(); 
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
    }
    
    public void odemeal(int ogrenciid,int miktar){
    	Connection con = null;
    	try{
    		Class.forName("com.mysql.jdbc.Driver"); 
            con = Connections.getDatabaseConnectionPath();
            java.util.Date utilDate = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            String query = "insert into odenenOdemeler (ogrenciId,odenenMiktar,odenenTarih) VALUES(?,?,?)";
			 PreparedStatement st = con.prepareStatement(query);
			 st.setInt(1,ogrenciid);
			 st.setInt(2, miktar);
			 st.setDate(3,sqlDate);
			 st.execute(); 
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
    }
}
