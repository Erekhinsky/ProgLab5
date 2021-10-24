package commands;

import UI.CommandInterface;
import UI.UserInterface;
import elementsOfCollection.Vehicle;

/**
 * Класс команды remove_first.
 */
public class RemoveFirst extends Command {

    /**
     * Стандартный конструктор, добавляющий строку вызова и описание команды.
     */
    public RemoveFirst() {
        cmdLine = "remove_first";
        description = "удалить первый элемент из коллекции";
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
        int size1 = commandInterface.getSize();
        commandInterface.removeFirst();
        int size2 = commandInterface.getSize();
        if (size2 < size1) ui.showMessage("Операция успешно выполнена");
        else ui.showMessage("Упс, что-то пошло не так.");
    }
}
