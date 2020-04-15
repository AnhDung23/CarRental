<%-- 
    Document   : cart
    Created on : Mar 14, 2020, 10:12:37 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart Page</title>
    </head>
    <body>
        <h1>Cart Information</h1>
        <s:if test="%{#session.NAME != null}">
            <font color="red">
            Welcome, <s:property value="%{#session.NAME}"/>
            </font>
        </s:if>

        <a href="showOrderHistory">
            <font color="blue" style="margin-left: 65%"> View History </font>
        </a>
        <a href="showCarsReturned">
            <font color="blue" style="margin-left: 5px"> Feed Back </font>
        </a>
        <s:if test="%{#session.CART != null}">
            <table border="1" cellspacing="0" style="text-align: center">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Car name</th>
                        <th>Color</th>
                        <th>Price</th>
                        <th>Category</th>
                        <th>Amount</th>
                        <th>Rental date</th>
                        <th>Return date</th>
                        <th>Total</th>
                        <th>Update</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody>
                    <s:set var="total" value="0"/>
                    <s:iterator value="%{#session.CART.cars}" status="counter" >
                        <s:form action="updateCart" theme="simple">
                            <tr>
                                <td>
                                    <s:property value="%{#counter.count}"/>
                                </td>
                                <td>
                                    <s:property value="value.carName"/>
                                </td>
                                <td>
                                    <s:property value="value.color"/>
                                </td>
                                <td>
                                    <s:property value="key"/>
                                    <s:set var="price" value="key"/> 
                                    <input type="hidden" name="price" value="${key}" />
                                </td>
                                <td>
                                    <s:property value="value.cate"/>
                                </td>
                                <td>
                                    <input type="text" name="amountAdd" value="${value.amountAdd}" size="4"/>
                                    <s:set var="quantity" value="value.amountAdd" />
                                </td>
                                <td>
                                    <input type="text" name="rentalDate" value="${value.rentalDate}" size="8"/>
                                </td>
                                <td>
                                    <input type="text" name="returnDate" value="${value.returnDate}" size="8"/>
                                </td>
                                <td>
                                    <s:property value="%{#price * #quantity}"/>                         
                                    <s:set var="total" value="%{#total + #price * #quantity}"/>
                                </td>
                                <td>
                                    <s:submit value="Update"/>
                                </td>
                                <td>

                                    <s:url var="deleteLink" value="deleteCart">
                                        <s:param name="price" value="key"/>
                                    </s:url>
                                    <s:a href="%{deleteLink}" cssStyle="text-decoration: none">
                                        <font color="blue">
                                        X
                                        </font>
                                    </s:a>
                                </td>
                            </tr>
                        </s:form>
                    </s:iterator>                                         
                </tbody>
            </table>
            <s:if test="%{#request.ERR_AMOUNT_UPDATE != null}">
                <br/><font color="red">
                <s:property value="%{#request.ERR_AMOUNT_UPDATE}"/><br/>
                </font>
            </s:if>
            <s:if test="%{#request.ERR_DATE_UPDATE != null}">
                <br/><font color="red">
                <s:property value="%{#request.ERR_DATE_UPDATE}"/><br/>
                </font>
            </s:if>   
            Total bill: <s:property value="%{#total}"/><br/>

            <s:form action="checkoutCart">
                <s:hidden name="total" value="%{#total}"/>
                <s:textfield name="discountCode" label="Discount code"/>
                <s:submit value="Confirm"/>
            </s:form>        
            <s:if test="%{#request.ERROR_CONFIRM != null}">
                <font color="red">
                <s:property value="%{#request.ERROR_CONFIRM}"/>
                </font>
            </s:if>
        </s:if><br/>

        <s:if test="%{#request.CHECKOUT_MSG != null}">
            <font color="green">
            <s:property value="%{#request.CHECKOUT_MSG}"/><br/>
            </font>           
        </s:if>
        <s:if test="%{#request.INVALID_CODE != null}">
            <font color="red">
            <s:property value="%{#request.INVALID_CODE}"/><br/>
            </font>
        </s:if>
        <s:if test="%{#session.CART == null}">
            <s:if test="%{#request.CHECKOUT_MSG == null}">
                <font color="blue">
                Your cart is empty!!!
                </font>
            </s:if>
        </s:if><br/>
        <a href="showAll">Back to home page</a>
    </body>
</html>
