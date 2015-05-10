<%@page import="yapiPackge.Kullanici"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
		<%
String username = request.getParameter("username");
String password = request.getParameter("password");
if(username!=null && password!=null){
	try{
		Kullanici kul = Kullanici.kullaniciGiris(username, password);
		if(kul==null){
			out.println("Kullanici Giris Hatali!");
		}else{
			session.setAttribute("username", username);
			session.setAttribute("password",password);
			session.setAttribute("userid",kul.getKullaniciId());
			session.setAttribute("userrole",kul.getRolId());
			response.sendRedirect("Anasayfa.jsp");
			
		}
	}catch(Exception ex){
		
		out.println(ex.getMessage());	
	}
}else{
	if(session.getAttribute("username")!=null ){
		response.sendRedirect("Anasayfa.jsp");	
	}
}
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="Site.css" rel="stylesheet" >
<link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.6.0/pure-min.css">
<script type="text/javascript">
function validForm(){
if (document.userlogin.username.value == ""){
	alert ( "Kullanici Adi Bos Birakilamaz." );
	document.loginform.username.focus();
	return false;
	}
if (document.userlogin.password.value == ""){
	alert ( "Sifre Bos Bırakılamaz." );
	document.userform.password.focus();
	return false;
	}	
	return true;
}
</script>
<title>Kullanici Girisi</title>
</head>
<body>
<form name="userlogin" class ="pure-form pure-form-aligned" action="KullaniciGiris.jsp" onSubmit="return validForm()" method="POST" >
	<h1>Giriş Yap</h1>
	<fieldset>
		<div class="pure-control-group">
            <label for="name">Kullanici Adi : </label>
		<input type="text" name="username"><br />
        </div>
		   <div class="pure-control-group">
            <label for="password">Şifre : </label>
		<input type="password" name="password" onblur="text_onblur(this);">
        </div>
        <div class="pure-controls">
		<input class = "pure-button" type="submit" value="Giriş Yap" onblur = "text_onblur(this);"><br /><br />
        </div>
	
	</fieldset>
		
		
	</form>
</body>
</html>