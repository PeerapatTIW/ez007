package com.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    // ชื่อผู้ใช้และรหัสผ่านที่กำหนดไว้ (Hardcoded สำหรับการทดลอง)
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "1234";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // รับข้อมูลจากฟอร์ม
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // ตั้งค่าประเภทเนื้อหา
        response.setContentType("text/html;charset=UTF-8");

        // ตรวจสอบชื่อผู้ใช้และรหัสผ่าน
        try (PrintWriter out = response.getWriter()) {
            if (USERNAME.equals(username) && PASSWORD.equals(password)) {
                // เข้าสู่ระบบสำเร็จ
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>เข้าสู่ระบบสำเร็จ</title>");
                out.println("<meta charset='UTF-8'>");
                out.println("</head>");
                out.println("<body style='font-family: Arial, sans-serif; text-align: center; margin-top: 50px;'>");
                out.println("<h1 style='color: green;'>ยินดีต้อนรับ, " + username + "!</h1>");
                out.println("<p>เข้าสู่ระบบสำเร็จ</p>");
                out.println("<a href='home.html' style='color: blue; text-decoration: none;'>ไปยังหน้าหลัก</a>");
                out.println("</body>");
                out.println("</html>");
            } else {
                // เข้าสู่ระบบไม่สำเร็จ
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>เข้าสู่ระบบล้มเหลว</title>");
                out.println("<meta charset='UTF-8'>");
                out.println("</head>");
                out.println("<body style='font-family: Arial, sans-serif; text-align: center; margin-top: 50px;'>");
                out.println("<h1 style='color: red;'>ชื่อผู้ใช้หรือรหัสผ่านไม่ถูกต้อง!</h1>");
                out.println("<p>กรุณาลองอีกครั้ง</p>");
                out.println("<a href='login.html' style='color: blue; text-decoration: none;'>กลับไปที่หน้าเข้าสู่ระบบ</a>");
                out.println("</body>");
                out.println("</html>");
            }
        }
    }
}
