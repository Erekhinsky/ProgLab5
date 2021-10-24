package commands;

import UI.CommandInterface;
import UI.UserInterface;

/**
 * Класс, от которого наследуются команды.
 */
public abstract class Command {

    /**
     * Строка вызова команды.
     */
    protected String cmdLine;

    /**
     * Описание команды.
     */
    protected String description;

    /**
     * Аргументы для команды.
     */
    protected String options;

    /**
     * Метод исполнения команды.
     *
     * @param ui               объект для взаимодействия с пользователем.
     * @param arguments        аргументы для исполнения команды.
     * @param commandInterface объект для взаимодействия с коллекцией.
     * @throws Exception в случае ошибки.
     */
    public abstract void execute(UserInterface ui, String[] arguments, CommandInterface commandInterface) throws Exception;

    /**
     * Пустой конструктор Command.
     */
    public Command() {
    }

    /**
     * Возвращает строку вызова команды.
     *
     * @return Строка вызова команды.
     */
    public String getCmdLine() {
        return cmdLine;
    }

    /**
     * Возвращает описание команды.
     *
     * @return Описание команды.
     */
    public String getDescription() {
        return description;
    }

    public String getOptions() {
        return options;
    }

}
