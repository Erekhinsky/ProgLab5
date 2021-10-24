package commands;

import UI.CommandInterface;
import UI.UserInterface;

import java.io.IOException;
import java.util.List;

/**
 * Класс команды print_field_descending_distance_travelled.
 */
public class PrintFieldDescendingDistanceTravelled extends Command {

    /**
     * Стандартный конструктор, добавляющий строку вызова и описание команды.
     */
    public PrintFieldDescendingDistanceTravelled() {
        cmdLine = "print_field_descending_distance_travelled";
        description = "вывести значения поля distanceTravelled всех элементов в порядке убывания";
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
        List<Float> distanceTravelled = commandInterface.printFieldDescendingDistanceTravelled();
        for (Float distanceTravel : distanceTravelled) {
            ui.showMessage(distanceTravel.toString());
        }
    }
}
