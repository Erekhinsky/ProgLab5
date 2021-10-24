package commands;

import UI.CommandInterface;
import UI.UserInterface;

/**
 * Класс команды save.
 */
public class Save extends Command {

    /**
     * Стандартный конструктор, добавляющий строку вызова и описание команды.
     */
    public Save() {
        cmdLine = "save";
        description = "сохранить коллекцию в файл";
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
        commandInterface.save();
        ui.showMessage("Коллекция сохранена в файл");
    }
}