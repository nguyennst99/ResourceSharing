<%-- 
    Document   : logingoogle
    Created on : Jul 7, 2020, 3:55:22 PM
    Author     : nguyennst
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login via Google</title>
        <script src="https://apis.google.com/js/platform.js" async defer></script>
        <meta name="google-signin-client_id" content="859062998108-cgr90j5fhbmqc38dm0kubgfvanh7oibq.apps.googleusercontent.com">
    </head>
    <body>


        <div class="g-signin2" data-onsuccess="onSignIn" id="myP"></div>

        <div id="status">
        </div>

        <script type="text/javascript">
            function onSignIn(googleUser) {

                var profile = googleUser.getBasicProfile();
                document.getElementById("myP").style.visibility = "hidden";
                document.getElementById("status").innerHTML = 'Welcome '+profile.getName()
                
                window.location.href = 'loginGG?email=' + profile.getEmail() + '&name=' + profile.getName();
            }
        </script>





        <button onclick="myFunction()">Sign Out</button>

        <script>
            function myFunction() {
                gapi.auth2.getAuthInstance().disconnect();
                location.reload();
            }
        </script>




    </body>
</html>
