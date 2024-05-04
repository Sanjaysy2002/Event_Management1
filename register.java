package Controller;


import java.io.IOException;
import java.io.PrintWriter;

import Model.Registration;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet(name = "register", urlPatterns = {"/register"})

public class register extends HttpServlet{
	
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        // type of the response sent to the client or browser
        PrintWriter out = response.getWriter();//html code can be written in jsp
        HttpSession session = request.getSession();//predefine method present in httprequest interface //creating session object
        Registration reg = new Registration(session);
        try {
            if (request.getParameter("register") != null) { //getparameter predefined method present in ServletRequest interface

                String name = request.getParameter("name");
                String phone = request.getParameter("phone");
                String email = request.getParameter("email");
                String pw = request.getParameter("pw");
                String cp = request.getParameter("cp");
                
                System.out.println(pw);

                if (pw.equals(cp)) {
                    String status = reg.Registration(name, phone, email, pw);
                    System.out.println(status);
                    
                    
                    if (status.equals("existed")) {

                        request.setAttribute("status", "Existed record");
                        RequestDispatcher rd1 = request.getRequestDispatcher("Registration.jsp");
                        rd1.forward(request, response); // to navigate one jsp to another 

                    } else if (status.equals("success")) {
                        request.setAttribute("status", "Successfully Registered");
                        RequestDispatcher rd1 = request.getRequestDispatcher("login.jsp");
                        rd1.forward(request, response);

                    } else if (status.equals("failure")) {
                        request.setAttribute("status", "Registration failed");
                        RequestDispatcher rd1 = request.getRequestDispatcher("Registration.jsp");
                        rd1.forward(request, response);

                    }
                }           } 
                
            
                else if (request.getParameter("login") != null) {
               String email = request.getParameter("email");
              String pass = request.getParameter("pw");
               String status = reg.login(email, pass);
               if (status.equals("success")) {
            	   RequestDispatcher rd1 = request.getRequestDispatcher("Eventcategories.jsp");
                  rd1.forward(request, response);

              } else if (status.equals("failure")) {
                  request.setAttribute("status", "Login failed");
                 RequestDispatcher rd1 = request.getRequestDispatcher("login.jsp");
                 rd1.forward(request, response);
              }
            } else if (request.getParameter("logout") != null) {
                session.invalidate();
                RequestDispatcher rd1 = request.getRequestDispatcher("Registration.jsp");
                rd1.forward(request, response);
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

   
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}



