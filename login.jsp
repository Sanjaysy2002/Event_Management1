<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width">
        <script type = "text/javascript" src = "https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.1/jquery.validate.min.js"></script>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" >
    </head>
    <body >
        <%@include file="Header.jsp"%>
        <script>
            jQuery.validator.addMethod("checkemail", function(value, element) {
                return /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(value) || /^[0-9]{10}$/.test(value);
            }, "Please enter the email format as abc@gmail.com");

            jQuery(document).ready(function($) {
                $("#login").validate({
                    rules: {
                        email: {
                            required: true,
                            checkemail: true
                        },
                        pw: {
                            required: true,
                            minlength: 6
                        },
                    },
                    messages: {
                        email: {
                            required: "Please enter the email.",
                        },
                        pw: {
                            required: "Please enter the password.",
                            minlength: "Please enter the password greater than or equal to 6.",
                        },
                    }
                });
            });



        </script>
        <style>
            .error{
                color:red;
            }
            .button{
                padding: 5px;
                width: 6%;
                background: cornflowerblue;
                color: white;
            }

            #msg
            {
                background: green;
                color:black;
                border: 1px solid green;
                width:24%;
                font-weight: bold;
                font-size: 25px;
                padding: 5px;
            }
            td input{
                display:block;
            }
            @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@200&display=swap');
*{
    padding: 0;
    margin: 0;
    box-sizing: border-box;
  
}

body{
    background: linear-gradient(90deg, rgba(2,0,36,1) 0%, rgba(75,14,154,1) 35%, rgba(0,212,255,1) 100%);
    font-family: 'Poppins', sans-serif;

}
form{
    border-radius: 20px;
    margin-top: 150px !important;
    width: 24% !important;
    background-color: white !important;
    padding: 10px;
     font-family: 'Poppins', sans-serif;
    
}

.btn-primary{
    width: 100%;
    border: none;
    border-radius: 50px;
    background: linear-gradient(90deg, rgba(2,0,36,1) 0%, rgba(75,14,154,1) 35%, rgba(0,212,255,1) 100%);
    
}

.form-control{
    color: rgba(0,0,0,.87);
    border-bottom-color: rgba(0,0,0,.42);
    box-shadow: none !important;
    border: none;
    border-bottom: 10px solid;
    border-radius: 10px 10px 0 0;
    box-sizing: border-box;
    }
 h4{
    font-size: 2rem !important;
    font-weight: 700;
 }  
 .form-label{
    font-weight: 800 !important;
 }
 .container
 {
    
 }
@media only screen and (max-width: 600px) {
    form {
        width: 100% !important;
    }
  }
        </style>

    <center>

        <% if (request.getAttribute("status") != null) {%>
        <div id="msg">  <%= request.getAttribute("status")%></div>
        <%}%>

        <form method="POST" id="login" action="register">
            <font color="blue" size="4">
            <h2> Login  </h2>
            </font>
            <br>
            <div class="container ">
                <div class="form-group col-md-4">
                    <label >Email address</label>
                    <input type="email" class="form-control" placeholder="Enter email"  name="email">
                </div>
                <div class="form-group col-md-4">
                    <label >Password</label>
                    <input type="password" class="form-control"  placeholder="Enter Password" name="pw">
                </div>
                <button type="submit" class="btn btn-primary" name="login">Submit</button>
                <div style="text-align: center; margin-top: 15px;">
                    <a href="forgotpass.jsp">Forgot password?</a>
                </div>
            </div>
        </form>

    </center>

    <%@include file="Footer.jsp"%>
</body>
</html>








