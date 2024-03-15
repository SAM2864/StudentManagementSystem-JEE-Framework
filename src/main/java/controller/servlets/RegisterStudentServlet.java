package controller.servlets;

import java.io.IOException;

import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.DBController;
import model.StudentModel;
import utils.StringUtils;

/**
 * This Servlet class handles student registration requests.
 * It extracts student information from the registration form submission,
 * performs basic data validation (to be implemented), and attempts to
 * register the student in the database using a `DBController`. 
 * The user is redirected to the login page upon successful registration.
 *
 * @author Prithivi Maharjan (prithivi.maharjan18@gmail.com)
 */
@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.SERVLET_URL_REGISTER })
public class RegisterStudentServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final DBController dbController;

    public RegisterStudentServlet() {
        this.dbController = new DBController();
    }

    /**
     * Handles HTTP POST requests for student registration.
     *
     * @param request The HttpServletRequest object containing registration form data.
     * @param response The HttpServletResponse object for sending responses.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException if an I/O error occurs.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

        // Extract student information from request parameters
        String firstName = request.getParameter(StringUtils.FIRST_NAME);
        String lastName = request.getParameter(StringUtils.LAST_NAME);
        LocalDate dob = LocalDate.parse(request.getParameter(StringUtils.BIRTHDAY));
        String gender = request.getParameter(StringUtils.GENDER);
        String email = request.getParameter(StringUtils.EMAIL);
        String phoneNumber = request.getParameter(StringUtils.PHONE_NUMBER);
        String subject = request.getParameter(StringUtils.SUBJECT);
        String username = request.getParameter(StringUtils.USERNAME);
        String password = request.getParameter(StringUtils.PASSWORD);

        // Implement data validation here (e.g., check for empty fields, email format, etc.)

        // Create a StudentModel object to hold student information
        StudentModel student = new StudentModel(
                firstName, lastName, dob, gender, email, phoneNumber,
                subject, username, password);

        // Call DBController to register the student
        int result = dbController.registerStudent(student);

        if (result == 1) {
            // Registration successful, redirect to login page
            resp.sendRedirect(request.getContextPath() + StringUtils.PAGE_URL_LOGIN);
        } else {
            // Registration failed (code to handle failure will be implemented later)
            //  You can set an error message in the request scope using appropriate StringUtils constant
            //  and forward the request back to the registration page for displaying the error.
        }
    }
}