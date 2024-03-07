package ch.fhnw.webfr.flashcard.web;

import ch.fhnw.webfr.flashcard.domain.Questionnaire;
import ch.fhnw.webfr.flashcard.persistence.QuestionnaireRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
@RequestMapping("/questionnaires")
public class QuestionnareController {

    @Autowired
    private QuestionnaireRepository questionnaireRepository;

    @RequestMapping(method = RequestMethod.GET)
    public void findAll(HttpServletResponse response, HttpServletRequest request)
            throws IOException {
        List<Questionnaire> questionnaires = questionnaireRepository.findAll();
        PrintWriter writer = response.getWriter();
        writer.append("<html><head><title>Example</title></head><body>");
        writer.append("<h3>Fragebögen</h3>");
        for (Questionnaire questionnaire : questionnaires) {
            String url = request.getContextPath() + request.getServletPath();
            url = url + "/"+ questionnaire.getId().toString();
            writer.append("<p><a href='").append(response.encodeURL(url)).append("'>").append(questionnaire.getTitle())
                    .append("</a></p>");
        }
        writer.append("</body></html>");
    }

    @GetMapping("/{id}")
    public void findById(Long id, HttpServletResponse response,
                         HttpServletRequest request) throws IOException {
        Questionnaire questionnaire;
        PrintWriter writer = response.getWriter();
        try {
            questionnaire = questionnaireRepository.findById(id);
        } catch (Exception e) {
            questionnaire = null;
        }
        writer.append("<html><head><title>Example</title></head><body>");
        writer.append("<h3>Questionnaire</h3>");
        if (questionnaire != null) {
            writer.append("<strong>").append(questionnaire.getTitle()).append("</strong></br>");
            writer.append("<span>").append(questionnaire.getDescription()).append("</span></p>");
        } else {
            writer.append("no questionnaire found");
        }
        writer.append("</body></html>");
    }

}
