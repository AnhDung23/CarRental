<%-- 
    Document   : billDetail
    Created on : Mar 17, 2020, 9:50:50 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bill Detai Page</title>
    </head>
    <body>
        <h1>Bill Detail</h1>
        <s:if test="%{#session.NAME != null}">
            <font color="red">
            Welcome, <s:property value="%{#session.NAME}"/>
            </font>
        </s:if>

        <a href="showOrderHistory">
            <font color="blue" style="margin-left: 70%"> View History </font>
        </a>

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
                        </tr>
                    </s:iterator>
                </tbody>
            </table>
        </s:if>
    </body>
</html>
