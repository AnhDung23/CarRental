<%-- 
    Document   : homePage
    Created on : Mar 8, 2020, 10:45:31 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Car Store</title>
    </head>
    <body>
        <h1>Car Store</h1>

        <s:if test="%{#session.NAME != null}">
            <font color="red">
            Welcome, <s:property value="%{#session.NAME}"/>
            </font>

            <a href="cart.jsp">
                <font color="blue" style="margin-left: 60%"> View cart </font>
            </a>
            <a href="showOrderHistory">
                <font color="blue" style="margin-left: 5px"> View History </font>
            </a>
            <a href="logout">
                <font color="blue" style="margin-left: 5px"> Logout </font>
            </a>
        </s:if>
        <s:if test="%{#session.NAME == null}">
            <a href="login.jsp">
                <font color="blue" style="margin-left: 80%"> Login </font>
            </a>
        </s:if>

        <s:form action="search">
            <s:textfield name="nameSearch" label="Car name"/>
            <s:select label="Category"
                      headerKey="-1" headerValue="---Category---"
                      list="lisCategory"
                      name="cateSearch"/>
            <s:textfield name="rentalDate" label="Rental date (dd-MM-yyyy)"/>
            <s:textfield name="returnDate" label="Return date (dd-MM-yyyy)"/>
            <s:textfield name="amount" label="Amount"/>
            <s:submit value="Search"/>   
        </s:form>
        <s:if test="%{#request.ERROR_DATE != null}">
            <font color="red">
            <s:property value="%{#request.ERROR_DATE}"/><br/>
            </font>
        </s:if>

        <s:if test="%{#request.ERROR_AMOUNT != null}">
            <font color="red">
            <s:property value="%{#request.ERROR_AMOUNT}"/><br/>
            </font>
        </s:if>

        <s:if test="%{#request.ERROR_NAME_CATE != null}">
            <font color="red">
            <s:property value="%{#request.ERROR_NAME_CATE}"/><br/>
            </font>
        </s:if>
        <br/>

        <a href="showAll">
            <font color="blue"> Show All </font>
        </a>

        <s:if test="%{#request.LIST != null}">
            <table border="1" cellspacing="0" style="text-align: center">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Car Name</th>
                        <th>Color</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Category</th>
                        <th>Status</th>
                        <th>Add to cart</th>
                    </tr>
                </thead>
                <tbody>
                    <s:iterator var="dto" value="%{#request.LIST}" status="counter">
                        <s:form action="addToCart" theme="simple">
                            <tr>
                                <td>
                                    <s:property value="%{#counter.count}"/>
                                </td>
                                <td>                                   
                                    <s:hidden name="carName" value="%{#dto.carName}"/>
                                    <s:url var="showDetailLink" value="showDetailCar">
                                        <s:param name="price" value="%{#dto.price}"/>
                                    </s:url>
                                    <s:a href="%{showDetailLink}">
                                        <s:property value="%{#dto.carName}"/>
                                    </s:a>
                                </td>
                                <td>
                                    <s:property value="%{#dto.color}"/>
                                    <s:hidden name="color" value="%{#dto.color}"/>
                                </td>
                                <td>
                                    <s:property value="%{#dto.price}"/>
                                    <s:hidden name="price" value="%{#dto.price}"/>
                                </td>
                                <td>
                                    <s:property value="%{#dto.quantity}"/>
                                </td>
                                <td>
                                    ${lisCategory.get(cateID)}
                                    <s:hidden name="cateID" value="%{#dto.cateID}"/>
                                </td>
                                <td>
                                    <s:property value="%{#dto.status}"/>
                                </td>
                                <td>
                                    <s:textfield name="amountAdd" size="10" value="1"/>
                                    <s:set var="disable" value="true"/>
                                    <s:if test="%{#session.NAME != null}">
                                        <s:set var="disable" value="false"/>
                                    </s:if>
                                    <s:hidden name="nameSearch" value="%{nameSearch}"/>
                                    <s:hidden name="cateSearch" value="%{cateSearch}"/>
                                    <s:hidden name="rentalDate" value="%{rentalDate}"/>
                                    <s:hidden name="returnDate" value="%{returnDate}"/>
                                    <s:hidden name="amount" value="%{amount}"/>
                                    <s:submit value="Add to cart" disabled="%{disable}"/>            
                                </td>
                            </tr>
                        </s:form>
                    </s:iterator>
                </tbody>
            </table>
        </s:if>
        <br/>
        <s:if test="%{#request.LIST == null}">
            <font color="red">
            No record is match!!!
            </font>
        </s:if>

    </body>
</html>
