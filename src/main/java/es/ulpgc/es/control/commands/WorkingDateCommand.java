package es.ulpgc.es.control.commands;

import es.ulpgc.es.control.Command;
import es.ulpgc.es.model.Calendar;

import java.time.LocalDate;
import java.util.Iterator;

public class WorkingDateCommand implements Command {
    private final Input input;
    private final Output output;

    public WorkingDateCommand(Input input, Output output) {
        this.input = input;
        this.output = output;
    }

    @Override
    public void execute() {output.result(nextWorkingdate());}

    private LocalDate nextWorkingdate() {
        Iterator<LocalDate> iterator = new Calendar().iteratorFrom(input.start());
        LocalDate result = null;
        for (int i = 0; i < input.workingDays(); i++) result = iterator.next();
        return result;

    }

    public interface Input {
        LocalDate start();

        int workingDays();
    }

    public interface Output {
        void result(LocalDate result);
    }
}
