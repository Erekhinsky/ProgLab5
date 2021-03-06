package commands;

import UI.CommandCenter;
import UI.CommandInterface;
import UI.UserInterface;

/**
 * Класс команды info.
 */
public class Info extends Command {

    /**
     * Стандартный конструктор, добавляющий строку вызова и описание команды.
     */
    public Info() {
        cmdLine = "info";
        description = "вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
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
        commandInterface.info();
    }
}