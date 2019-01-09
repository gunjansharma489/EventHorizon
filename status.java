/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author udit1
 */
public class status extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String unum=request.getParameter("num");
            String pck=request.getParameter("pck");
            
            Class.forName("com.mysql.jdbc.Driver");
           Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/birthday","root","12345");
          
           if(pck.equals("Platinum"))
           {
           String st1="select * from platinum where unumber=?";
           PreparedStatement ps=con.prepareStatement(st1);
           ps.setString(1, unum);
           ResultSet rs=ps.executeQuery();
           
           if(rs.next())
           {
               RequestDispatcher rd=request.getRequestDispatcher("status.jsp");
               rd.forward(request,response);
           }
           else
           {
               RequestDispatcher rd=request.getRequestDispatcher("status.html");
               rd.include(request,response);
               out.println("<script type=\"text/javascript\">");
                out.println("alert('incorrect Unique number or package')");
                out.println("</script>");
           }
           }else 
               if(pck.equals("Golden"))
           {
           String st1="select * from golden where unumber=?";
           PreparedStatement ps=con.prepareStatement(st1);
           ps.setString(1, unum);
           ResultSet rs=ps.executeQuery();
           
           if(rs.next())
           {
               RequestDispatcher rd=request.getRequestDispatcher("status.jsp");
               rd.forward(request,response);
               out.println("hello");
           }
           else
           {
               RequestDispatcher rd=request.getRequestDispatcher("status.html");
               rd.include(request,response);
               out.println("<script type=\"text/javascript\">");
                out.println("alert('incorrect Unique number or package')");
                out.println("</script>");
           }
           }else
                   if(pck.equals("Silver"))
           {
           String st1="select * from silver where unumber=?";
           PreparedStatement ps=con.prepareStatement(st1);
           ps.setString(1, unum);
           ResultSet rs=ps.executeQuery();
           
           if(rs.next())
           {
               RequestDispatcher rd=request.getRequestDispatcher("status.jsp");
               rd.forward(request,response);
           }
           else
           {
               RequestDispatcher rd=request.getRequestDispatcher("status.html");
               rd.include(request,response);
               out.println("<script type=\"text/javascript\">");
                out.println("alert('incorrect Unique number or package')");
                out.println("</script>");
           }
           }else
                       {
           String st1="select * from custom where unumber=?";
           PreparedStatement ps=con.prepareStatement(st1);
           ps.setString(1, unum);
           ResultSet rs=ps.executeQuery();
           
           if(rs.next())
           {
               RequestDispatcher rd=request.getRequestDispatcher("statusc.jsp");
               rd.forward(request,response);
           }
           else
           {
               RequestDispatcher rd=request.getRequestDispatcher("status.html");
               rd.include(request,response);
               out.println("<script type=\"text/javascript\">");
                out.println("alert('incorrect Unique number or package')");
                out.println("</script>");
           }
                       }
        }catch(Exception e)
        {
            out.println(e);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
