package com.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.*;

@WebServlet("/editMovie")
public class EditMovieServlet extends HttpServlet {
    private static final String FILE_PATH = "D:/mypro3/ez007/date/movie_info.txt";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        response.setContentType("text/html;charset=UTF-8");

        String[] recordToEdit = null;

        // ค้นหาข้อมูลที่จะแก้ไข
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] record = line.split(",");
                if (record[0].equals(id)) {
                    recordToEdit = record;
                    break;
                }
            }
        }

        try (PrintWriter out = response.getWriter()) {
            if (recordToEdit == null) {
                out.println("<p>Record not found.</p>");
                out.println("<a href='search.html'>Back to Search</a>");
            } else {
                out.println("<!DOCTYPE html>");
                out.println("<html lang='en'>");
                out.println("<head><meta charset='UTF-8'><title>Edit Movie</title></head>");
                out.println("<body>");
                out.println("<h1>Edit Movie</h1>");
                out.println("<form action='editMovie' method='post'>");
                out.println("<input type='hidden' name='oldId' value='" + recordToEdit[0] + "'>");
                out.println("<label>Title: <input type='text' name='movie_title' value='" + recordToEdit[1] + "'></label><br>");
                out.println("<label>Genre: <input type='text' name='genre' value='" + recordToEdit[2] + "'></label><br>");
                out.println("<label>Director: <input type='text' name='director' value='" + recordToEdit[3] + "'></label><br>");
                out.println("<label>Duration: <input type='text' name='duration' value='" + recordToEdit[4] + "'></label><br>");
                out.println("<button type='submit'>Save</button>");
                out.println("</form>");
                out.println("<a href='search.html'>Back to Search</a>");
                out.println("</body></html>");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oldId = request.getParameter("oldId");
        String movieTitle = request.getParameter("movie_title");
        String genre = request.getParameter("genre");
        String director = request.getParameter("director");
        String duration = request.getParameter("duration");

        List<String[]> records = new ArrayList<>();
        boolean updated = false;

        // อ่านไฟล์และอัปเดตข้อมูล
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] record = line.split(",");
                if (record[0].equals(oldId)) {
                    record[1] = movieTitle;
                    record[2] = genre;
                    record[3] = director;
                    record[4] = duration;
                    updated = true;
                }
                records.add(record);
            }
        }

        // เขียนข้อมูลกลับไปที่ไฟล์
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (String[] record : records) {
                writer.write(String.join(",", record));
                writer.newLine();
            }
        }

        try (PrintWriter out = response.getWriter()) {
            if (updated) {
                out.println("<p>Record updated successfully.</p>");
            } else {
                out.println("<p>Record not found.</p>");
            }
            out.println("<a href='search.html'>Back to Search</a>");
        }
    }
}
