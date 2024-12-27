package es.ulpgc.es;

import es.ulpgc.es.control.CommandFactory;
import es.ulpgc.es.control.commands.WorkingDateCommand;
import es.ulpgc.es.control.commands.WorkingDaysCommand;
import es.ulpgc.es.view.WorkingDaysService;
import es.ulpgc.es.view.adapters.WorkingDateRequestAdapter;
import es.ulpgc.es.view.adapters.WorkingDaysRequestAdapter;

public class Main {

    public static void main(String[] args) {
        CommandFactory factory = new CommandFactory();
        factory.register("working-days", workingDaysBuilder());
        factory.register("working-date", workingDateBuilder());
        new WorkingDaysService(7070, factory).start();
    }

    private static CommandFactory.Builder workingDaysBuilder() {
        return (req, res) -> {
            WorkingDaysRequestAdapter adapter = new WorkingDaysRequestAdapter();
            WorkingDaysCommand.Input input = adapter.inputFor(req);
            WorkingDaysCommand.Output output = adapter.outputFor(res);
            return new WorkingDaysCommand(input, output);
        };

    }

    private static CommandFactory.Builder workingDateBuilder() {
        return (req, res) -> {
            WorkingDateRequestAdapter adapter = new WorkingDateRequestAdapter();
            WorkingDateCommand.Input input = adapter.inputFor(req);
            WorkingDateCommand.Output output = adapter.outputFor(res);
            return new WorkingDateCommand(input, output);
        };
    }
}
