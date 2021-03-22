package commands;

import data.HumanBeing;
import exceptions.IncorrectScriptInputException;
import exceptions.WrongAmountOfElementsException;
import manager.AskManager;
import manager.CollectionManager;

public class AddIfMax implements Executable{
    private AskManager askManager;
    private CollectionManager collectionManager;

    public AddIfMax(AskManager askManager, CollectionManager collectionManager) {
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
            if (Float.compare(human.getImpactSpeed(), collectionManager.getMaxImpactSpeed()) == 1) {
                collectionManager.add(human);
                System.out.println("Элемент успешно добавлен в коллекцию!");
            }
            else System.out.println("Элемент не был добавлен в коллекцию, так как его значение поля ImpactSpeed меньше максимального");
            return true;
        } catch (IncorrectScriptInputException e) {
            System.out.println("Не удалось выполнить скрипт! Введены некорректные данные!");
            return false;
        } catch (WrongAmountOfElementsException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
