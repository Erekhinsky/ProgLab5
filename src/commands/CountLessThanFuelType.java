package commands;

import UI.CommandInterface;
import UI.UserInterface;
import elementsOfCollection.FuelType;
import utils.ValidationClass;

/**
 * Класс команды count_less_than_fuel_type.
 */
public class CountLessThanFuelType extends Command {

    /**
     * Стандартный конструктор, добавляющий строку вызова и описание команды.
     */
    public CountLessThanFuelType() {
        cmdLine = "count_less_than_fuel_type";
        description = "вывести количество элементов, значение поля fuelType которых меньше заданного";
        options = "Параметры: Fuel Type:" + FuelType.getPossibleValues();
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
        int result = 0;
        if (ValidationClass.validateFuelType(arguments[1], true, ui)) {
            FuelType fuelType = FuelType.valueOf(arguments[1].toUpperCase());
            result = commandInterface.countLessThanFuelType(fuelType);
        } else ui.showMessage("Неверно введено значение Fuel Type.");
        ui.showMessage("Элементов с типом топлива, меньше указанного: " + result);
    }

}
