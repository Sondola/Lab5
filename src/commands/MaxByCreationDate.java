package commands;

import exceptions.NoElementsInCollectionException;
import exceptions.WrongAmountOfElementsException;
import manager.AskManager;
import manager.CollectionManager;

public class MaxByCreationDate implements Executable{
    private CollectionManager collectionManager;

    public MaxByCreationDate(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public boolean execute(String str) {
        try {
            if (str.length() != 0) throw new WrongAmountOfElementsException("Неправильное количество аргументов для команды");
            if (collectionManager.collectionSize() != 0)
                System.out.println(collectionManager.maxByCreationDate().toString());
            else throw new NoElementsInCollectionException("Коллекция пуста!");
            return true;
        } catch (WrongAmountOfElementsException | NoElementsInCollectionException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
