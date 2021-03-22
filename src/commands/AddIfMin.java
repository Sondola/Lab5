package commands;

import data.HumanBeing;
import exceptions.*;
import manager.AskManager;
import manager.CollectionManager;

public class AddIfMin implements Executable{
    private AskManager askManager;
    private CollectionManager collectionManager;

    public AddIfMin(AskManager askManager, CollectionManager collectionManager) {
        this.askManager = askManager;
        this.collectionManager = collectionManager;
    }

    public boolean execute(String str) {
        try {
            if(str.length() != 0){
                throw new WrongAmountOfElementsException("Неправильное количество аргументов для команды");
            }
            HumanBeing human = new HumanBeing(askManager.askName(),
                    askManager.askCoordinates(),
                    askManager.askRealHero(),
                    askManager.askHasToothpick(),
                    askManager.askImpactSpeed(),
                    askManager.askSoundtrackName(),
                    askManager.askMinutesOfWaiting(),
                    askManager.askMood(),
                    askManager.askCar());

            if (collectionManager.collectionSize() != 0) {
                if (human.getImpactSpeed() < collectionManager.getMinImpactSpeed()) {
                    collectionManager.add(human);
                    System.out.println("Элемент успешно добавлен в коллекцию!");
                } else
                    System.out.println("Элемент не был добавлен в коллекцию, так как его значение поля ImpactSpeed больше минимального");
            } else throw new NoElementsInCollectionException("В коллекции нет элементов для сравнения!");
            return true;
        } catch (IncorrectScriptInputException e) {
            System.out.println("Не удалось выполнить скрипт! Введены некорректные данные!");
            return false;
        } catch (WrongAmountOfElementsException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (NoElementsInCollectionException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
