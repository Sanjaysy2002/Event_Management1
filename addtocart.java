package Controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import Model.Registration;

@WebServlet(name = "addtocart", urlPatterns = {"/addtocart"})
public class addtocart extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        Registration r = new Registration(session);
        try {
            
            
if (session.getAttribute("name") != null && request.getParameter("bookevent") != null) {
                String status = r.Booknow(request);
                if (status.equals("success")) {
                    request.setAttribute("status", "Booking successful.<a href='Eventstatus.jsp'>Click here</a> to check status.");
                    RequestDispatcher rd = request.getRequestDispatcher("EventBooking.jsp?event_id=" + request.getParameter("event_id"));
                    rd.forward(request, response);
                } else if (status.equals("failure")) {
                    request.setAttribute("status", "Booking failed");
                    RequestDispatcher rd = request.getRequestDispatcher("EventBooking.jsp?event_id=" + request.getParameter("event_id"));
                    rd.forward(request, response);
                } else if (status.equals("existed")) {
                    request.setAttribute("status", "Date not available for event");
                    RequestDispatcher rd = request.getRequestDispatcher("EventBooking.jsp?event_id=" + request.getParameter("event_id"));
                    rd.forward(request, response);
                }
            } 
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public addtocart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
        processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
        processRequest(request, response);
	}

}
