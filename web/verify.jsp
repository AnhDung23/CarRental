<%-- 
    Document   : verify
    Created on : Mar 8, 2020, 10:54:42 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Verify</title>
    </head>
    <body>
        <h1>Verify Email</h1>
        <s:form action="verifyEmail">
            <s:textfield name="code"/>
            <s:hidden name="email" value="%{#request.EMAIL}"/>
            <s:hidden name="verifyCode" value="%{#request.CODE_VERIFY}"/>
            <s:submit value="Send"/>
        </s:form>
    </body>
</html>
