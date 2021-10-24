package commands;

import UI.CommandInterface;
import UI.UserInterface;
import elementsOfCollection.Vehicle;

/**
 * Класс команды remove_lower.
 */
public class RemoveLower extends Command {

    /**
     * Стандартный конструктор, добавляющий строку вызова и описание команды.
     */
    public RemoveLower() {
        cmdLine = "remove_lower";
        description = "удалить из коллекции все элементы, меньшие, чем заданный";
        options = "Параметры: Сравниваемый объект";
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
        Vehicle vehicle = ui.readVehicle(ui);
        commandInterface.removeLower(vehicle);
        int size2 = commandInterface.getSize();
        if (size2 < size1)
            ui.showMessage("Операция успешно выполнена");
    }
}
