<%-- 
    Document   : carReturned
    Created on : Mar 17, 2020, 3:53:23 PM
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
        <h1>Car Page</h1>
        <s:if test="%{#session.NAME != null}">
            <font color="red">
            Welcome, <s:property value="%{#session.NAME}"/><br/>
            </font>
        </s:if>
        <s:if test="%{#request.LIST != null}">
            <table border="1" cellspacing="0" style="text-align: center">
                <thead>
                    <tr>
                        <th>Name car</th>
                        <th>Color</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Rental date</th>
                        <th>Return date</th>
                        <th>Total</th>
                        <th>FeedBack</th>         
                    </tr>
                </thead>
                <tbody>
                    <s:iterator var="dto" value="%{#request.LIST}">
                        <tr>
                            <td>
                                <s:property value="%{#dto.carName}"/>
                            </td>
                            <td>
                                <s:property value="%{#dto.color}"/>
                            </td>
                            <td>
                                
                                <s:property value="%{#dto.price}"/>
                            </td>
                            <td>
                                <s:property value="%{#dto.quantity}"/>
                            </td>
                            <td>
                                ${dto.rentalDate}
                            </td>
                            <td>
                                ${dto.returnDate}
                            </td>
                            <td>
                                <s:property value="%{#dto.total}"/>
                            </td>
                            <td>
                                <s:url var="showDetailLink" value="showFeedBackPage">
                                    <s:param name="price" value="%{#dto.price}"/>
                                </s:url>
                                <s:a href="%{showDetailLink}">FeedBack</s:a>
                            </td>
                        </tr>
                    </s:iterator>
                </tbody>
            </table>
        </s:if>
        <s:if test="%{#request.LIST == null}">
            <font color="red">
            No record is match!!!
            </font><br/>
        </s:if>
            <a href="showAll">Back to home page</a><br/>
            
    </body>
</html>
