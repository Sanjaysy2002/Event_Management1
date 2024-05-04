package Model;

import  java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class Registration {
	
	Connection con=null;
	HttpSession session;
	
	public Registration(HttpSession session) {
		String url="jdbc:mysql://localhost:3306/eventmanagement?user=root&password=tiger";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			 con=DriverManager.getConnection(url);
			 this.session=session;
			 
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	//registration method
	
	 public String Registration(String name, String phone, String email, String pw) {
	        PreparedStatement ps;
	        String status = "";
	        try {
	            Statement st = null;
	            ResultSet rs = null;
	            st = con.createStatement();
	            rs = st.executeQuery("select * from userdata where phone='" + phone + "' or email='" + email + "';"); //used to execute only DQL quries
	            boolean b = rs.next();//used to check whether the record is present or not
	            if (b) {
	                status = "existed";
	            } else {
	                ps = (PreparedStatement)con.prepareStatement("insert into userdata values(0,?,?,?,?,now())");//prepareStatement is used to execute sql queries
	                ps.setString(1, name);//predefined method in PS interface//insert data to session
	                ps.setString(2, phone);
	                ps.setString(3, email);
	                ps.setString(4, pw);
	                int a = ps.executeUpdate();//DML Queries 
	                if (a > 0) {
	                    status = "success";
	                } else {
	                    status = "failure";
	                }
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return status;
	    }
	 
	 
	
	
	public ArrayList<Dproduct> get_eventinfo(String event_category) {
	
		System.out.println("category ="+event_category);
		
        ArrayList<Dproduct> al = new ArrayList<Dproduct>();
        Statement st;
        ResultSet rs;
        try {
            st = con.createStatement();
           // String qry = " select * from events where event_category='" + event_category + " ';";
            String qry = " select * from events  where event_category='"+event_category+""
            		+ "'";

            rs = st.executeQuery(qry);
            System.out.println(event_category);
            while (rs.next()) {
                Dproduct d = new Dproduct();
                d.setP_id(rs.getString("event_id")); // 1..r---db 2.. pass the data or value to Dproduct class set method
                d.setP_image(rs.getString("event_image"));
                //System.out.println("image:"+rs.getString("event_image"));
                d.setP_name(rs.getString("event_name"));
                d.setP_cost(rs.getString("event_cost"));
                d.setP_details(rs.getString("event_details"));
                al.add(d);
            }

            
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(al);
        return al;

    }


public Dproduct getEventFormInfo(String event_id) {
        Statement st = null;
        ResultSet rs = null;
        Dproduct s = null;
        try {
            st = con.createStatement();
            rs = st.executeQuery("select * from events where event_id= '" + event_id + "'");
            boolean b = rs.next();
            if (b == true) {
                s = new Dproduct();
                s.setP_name(rs.getString("event_name"));
                s.setP_cost(rs.getString("event_cost"));
            } else {
                s = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return s;
    }

public String Booknow(HttpServletRequest request) {
        String status = "";
        ResultSet rs = null;
        try {
            Statement st = con.createStatement();
            rs = st.executeQuery("select event_id from bookevent where eventdate= '" + request.getParameter("edate") + "'");
            boolean b = rs.next();
            if (b == true) {
                status = "existed";
            } else {
                String qry = "insert into bookevent "
                        + "select 0,event_image,event_name,'" 
                        + request.getParameter("pno") + "', '" 
                        + request.getParameter("email") + "','" 
                        + request.getParameter("address") +
                        "',event_cost,'" + session.getAttribute("name") 
                        + "'," + session.getAttribute("id")
                        + " ,'pending',now(),'" 
                        + request.getParameter("edate") +
                        "' from events where event_id=" + 
                        request.getParameter("event_id") + ";";
                int a = st.executeUpdate(qry);
                if (a > 0) {
                    status = "success";
                } else {
                    status = "failure";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

	
public String login(String email,String pw) {
	String status1 = "";
	String id="";
	String name="";
	String emails="";
	
    Statement st = null;
    ResultSet rs = null;

    try {
		st = con.createStatement();
        rs = st.executeQuery("select * from userdata where email='" + email + "' and password='" + pw + "';");
       // System.out.println(email);
      //  System.out.println(pw);
        boolean b = rs.next();
        if(b==true) {
            id = rs.getString("id");
            name = rs.getString("name");
            emails = rs.getString("email");

            session.setAttribute("name", name);
            session.setAttribute("email", emails);
            session.setAttribute("id", id);

            status1 = "success";
        }else {
        	status1 = "failure";
        }
    } catch (SQLException e) {
		e.printStackTrace();
	}
	
	return status1;
}


public Dproduct getInfo() {
	
	Dproduct d=null;
	Statement st = null;
    ResultSet rs = null;

    try {
		st = con.createStatement();
		
        rs = st.executeQuery("select * from userdata where sl= '" + session.getAttribute("id") + "'");
        boolean b = rs.next();
        
        if (b == true) {
            d = new Dproduct();
            d.setP_name(rs.getString("name"));
            d.setPhone(rs.getString("phone"));
            d.setEmail(rs.getString("email"));
        }else {
            d = null;
        }


        
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	return d;
}



}
