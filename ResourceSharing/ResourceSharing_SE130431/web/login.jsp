<%-- 
    Document   : login
    Created on : Jun 28, 2020, 4:50:36 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <script src="https://www.google.com/recaptcha/api.js"></script>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    </head>
    <body>



    <div id="login">
        <h3 class="text-center text-white pt-5">Login form</h3>
        <div class="container">
            <div id="login-row" class="row justify-content-center align-items-center">
                <div id="login-column" class="col-md-6">
                    <div id="login-box" class="col-md-12">
                        <form id="login-form" class="form" action="login" method="post">
                            <h3 class="text-center text-info">Login</h3>
                            <div class="form-group">
                                <label for="username" class="text-info">Username:</label><br>
                                <input type="text" name="userID" id="username" class="form-control" required="" placeholder="Enter userID">
                            </div>
                            <div class="form-group">
                                <label for="password" class="text-info">Password:</label><br>
                                <input type="password" name="password" id="password" class="form-control" required="" placeholder="Enter password">
                            </div>
                            <div class="form-group">

                                <input type="submit" name="submit" class="btn btn-info btn-md" value="Login">
                            </div>
                            <font color="red">
                            <s:property value="%{#request.INVALID}"/>
                            </font>
                            <div class="g-recaptcha" data-sitekey="6LcGUa0ZAAAAAP0-isW44R2_pCxPcAl1Sy_zsXmu"></div>                                
                            <div id="register-link" class="text-right">
                                <a href="create" class="text-info">Register here</a>
                            </div>
                            <div id="register-link" class="text-right">
                                <a href="logingg" class="text-info">Login via Google</a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</body>
</html>
