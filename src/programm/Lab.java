package programm;

import commands.*;
import data.Mood;
import manager.*;
import manager.Console;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Lab {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Недопустимое количество аргументов командной строки!");
            System.exit(0);
        }

        try{
            FileInputStream is = new FileInputStream(args[0]);
            //FileInputStream is = new FileInputStream("lab5.xml");
        }catch (FileNotFoundException e) {
            File file = new File(args[0]);
            if (!file.canRead() && !file.canWrite()) {
                try {
                    file.createNewFile();
                    System.out.println("Файла не было, но мы его создали!");
                } catch (IOException e2) {
                    System.out.println("error");
                    System.exit(0);
                }
            } else {
                System.out.println("Ошибка доступа");
                System.exit(0);
            }
        }
        File fileName = new File(args[0]);
        //File fileName = new File("lab5.xml");

        BufferedInputStream bf = new BufferedInputStream(System.in);
        BufferedReader r = new BufferedReader(new InputStreamReader(bf, StandardCharsets.UTF_8));
        AskManager askManager = new AskManager(r);

        CollectionManager collectionManager = new CollectionManager(fileName);
        Console console = new Console(r, askManager);
        CommandManager commandManager = new CommandManager(
                new Info(collectionManager),
                new Help(),
                new Show(collectionManager),
                new Add(askManager, collectionManager),
                new Update(askManager, collectionManager),
                new RemoveById(askManager, collectionManager),
                new Clear(collectionManager),
                new Save(collectionManager, fileName),
                new ExecuteScript(console),
                new Exit(console),
                new AddIfMax(askManager, collectionManager),
                new AddIfMin(askManager, collectionManager),
                new RemoveGreater(askManager, collectionManager),
                new RemoveAllByCar(askManager, collectionManager),
                new MaxByCreationDate(collectionManager),
                new FilterGreaterThanSoundtrackName(askManager, collectionManager));
        StringWorking stringWorking = new StringWorking(commandManager);
        console.setStringWorking(stringWorking);

        console.interactiveMode();

    }
}