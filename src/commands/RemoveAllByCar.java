package commands;

import data.*;
import exceptions.IncorrectScriptInputException;
import exceptions.NoElementsInCollectionException;
import exceptions.WrongAmountOfElementsException;
import manager.AskManager;
import manager.CollectionManager;

public class RemoveAllByCar implements Executable{
    private AskManager askManager;
    private CollectionManager collectionManager;

    public RemoveAllByCar(AskManager askManager, CollectionManager collectionManager) {
        this.askManager = askManager;
        this.collectionManager = collectionManager;
    }

    public boolean execute(String str) {
        try {
            if (str.length() != 0) throw new WrongAmountOfElementsException("Неправильное количество аргументов для команды");
            if (collectionManager.collectionSize() != 0) {
                Car car = askManager.askCar();
                collectionManager.removeByCar(car);
            }
            else throw new NoElementsInCollectionException("Коллекция пуста!");
            return true;
        } catch (WrongAmountOfElementsException | NoElementsInCollectionException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (IncorrectScriptInputException e) {
            System.out.println("Не удалось выполнить скрипт! Введены некорректные данные!");
            return false;
        }
    }
}
