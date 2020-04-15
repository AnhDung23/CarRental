<%-- 
    Document   : detailCar
    Created on : Mar 17, 2020, 9:31:26 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Car Page</title>
    </head>
    <body>
        <h1>Car Detail</h1>
        <s:if test="%{#session.NAME != null}">
            <font color="red">
            Welcome, <s:property value="%{#session.NAME}"/><br/>
            </font>
        </s:if><br/>

        <s:if test="%{#request.DTO != null}">
            <s:set var="dto" value="%{#request.DTO}"/>
            <s:label label="Name cart"/>
            <s:property value="%{#dto.carName}"/><br/>
            <s:label label="Color"/>
            <s:property value="%{#dto.color}"/><br/>
            <s:label label="Price"/>
            <s:property value="%{#dto.price}"/><br/><br/>
            <s:label label="Feed back"/><br/>
            <s:iterator var="dtoFB" value="%{#request.LIST_FB}">
                <s:property value="%{#dtoFB.email}"/>
                <s:label label=" "/>
                <s:property value="%{#dtoFB.contentFB}"/>
                <s:label label=" - Rate"/>
                <s:property value="%{#dtoFB.rate}"/><br/>
            </s:iterator>
        </s:if><br/>
        <a href="showAll">Back to home page</a>
    </body>
</html>
