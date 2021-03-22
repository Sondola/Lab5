package commands;

import data.HumanBeing;
import exceptions.IncorrectScriptInputException;
import exceptions.NoElementsInCollectionException;
import exceptions.WrongAmountOfElementsException;
import manager.AskManager;
import manager.CollectionManager;

public class RemoveGreater implements Executable{
    private AskManager askManager;
    private CollectionManager collectionManager;

    public RemoveGreater(AskManager askManager, CollectionManager collectionManager) {
        this.askManager = askManager;
        this.collectionManager = collectionManager;
    }

    public boolean execute(String str) {
        try {
            if (str.length() != 0) throw new WrongAmountOfElementsException("Неправильное количество аргументов для команды");
            if (collectionManager.collectionSize() != 0) {
                HumanBeing human = new HumanBeing(askManager.askName(),
                        askManager.askCoordinates(),
                        askManager.askRealHero(),
                        askManager.askHasToothpick(),
                        askManager.askImpactSpeed(),
                        askManager.askSoundtrackName(),
                        askManager.askMinutesOfWaiting(),
                        askManager.askMood(),
                        askManager.askCar());
                collectionManager.removeGreater(human);
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
