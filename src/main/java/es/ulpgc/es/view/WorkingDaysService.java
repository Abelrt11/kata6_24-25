package es.ulpgc.es.view;

import es.ulpgc.es.control.CommandFactory;
import io.javalin.Javalin;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class WorkingDaysService {
    private final int port;
    private final CommandFactory factory;
    private Javalin app;

    public WorkingDaysService(int port, CommandFactory factory) {
        this.port = port;
        this.factory = factory;
    }

    public void start() {
        app = Javalin.create()
                .get("/working-days", ctx -> execute("working.days", ctx.req(), ctx.res()))
                .get("/working-date", ctx -> execute("working-date", ctx.req(), ctx.res()))
                .start(port);
    }

    private void execute(String command, HttpServletRequest req, HttpServletResponse res) {
        factory.with(req, res).build(command).execute();
    }

    public void stop() {app.stop();}
}
