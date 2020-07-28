<%-- 
    Document   : employeeViewHistory
    Created on : Jul 17, 2020, 1:12:22 PM
    Author     : nguyennst
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View History</title>
    </head>
    <body>
        
        <%@include file="headerEmployee.jsp" %>
        <%@include file="menuEmployeeF.jsp" %>
        
        <h2>
            <font color="green">
            <h2 style="font-size: 20px">Welcome, <s:property value="%{#session.NAME}"/></h2>       
            </font>
        </h2>

    <center>
        <div style=" margin: 50px; text-align: left; width: 350px; height: 100px; background-color: #f3ecec; border: 1px solid #003333;">
            <center>
                <s:form action="employeeSearchHistory" method="POST" >
                    <s:textfield name="nameR" value="%{nameR}" label="Resource Name"/>
                    <s:textfield name="date" type="date" label="Date" value="%{date}"/>
                    <s:hidden name="pageNumber" value="1"/>
                    <s:submit value="Search"/>
                </s:form>
            </center>
        </div>
    </center>
    <center>
        <s:if test="%{listHistory.isEmpty()}">
            <font color="green">
            <h3>List Request Empty.</h3>
            </font>
        </s:if>
    </center>
    <s:else>
        <center>
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Resource Name</th>
                        <th>Quantity</th>
                        <th>Rental Date</th>
                        <th>Return Date</th>
                        <th>Request Date</th>
                        <th>Status</th>
                        <th>Delete</th>

                    </tr>
                </thead>
                <tbody>

                    <s:iterator value="%{listHistory}"  status="counter">
                        <tr>
                            <td>
                                <s:property value="%{#counter.count}" />
                            </td>
                            <td><s:property value="%{name}"/></td>
                            <td><s:property value="%{quantity}"/></td>
                            <td><s:property value="%{rentalDate}"/></td>
                            <td><s:property value="%{returnDate}"/></td>
                            <td><s:property value="%{createDate}"/></td>
                            <td>
                                <s:if test="%{statusID == 1}">
                                    New
                                </s:if>
                                <s:if test="%{statusID == 2}">
                                    Accept
                                </s:if>
                                <s:if test="%{statusID == 3}">
                                    Delete
                                </s:if>
                            </td>
                            <td>
                                <s:if test="%{rentalDate >= #session.CURRENTDATE}">
                                    <s:form action="employeeDeleteRequestHistory">
                                        <s:hidden name="detailID" value="%{detailID}" />
                                        <s:hidden name="nameR" value="%{nameR}" />
                                        <s:hidden name="date" value="%{date}" />
                                        <s:hidden name="pageNumber" value="1"/>
                                        <s:submit value="Delete" />
                                    </s:form>
                                </s:if>
                                <s:else>
                                    Expiry
                                </s:else>
                            </td>
                        </tr>
                    </s:iterator>

                </tbody>
            </table>
        </center>


    </s:else>

    <div style="margin-top: 175px; width: 50px; height: 40px;">
        <s:if test="%{numberOfPage > 1}">
            <s:iterator begin="1" end="%{numberOfPage}" var="i">
                <div style="float: left; width: 20px">
                    <s:form action="employeeSearchHistory" method="POST">
                        <s:hidden name="pageNumber" value="%{i}"/>
                        <s:hidden name="nameR" value="%{nameR}"/>
                        <s:hidden name="date" value="%{date}"/>
                        <s:submit value="%{#i}"/>
                    </s:form>
                </div>
            </s:iterator>
        </s:if>
    </div>

    
    <%@include file="footer.jsp" %>
</body>
</html>
