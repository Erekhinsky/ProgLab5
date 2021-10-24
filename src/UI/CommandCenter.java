package UI;

import commands.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс управления командами.
 */
public class CommandCenter {

    /**
     * Список команд.
     */
    public final HashMap<String, Command> commands = new HashMap<>();
    /**
     * Объект управления командами.
     */
    public static CommandCenter commandCenter = new CommandCenter();

    /**
     * Конструктор управления командами, где добавляются все команды.
     */
    public CommandCenter() {
        addCmd(new Help());
        addCmd(new Info());
        addCmd(new Show());
        addCmd(new Update());
        addCmd(new Clear());
        addCmd(new AddIfMax());
        addCmd(new ExecuteScript());
        addCmd(new Exit());
        addCmd(new CountLessThanFuelType());
        addCmd(new RemoveLower());
        addCmd(new Add());
        addCmd(new FilterLessThanEnginePower());
        addCmd(new RemoveFirst());
        addCmd(new RemoveById());
        addCmd(new PrintFieldDescendingDistanceTravelled());
        addCmd(new Save());
    }

    /**
     * Метод добавления команды в список команд.
     *
     * @param command Команда.
     */
    public void addCmd(Command command) {
        commands.put(command.getCmdLine(), command);
    }

    /**
     * Метод, распознающий команду в строке, введенной пользователем.
     *
     * @param cmdLine Строка, содержащая команду.
     * @return Объект класса соответствующей команды.
     */
    public Command getCmdCommand(String cmdLine) {
        return commands.getOrDefault(cmdLine, null);
    }

    /**
     * Метод, возвращающий единственный объект класса. Реализация шаблона "Синглтон".
     *
     * @return Объект центра управления командами.
     */
    public static CommandCenter getInstance() {
        if (commandCenter == null)
            return new CommandCenter();
        return commandCenter;
    }

    /**
     * Метод, возвращающий список команд.
     *
     * @return Список команд.
     */
    public List<Command> reciveAllCommands() {
        return commands.keySet().stream().map(commands::get).collect(Collectors.toList());
    }

    /**
     * Метод, вызывающий исполнение команды.
     *
     * @param ui               Объект, через который ведется взаимодействие с пользователем.
     * @param line             Часть строки пользовательского ввода, содержащая команду.
     * @param fullLine         Полная строка ввода с аргументами.
     * @param commandInterface Объект для взаимодействия с коллекцией.
     * @throws Exception В случае ошибки ввода/вывода.
     */
    public void executeCommand(UserInterface ui, String line, String fullLine, CommandInterface commandInterface) throws Exception {
        Command cmd = getCmdCommand(line);
        String[] args = fullLine.split(" ");
        cmd.execute(ui, args, commandInterface);
    }
}
