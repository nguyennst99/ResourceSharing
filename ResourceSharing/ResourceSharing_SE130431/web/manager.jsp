<%-- 
Document   : manager
Created on : Jul 5, 2020, 5:15:33 PM
Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Request</title>
    </head>
    <body>
        <%@include file="headerManager.jsp" %>
        <%@include file="menuManager.jsp" %>
        <h2>
            <font color="green">
            <h2 style="font-size: 20px">Welcome, <s:property value="%{#session.NAME}"/></h2>       
            </font>
        </h2>


        <br/><br/><br/>

    <center>
        <div style=" margin: 50px; text-align: left; width: 350px; height: 170px; background-color: #f3ecec; border: 1px solid #003333;">
            <center>
                <s:form action="managerSearch" method="POST" >
                    <s:textfield name="Sname" value="%{Sname}" label="Resource Name"/>
                    <s:textfield name="ScreateDate" type="date" label="Date" value="%{ScreateDate}"/>
                    <s:select label="Status" name="SstatusID" list="%{#session.STATUS}" headerKey="0" headerValue="All" listKey="%{statusID}" listValue="%{statusName}"/>          
                    <s:textfield name="SuserID" value="%{SuserID}" label="User ID"/>
                    <s:hidden name="pageNumber" value="1"/>
                    <s:submit value="Search"/>
                </s:form>
            </center>
        </div>
    </center>

    <br/><br/><br/>
    <br/><br/><br/>
    <center>
        <s:if test="%{listRequest.isEmpty()}">
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
                        <th>User ID</th>
                        <th>Approval</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody>

                    <s:iterator value="%{listRequest}"  status="counter">
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
                            <td><s:property value="%{userID}"/></td>
                            <td>
                                <s:if test="%{rentalDate >= #session.CURRENTDATE}">
                                    <s:if test="%{statusID == 3}"> 
                                        Deleted
                                    </s:if>
                                    <s:else>
                                        <s:form action="managerApproval">
                                            <s:hidden name="detailID" value="%{detailID}" />
                                            <s:hidden name="Sname" value="%{Sname}" />
                                            <s:hidden name="ScreateDate" value="%{ScreateDate}" />
                                            <s:hidden name="SstatusID" value="%{SstatusID}" />
                                            <s:hidden name="SuserID" value="%{SuserID}" />
                                            <s:hidden name="pageNumber" value="1"/>
                                            <s:submit value="Approval" />
                                        </s:form>
                                    </s:else>
                                </s:if>
                                <s:else>
                                    Expiry
                                </s:else>
                            </td>
                            <td>
                                <s:if test="%{rentalDate >= #session.CURRENTDATE}">
                                    <s:if test="%{statusID == 3}"> 
                                        Deleted
                                    </s:if>
                                    <s:else>
                                        <s:form action="managerDelete">
                                            <s:hidden name="detailID" value="%{detailID}" />
                                            <s:hidden name="Sname" value="%{Sname}" />
                                            <s:hidden name="ScreateDate" value="%{ScreateDate}" />
                                            <s:hidden name="SstatusID" value="%{SstatusID}" />
                                            <s:hidden name="SuserID" value="%{SuserID}" />
                                            <s:hidden name="pageNumber" value="1"/>
                                            <s:submit value="Delete" onclick="return confirm('Are you sure you want to delete?')" />
                                        </s:form>
                                    </s:else>
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


    <s:if test="%{numberOfPage > 1}">
        <s:iterator begin="1" end="%{numberOfPage}" var="i">
            <div style="float: left; width: 20px">
                <s:form action="managerSearch" method="POST">
                    <s:hidden name="pageNumber" value="%{i}"/>
                    <s:hidden name="Sname" value="%{Sname}"/>
                    <s:hidden name="ScreateDate" value="%{ScreateDate}"/>
                    <s:hidden name="SuserID" value="%{SuserID}"/>
                    <s:hidden name="SstatusID" value="%{SstatusID}"/>
                    <s:submit value="%{#i}"/>
                </s:form>
            </div>
        </s:iterator>
    </s:if>
    
    <%@include file="footer.jsp" %>
</body>
</html>
