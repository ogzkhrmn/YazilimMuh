<%@page import="yapiPackge.Sinif"%>
<%@page import="java.util.ArrayList"%>
<%@page import="yapiPackge.Sube"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    String username =(String)session.getAttribute("username");
    if(username==null || username.equals("")){
    	response.sendRedirect("KullaniciGiris.jsp");
    	return;
    }
    int userRole = (Integer)(session.getAttribute("userrole"));
  	if(request.getParameter("sinifadi")!= null && request.getParameter("subeid")!=null){
  		try{
  	  		Sinif.sinifEkle(new Sinif(Integer.parseInt(request.getParameter("subeid")),request.getParameter("sinifadi")));
  		}catch(Exception ex){
  			ex.printStackTrace();
  		}
  	}
    
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="Site.css" rel="stylesheet" >
<link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.6.0/pure-min.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ana Sayfa</title>
</head>
<body style="margin-left:20%;margin-right:20%">
	<div class="topmenu">
		<div class="pure-menu pure-menu-horizontal">
	
    <ul class="pure-menu-list">
        <li class="pure-menu-item"><a href="Anasayfa.jsp" class="pure-menu-link">Ana Sayfa</a></li>
        <li class="pure-menu-item"><a href="Sube.jsp" class="pure-menu-link">Şubeler</a></li>
        <%
        if(userRole==1){
            out.print("<li class='pure-menu-item'><a href='' class='pure-menu-link'>Öğrenciler</a></li>");
            out.print("<li class='pure-menu-item'><a href='' class='pure-menu-link'>Kurslar</a></li>");
            out.print("<li class='pure-menu-item'><a href='' class='pure-menu-link'>Öğretmenler</a></li>");
            out.print("<li class='pure-menu-item'><a href='YeniSubeEkle.jsp' class ='popup'>Yeni Şube Ekle</a></li>");
        }else if(userRole==-1){
            out.print("<li class='pure-menu-item'><a href='KullaniciGiris.jsp'>Kullanici Girisi</a></li>");
        }else if(userRole==2){
        	  out.print("<li class='pure-menu-item'><a href='' class='pure-menu-link'>Öğrenciler</a></li>");
              out.print("<li class='pure-menu-item'><a href='' class='pure-menu-link'>Öğretmenler</a></li>");
              out.print("<li class='pure-menu-item'><a href='OgrenciKayit.jsp' class ='popup'>Yeni Öğrenci Kayit</a></li>");
        }
        %>
    </ul>
		</div>
	</div>
<div id="subeler">
<form action="Sube.jsp" method="post">
<select id="subeler" name="subeid" onchange="subeSecildiginde()">
	  			<option value='SecimYap'>Lütfen Bir Seçim Yapınız</option>
	  				<%
	  				ArrayList<Sube> listSube = Sube.getSubeListesi();
	  				for(int i=0;i<listSube.size();i++){
	  					out.print("<option value = '"+listSube.get(i).getId()+"' >"+listSube.get(i).getSubeAdi()+"</option>");
	  				}
	  				%>
	  			</select>
	  			<button id="btnSubeGoster" name="subegoster" type="submit">Subeyi Göster</button>
</form>
</div>
<br>
<div class="infodiv">
<%
if(request.getParameter("subeid")!=null){
	try{
		int subeid = Integer.parseInt(request.getParameter("subeid"));
		out.println("<div id='sube'>");
		out.print(Sube.getSube(subeid).toString());
		out.print("</div>");
		if(userRole==1){
			out.print("<hr>Yeni Derslik Ekle<hr><div class=''><form action='Sube.jsp' name='derslik' method ='post'><input type='hidden' name='subeid' value='"+subeid+"' >"+
					"<div class=''>"+
					          " <label for='name'> Sinif Adi</label>"+
					           " <input id='sinifadi' name='sinifadi' type='text' >"+
					 "</div> "+
					 
				  	"<button id='btnSubeGoster' name='derslikekle' type='submit'>Derslik Ekle</button>"+
				"</form>"+
			"</div>");
		}
		
		
	}catch(Exception ex){
		ex.printStackTrace();
		response.sendRedirect("Anasayfa.jsp");
		return;
	}
	
}
%>
<hr>

</div>




</body>
</html>