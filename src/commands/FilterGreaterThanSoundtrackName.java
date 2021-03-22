package commands;

import exceptions.*;
import manager.AskManager;
import manager.CollectionManager;

public class FilterGreaterThanSoundtrackName implements Executable{
    private AskManager askManager;
    private CollectionManager collectionManager;

    public FilterGreaterThanSoundtrackName(AskManager askManager, CollectionManager collectionManager) {
        this.askManager = askManager;
        this.collectionManager = collectionManager;
    }

    public boolean execute(String str) {
        try {
            if(str.length() == 0){
                throw new WrongAmountOfElementsException("Неправильное количество аргументов для команды");
            }
            if (collectionManager.collectionSize() != 0)
                collectionManager.showCollection(collectionManager.filterGreaterThanSoundtrackName(askManager.askSoundtrackName()));
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
