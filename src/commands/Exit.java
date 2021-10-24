package commands;

import UI.CommandInterface;
import UI.UserInterface;

/**
 * Класс команды exit.
 */
public class Exit extends Command {

    /**
     * Стандартный конструктор, добавляющий строку вызова и описание команды.
     */
    public Exit() {
        cmdLine = "exit";
        description = "завершить программу";
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
        String confirmation;
        if (commandInterface.checkChanges()) {
            confirmation = ui.readArgument("Выход из приложения. " +
                    "Введите: " + "Yes" + ", если хотите выйти без сохранения; " + "No" + ", если хотите продолжить работу; " +
                    "Save" + " если хотите сохранить коллекцию и выйти", false);
            switch (confirmation) {
                case "Yes": {
                    ui.showMessage("Завершение работы");
                    System.exit(0);
                }
                case "yes": {
                    ui.showMessage("Завершение работы");
                    System.exit(0);
                }
                case "No":
                    ui.showMessage("Работа возобновлена");
                case "no":
                    ui.showMessage("Работа возобновлена");
                case "Save": {
                    commandInterface.save();
                    ui.showMessage("Коллекция сохранена, Завершение работы");
                    System.exit(0);
                }
                case "save": {
                    commandInterface.save();
                    ui.showMessage("Коллекция сохранена, Завершение работы");
                    System.exit(0);
                }
                default:
                    ui.showMessage("Команда не распознана " + "\n" + "Введите: " + "Yes" +
                            ", если хотите выйти без сохранения; " + "No" + ", если хотите продолжить работу; " +
                            "Save" + " если хотите сохранить коллекцию и выйти");
            }
        } else {
            ui.showMessage("Завершение работы");
            System.exit(0);
        }
    }
}
