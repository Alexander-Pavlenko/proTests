package ua.nure.pavlenko.SummaryTask4.controller.command;

import ua.nure.pavlenko.SummaryTask4.controller.Attribute;
import ua.nure.pavlenko.SummaryTask4.controller.command.AdminCommands.*;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Alexander on 21.05.2017.
 */
public class CommandContainer {
   // private static final Logger LOG = Logger.getLogger(CommandContainer.class);

    private static Map<String, Command> commands = new TreeMap<String, Command>();

    static {



        // common commands
        commands.put(Attribute.HOME, new HomeCommand());
        commands.put(Attribute.LOGIN, new Login());
        commands.put(Attribute.LOGOUT, new LogOut());
        commands.put(Attribute.REGISTRATION, new Registration());
        commands.put(Attribute.SELECTED_LANGUAGE, new SelectedLanguage());
        commands.put(Attribute.TESTS, new Tests());
        commands.put(Attribute.TEST, new Test());
        commands.put(Attribute.TESTING, new Testing());
        commands.put(Attribute.START, new StartTesting());
        commands.put(Attribute.COLLECT, new CollectorAnswer());
        commands.put(Attribute.RESULT, new ResultTest());
        commands.put(Attribute.PROFILE, new Profile());
        commands.put(Attribute.ADMIN_OFFICE, new AdminOffice());
        commands.put(Attribute.ADMIN_OFFICE_USER, new AdminOfficeUser());
        commands.put(Attribute.CREATE_QUEST, new CreateQuest());
        commands.put(Attribute.CREATE_SUBJECT, new CreateSubject());
        commands.put(Attribute.CREATE_TEST, new CreateTest());
        commands.put(Attribute.BLOCKING_USER, new BlockingUser());
        commands.put(Attribute.LANGUAGE, new Language());

//
    }

    /**
     * Returns command object with the given name.
     *
     * @param commandName
     *            Name of the command.
     * @return Command object.
     */
    public static Command get(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
//            LOG.trace("Command not found, name --> " + commandName);
            return commands.get("noCommand");
        }
        return commands.get(commandName);
    }
}
