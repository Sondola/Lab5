package commands;

import exceptions.WrongAmountOfElementsException;
import manager.Console;

public class Exit implements Executable{
    private Console console;
    /**
     * Constructor for the command
     */
    public Exit(Console console){
        this.console = console;
    }

    /**
     * Command execution
     * @param str command argument
     * @return command result
     */
    public boolean execute(String str) {
        try {
            if(str.length() != 0){
                throw new WrongAmountOfElementsException("Неправильное количество аргументов для команды");
            }
            console.setWork(false);
            return true;
        } catch (WrongAmountOfElementsException e) {
            System.out.println(e.getMessage());
            return false;
        }    }
}
