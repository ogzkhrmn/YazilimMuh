<%@page import="yapiPackge.Kurs"%>
<%@page import="yapiPackge.Sube"%>
<%@page import="java.util.Date"%>
<%@page import="yapiPackge.Ogretmen"%>
<%@page import="com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException"%>
<%@page import="yapiPackge.OdemeBilgi"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%
    String username =(String)session.getAttribute("username");
    if(username==null || username.equals("")){
    	response.sendRedirect("Anasayfa.jsp");
    	return;
    }
    int userRole = (Integer)(session.getAttribute("userrole"));
    if(userRole!=1){
    	response.sendRedirect("Anasayfa.jsp");
    	return;
    }
    
    String ad = request.getParameter("ogretmenad");
    String soyad =request.getParameter("ogretmensoyad");
    String ev=request.getParameter("ogretmenev");
    String cep=request.getParameter("ogretmencep");
    String kursid=  request.getParameter("kursid");
    String subeid = request.getParameter("subeid");
    if(ad!=null && soyad!=null && ev!=null && cep!=null){
    	try{
 
    	    int ogretmenId = Ogretmen.ogretmenEkle(new Ogretmen(ad,soyad,ev,cep,new Date(),Integer.parseInt(kursid),Integer.parseInt(subeid)));
    	    if(ogretmenId!=-1){
    	    	out.println("Kayit basariyla sonuçlandı.");
    	    	return;
    	    }else{
    	    	out.println("Ogretmen Kayit Basarisiz Oldu.Tekrar Deneyiniz..");
    	    }
    	}catch(Exception ex){
    	}
    }
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="http://code.jquery.com/jquery-latest.min.js"type="text/javascript"></script>
<script src ="SiteJavascript.js" type="text/javascript"></script>
<link rel="stylesheet" href="Site.css">
<link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.6.0/pure-min.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Yeni Öğretmen Kayıt</title>
</head>
<body style="margin-left:20%;margin-right:20%">
	<form action="OgretmenKayit.jsp" method="POST" class="pure-form pure-form-aligned">
		<fieldset>
        <div class="pure-control-group">
            <label for="name">Öğretmen Adı : </label>
            <input id="ogretmenad" name="ogretmenad" type="text" >
        </div>

        <div class="pure-control-group">
            <label for="name">Öğretmen Soyadı :  </label>
            <input id="ogretmensoyad" type="text" name="ogretmensoyad">
        </div>
		 <div class="pure-control-group">
            <label for="name">Ev Telefonu :</label>
            <input id="ogretmenev" type="text" name="ogretmenev">
        </div>
         <div class="pure-control-group">
            <label for="name">Cep Telefonu : </label>
            <input id="ogretmencep" type="text" name="ogretmencep">
        </div>
         <div class="pure-control-group">
            <label for="name">Çalışabilecegi Sube : </label>
            <select id="subeler" name="subeid" onchange="subeSecildiginde()">
	  			<option value='SecimYap'>Lütfen Bir Seçim Yapınız</option>
	  				<%
	  				ArrayList<Sube> listSube = Sube.getSubeListesi();
	  				for(int i=0;i<listSube.size();i++){
	  					out.print("<option value = '"+listSube.get(i).getId()+"' >"+listSube.get(i).getSubeAdi()+"</option>");
	  				}
	  				%>
	  			</select>
        </div>

        <div class="pure-control-group">
            <label for="name">Çalışabilecegi Kurs  </label>
            <select id="kurslar" name="kursid" onchange="subeSecildiginde()">
	  			<option value='SecimYap'>Lütfen Bir Seçim Yapınız</option>
	  				<%
	  				ArrayList<Kurs> listKurs = Kurs.kursListesiGetir();
	  				for(int i=0;i<listKurs.size();i++){
	  					out.print("<option value = '"+listKurs.get(i).getId()+"' >"+listKurs.get(i).getKursadi()+"</option>");
	  				}
	  				%>
	  			</select>
        </div>
        <div class="pure-controls">
            <button type="submit" class="pure-button pure-button-primary">Öğretmeni Kaydet</button>
        </div>
    </fieldset>
	
	
	</form>
</body>
</html>