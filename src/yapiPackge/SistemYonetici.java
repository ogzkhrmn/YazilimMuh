package yapiPackge;

import java.sql.Connection;

public class SistemYonetici {
    
    public void dilbilgisi(){
        Connection con = null;
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
	    con = Connections.getDatabaseConnectionPath();
        }
        catch(Exception e){
            
        }
    }
}
