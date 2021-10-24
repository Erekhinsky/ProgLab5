package commands;

import UI.CommandCenter;
import UI.CommandInterface;
import UI.UserInterface;

/**
 * Класс команды help.
 */
public class Help extends Command {

    /**
     * Стандартный конструктор, добавляющий строку вызова и описание команды.
     */
    public Help() {
        cmdLine = "help";
        description = "Вывести справку по доступным командам";
        options = "Нет параметров.";
    }

    /**
     * Метод исполнения
     *
     * @param ui               объект, через который ведется взаимодействие с пользователем.
     * @param arguments        необходимые для исполнения аргументы.
     * @param commandInterface объект для взаимодействия с коллекцией.
     */
    @Override
    public void execute(UserInterface ui, String[] arguments, CommandInterface commandInterface) throws Exception {
        for (Command cmd : CommandCenter.getInstance().reciveAllCommands()) {
            ui.showMessage(cmd.getCmdLine() + ": " + cmd.getDescription() + ": " + cmd.getOptions());
        }
    }

}
