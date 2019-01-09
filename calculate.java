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
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author udit1
 */
public class calculate extends HttpServlet {

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
            int acc=0;
            int musicc=0;
            int hdc=0;
            int vegc=0;
            int nonvc=0;
            int vnonvc=0;
            int cpp=0;
            int foodc=0;
            
            String ac=request.getParameter("ac");
            String music=request.getParameter("music");
            String hd=request.getParameter("hd");
            String food=request.getParameter("food");
            int nperson=Integer.parseInt(request.getParameter("nperson"));
            
           Class.forName("com.mysql.jdbc.Driver");
           Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/birthday","root","12345");
           String qr="select * from acustom where year=2018";
           Statement st=con.createStatement();
           ResultSet rs=st.executeQuery(qr);
           
           while(rs.next())
           {
               acc=Integer.parseInt(rs.getString(1));
               musicc=Integer.parseInt(rs.getString(2));
               hdc=Integer.parseInt(rs.getString(3));
               vegc=Integer.parseInt(rs.getString(4));
               nonvc=Integer.parseInt(rs.getString(5));
               vnonvc=Integer.parseInt(rs.getString(6));
               cpp=Integer.parseInt(rs.getString(7));
               String year=rs.getString(8);
           }
           
           if(ac.equals("No"))
           {
            acc=0;
           }
           if(music.equals("No"))
           {
            musicc=0;
           } 
           if(hd.equals("No"))
           {
            hdc=0;
           }
           if(food.equals("Veg"))
           {
            foodc=vegc;
           }
           else if(food.equals("NonVeg"))
           {
            foodc=nonvc;
           }
           else
           {
            foodc=vnonvc;
           }
           
           int tcharge=acc+musicc+hdc+(foodc*nperson)+(cpp*nperson);
           out.println(tcharge);
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
