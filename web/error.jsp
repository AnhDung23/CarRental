<%-- 
    Document   : error
    Created on : Mar 8, 2020, 6:40:54 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Page</title>
    </head>
    <body>
        <h1>Error</h1>
        <font color="red">
        <s:property value="%{#request.ERROR}"/>
        </font><br/>
        <font color="blue">
            ${requestScope.ERROR}
        </font><br/>
        <a href="showAll">Back to home page</a>
    </body>
</html>
