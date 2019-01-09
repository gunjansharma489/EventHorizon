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
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author udit1
 */
public class Customized extends HttpServlet {

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
            
            String Name=request.getParameter("name");
            String Address=request.getParameter("address");
            String Phone=request.getParameter("phone");
            String Bname=request.getParameter("bname");
            String Pdate=request.getParameter("pdate");            
            String AC=request.getParameter("ac");
            String Music=request.getParameter("music");
            String HD=request.getParameter("hd");
            String Ptime=request.getParameter("tparty");
            String Food=request.getParameter("food");
            String Nperson=request.getParameter("nperson");
            String Tcharge=request.getParameter("tcharge");
            
             
            Class.forName("com.mysql.jdbc.Driver");
           Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/birthday","root","12345");
          
           String st1="select * from custom where pdate=?";
           PreparedStatement ps1=con.prepareStatement(st1);
           ps1.setString(1,Pdate);
           ResultSet rs=ps1.executeQuery();
           if(rs.next())
           {
            RequestDispatcher rd=request.getRequestDispatcher("custtry.jsp");
            rd.include(request,response);
            out.println("<script type=\"text/javascript\">");
                out.println("alert('Sorry, The date is already reserved')");
                out.println("</script>");
           }
           else
           {
            int count=0;
               String qr3="select count(*) as name from custom";
               Statement ps3=con.createStatement();
           ResultSet rs3=ps3.executeQuery(qr3);
           
           
           while(rs3.next())
           {
           count=rs3.getInt("name");
           }
           count=count+1;
           String Unum=String.valueOf(count);
           Unum="C0"+Unum;
           
           String st="insert into custom values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
           PreparedStatement ps= con.prepareStatement(st);
           ps.setString(1,Name);
           ps.setString(2,Address);
           ps.setString(3,Phone);
           ps.setString(4,Bname);
           ps.setString(5,Pdate);
           ps.setString(6,AC);
           ps.setString(7,Music);
           ps.setString(8,HD);
           ps.setString(9,Ptime);
           ps.setString(10,Food);
           ps.setString(11,Nperson);
           ps.setString(12,Tcharge);
           ps.setString(13,Unum);
           
            ps.executeUpdate();
            
            RequestDispatcher rd=request.getRequestDispatcher("custtry.jsp");
            rd.include(request,response);
            out.println("<script type=\"text/javascript\">");
                out.println("alert('Successfully Registered. Your unique registered number is"+Unum+"')");
                out.println("</script>");
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
