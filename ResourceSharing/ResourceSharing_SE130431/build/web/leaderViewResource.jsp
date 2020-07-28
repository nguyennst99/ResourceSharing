<%-- 
    Document   : leaderViewResource
    Created on : Jul 15, 2020, 2:40:28 PM
    Author     : nguyennst
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Request Resource</title>
    </head>
    <body>
        <%@include file="headerLeader.jsp" %>
        <%@include file="menuLeaderF.jsp" %>
        <h2>
            <font color="green">
            <h2 style="font-size: 20px">Welcome, <s:property value="%{#session.NAME}"/></h2>       
            </font>
        </h2>
    <center>
        <h1>Request Resource</h1>
        <div style="padding: 20px; margin: 30px; text-align: left; width: 700px; min-height: 200px; background-color: #F5F5F5; border: 1px solid #CDCDCD;">
            <s:if test="%{#session.CART.items.isEmpty()}">
                <h3>Request Empty.</h3>
            </s:if>
            <s:else>
                <table border="1">
                    <thead>
                        <tr>
                            <th>Resource Name</th>
                            <th>Category</th>
                            <th>Rental Date</th>
                            <th>Return Date</th>
                            <th>Quantity</th>
                            <th>Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="%{#session.CART.items.values()}">
                            <tr>
                                <td><s:property value="%{name}"/></td>
                                <td><s:property value="%{categoryName}"/></td>
                                <td><s:property value="%{rentalDate}"/></td>
                                <td><s:property value="%{returnDate}"/></td>
                                <td>
                                    <s:form action="leaderUpdateRequest" method="POST">
                                        <s:textfield type="number" name="quantity" value="%{quantity}" min="1"/>
                                        <s:hidden name="detailID" value="%{detailID}"/>
                                        <s:submit value="Update"/>
                                    </s:form>
                                </td>
                                <td>
                                    <s:form action="leaderDeleteRequest" method="POST">
                                        <s:hidden name="detailID" value="%{detailID}"/>
                                        <s:submit value="Delete" onclick="return confirm('Are you sure you want to delete?')"/>
                                    </s:form>
                                </td>
                            </tr>
                        </s:iterator>
                    </tbody>
                </table>
                <s:form action="leaderConfirmAction" >
                    <s:submit value="Request"/>
                </s:form>

                <s:if test="%{#request.LISTSOLDOUT.isEmpty() == false}">
                    <font color="red">
                    <s:iterator value="%{#request.LISTSOLDOUT}">
                        <s:property value="%{name}"/> is out of stock <br/>
                    </s:iterator>
                    </font>
                </s:if>
            </s:else>
        </div>
    </center>
<br/><br/><br/><br/><br/>
<br/><br/><br/><br/><br/>
<br/><br/><br/><br/><br/>
<br/><br/><br/>
<%@include file="footer.jsp" %>
</body>
</html>
