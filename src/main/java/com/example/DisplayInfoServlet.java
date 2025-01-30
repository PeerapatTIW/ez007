package com.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/display-info")
public class DisplayInfoServlet extends HttpServlet {
    private static final String FILE_PATH = "D:/mypro3/ez007/date/movie_info.txt";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // อ่านข้อมูลจากไฟล์
        List<String[]> records = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                records.add(line.split(",")); // แยกข้อมูลด้วยเครื่องหมาย ","
            }
        }

        // แสดงผลในรูปแบบ HTML
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html lang='en'>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<title>แสดงข้อมูลภาพยนตร์</title>");
            out.println("<style>");
            out.println("* {");
            out.println("    margin: 0;");
            out.println("    padding: 0;");
            out.println("    box-sizing: border-box;");
            out.println("}");
            out.println("body {");
            out.println("    font-family: Arial, sans-serif;");
            out.println("    background-color: #f4f4f9;");
            out.println("    color: #333;");
            out.println("}");
            out.println(".navbar {");
            out.println("    display: flex;");
            out.println("    justify-content: space-between;");
            out.println("    align-items: center;");
            out.println("    background-color: #007BFF;");
            out.println("    padding: 10px 20px;");
            out.println("    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);");
            out.println("}");
            out.println(".navbar h1 {");
            out.println("    color: white;");
            out.println("    font-size: 20px;");
            out.println("}");
            out.println(".navbar ul {");
            out.println("    list-style: none;");
            out.println("    display: flex;");
            out.println("    gap: 20px;");
            out.println("}");
            out.println(".navbar ul li {");
            out.println("    display: inline;");
            out.println("}");
            out.println(".navbar ul li a {");
            out.println("    text-decoration: none;");
            out.println("    color: white;");
            out.println("    font-size: 16px;");
            out.println("    padding: 8px 12px;");
            out.println("    transition: background-color 0.3s ease;");
            out.println("}");
            out.println(".navbar ul li a:hover {");
            out.println("    background-color: #0056b3;");
            out.println("    border-radius: 5px;");
            out.println("}");
            out.println(".content {");
            out.println("    display: flex;");
            out.println("    flex-direction: column;");
            out.println("    align-items: center;");
            out.println("    justify-content: center;");
            out.println("    height: calc(100vh - 60px);");
            out.println("    text-align: center;");
            out.println("}");
            out.println("h2 {");
            out.println("    font-size: 28px;");
            out.println("    margin-bottom: 20px;");
            out.println("}");
            out.println("table {");
            out.println("    width: 80%;");
            out.println("    border-collapse: collapse;");
            out.println("    margin: 20px 0;");
            out.println("    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);");
            out.println("}");
            out.println("th, td {");
            out.println("    border: 1px solid #ddd;");
            out.println("    padding: 10px;");
            out.println("    text-align: left;");
            out.println("}");
            out.println("th {");
            out.println("    background-color: #f4f4f4;");
            out.println("    color: #333;");
            out.println("}");
            out.println("tr:nth-child(even) {");
            out.println("    background-color: #f9f9f9;");
            out.println("}");
            out.println(".logout-btn {");
            out.println("    background-color: #dc3545;");
            out.println("    color: white;");
            out.println("    padding: 8px 16px;");
            out.println("    border-radius: 5px;");
            out.println("    text-decoration: none;");
            out.println("    font-size: 16px;");
            out.println("    transition: background-color 0.3s ease;");
            out.println("}");
            out.println(".logout-btn:hover {");
            out.println("    background-color: #c82333;");
            out.println("}");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");

            // Navbar
            out.println("<nav class='navbar'>");
            out.println("<h1>เว็บไซต์ของฉัน</h1>");
            out.println("<ul>");
            out.println("<li><a href='home.html'>หน้าหลัก</a></li>");
            out.println("<li><a href='display-info'>ข้อมูลหนัง</a></li>");
            out.println(" <li><a href='search.html'>ค้นหาข้อมูล</a></li>");
            out.println("<li><a href='services.html'>เพิ่มข้อมูลหนัง</a></li>");
            out.println("<li><a href='index.html' class='logout-btn'>ออกจากระบบ</a></li>");
            out.println("</ul>");
            out.println("</nav>");

            // ข้อมูลภาพยนตร์
            out.println("<div class='content'>");
            out.println("<h2>ข้อมูลภาพยนตร์</h2>");
            out.println("<table>");
            out.println("<tr><th>ชื่อภาพยนตร์</th><th>ประเภท</th><th>ผู้กำกับ</th><th>ความยาว</th><th>เรื่องย่อ</th></tr>");

            for (String[] record : records) {
                out.println("<tr>");
                out.println("<td>" + record[0] + "</td>");
                out.println("<td>" + record[1] + "</td>");
                out.println("<td>" + record[2] + "</td>");
                out.println("<td>" + record[3] + "</td>");
                out.println("<td>" + record[4] + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("<br><a href='services.html'>กลับไปหน้าเพิ่มข้อมูล</a>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
