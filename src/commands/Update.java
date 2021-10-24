package commands;

import UI.CommandInterface;
import UI.UserInterface;
import collection.VehicleStorage;
import elementsOfCollection.Vehicle;
import exception.IncorrectValueException;

import java.io.IOException;

/**
 * Класс команды update.
 */
public class Update extends Command {

    /**
     * Стандартный конструктор, добавляющий строку вызова и описание команды.
     */
    public Update() {
        cmdLine = "update";
        description = "обновить значение элемента коллекции, id которого равен заданному";
        options = "Параметры: ID заменяемого объекта, Добавляемый объект";
    }

    /**
     * Метод исполнения
     *
     * @param ui                 объект, через который ведется взаимодействие с пользователем.
     * @param arguments          необходимые для исполнения аргументы.
     * @param storageInteraction объект для взаимодействия с коллекцией.
     */
    public void execute(UserInterface ui, String[] arguments, CommandInterface storageInteraction) throws IOException, IncorrectValueException {
        long id = Long.parseLong(arguments[1]);
        Vehicle vehicle;
        final Boolean[] checker = {true};
        VehicleStorage.vehicles.forEach((value) -> {
            if (value.getId() == id) {
                checker[0] = true;
            }
        });
        if (checker[0]) {
            vehicle = ui.readVehicle(ui);
            storageInteraction.update(id, vehicle);
            ui.showMessage("Сотрудник обновлен");
        } else ui.showMessage("Сотрудника с таким идентификатором нет");
    }
}