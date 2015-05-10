<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    int userRole = -1;
    String username =(String)session.getAttribute("username");
    if(username==null || username.equals("")){
    	userRole = -1;
    }else{
        userRole = (Integer)(session.getAttribute("userrole"));
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
            
        }
        %>
    </ul>
		</div>
	</div>
	
	<div style="width: 50%; margin:auto; margin-top:100px;">
		<img alt="" src="Resimler/dilkursuresim.jpg">
	</div>
</body>
</html>