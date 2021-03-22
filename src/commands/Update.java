package commands;

import data.HumanBeing;
import exceptions.IncorrectScriptInputException;
import exceptions.WrongAmountOfElementsException;
import exceptions.WrongIdException;
import manager.AskManager;
import manager.CollectionManager;

public class Update implements Executable{
    private AskManager askManager;
    private CollectionManager collectionManager;

    public Update(AskManager askManager, CollectionManager collectionManager) {
        this.askManager = askManager;
        this.collectionManager = collectionManager;
    }

    public boolean execute(String str) {
        try {
            if (str.length() == 0) throw new WrongAmountOfElementsException("Неправильное количество аргументов для команды");
            int id = Integer.parseInt(str);
            HumanBeing human = collectionManager.getById(id);
            if (human != null) {
                human.setName(askManager.askName());
                human.setCoordinates(askManager.askCoordinates());
                human.setRealHero(askManager.askRealHero());
                human.setRealHero(askManager.askHasToothpick());
                human.setImpactSpeed(askManager.askImpactSpeed());
                human.setSoundtrackName(askManager.askSoundtrackName());
                human.setMinutesOfWaiting(askManager.askMinutesOfWaiting());
                human.setMood(askManager.askMood());
                human.setCar(askManager.askCar());
                collectionManager.setNewHuman(id, human);
                System.out.println("Данные были успешно обновлены!");
            }
            else throw new WrongIdException("Не найден человек с таким id");
            return true;
        } catch (WrongAmountOfElementsException | WrongIdException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (IncorrectScriptInputException e) {
            System.out.println("Не удалось выполнить скрипт! Введены некорректные данные!");
            return false;
        } catch (NumberFormatException e) {
            System.out.println("Некорректно введен id");
            return false;
        }
    }
}
