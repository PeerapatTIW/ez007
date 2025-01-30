package com.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/deleteMovie")
public class DeleteMovieServlet extends HttpServlet {
    private static final String FILE_PATH = "D:/mypro3/ez007/date/movie_info.txt";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String titleToDelete = request.getParameter("title");

        List<String[]> records = new ArrayList<>();

        // อ่านไฟล์และเก็บข้อมูลที่ไม่ตรงกับชื่อที่ต้องการลบ
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] record = line.split(",");
                if (!record[0].equals(titleToDelete)) {
                    records.add(record);  // เก็บข้อมูลที่ไม่ถูกลบ
                }
            }
        }

        // เขียนข้อมูลที่เหลือกลับไปที่ไฟล์
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (String[] record : records) {
                writer.write(String.join(",", record));
                writer.newLine();
            }
        }

        // เปลี่ยนเส้นทางกลับไปที่หน้าแสดงผลการค้นหา
        response.sendRedirect("search.html");
    }
}
