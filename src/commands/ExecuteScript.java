package commands;

import UI.CommandCenter;
import UI.CommandInterface;
import UI.UserInterface;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InterruptedIOException;
import java.io.OutputStreamWriter;
import java.security.InvalidAlgorithmParameterException;
import java.util.HashSet;
import java.util.NoSuchElementException;

/**
 * Класс команды execute_script.
 */
public class ExecuteScript extends Command {

    private static final HashSet<String> paths = new HashSet<>();

    /**
     * Стандартный конструктор, добавляющий строку вызова и описание команды.
     */
    public ExecuteScript() {
        cmdLine = "execute_script";
        description = "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме";
        options = "Параметры: Путь к исполняемому файлу";
    }

    /**
     * Метод исполнения
     *
     * @param ui               объект, через который ведется взаимодействие с пользователем.
     * @param arguments        необходимые для исполнения аргументы.
     * @param commandInterface объект для взаимодействия с коллекцией.
     */
    public void execute(UserInterface ui, String[] arguments, CommandInterface commandInterface) throws Exception {
        boolean success;
        try {
            UserInterface scriptInteraction = new UserInterface(new FileReader(arguments[1]), false, new OutputStreamWriter(System.out));
            String line;
            String path = arguments[1];
            success = true;
            while (scriptInteraction.hasNextLine()) {
                line = scriptInteraction.read();
                String cmd = line.split(" ")[0];
                if (cmd.equals("execute_script")) {
                    if (!paths.contains(path)) {
                        paths.add(path);
                        CommandCenter.getInstance().executeCommand(scriptInteraction, cmd, line, commandInterface);
                    } else {
                        paths.clear();
                        throw new InvalidAlgorithmParameterException("Выполнение скрипта остановлено, т.к. возможна рекурсия");
                    }
                } else CommandCenter.getInstance().executeCommand(scriptInteraction, cmd, line, commandInterface);
            }
            paths.clear();
            if (success)
                ui.showMessage("Скрипт выполнен");
            else ui.showMessage("Скрипт не выполнен");
        } catch (FileNotFoundException e) {
            ui.showMessage("В качестве аргумента указан путь к несуществующему файлу");
            success = false;
            paths.clear();
        } catch (NoSuchElementException e) {
            ui.showMessage("Скрипт некорректен, проверьте верность введенных команд");
            success = false;
            paths.clear();
        } catch (InterruptedIOException e) {
            ui.showMessage("Скрипт содержит некорректный ввод данных");
            success = false;
            paths.clear();
        }
    }
}
