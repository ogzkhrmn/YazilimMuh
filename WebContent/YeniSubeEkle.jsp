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
    if(userRole!=1){
    	response.sendRedirect("Anasayfa.jsp");
    	return;
    }
    String subeadi = request.getParameter("subeadi");
    String subeadres = request.getParameter("subeadres");
    String subetoplu = request.getParameter("subetoplu");
    String subeozel = request.getParameter("subeozel");
    String subesosyal = request.getParameter("subesosyal");
    System.out.println(subeadi);
    if(subeadi!=null && subeadres!=null && subetoplu!=null && subeozel!=null && subesosyal!=null){
    	boolean result = Sube.subeKaydet(new Sube(subeadi,subeadres,subetoplu,subeozel,subesosyal));
    	if(result){
    		out.println("Sube basariyla kaydedildi . Sayfayı kapatabilirsiniz...");
    		return;
    	}else{
    		out.println("Şube kaydetme başarısız oldu . Tekrar deneyiniz...");
    		return;
    	}
    }
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.6.0/pure-min.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Yeni Sube Ekle</title>
</head>
<body style="margin: auto; width: 50%">
	<form action="YeniSubeEkle.jsp" method="get" class="pure-form pure-form-aligned">
		<fieldset>
        <div class="pure-control-group">
            <label for="name">Şube Adı : </label>
            <input id="subeadi" name="subeadi" type="text" >
        </div>

        <div class="pure-control-group">
            <label for="name">Şube Adresi : </label>
            <input id="subeadres" type="text" name="subeadres">
        </div>

         <div class="pure-control-group">
            <label for="name">Toplu Taşıma Olanakları :  </label>
            <textarea id="subetoplu" name="subetoplu"></textarea>
        </div>

         <div class="pure-control-group">
            <label for="name">Özel Otomobil Olanakları : </label>
            <textarea id="subeozel" name="subeozel"></textarea>
        </div>
		
         <div class="pure-control-group">
            <label for="name">Sosyal Olanaklar :  </label>
            <textarea id="subesosyal" name="subesosyal"></textarea>
        </div>
        <div class="pure-controls">
           
            <button type="submit" class="pure-button pure-button-primary">Şubeyi Kaydet</button>
        </div>
    </fieldset>
	
	
	</form>
</body>
</html>