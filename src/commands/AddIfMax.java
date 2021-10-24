package commands;

import UI.CommandInterface;
import UI.UserInterface;
import elementsOfCollection.Vehicle;

/**
 * Класс команды add_if_max.
 */
public class AddIfMax extends Command {

    /**
     * Стандартный конструктор, добавляющий строку вызова и описание команды.
     */
    public AddIfMax() {
        cmdLine = "add_if_max";
        description = "добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции";
        options = "Параметры: Добавляемый объект";
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
        commandInterface.addIfMax(vehicle);
        int size2 = commandInterface.getSize();
        if (size2 > size1) {
            ui.showMessage("Операция успешно выполнена");
        } else ui.showMessage("Похоже, добавляемый объект меньше максимального");
    }
}