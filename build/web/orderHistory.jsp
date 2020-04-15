<%-- 
    Document   : orderHistory
    Created on : Mar 17, 2020, 8:36:21 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order History Page</title>
    </head>
    <body>
        <h1>Order History</h1>
        <s:if test="%{#session.NAME != null}">
            <font color="red">
            Welcome, <s:property value="%{#session.NAME}"/><br/>
            </font>
        </s:if>
        <s:form action="searchHistory">
            <s:textfield name="nameSearch" label="Name car"/>
            <s:textfield name="orderFrom" label="From date"/>
            <s:textfield name="orderTo" label="To date"/>
            <s:submit value="Search"/>
        </s:form>
        <s:if test="%{#request.ERROR_SEARCH != null}">
            <font color="red">
            <s:property value="%{#request.ERROR_SEARCH}"/><br/>
            </font>
        </s:if>

        <s:if test="%{#request.LIST != null}">
            <table border="1" cellspacing="0" style="text-align: center">
                <thead>
                    <tr>
                        <th>ID Bill</th>
                        <th>Number of cars</th>
                        <th>Order date</th>
                        <th>Total</th>
                        <th>Discount code</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody>
                    <s:iterator var="dto" value="%{#request.LIST}">
                        <s:form action="deleteOrderBill" theme="simple">
                            <tr>
                                <td>
                                    <s:url var="showDetailLink" value="showBillDetail">
                                        <s:param name="idBill" value="%{#dto.idBill}"/>
                                    </s:url>
                                    <s:a href="%{#showDetailLink}">
                                        <s:property value="%{#dto.idBill}"/>
                                    </s:a>
                                    <s:hidden name="idBill" value="%{#dto.idBill}"/>
                                </td>
                                <td>
                                    <s:property value="%{#dto.numOfCars}"/>
                                </td>
                                <td>
                                    ${dto.orderDate}
                                    <%--<s:property value="%{#dto.orderDate}"/>--%>
                                </td>
                                <td>
                                    <s:property value="%{#dto.total}"/>
                                </td>
                                <td>
                                    <s:property value="%{#dto.discountCode}"/>
                                </td>
                                <td>
                                    <s:submit value="Delete"/>
                                </td>
                            </tr>
                        </s:form>
                    </s:iterator>
                </tbody>
            </table>
        </s:if>
        <s:if test="%{#request.LIST == null}">
            <font color="red">
            No record is match!!!
            </font><br/>
        </s:if>

        <a href="cart.jsp">Back to cart page</a><br/>
        <a href="showAll">Back to home page</a>
    </body>
</html>
