package commands;

import UI.CommandInterface;
import UI.UserInterface;

import java.io.IOException;

/**
 * Класс команды remove_by_id.
 */
public class RemoveById extends Command {

    /**
     * Стандартный конструктор, добавляющий строку вызова и описание команды.
     */
    public RemoveById() {
        cmdLine = "remove_by_id";
        description = "удалить элемент из коллекции по его id";
        options = "Параметры: ID объекта.";
    }

    /**
     * Метод исполнения
     *
     * @param ui               объект, через который ведется взаимодействие с пользователем.
     * @param arguments        необходимые для исполнения аргументы.
     * @param commandInterface объект для взаимодействия с коллекцией.
     */
    public void execute(UserInterface ui, String[] arguments, CommandInterface commandInterface) throws IOException {
        long id = Long.parseLong(arguments[1]);
        if (commandInterface.findById(id)) {
            commandInterface.removeById(id);
            ui.showMessage("Продукт удален");
        }
    }
}
