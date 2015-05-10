package yapiPackge;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Ders {
	
	public ArrayList<Integer> dersolangunler(int subeid){
		Connection con = null;
		ArrayList<Integer> list=new ArrayList<Integer>();
		try{
			Class.forName("com.mysql.jdbc.Driver"); 
			con = Connections.getDatabaseConnectionPath();
			String query = "Select DersGunu from Ders d,Kurs k,Sinif s,Sube su where k.subeid=su.id and s.subeid=su.id and d.sinifid=s.id and su.id="+subeid;
			Statement st = con.prepareStatement(query);
			ResultSet set = st.executeQuery(query);
			int i=1;
			while(set.next()){
				list.add(set.getInt(i));
				i++;
			}
	        st.clearBatch();
		}
		catch(Exception e){
			
		}
		return list;
	}
}
