<%-- 
    Document   : create
    Created on : Jul 6, 2020, 11:52:07 PM
    Author     : nguyennst
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Account</title>
    </head>
    <body>
        
        <%@include file="headerRegister.jsp" %>
        <%@include file="menuaRegister.jsp" %>
        
        <center>
            
            <h2>
            <font color="green">
            <h2 style="font-size: 20px">Register to continue</h2>       
            </font>
        </h2>

        <div style="padding: 20px; margin: 50px; text-align: left; width: 300px; min-height: 50px; background-color: #F5F5F5; border: 1px solid #CDCDCD;">
            <s:form action="createAction" method="POST">
                <s:textfield name="email" label="Email" />
                <s:password name="password" label="Password"/>
                <s:password name="confirm" label="Confirm Password"/>
                <s:textfield name="name" label="Name"/>
                <s:textfield name="phone" label="Phone" type="number"/>
                <s:textfield name="address" label="Address"/>
                <s:submit value="Register" />
            </s:form>
        </div>
    </center>
    <br/><br/><br/><br/><br/>
    <br/><br/><br/><br/><br/>
    <%@include file="footer.jsp" %>
    </body>
</html>
