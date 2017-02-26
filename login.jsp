<%-- 
    Document   : login
    Created on : Mar 6, 2016, 12:09:36 PM
    Author     : hnd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    
          <link rel="stylesheet" type="text/css" href="login_style.css" />
        
    </head>
    <body>
        <h1 align="center">Login form</h1>
        <form method="post">
            <table style="background-color: background;width: 300px;height: 150px;" align="center">
                <tr>
                    <td style="color: white;">User Name :</td>
                    <td><input type="text" name="txtUN"/></td>
                </tr>
                <tr>
                    <td style="color: white;">Password :</td>
                    <td><input type="password" name="txtPW"/></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Submit" class="FontStyle" name="btnSub"/></td>
                    <td><input type="reset" name="Reset" class="fontstyle"/></td>
                </tr>
                    
            </table>
        </form>
        <%
            
            if(request.getParameter("txtUN")!=null && request.getParameter("txtPW") !=null){
            String name=request.getParameter("txtUN");
            String pass=request.getParameter("txtPW");
            
           if(name.equalsIgnoreCase("admin")&& pass.equalsIgnoreCase("admin123")){
               
            response.sendRedirect("Home.jsp");
           }
           else{
               out.println("<span align='center' style='red'><b>Invalid Use name or Password!</b></span>");
           }
            }
            %>
       
         <a href="Home.jsp">Home</a> 
            
    </body>
</html>