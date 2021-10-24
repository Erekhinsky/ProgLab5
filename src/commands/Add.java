package commands;

import UI.CommandInterface;
import UI.UserInterface;
import elementsOfCollection.Vehicle;

/**
 * Класс команды add.
 */
public class Add extends Command {

    /**
     * Стандартный конструктор, добавляющий строку вызова и описание команды.
     */
    public Add() {
        cmdLine = "add";
        description = "добавить новый элемент с заданным ключом";
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
        Vehicle vehicle = ui.readVehicle(ui);
        vehicle.showVehicle();
        int initSize = commandInterface.getSize();
        commandInterface.add(vehicle);
        if (commandInterface.getSize() > initSize)
            ui.showMessage("Транспорт успешно добавлен");
        else ui.showMessage("Такой транспорт уже есть");
    }
}
