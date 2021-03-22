package commands;

import exceptions.WrongAmountOfElementsException;

public class Help implements Executable{
    public boolean execute(String str) {
        try{
            if(str.length() != 0){
                throw new WrongAmountOfElementsException("Неправильное количество аргументов для команды");
            }
            System.out.println("Программа поддерживает выполнение следующих команд:\n" +
                    "help - вывести справку по доступным командам\n" +
                    "info - вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                    "show - вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                    "add {element} - добавить новый элемент в коллекцию\n" +
                    "update id {element} - обновить значение элемента коллекции, id которого равен заданному\n" +
                    "remove_by_id id - удалить элемент из коллекции по его id\n" +
                    "clear - очистить коллекцию\n" +
                    "save - сохранить коллекцию в файл\n" +
                    "execute_script file_name - считать и исполнить скрипт из указанного файла\n" +
                    "exit - завершить программу (без сохранения в файл)\n" +
                    "add_if_max {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции\n" +
                    "add_if_min {element} : добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции\n" +
                    "remove_greater {element} : удалить из коллекции все элементы, превышающие заданный\n" +
                    "remove_all_by_car car : удалить из коллекции все элементы, значение поля car которого эквивалентно заданному\n" +
                    "max_by_creation_date : вывести любой объект из коллекции, значение поля creationDate которого является максимальным\n" +
                    "filter_greater_than_soundtrack_name soundtrackName : вывести элементы, значение поля soundtrackName которых больше заданного");
            return true;
        }
        catch (WrongAmountOfElementsException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
