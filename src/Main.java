import UI.CommandCenter;
import UI.StorageInteraction;
import UI.UserInterface;
import collection.VehicleStorage;
import utils.Parser;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;

/**
 * Главный класс консольного приложения.
 */
public class Main {

    /**
     * Главный метод.
     *
     * @param args путь к изначальному файлу с данными.
     * @throws IOException в случае ошибки ввода/вывода.
     */
    public static void main(String[] args) throws IOException {

        String path = args[0];
        Path p = Paths.get(path);
        UserInterface userInteraction = new UserInterface(new InputStreamReader(System.in), true, new OutputStreamWriter(System.out));
        VehicleStorage storage;
        StorageInteraction interactiveStorage = null;

        boolean firstOpening = true;

        VehicleStorage.getVehicles();

        try {
            if (p.toRealPath().toString().length() > 3 && p.toRealPath().toString().trim().startsWith("/dev")) {
                userInteraction.showMessage("Недопустимый путь к файлу!");
            } else {
                while (true) {
                    try {
                        if (firstOpening) {
                            try {
                                InputStreamReader isr = new InputStreamReader(new FileInputStream(String.valueOf(p)));
                                VehicleStorage.vehicles = Parser.readArrayFromFile(isr);
                                System.out.println("Файл успешно открылся");
                            } catch (NoSuchElementException e) {
                                userInteraction.showMessage("Ввод недоступен");
                                PrintWriter pw = new PrintWriter("errorLog.txt");
                                e.printStackTrace(pw);
                                pw.close();
                                System.exit(1);
                            }
                            storage = new VehicleStorage();
                            interactiveStorage = new StorageInteraction(storage, args[0]);
                            if (storage.getCollection().size() < 1) {
                                userInteraction.showMessage("Пустая коллекция");
                            }
                            userInteraction.showMessage("Введите команду, полный список команд можно получить с помощью команды help." +
                                    "\n" + "При вводе нового объекта ввод необходимо осуществлять, начиная с Имени объекта.");
                            firstOpening = false;
                        }
                        String line;
                        do {
                            line = userInteraction.read().trim();
                            String cmd = line.split(" ")[0];
                            CommandCenter.getInstance().executeCommand(userInteraction, cmd, line, interactiveStorage);
                        } while (userInteraction.hasNextLine());
                    } catch (NullPointerException e) {
                        userInteraction.showMessage("Такой команды нет, проверьте правильность ввода или посмотрите список команд с помощью help");
                        PrintWriter pw = new PrintWriter("errorLog.txt");
                        e.printStackTrace(pw);
                        pw.close();
                    } catch (ArrayIndexOutOfBoundsException e) {
                        userInteraction.showMessage("Введенные аргументы не соответсвуют требуемым для выполнения, повторите ввод команды");
                        PrintWriter pw = new PrintWriter("errorLog.txt");
                        e.printStackTrace(pw);
                        pw.close();
                    } catch (FileNotFoundException e) {
                        userInteraction.showMessage("В качестве аргумента указан путь к несуществующему файлу или доступ к файлу закрыт");
                        PrintWriter pw = new PrintWriter("errorLog.txt");
                        e.printStackTrace(pw);
                        pw.close();
                    } catch (IOException e) {
                        userInteraction.showMessage("Произошла ошибка ввода/вывода");
                        PrintWriter pw = new PrintWriter("errorLog.txt");
                        e.printStackTrace(pw);
                        pw.close();
                    } catch (InvalidAlgorithmParameterException e) {
                        userInteraction.showMessage("Дальнейшее исполнение скрипта приведет к рекурсии");
                        PrintWriter pw = new PrintWriter("errorLog.txt");
                        e.printStackTrace(pw);
                        pw.close();
                    } catch (DateTimeParseException e) {
                        userInteraction.showMessage("Дата указана неверно, повторите ввод команды");
                        PrintWriter pw = new PrintWriter("errorLog.txt");
                        e.printStackTrace(pw);
                        pw.close();
                    } catch (NumberFormatException e) {
                        userInteraction.showMessage("Неправильно введены числовые данные, повторите ввод команды");
                        PrintWriter pw = new PrintWriter("errorLog.txt");
                        e.printStackTrace(pw);
                        pw.close();
                    } catch (IllegalArgumentException e) {
                        userInteraction.showMessage("Указано неверное значение поля, повторите ввод команды");
                        PrintWriter pw = new PrintWriter("errorLog.txt");
                        e.printStackTrace(pw);
                        pw.close();
                    } catch (NoSuchElementException e) {
                        userInteraction.showMessage("Ввод недоступен");
                        PrintWriter pw = new PrintWriter("errorLog.txt");
                        e.printStackTrace(pw);
                        pw.close();
                        System.exit(1);
                    } catch (Exception e) {
                        if (e.getMessage().equals("В коллекции достигнуто максимальное количество элементов"))
                            userInteraction.showMessage("Коллекция уже содержит максимальное число элементов");
                        else {
                            userInteraction.showMessage("Произошла неизвестная ошибка");
                            PrintWriter pw = new PrintWriter("errorLog.txt");
                            e.printStackTrace(pw);
                            pw.close();
                        }
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            userInteraction.showMessage("Не указан путь изначального файла");
            PrintWriter pw = new PrintWriter("errorLog.txt");
            e.printStackTrace(pw);
            pw.close();
            System.exit(1);
        }
    }
}
