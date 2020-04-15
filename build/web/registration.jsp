<%-- 
    Document   : registration
    Created on : Mar 8, 2020, 10:25:08 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration</title>
        <s:head/>
    </head>
    <body>
        <h1>Information</h1>
        <s:form action="createAccount" >
            <s:textfield name="email" label="Email"/>
            <s:password name="password" label="Password"/>
            <s:textfield name="fullname" label="Fullname"/>
            <s:textfield name="phone" label="Phone"/>
            <s:textfield name="address" label="Address"/>
            <s:submit value="Create"/>
        </s:form>
        <s:if test="%{exception.message.indexOf('duplicate') > -1}">
            <font color="red">
            <s:property value="email"/> is existed!!!
            </font>
        </s:if>
    </body>
</html>
