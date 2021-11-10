package UI;

import collection.VehicleStorage;
import elementsOfCollection.FuelType;
import elementsOfCollection.Vehicle;
import exception.IncorrectValueException;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Класс взаимодействия с коллекцией.
 */
public final class StorageInteraction implements CommandInterface {

    /**
     * Статическое поле-хранилище коллекции.
     */
    private static VehicleStorage storage;
    /**
     * Статическое поле, содержащее путь к файлу с хранимой коллекцией.
     */
    private static String originPath;
    /**
     * Статическое поле, отображающее наличие внесенных несохраненных изменений в хранимую коллекцию.
     */
    private static boolean changesMade = false;

    /**
     * Стандартный конструктор, задает хранилище, с которым будет работа.
     *
     * @param storage    хранилище.
     * @param originPath путь к файлу.
     */
    public StorageInteraction(VehicleStorage storage, String originPath) {
        StorageInteraction.storage = storage;
        StorageInteraction.originPath = originPath;
    }

    /**
     * Метод, реализующий команду info.
     */
    public void info() {
        System.out.println("Дата доступа к коллекции: " + storage.getInitializationDate() + "\n"
                + "Тип коллекции: " + storage.getCollection().getClass() + "\n"
                + "Размер коллекции: " + storage.getCollection().size());
    }

    /**
     * Метод, реализующий команду help.
     *
     * @return Список команд и их описание.
     */
    @Override
    public String help() {
        return null;
    }

    /**
     * Метод, реализующий команду show.
     *
     * @return Строковое представление объектов коллекции.
     */
    public String show() {
        for (Vehicle vehicle : storage.getCollection()) {
            vehicle.showVehicle();
        }
        return null;
    }

    /**
     * Метод, реализующий команду add.
     *
     * @param vehicle Добавляемый объект.
     */
    public void add(Vehicle vehicle) {
        if (VehicleStorage.checkID(vehicle.getId(), VehicleStorage.vehicles)) {
            VehicleStorage.vehicles.add(vehicle);
        } else try {
            throw new IncorrectValueException("Продукт с таким ID уже существует.");
        } catch (IncorrectValueException e) {
            e.printStackTrace();
        }
        changesMade = true;
    }

    /**
     * Метод, реализующий команду update.
     *
     * @param vehicle Новый объект.
     * @param id      ID заменяемого объекта.
     */
    @Override
    public void update(long id, Vehicle vehicle) {
        removeById(id);
        try {
            vehicle.setId(id);
        } catch (IncorrectValueException e) {
            e.printStackTrace();
        }
        storage.getCollection().add(vehicle);
        changesMade = true;
    }

    /**
     * Метод, реализующий команду remove_by_id.
     *
     * @param id Ключ удаляемого объекта.
     */
    @Override
    public void removeById(long id) {
        Vehicle vehicle = returnById(id);
        if (vehicle != null) {
            storage.getCollection().remove(vehicle);
        }
        changesMade = true;
    }

    /**
     * Метод, реализующий команду clear.
     */
    public void clear() {
        storage.clear();
        changesMade = true;
    }

