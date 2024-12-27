package es.ulpgc.es.view.adapters;

import es.ulpgc.es.control.commands.WorkingDateCommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;


public class WorkingDateRequestAdapter {
    public WorkingDateCommand.Input inputFor(HttpServletRequest req) {
        return new WorkingDateCommand.Input() {
            @Override
            public LocalDate start() { return LocalDate.parse(req.getParameter("start")); }

            @Override
            public int workingDays() { return Integer.parseInt(req.getParameter("days")); }
        };
    }

    public WorkingDateCommand.Output outputFor(HttpServletResponse res) {
        return result -> {
            try {
                res.getWriter().write(result.toString());

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
    }
}
