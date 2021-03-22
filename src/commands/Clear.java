package commands;

import exceptions.WrongAmountOfElementsException;
import manager.CollectionManager;

public class Clear implements Executable{
    private CollectionManager collectionManager;

    public Clear(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public boolean execute(String str) {
        try {
            if(str.length() != 0){
                throw new WrongAmountOfElementsException("Неправильное количество аргументов для команды");
            }
            collectionManager.clearCollection();
            System.out.println("Коллекция очищена");
            return true;
        } catch (WrongAmountOfElementsException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
