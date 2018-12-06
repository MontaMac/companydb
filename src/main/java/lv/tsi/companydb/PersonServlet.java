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

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Connection conn = DriverManager
                    .getConnection("jdbc:derby://localhost:1527/companydb");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from APP.PERSON");
                    PrintWriter out = response.getWriter();
                    while (rs.next()) {
                        long id = rs.getLong("ID");
                        String fullName = rs.getString("FULL_NAME");
                        out.printf("%d %s\n", id, fullName);
                    }
                    conn.close();
                    out.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
