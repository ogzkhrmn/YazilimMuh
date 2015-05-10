<%@page import="yapiPackge.Sube"%>
<%@page import="yapiPackge.Ogrenci"%>
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
    if(userRole==-1){
    	response.sendRedirect("Anasayfa.jsp");
    	return;
    }
    
    String ad = request.getParameter("ogrenciad");
    String soyad =request.getParameter("ogrencisoyad");
    String ev=request.getParameter("ogrenciev");
    String cep=request.getParameter("ogrencicep");
    if(ad!=null && soyad!=null && ev!=null && cep!=null){
    	try{
    	    int odemeTipi = Integer.parseInt(request.getParameter("odemebilgisi"));
    	    boolean result = Ogrenci.OgrenciEkle(new Ogrenci(ad,soyad,ev,cep), odemeTipi);
    	    if(result){
    	    	out.println("Ogrenci Kayit Basarili. Sayfayı kapatabilirsiniz");
    	    	// Burada redirect yapılabilir .
    	    	return;
    	    }else{
    	    	out.println("Ogrenci Kayit Basarisiz Oldu.Tekrar Deneyiniz..");
    	    }
    	}catch(ParseException ex){
    		out.println("Odeme Tipi Seçilmedi veya Hata Oluştu!");
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
<title>Yeni Öğrenci Kayıt</title>
</head>
<body style="margin-left:20%;margin-right:20%">
	<form action="OgrenciKayit.jsp" method="POST" class="pure-form pure-form-aligned">
		<fieldset>
        <div class="pure-control-group">
            <label for="name">Öğrenci Adı : </label>
            <input id="ogrenciad" name="ogrenciad" type="text" >
        </div>

        <div class="pure-control-group">
            <label for="name">Öğrenci Soyadı :  </label>
            <input id="ogrencisoyad" type="text" name="ogrencisoyad">
        </div>
		 <div class="pure-control-group">
            <label for="name">Ev Telefonu :</label>
            <input id="ogrenciev" type="text" name="ogrenciev">
        </div>
         <div class="pure-control-group">
            <label for="name">Cep Telefonu : </label>
            <input id="ogrencicep" type="text" name="ogrencicep">
        </div>
         <div class="pure-control-group">
            <label for="name">Öğrenci Ödeme Bilgileri  </label>
        </div>
         <div class="pure-control-group">
            <label for="name">Ödeme Tipini Seçin</label>
            <select id="odemebilgisi" name="odemebilgisi" onchange="subeSecildiginde()">
	  			<option value='SecimYap'>Lütfen Bir Seçim Yapınız</option>
	  				<%
	  				ArrayList<OdemeBilgi> listOdemeBilgisi = OdemeBilgi.odemeBilgileriniGetir();
	  				for(int i=0;i<listOdemeBilgisi.size();i++){
	  					out.print("<option value = '"+listOdemeBilgisi.get(i).getId()+"' >"+listOdemeBilgisi.get(i).getOdemeTanim()+"</option>");
	  				}
	  				%>
	  			</select>
        </div>
         <div class="pure-control-group">
            <label for="name">Şube Seçimi Yapınız</label>
            <select id="subeler" name="subeid" onchange="subeSecildiginde()">
	  			<option value='SecimYap'>Şube Seçimi Yapınız.</option>
	  				<%
	  				ArrayList<Sube> listSube = Sube.getSubeListesi();
	  				for(int i=0;i<listSube.size();i++){
	  					out.print("<option value = '"+listSube.get(i).getId()+"' >"+listSube.get(i).getSubeAdi()+"</option>");
	  				}
	  				%>
	  			</select>
        </div>
        <div class="pure-controls">
            <button type="submit" class="pure-button pure-button-primary">Öğrenciyi Kaydet</button>
        </div>
    </fieldset>
	
	
	</form>
</body>
</html>