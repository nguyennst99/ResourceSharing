<%-- 
    Document   : employee
    Created on : Jul 5, 2020, 5:00:14 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Employee</title>
    </head>
    <body>

        <%@include file="headerEmployee.jsp" %>
        <%@include file="menuaEmployee.jsp" %>


        <h2>
            <font color="green">
            <h2 style="font-size: 20px">Welcome, <s:property value="%{#session.NAME}"/></h2>       
            </font>
        </h2>


        <br/>
    <center>
        <div style=" margin: 50px; width: 350px; height: 170px; background-color: #f3ecec; border: 1px solid #003333;">
            <center>
                <s:form action="employeeSearch" >
                    <s:textfield name="nameS" label="Resource Name" value="%{nameS}"/>
                    <s:select label="Category" name="categoryIDS" list="%{#session.CATEGORY}" headerKey="0" headerValue="All" listKey="%{categoryID}" listValue="%{categoryName}"/>
                    <s:textfield name="rentalDate" label="Rental Date" type="date" value="%{rentalDate}" />
                    <s:textfield name="returnDate" label="Return Date" type="date" value="%{returnDate}"/>
                    <s:hidden name="pageNumber" value="1"/>
                    <s:submit value="Search"/>
                </s:form>
            </center>
        </div>
    </center>

    <center>
        <s:if test="%{list.isEmpty()}">
            <font color="green">
            <h3>No record found.</h3>
            </font>
        </s:if>
        <s:else>
            <s:iterator value="%{list}">
                <div style="float: left;padding: 20px; margin: 30px; text-align: left; width: 200px; min-height: 310px; background-color: #f3ecec; border: 1px solid #CDCDCD;">
                    <center>
                        <h5>Name: <s:property value="%{name}"/></h5>
                        <h5>Color: <s:property value="%{color}"/></h5>
                        <h5>Quantity: <s:property value="%{quantity}"/></h5>
                        <h5>Category: <s:property value="%{categoryName}"/></h5>
                        <s:form action="employeeRent" method="POST">
                            <s:hidden name="resourceID" value="%{resourceID}"/>
                            <s:hidden name="nameS" value="%{nameS}"/>
                            <s:hidden name="name" value="%{name}"/>
                            <s:hidden name="categoryIDS" value="%{categoryIDS}"/>
                            <s:hidden name="categoryName" value="%{categoryName}"/>
                            <s:hidden name="rentalDate" value="%{rentalDate}"/>
                            <s:hidden name="returnDate" value="%{returnDate}"/>
                            <s:hidden name="quantity" value="%{quantity}"/>
                            <s:hidden name="pageNumber" value="1"/>
                            <s:if test="%{roleID == 3}">
                                <s:submit value="Rent"/>
                            </s:if>
                            <s:else>
                                <h5>
                                    <font color="green">
                                    The resource is rent by Leader
                                    </font>
                                </h5>
                            </s:else>
                        </s:form>
                    </center>
                </div>
            </s:iterator>
        </s:else>
    </center>

    <div style="margin-top: 1000px; width: 50px; height: 40px;">
        <s:if test="%{numberOfPage > 1}">
            <s:iterator begin="1" end="%{numberOfPage}" var="i">
                <div style="float: left; width: 20px">
                    <s:form action="employeeSearch" method="POST">
                        <s:hidden name="nameS" value="%{nameS}"/>
                        <s:hidden name="categoryIDS" value="%{categoryIDS}"/>
                        <s:hidden name="rentalDate" value="%{rentalDate}"/>
                        <s:hidden name="returnDate" value="%{returnDate}"/>
                        <s:hidden name="pageNumber" value="%{i}"/>
                        <s:submit value="%{#i}"/>
                    </s:form>
                </div>
            </s:iterator>
        </s:if>
    </div>

    <%@include file="footer.jsp" %>
</body>
</html>
