package commands;

import exceptions.NoElementsInCollectionException;
import exceptions.WrongAmountOfElementsException;
import exceptions.WrongIdException;
import manager.AskManager;
import manager.CollectionManager;

public class RemoveById implements Executable{
    private CollectionManager collectionManager;

    public RemoveById(AskManager askManager, CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public boolean execute(String str) {
        try {
            if (str.length() == 0) throw new WrongAmountOfElementsException("Неправильное количество аргументов для команды");
            if (collectionManager.collectionSize() != 0) {
                int id = Integer.parseInt(str);
                if (collectionManager.getById(id) != null)
                    collectionManager.removeById(id);
                else throw new WrongIdException("Не найден человек с таким id");
            }
            else throw new NoElementsInCollectionException("Коллекция пуста!");
            return true;
        } catch (WrongAmountOfElementsException | WrongIdException | NoElementsInCollectionException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (NumberFormatException e) {
            System.out.println("Некорректно введен id");
            return false;
        }
    }
}
