package commands;

import UI.CommandInterface;
import UI.UserInterface;

/**
 * Класс команды filter_less_than_engine_power.
 */
public class FilterLessThanEnginePower extends Command {

    /**
     * Стандартный конструктор, добавляющий строку вызова и описание команды.
     */
    public FilterLessThanEnginePower() {
        cmdLine = "filter_less_than_engine_power";
        description = "вывести элементы, значение поля enginePower которых меньше заданного";
        options = "Параметры: Мощность двигателя";
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
        long enginePower = Long.parseLong(arguments[1]);
        commandInterface.filterLessThanEnginePower(enginePower);
    }
}
