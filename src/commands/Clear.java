package commands;

import UI.CommandInterface;
import UI.UserInterface;

/**
 * Класс команды clear.
 */
public class Clear extends Command {

    /**
     * Стандартный конструктор, добавляющий строку вызова и описание команды.
     */
    public Clear() {
        cmdLine = "clear";
        description = "очистить коллекцию";
        options = "Нет параметров.";
    }

    /**
     * Метод исполнения
     *
     * @param ui               объект, через который ведется взаимодействие с пользователем.
     * @param arguments        необходимые для исполнения аргументы.
     * @param commandInterface объект для взаимодействия с коллекцией.
     */
    public void execute(UserInterface ui, String[] arguments, CommandInterface commandInterface) throws Exception {
        commandInterface.clear();
        if (commandInterface.getSize() > 0)
            ui.showMessage("Что-то пошло не так, попробуйте еще раз");
        else ui.showMessage("Коллекция очищена");
    }
}

