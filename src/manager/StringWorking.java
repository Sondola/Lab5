package manager;

import exceptions.IncorrectCommandException;
import exceptions.RecursionScriptException;

import java.util.Stack;

/**
 * Class for refactoring the command line and contacting with {@link CommandManager}
 * @author Julia Polezhaeva
 * @version 1.1
 */

public class StringWorking {
    /**
     * Manger for commands
     * @see CommandManager
     */
    private CommandManager commandManager;
    /**
     * Name of scripts which are executing now
     */
    private Stack<String> scriptNames;

    /**
     * Constructor for class
     * @param commandManager {@link CommandManager}
     */
    public StringWorking(CommandManager commandManager){
        this.commandManager = commandManager;
        scriptNames = new Stack<>();
    }

    /**
     * Refactoring string before the execution
     * @param commandArr command line
     * @return refactored String
     * @throws IncorrectCommandException undefined command
     */
    private String refactorString(String[] commandArr) throws IncorrectCommandException {
        if(commandArr.length == 0) throw new IncorrectCommandException("Введена некорректная команда");
        String line;
        if(commandArr.length == 1){
            line = "";
        }
        else{
            line = commandArr[1];
        }
        return line;
    }

    /**
     * Connecting with {@link CommandManager}
     * @param command command line
     * @return result of the execution
     * @throws IncorrectCommandException undefined command
     * @throws RecursionScriptException script recursion
     */
    public boolean chooseCommand(String command) throws IncorrectCommandException, RecursionScriptException {
        String[] commandArr = command.trim().split(" ", 2);
        String line = refactorString(commandArr);
        boolean res;
        switch (commandArr[0]){
            case "help":
                return commandManager.help(line);
            case "info":
                return commandManager.info(line);
            case "show":
                return commandManager.show(line);
            case "add":
                return commandManager.add(line);
            case "update":
                return commandManager.update(line);
            case "remove_by_id":
                return commandManager.remove_by_id(line);
            case "clear":
                return commandManager.clear(line);
            case "save":
                return commandManager.save(line);
            case "execute_script":
                for(String name : scriptNames){
                    if(name.equals(line)){
                        throw new RecursionScriptException("Ошибка: рекурсивный вызов файла-скрипта");
                    }
                }
                scriptNames.push(line);
                res = commandManager.execute_script(line);
                scriptNames.pop();
                return res;
            case "exit":
                return commandManager.exit(line);
            case "add_if_max":
                return commandManager.add_if_max(line);
            case "add_if_min":
                return commandManager.add_if_min(line);
            case "remove_greater":
                return commandManager.remove_greater(line);
            case "remove_all_by_car":
                return commandManager.remove_all_by_car (line);
            case "max_by_creation_date":
                return commandManager.max_by_creation_date (line);
            case "filter_greater_than_soundtrack_name":
                return commandManager.filter_greater_than_soundtrack_name (line);
            default:
                throw new IncorrectCommandException("Введена некорректная команда! Введите help для просмотра списка команд");
        }
    }
}
