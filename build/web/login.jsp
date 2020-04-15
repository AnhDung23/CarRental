<%-- 
    Document   : login
    Created on : Mar 8, 2020, 10:14:59 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <script src="https://www.google.com/recaptcha/api.js" async defer></script>
    </head>
    <body>
        <h1>Login Page</h1>

        <s:form action="login" >

            <s:textfield name="email" label="Email" id="email"/>
            <s:password name="password" label="Password" id="password"/>

            <div class="g-recaptcha" data-sitekey="6Lel0-AUAAAAAOjPWCEwSOO-Bwzx4yNCcDcBmvJU"></div>   
            <s:submit value="Login" id="login-btn"/>      

        </s:form>
        <s:if test="%{#request.ERROR != null}">
            <font color="red">
            <s:property value="%{#request.ERROR}"/><br/>
            </font>         
        </s:if>
        <a href="registration.jsp">Registration</a><br/>
        <a href="showAll">Back to home page</a>
    </body>
</html>
