package ch.fhnw.webfr.flashcard.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import ch.fhnw.webfr.flashcard.domain.Questionnaire;
import ch.fhnw.webfr.flashcard.persistence.QuestionnaireRepository;
import ch.fhnw.webfr.flashcard.util.QuestionnaireInitializer;
import jakarta.servlet.*;

/**
 * Arbeitsblatt 2.1, Woche 2
 */
public class BasicFilter implements Filter {

    private ServletContext context;

    public void init(FilterConfig fConfig) throws ServletException {
        this.context = fConfig.getServletContext();
        this.context.log("RequestLoggingFilter initialized");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        chain.doFilter(request, response);

    };

    public void destroy() {

    }

}
