package collection;

import exception.InvalidInputException;
import elementsOfCollection.Vehicle;

import java.util.Date;
import java.util.Vector;

public class VehicleStorage {

    /**
     * Хранимая коллекция.
     */
    public static Vector<Vehicle> vehicles = new Vector<>();

    /**
     * Метод вывода хранимой коллекции.
     */
    public static void getVehicles() {
        System.out.println(vehicles);
    }

    /**
     * Метод очистки хранимой коллекции.
     */
    public void clear() {
        vehicles.clear();
    }

    /**
     * Метод, возвращающий дату создания коллекции.
     *
     * @return Дата создания.
     */
    public Date getInitializationDate() {
        return new Date();
    }

    /**
     * Метод, возвращающий хранимую коллекцию.
     *
     * @return Хранимая коллекция.
     */
    public Vector<Vehicle> getCollection() {
        return vehicles;
    }

    /**
     * Метод, проверяющий наличие объекта по ID.
     *
     * @param id ID для поиска.
     * @return Объект, ID которого равно заданному.
     */
    public Vehicle returnById(long id) {
        if (getCollection().iterator().next().getId() == id) {
            return getCollection().iterator().next();
        } else return null;
    }

    /**
     * Метод проверки ID на совпадение с другими в коллекции.
     *
     * @param id       Ключ-ID объекта.
     * @param vehicles Хранимая коллекция.
     * @return Результат проверки, где false- проходит проверку.
     */
    public static Boolean checkID(long id, Vector<Vehicle> vehicles) {
        final Boolean[] checker = {true};
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getId() != id) {
                checker[0] = true;
            } else try {
                throw new InvalidInputException("Продукт с таким ID уже существует.");
            } catch (InvalidInputException invalidInputException) {
                invalidInputException.printStackTrace();
            }
        }
        return checker[0];
    }

}
