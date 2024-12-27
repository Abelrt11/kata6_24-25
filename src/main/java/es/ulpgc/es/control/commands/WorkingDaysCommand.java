package es.ulpgc.es.control.commands;


import es.ulpgc.es.control.Command;

import java.time.LocalDate;
import java.util.Iterator;
import es.ulpgc.es.model.Calendar;

public class WorkingDaysCommand implements Command {
    private final Input input;
    private final Output output;

    public WorkingDaysCommand(Input input, Output output) {
        this.input = input;
        this.output = output;
    }

    @Override
    public void execute() {output.workingDays(calculateWorkingDates());}

    private int calculateWorkingDates() {
        Iterator<LocalDate> from = new Calendar().iteratorFrom(input.start());
        LocalDate current = input.start();
        int workingDays = 0;
        while(current.isBefore(input.end())) {
            workingDays++;
            current = from.next();
        }
        return workingDays;
    }

    public interface Input {
        LocalDate start();

        LocalDate end();
    }

    public interface Output {
        void workingDays(int days);
    }
}

