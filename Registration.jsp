<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

    <head>
        <title>EventManagement
        </title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width">
        <script type = "text/javascript" src = "https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.1/jquery.validate.min.js"></script>
    </head>
    <body background="images/ab.jpg">
        <%@include file="Header.jsp"%>
        
        <script>



            //            jQuery.validator.addMethod( name, method [, message ] )
//            
// value---> "current value of the validated element".
//elememt---> " element to be validated ".

            jQuery.validator.addMethod("checkemail", function(value, element) {
                return /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(value) || /^[0-9]{10}$/.test(value);
            });
            jQuery(document).ready(function($) {
                $("#signup").validate({
                    //ignore: [],
                    rules: {
                        name: {
                            required: true
                        },
                        email: {
                            required: true,
                            checkemail: true
                        },
                        phone: {
                            required: true,
                            minlength: 10,
                            maxlength: 10
                        },
                        pw: {
                            required: true,
                            minlength: 6
                        },
                        cp: {
                            required: true,
                            minlength: 6,
                            equalTo: "#pw"
                        }
                    },
                    messages: {
                        name: {
                            required: "Please enter the name."
                        },
                        email: {
                            required: "Please enter the email.",
                            email: "Please enter valid email id"
                        },
                        phone: {
                            required: "Please enter the number.",
                            minlength: "Please enter the  10 digit number .",
                            maxlength: "more than 10 digits."
                        },
                        pw: {
                            required: "Please enter the password.",
                            minlength: "Please enter the password greater than or equal to  6.",
                        },
                        cp: {
                            required: "Please reenter the password.",
                            minlength: "Please enter the password greater than or equal to 6.",
                            equalTo: "mismatch password"
                        }
                    }
                });
            });
        </script>
        <style>
      



.container{
    width: 100%;
    max-width: 550px;
    background: rgba(0, 0, 0, 0.5);
    padding: 50px;
    margin: 0 28px;
    border-radius: 10px;
    box-shadow: inset -2px 2px 2px white;
}

.form-title{
    font-size: 26px;
    font-weight: 600;
    text-align: center;
    padding-bottom: 6px;
    color: white;
    text-shadow: 2px 2px 2px black;
    border-bottom: solid 1px white;
}

.main-user-info{
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
    padding: 20px 0;
}

.user-input-box:nth-child(2n){
    justify-content: end;
}

.user-input-box{
    display: flex;
    flex-wrap: wrap;
    width: 50%;
    padding-bottom: 15px;
}

.user-input-box label{
    width: 95%;
    color: white;
    font-size: 20px;
    font-weight: 400;
    margin: 5px 0;
}

.user-input-box input{
    height: 40px;
    width: 95%;
    border-radius: 7px;
    outline: none;
    border: 1px solid grey;
    padding: 0 10px;
}

.gender-title{
    color:white;
    font-size: 24px;
    font-weight: 600;
    border-bottom: 1px solid white;
}

.gender-category{
    margin: 15px 0;
    color: white;
}

.gender-category label{
    padding: 0 20px 0 5px;
}

.gender-category label,
.gender-category input,
.form-submit-btn input{
    cursor: pointer;
}

.form-submit-btn{
    margin-top: 10px;
}

.form-submit-btn input{
    display: block;
    width: 20%;
    margin-top: 10px;
    font-size: 20px;
    padding: 10px;
    border:none;
    border-radius: 3px;
    color: rgb(209, 209, 209);
    background: rgba(63, 114, 76, 0.7);
}

.form-submit-btn input:hover{
    background: rgba(56, 204, 93, 0.7);
    color: rgb(255, 255, 255);
}



    .user-input-box{
        margin-bottom: 12px;
        width: 100%;
    }

    .user-input-box:nth-child(2n){
        justify-content: space-between;
    }

    .gender-category{
        display: flex;
        justify-content: space-between;
        width: 100%;
    }

    .main-user-info{
        max-height: 380px;
        overflow: auto;
    }

    .main-user-info::-webkit-scrollbar{
        width: 0;
    }
}
        
            .error{   
                color:red;
            }
            input{
                display: block;
            }
            .body{
            background: images/hi.jpg;
            background-size: cover;
            font-family: arial;
            color:#333;
            font-size: 13px;
            margin: 0;
            
            }
            #msg
            {
                background: red;
                color:black;
                border: 1px solid red;
                width:24%;
                font-weight: bold;
                font-size: 25px;
                padding: 5px;
            }
        </style>
    <center>

        <br><br>

        <% if (request.getAttribute("status") != null) {%>
        <div id="msg">  <%= request.getAttribute("status")%></div>
        <%}%>


        <form method="POST" id ="signup" action="register">
            <font color="green"> 

           <!--  <h2> Event management Registration </h2>
            </font>
            <table>
                <tr>
                    <td> NAME  </td>
                    <td> <input type="text" id="name" name="name"></td>
                </tr>   
                <tr>
                    <td> PHONE NUMBER   </td>
                    <td> <input type="number" name="phone" > </td>
                </tr>
                <tr>
                    <td> Email  </td>
                    <td>   <input type="email" name="email"></td>
                </tr>
                <tr>
                    <td> Password  </td>
                    <td>   <input type="password" id="pw" name="pw"></td>
                </tr>
                <tr>
                    <td> Confirm Password  </td>
                    <td>   <input type="password" name="cp"></td> 
                </tr>
                <tr>
                    <td> </br> <input type="submit" name="register" value="Register"></td>
                </tr>
            </table>--> 
            <div class="container">
      
          <div class="user-input-box">
            <label for="username">Name</label>
            <input type="text"
                    id="name"
                    name="name"
                    placeholder="Enter Username"/>
          </div>
          <div class="user-input-box">
            <label for="email">Email</label>
            <input type="email"
                    name="email"
                    placeholder="Enter Email"/>
          </div>
          <div class="user-input-box">
            <label for="phoneNumber">Phone Number</label>
            <input type="number"
                    name="phone"
                    placeholder="Enter Phone Number"/>
          </div>
          <div class="user-input-box">
            <label for="password">Password</label>
            <input type="password"
                    id="pw"
                    name="pw"
                    placeholder="Enter Password"/>
          </div>
          <div class="user-input-box">
            <label for="confirmPassword">Confirm Password</label>
            <input type="password"
                    name="cp"
                    placeholder="Confirm Password"/>
          </div>
        </div>
            <div class="form-submit-btn">
          <input type="submit" name="register" value="Register">
            
        </form>
    </center>
      <%@include file="Footer.jsp"%>
</body>
</html>