    /**
     * Метод, реализующий команду save.
     *
     * @throws IOException В случае ошибки ввода/вывода.
     */
    public void save() throws IOException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        BufferedWriter bufferedWriter = new BufferedWriter(new PrintWriter(StorageInteraction.originPath));
        bufferedWriter.write("{" + "\n");
        bufferedWriter.write("\t" + "\"vehicle\" : [" + "\n");
        int counter = 0;
        for (Vehicle v : storage.getCollection()) {
            bufferedWriter.write("\t" + "\t" + "{" + "\n");
            bufferedWriter.write("\t" + "\t" + "\t" + "\"id\": " + "\"" + v.getId() + "\"," + "\n");
            bufferedWriter.write("\t" + "\t" + "\t" + "\"name\": " + "\"" + v.getName() + "\"," + "\n");
            bufferedWriter.write("\t" + "\t" + "\t" + "\"coordinates\": { " + "\n");
            bufferedWriter.write("\t" + "\t" + "\t" + "\t" + "\"x\": " + "\"" + v.getX() + "\"," + "\n");
            bufferedWriter.write("\t" + "\t" + "\t" + "\t" + "\"y\": " + "\"" + v.getY() + "\"" + "\n");
            bufferedWriter.write("\t" + "\t" + "\t" + "}," + "\n");
            bufferedWriter.write("\t" + "\t" + "\t" + "\"creationDate\": " + "\"" + v.getCreationDate().format(formatter) + "\"," + "\n");
            bufferedWriter.write("\t" + "\t" + "\t" + "\"enginePower\": " + "\"" + v.getEnginePower() + "\"," + "\n");
            bufferedWriter.write("\t" + "\t" + "\t" + "\"numberOfWheels\": " + "\"" + v.getNumberOfWheels() + "\"," + "\n");
            bufferedWriter.write("\t" + "\t" + "\t" + "\"distanceTravelled\": " + "\"" + v.getDistanceTravelled() + "\"," + "\n");
            bufferedWriter.write("\t" + "\t" + "\t" + "\"fuelType\": " + "\"" + v.getFuelType() + "\"" + "\n");
            if (counter == storage.getCollection().size() - 1) {
                bufferedWriter.write("\t" + "\t" + "}" + "\n");
            } else bufferedWriter.write("\t" + "\t" + "}," + "\n");
            counter++;
        }
        bufferedWriter.write("\t" + "]" + "\n");
        bufferedWriter.write("}" + "\n");
        bufferedWriter.flush();
        changesMade = false;
    }

    /**
     * Метод, реализующий команду exit.
     */
    public void exit() {
        System.exit(0);
    }

    /**
     * Метод, реализующий команду remove_first.
     */
    @Override
    public void removeFirst() {
        storage.getCollection().remove(0);
        changesMade = true;
    }

    /**
     * Метод, реализующий команду remove_lower.
     *
     * @param vehicle Объект сравнения.
     */
    @Override
    public void removeLower(Vehicle vehicle) {
        Vector<Vehicle> vehicles = storage.getCollection();
        List<Vehicle> toBeSortedVehicles = new ArrayList<>(vehicles);
        List<Vehicle> toBeRemovedVehicles = new ArrayList<>();
        toBeSortedVehicles.sort(Comparator.comparing(Vehicle::getEnginePower));
        for (Vehicle v : toBeSortedVehicles) {
            if (v.compareTo(vehicle) < 0)
                toBeRemovedVehicles.add(v);
        }
        for (Vehicle v : toBeRemovedVehicles) {
            storage.getCollection().remove(v);
        }
        changesMade = true;
    }

    /**
     * Метод, реализующий команду add_if_max.
     *
     * @param vehicle Транспорт, добавляемый в коллекцию, если его значение превышает значение наибольшего элемента.
     */
    @Override
    public void addIfMax(Vehicle vehicle) {
        Vector<Vehicle> vehicles = storage.getCollection();
        Vehicle maxVehicle = new Vehicle();
        List<Vehicle> toBeSortedVehicles = new ArrayList<>(vehicles);
        toBeSortedVehicles.sort(Comparator.comparing(Vehicle::getEnginePower));
        for (Vehicle v : toBeSortedVehicles) {
            if (v.compareTo(maxVehicle) > 0)
                maxVehicle = v;
        }
        if (maxVehicle.getEnginePower() < vehicle.getEnginePower()) {
            storage.getCollection().add(vehicle);
        }
    }

    /**
     * Метод, реализующий команду filter_less_than_engine_power.
     *
     * @param enginePower Мощность двигателя транспорта для фильтра.
     */
    @Override
    public void filterLessThanEnginePower(long enginePower) {
        Vehicle vehicle = new Vehicle();
        String vehicles = storage.getCollection().stream()
                .filter(v -> v.getEnginePower() < enginePower)
                .map(v -> vehicle.returnVehicleDescribe(v) + "\n\n")
                .collect(Collectors.joining());
        if (vehicles.length() > 1) {
            System.out.println("Элементы, мощность двигателя которых меньше заданного: \n" + vehicles);
        } else {
            System.out.println("Нет элементов с мощностью двигателя, меньшей заданной. \n");
        }
        changesMade = true;
    }

    /**
     * Метод, реализующий команду count_greater_than_price.
     *
     * @param fuelType Тип топлива
     * @return Число объектов с типом топлива, выше указанного.
     */
    @Override
    public int countLessThanFuelType(FuelType fuelType) {
        Iterator<Vehicle> itr = storage.getCollection().iterator();
        int counter = 0;
        while (itr.hasNext()) {
            Vehicle vehicle = itr.next();
            FuelType fl = vehicle.getFuelType();
            if (FuelType.compareFuelType(fuelType) > FuelType.compareFuelType(fl)) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * Метод, реализующий команду print_field_descending_distance_travelled.
     */
    public List<Float> printFieldDescendingDistanceTravelled() {
        List<Float> distanceTravelled = new ArrayList<>();
        for (Vehicle v : storage.getCollection()) {
            distanceTravelled.add(v.getDistanceTravelled());
        }
        Collections.sort(distanceTravelled, Collections.reverseOrder());
        return distanceTravelled;
    }


    /**
     * Метод, возвращающий размер хранимой коллекции.
     *
     * @return размер коллекции.
     */
    public int getSize() {
        return storage.getCollection().size();
    }

    /**
     * Метод, возвращающий объект по ID.
     *
     * @param id ID для поиска.
     * @return Объект, ID которого равно заданному.
     */
    public Vehicle returnById(long id) {
        Vehicle vehicle = new Vehicle();
        for (Vehicle v : storage.getCollection()) {
            if (v.getId() == id) {
                vehicle = v;
            }
        }
        return vehicle;
    }

    /**
     * Метод, проверяющий наличие объекта по ID.
     *
     * @param id ID для поиска.
     * @return True если объект существует, иначе false.
     */
    @Override
    public boolean findById(long id) {
        Vehicle vehicle = new Vehicle();
        for (Vehicle v : storage.getCollection()) {
            if (v.getId() == id) {
                vehicle = v;
            }
        }
        return true;
    }

    /**
     * Метод, проверяюший наличие/отсутсвие несохраненных изменений коллекции.
     *
     * @return True - если есть несохраненные изменения, иначе false.
     */
    @Override
    public boolean checkChanges() {
        return changesMade;
    }
}
