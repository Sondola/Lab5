package commands;

import exceptions.WrongAmountOfElementsException;
import manager.Console;

/**
 * Command for script executing from the certain file
 * @author Julia Polezhaeva
 * @version 1.1
 */

public class ExecuteScript implements Executable{
    private Console console;
    /**
     * Constructor for the command
     */
    public ExecuteScript(Console console){
        this.console = console;
    }

    /**
     * Command execution
     * @param str command argument
     * @return command result
     */
    @Override
    public boolean execute(String str) {
        try{
            String [] commandArr = str.trim().split(" ");
            if(str.length() == 0 || commandArr.length != 1){
                throw new WrongAmountOfElementsException("Неправильное количество аргументов для команды");
            }
            console.scriptMode(str, console.isWork());
            return true;
        }
        catch (WrongAmountOfElementsException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

}