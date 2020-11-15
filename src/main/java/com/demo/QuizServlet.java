package com.demo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "QuizServlet", value ="/quiz")
public class QuizServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Quiz q;
        HttpSession session =request.getSession();
        q = (Quiz) session.getAttribute("quiz");
        q.incrementCount();

        int answer = Integer.parseInt(request.getParameter("inp1"));
        if(answer == q.getAnswer()) {
            if(q.getCount()<=5)
                q.incrementScore();
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if(q.getCount()< 5) {

            out.println("<html>" +
                    "<body>" +
                    "<h2> The Number Quiz</h2>" +
                    "<h4>Your current score is " + q.getScore() + " </h4>" +
                    "Quiz Object reference  + " +q.toString() +" .<br>" +
                    "Guess the Next number in the sequence.<br>" +
                    "<br>" + q.getQuestion() + "<br>" +
                    "<form  method='post' action ='quiz'> " +
                    "<br>Your answer: <input type='text' name='inp1' value=''> pr Ans"+answer+" vs Pr Soln "+q.getAnswer()+" <br>" +
                    "<br><input type='submit' value='Submit' >" +
                    "</form>" +
                    "</body>" +
                    "</html>");
        }else{
            out.println("<html>" +
                    "<body>" +
                    "<h2> The Number Quiz</h2>" +
                    "<h4>Your current score is " + q.getScore() + " </h4>" +
                    "You have completed the Number quiz, with a score of "+q.getScore()+" out of 5.<br>" +
                    "</body>" +
                    "</html>");

        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session =request.getSession();
        Quiz quiz = new Quiz();



        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>" +
                "<body>" +
                "<h2> The Number Quiz</h2>" +
                "<h4>Your current score is "+ quiz.getScore() +" </h4>" +
                "Guess the Next number in the sequence.<br>" +
                "<br>"+ quiz.getQuestion()+ "<br>" +
                "<form  method='post' action ='quiz'> " +
                "<br>Your answer: <input type='text' name='inp1' value=''> <br>" +
                "<br><input type='submit' value='Submit' >" +
                "</form>" +
                "</body>" +
                "</html>");

        session.setAttribute("quiz",quiz);
        session.setMaxInactiveInterval(20*60);

    }
}
