package com.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet("/save-movie")
public class savemovie extends HttpServlet {
    private static final String FILE_PATH = "D:/mypro3/ez007/date/movie_info.txt";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // รับข้อมูลจากฟอร์ม
        String movieTitle = request.getParameter("movie_title");
        String genre = request.getParameter("genre");
        String director = request.getParameter("director");
        String duration = request.getParameter("duration");
        String synopsis = request.getParameter("synopsis"); // รับเรื่องย่อ

        // เขียนข้อมูลลงไฟล์
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(movieTitle + "," + genre + "," + director + "," + duration + "," + synopsis);
            writer.newLine();
        }

        // แสดงผลตอบกลับ
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<h1>ข้อมูลภาพยนตร์ถูกบันทึกเรียบร้อยแล้ว</h1>");
            out.println("<a href=\"services.html\">กลับไปหน้าเพิ่มข้อมูล</a>");
        }
    }
}
