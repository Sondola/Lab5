package manager;

import exceptions.IncorrectCommandException;
import exceptions.RecursionScriptException;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Working environment for command line reading
 *
 * @author NastyaBordun
 * @version 1.1
 */

public class Console {
    /**
     * Variable to identify the need for further work
     */
    private boolean work;
    /**
     * Reader for line reading support {@link BufferedReader#readLine()}
     */
    private BufferedReader br;
    /**
     * Class providing getting correct information from the user {@link AskManager}
     */
    private AskManager askManager;

    private StringWorking stringWorking = null;

    /**
     * Constructor - new working environment creating
     *
     * @see Console#interactiveMode()
     */
    public Console(BufferedReader br, AskManager askManager) {
        this.br = br;
        this.askManager = askManager;
        this.work = true;
    }

    /**
     * Setting {@link StringWorking} editor for commands
     *
     * @param stringWorking
     */
    public void setStringWorking(StringWorking stringWorking) {
        this.stringWorking = stringWorking;
    }

    /**
     * Setting working state
     *
     * @param work needful state
     */
    public void setWork(boolean work) {
        this.work = work;
    }

    /**
     * Checking the working state
     *
     * @return working state
     */
    public boolean isWork() {
        return work;
    }

    /**
     * Work in the interactive mode
     */
    public void interactiveMode() {
        while (work) {
            try {
                String command = br.readLine().trim();
                if (stringWorking.chooseCommand(command)) {
                    System.out.println("Команда выполнена успешно");
                } else {
                    System.out.println("Команда не выполнена");
                }
            } catch (IOException e) {
                System.out.println("Ошибка ввода");
            } catch (IncorrectCommandException | RecursionScriptException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Work with a script
     *
     * @param path     path to file passed by chooseCommand method
     * @param lastWork last value of working state for recursion
     * @see AskManager#addScriptMode(BufferedReader)
     */
    public void scriptMode(String path, boolean lastWork) {
        try {
            FileInputStream file = new FileInputStream(path);
            BufferedInputStream bf2 = new BufferedInputStream(file);
            BufferedReader r2 = new BufferedReader(new InputStreamReader(bf2, StandardCharsets.UTF_8));

            //BufferedReader br = new BufferedReader(new FileReader(path));
            askManager.addScriptMode(r2);

            System.out.println("Взаимодействие с файлом-скриптом");

//            this.setWork(true);
            String line = r2.readLine().trim();
            while (line != null && this.work) {
//            while(line != null){
                try {
                    if (stringWorking.chooseCommand(line)) {
                        System.out.println("Команда выполнена успешно");
                    } else {
                        System.out.println("Команда не выполнена");
                    }

                } catch (IncorrectCommandException | RecursionScriptException e) {
                    System.out.println(e.getMessage());
                }
                line = r2.readLine();
            }
            if (work) {
                setWork(lastWork);
                askManager.setInteractiveMode(true);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
            setWork(lastWork);
            askManager.setInteractiveMode(true);
        } catch (IOException e) {
            System.out.println("Ошибка ввода");
            setWork(lastWork);
            askManager.setInteractiveMode(true);
        }
    }


}