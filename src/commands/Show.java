package commands;

import UI.CommandInterface;
import UI.UserInterface;

import java.io.IOException;

/**
 * Класс команды show.
 */
public class Show extends Command {

    /**
     * Стандартный конструктор, добавляющий строку вызова и описание команды.
     */
    public Show() {
        cmdLine = "show";
        description = "вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
        options = "Нет параметров.";
    }

    /**
     * Метод исполнения
     *
     * @param ui               объект, через который ведется взаимодействие с пользователем.
     * @param arguments        необходимые для исполнения аргументы.
     * @param commandInterface объект для взаимодействия с коллекцией.
     */
    public void execute(UserInterface ui, String[] arguments, CommandInterface commandInterface) throws IOException {
        if (commandInterface.getSize() == 0)
            ui.showMessage("Коллекция пуста");
        else {
            ui.showMessage("Коллекция: ");
            commandInterface.show();
        }
    }
}
