<%@page import="yapiPackge.Kurs"%>
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
    if(request.getParameter("kursadi")!=null && request.getParameter("kursucret")!=null){
    	System.out.println("Kurs eklemedeyim");
    	String kursadi = request.getParameter("kursadi");
    	int kursucreti = Integer.parseInt(request.getParameter("kursucret"));
    	Kurs.kursEkle(kursadi, kursucreti);
    	response.sendRedirect("Kurs.jsp");
    	
    }
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="Site.css" rel="stylesheet" >
<link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.6.0/pure-min.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Kurslar</title>
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
<div id="subeler" class="infodiv" >
<form action="Kurs.jsp" method="POST">
<select id="kurslar" name="kursid" onchange="subeSecildiginde()">
	  			<option value='SecimYap'>Lütfen Bir Seçim Yapınız</option>
	  				<%
	  				ArrayList<Kurs> listKurs = Kurs.kursListesiGetir();
	  				for(int i=0;i<listKurs.size();i++){
	  					out.print("<option value = '"+listKurs.get(i).getId()+"' >"+listKurs.get(i).getKursadi()+"</option>");
	  				}
	  				%>
	  			</select>
	  			<button id="btnKursGoster" name="kursGoster" type="submit">Kurs Bilgilerini Görüntüle</button>
	  			<div >
				<%
				if(request.getParameter("kursid")!=null){
					try{
						int kursid = Integer.parseInt(request.getParameter("kursid"));
						Kurs kurs = Kurs.kursGetir(kursid);
						out.print(kurs.toString());
					}catch(Exception ex){
						ex.printStackTrace();
						response.sendRedirect("Anasayfa.jsp");
						return;
					}
				}
				%>
				</div>
</form>
</div>
<br>

<div>
	<div class="infodiv">
		<form action="Kurs.jsp" method="get">
			<fieldset>
        <div class="pure-control-group">
            <label for="name">Kurs Adi :  </label>
            <input id="kursadi" name="kursadi" type="text" >
        </div>

        <div class="pure-control-group">
            <label for="name">Kurs Ucreti :  </label>
            <input id="kursucret" type="text" name="kursucret">
        </div>
        <div class="pure-controls">
            <button type="submit" class="pure-button pure-button-primary">Yeni Kurs Ekle </button>
        </div>
    </fieldset>
		
		</form>
	</div>
</div>
</body>
</html>