<%-- 
    Document   : feedBackPage
    Created on : Mar 17, 2020, 4:59:03 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Feedback Page</title>
    </head>
    <body>
        <h1>Feed Back</h1>
        <s:if test="%{#session.NAME != null}">
            <font color="red">
            Welcome, <s:property value="%{#session.NAME}"/><br/>
            </font>
        </s:if><br/>

        <s:if test="%{#session.DTO != null}">
            <s:set var="dto" value="%{#session.DTO}"/>
            <s:label label="Name cart"/>
            <s:property value="%{#dto.carName}"/><br/>
            <s:label label="Color"/>
            <s:property value="%{#dto.color}"/><br/>
            <s:label label="Price"/>
            <s:property value="%{#dto.price}"/><br/>
        </s:if>

        <s:set var="listRate" value="%{#session.LIST_RATE}"/>
        <s:form action="feedBack">
            <s:textfield name="contentFB" label="FeedBack"/>
            <s:select label="Rate"
                      headerKey="-1" headerValue="---Rating---"
                      list="%{#listRate}"
                      name="rating"/>
            <s:submit value="Send"/>
        </s:form>
        <s:if test="%{#request.ERR_FB != null}">
            <font color="red">
            <s:property value="%{#request.ERR_FB}"/><br/>
            </font>
        </s:if>
        <s:if test="%{#request.FB != null}">
            <font color="green">
            <s:property value="%{#request.FB}"/><br/>
            </font>
        </s:if>
        <a href="showAll">Back to home page</a>
    </body>
</html>
