package lv.tsi.companydb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "PersonServlet", urlPatterns = "/persons")
public class PersonServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String login = request.getParameter("fullname");
            Connection conn = DriverManager
                    .getConnection("jdbc:derby://localhost:1527/companydb");
            PreparedStatement stmt = conn.prepareStatement( "select * " + "from APP.PERSON where FULL_NAME =?");
            stmt.setString(1,login);
            ResultSet rs = stmt.executeQuery();
            PrintWriter out = response.getWriter();
            while (rs.next()) {
                long id = rs.getLong("ID");
                String fullName = rs.getString("FULL_NAME");
                double salary = rs.getDouble("SALARY");
                out.printf("%d %s %.2f\n", id, fullName, salary);
            }
            conn.close();
            out.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}