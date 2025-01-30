// SearchServlet.java
package com.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.*;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    private static final String FILE_PATH = "D:/mypro3/ez007/date/movie_info.txt";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("query");
        response.setContentType("text/html;charset=UTF-8");

        List<String[]> matchedRecords = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] record = line.split(",");
                if (record[0].toLowerCase().contains(query.toLowerCase())) {
                    matchedRecords.add(record);
                }
            }
        }

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html lang='en'>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<title>Search Results</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f9f9f9; }");
            out.println(".navbar { display: flex; justify-content: space-between; align-items: center; background-color: #007bff; padding: 10px 20px; color: white; box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); }");
            out.println(".navbar a { text-decoration: none; color: white; margin: 0 10px; font-weight: bold; }");
            out.println(".container { padding: 20px; }");
            out.println("table { width: 100%; border-collapse: collapse; margin-top: 20px; }");
            out.println("table th, table td { border: 1px solid #ddd; padding: 8px; text-align: left; }");
            out.println("table th { background-color: #007bff; color: white; }");
            out.println(".button { padding: 5px 10px; background-color: #007bff; color: white; text-decoration: none; border-radius: 3px; }");
            out.println(".button:hover { background-color: #0056b3; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");

            // Navbar
            out.println("<div class='navbar'>");
            out.println("<span>Movie Management</span>");
            out.println("<div>");
            out.println("<a href='home.html'>Home</a>");
            out.println("<a href='search.html'>Search</a>");
            out.println("<a href='addMovie.html'>Add Movie</a>");
            out.println("</div>");
            out.println("</div>");

            // Search Results
            out.println("<div class='container'>");
            out.println("<h2>Search Results</h2>");

            if (matchedRecords.isEmpty()) {
                out.println("<p>No results found for your query.</p>");
            } else {
                out.println("<table>");
                out.println("<thead><tr><th>Title</th><th>Genre</th><th>Director</th><th>Duration</th><th>Actions</th></tr></thead>");
                out.println("<tbody>");
                for (String[] record : matchedRecords) {
                    out.println("<tr>");
                    out.println("<td>" + record[0] + "</td>");
                    out.println("<td>" + record[1] + "</td>");
                    out.println("<td>" + record[2] + "</td>");
                    out.println("<td>" + record[3] + "</td>");
                    out.println("<td>");
                    out.println("<a class='button' href='editMovie?id=" + record[0] + "'>Edit</a>");
                    out.println("<a class='button' href='deleteMovie?title=" + record[0] + "'>Delete</a>");
                    out.println("</td>");
                    out.println("</tr>");
                }
                out.println("</tbody>");
                out.println("</table>");
            }

            out.println("<a href='search.html' class='button'>Search Again</a>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
