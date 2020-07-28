<%-- 
    Document   : confirmEmail
    Created on : Jul 9, 2020, 2:26:46 PM
    Author     : nguyennst
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirm Email</title>
    </head>
    <body>
        <h1>Welcome, <s:property value="%{#session.NAME}"/></h1>
        <div style="padding: 20px; margin: 50px; text-align: left; width: 300px; min-height: 50px; background-color: #F5F5F5; border: 1px solid #CDCDCD;">
            <s:form action="confirmEmail" method="POST">
                <s:textfield name="code" label="Verify Code"/>
                <s:submit value="Confirm" />
            </s:form>
        </div>
    </body>
</html>
