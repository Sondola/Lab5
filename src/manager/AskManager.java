package manager;

import data.*;
import exceptions.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

public class AskManager {
    /**
     * Reader for interactive mode
     */
    private BufferedReader br;
    /**
     * Reader for script mode
     */
    private BufferedReader br2;
    /**
     * Mode (true - interactive; false - script)
     */
    private boolean interactiveMode;

    /**
     * Creating Ask Manager
     * @param br {@link AskManager#br}
     */
    public AskManager(BufferedReader br){
        this.br = br;
        this.interactiveMode = true;
    }

    /**
     * Changing Interactive Mode to Script Mode
     * @param br2 {@link AskManager#br2}
     */
    public void addScriptMode(BufferedReader br2){
        setBf2(br2);
        setInteractiveMode(false);
    }

    /**
     * Setting {@link AskManager#br2}
     * @param br2 new Reader
     */
    private void setBf2(BufferedReader br2){
        this.br2 = br2;
    }

    /**
     * Setting {@link AskManager#interactiveMode}
     * @param interactiveMode new mode
     */
    void setInteractiveMode(boolean interactiveMode){
        this.interactiveMode = interactiveMode;
    }

    public String askName() throws IncorrectScriptInputException{
        String name = null;
        while(true) {
            try {
                System.out.println("Введите имя: ");
                if(interactiveMode){
                    name = br.readLine().trim().replaceAll("\uFFFD", "");
                }
                else{
                    name = br2.readLine().trim().replaceAll("\uFFFD", "");
                }
                if (name.equals("")) throw new EmptyNameException("Поле не может быть пустым!");
                break;
            } catch (EmptyNameException e2) {
                System.out.println(e2.getMessage());
                if (!interactiveMode) throw new IncorrectScriptInputException();
            } catch (IOException e) {
                System.out.println("Ошибка ввода!");
                if (!interactiveMode) throw new IncorrectScriptInputException();
            }
        }
        return name;
    }

    public Coordinates askCoordinates() throws IncorrectScriptInputException {
        Coordinates coordinates = new Coordinates(askCoordinateX(), askCoordinateY());
        return coordinates;
    }

    public Integer askCoordinateX() throws IncorrectScriptInputException {
        Integer coordX = 0;
        while(true) {
            try {
                System.out.println("Введите координату по X: ");
                String strX = "";
                if(interactiveMode){
                    strX = br.readLine().trim().replaceAll("\uFFFD", "");
                }
                else{
                    strX = br2.readLine().trim().replaceAll("\uFFFD", "");
                }
                if (strX.equals("")) throw new EmptyCoordinateException("Поле не может быть пустым!");
                coordX = Integer.parseInt(strX);
                break;
            } catch (EmptyCoordinateException e2) {
                System.out.println(e2.getMessage());
                if (!interactiveMode) throw new IncorrectScriptInputException();
            } catch (NumberFormatException e) {
                System.out.println("Некорректный ввод! Введите целое число!");
                if (!interactiveMode) throw new IncorrectScriptInputException();
            } catch (IOException e) {
                System.out.println("Ошибка ввода!");
                if (!interactiveMode) throw new IncorrectScriptInputException();
            }
        }
        return coordX;
    }

    public float askCoordinateY() throws IncorrectScriptInputException {
        float coordY = 0;
        while(true) {
            try {
                System.out.println("Введите координату по Y: ");
                String strY = "";
                if(interactiveMode){
                    strY = br.readLine().trim().replaceAll("\uFFFD", "");
                }
                else{
                    strY = br2.readLine().trim().replaceAll("\uFFFD", "");
                }
                if (strY.equals("")) throw new EmptyCoordinateException("Поле не может быть пустым!");
                coordY = Float.parseFloat(strY);
                break;
            } catch (EmptyCoordinateException e2) {
                System.out.println(e2.getMessage());
                if (!interactiveMode) throw new IncorrectScriptInputException();
            } catch (NumberFormatException e) {
                System.out.println("Некорректный ввод! Введите целое или дробное число!");
                if (!interactiveMode) throw new IncorrectScriptInputException();
            } catch (IOException e) {
                System.out.println("Ошибка ввода!");
                if (!interactiveMode) throw new IncorrectScriptInputException();
            }
        }
        return coordY;
    }

    public Boolean askRealHero() throws IncorrectScriptInputException {
        Boolean realHero = null;
        while(true) {
            try {
                System.out.println("Это реальный герой?" + "\n0 - нет" + "\n1 - да");
                String rH = "";
                if(interactiveMode){
                    rH = br.readLine().trim().replaceAll("\uFFFD", "");
                }
                else{
                    rH = br2.readLine().trim().replaceAll("\uFFFD", "");
                }
                if (rH.equals("")) throw new EmptyCoordinateException("Поле не может быть пустым!");
                if (rH.equals("0")) realHero = false;
                else if (rH.equals("1")) realHero = true;
                else throw new IncorrectRealHeroException("Некорректный ввод!");
                break;
            } catch (IncorrectRealHeroException e) {
                System.out.println(e.getMessage());
                if (!interactiveMode) throw new IncorrectScriptInputException();
            } catch (EmptyCoordinateException e) {
                System.out.println(e.getMessage());
                if (!interactiveMode) throw new IncorrectScriptInputException();
            } catch (IOException e) {
                System.out.println("Ошибка ввода!");
                if (!interactiveMode) throw new IncorrectScriptInputException();
            }
        }
        return realHero;
    }

    public Boolean askHasToothpick() throws IncorrectScriptInputException {
        Boolean hasToothpick = null;
        while(true) {
            try {
                System.out.println("Зубочистка присутствует?" + "\n0 - нет" + "\n1 - да" + "\n2 - понятия не имею");
                String toothpick = "";
                if(interactiveMode){
                    toothpick = br.readLine().trim().replaceAll("\uFFFD", "");
                }
                else{
                    toothpick = br2.readLine().trim().replaceAll("\uFFFD", "");
                }
                if (toothpick.equals("0")) hasToothpick = false;
                else if (toothpick.equals("1")) hasToothpick = true;
                else if (toothpick.equals("2")) break;
                else throw new IncorrectToothpickException("Некорректный ввод!");
                break;
            } catch (IncorrectToothpickException e) {
                System.out.println(e.getMessage());
                if (!interactiveMode) throw new IncorrectScriptInputException();
            } catch (IOException e) {
                System.out.println("Ошибка ввода!");
                if (!interactiveMode) throw new IncorrectScriptInputException();
            }
        }
        return hasToothpick;
    }

    public Float askImpactSpeed() throws IncorrectScriptInputException{
        Float impactSpeed = 0.0F;
        while(true) {
            try {
                System.out.println("Введите скорость удара: ");
                String iS = "";
                if(interactiveMode){
                    iS = br.readLine().trim().replaceAll("\uFFFD", "");
                }
                else{
                    iS = br2.readLine().trim().replaceAll("\uFFFD", "");
                }
                if (iS.equals("")) throw new EmptyCoordinateException("Поле не может быть пустым!");
                impactSpeed = Float.parseFloat(iS);
                if (impactSpeed < -882) throw new IncorrectImpactSpeedException("Значение поля не может быть меньше -882!");
                break;
            } catch (IncorrectImpactSpeedException | EmptyCoordinateException e) {
                System.out.println(e.getMessage());
                if (!interactiveMode) throw new IncorrectScriptInputException();
            } catch (NumberFormatException e) {
                System.out.println("Некорректный ввод! Введите целое или дробное число!");
                if (!interactiveMode) throw new IncorrectScriptInputException();
            } catch (IOException e) {
                System.out.println("Ошибка ввода!");
                if (!interactiveMode) throw new IncorrectScriptInputException();
            }
        }
        return impactSpeed;
    }

    public String askSoundtrackName() throws IncorrectScriptInputException{
        String soundtrackName = null;
        while(true) {
            try {
                System.out.println("Введите название песни: ");
                if(interactiveMode){
                    soundtrackName = br.readLine().trim().replaceAll("\uFFFD", "");
                }
                else{
                    soundtrackName = br2.readLine().trim().replaceAll("\uFFFD", "");
                }
                if (soundtrackName.equals("")) throw new EmptyNameException("Поле не может быть пустым!");
                break;
            } catch (EmptyNameException e2) {
                System.out.println(e2.getMessage());
                if (!interactiveMode) throw new IncorrectScriptInputException();
            } catch (IOException e) {
                System.out.println("Ошибка ввода!");
                if (!interactiveMode) throw new IncorrectScriptInputException();
            }
        }
        return soundtrackName;
    }

    public float askMinutesOfWaiting() throws IncorrectScriptInputException{
        float minutesOfWaiting = 0;
        while(true) {
            try {
                System.out.println("Введите количество минут ожидания: ");
                String mow = "";
                if(interactiveMode){
                    mow = br.readLine().trim().replaceAll("\uFFFD", "");
                }
                else{
                    mow = br2.readLine().trim().replaceAll("\uFFFD", "");
                }
                if (mow.equals("")) throw new EmptyCoordinateException("Поле не может быть пустым!");
                minutesOfWaiting = Float.parseFloat(mow);
                break;
            } catch (EmptyCoordinateException e2) {
                System.out.println(e2.getMessage());
                if (!interactiveMode) throw new IncorrectScriptInputException();
            } catch (NumberFormatException e) {
                System.out.println("Некорректный ввод! Введите целое или дробное число!");
                if (!interactiveMode) throw new IncorrectScriptInputException();
            } catch (IOException e) {
                System.out.println("Ошибка ввода!");
                if (!interactiveMode) throw new IncorrectScriptInputException();
            }
        }
        return minutesOfWaiting;
    }

    public Mood askMood() throws IncorrectScriptInputException{
        Mood mood = null;
        while(true) {
            try {
                System.out.println("Введите настроение из предложенных вариантов: ");
                Mood.printMood();
                String m = "";
                if(interactiveMode){
                    m = br.readLine().trim().replaceAll("\uFFFD", "");
                }
                else{
                    m = br2.readLine().trim().replaceAll("\uFFFD", "");
                }
                if (m.equals("")) break;
                mood = Mood.valueOf(m);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Некорректный ввод!");
                if (!interactiveMode) throw new IncorrectScriptInputException();
            } catch (IOException e) {
                System.out.println("Ошибка ввода!");
                if (!interactiveMode) throw new IncorrectScriptInputException();
            }
        }
        return mood;
    }

    public Car askCar() throws IncorrectScriptInputException{
        Car car = new Car(askCarName());
        return car;
    }

    public String askCarName() throws IncorrectScriptInputException{
        String carName = null;
        try {
            System.out.println("Введите название машины: ");
            if (interactiveMode) {
                carName = br.readLine().trim().replaceAll("\uFFFD", "");
            } else {
                carName = br2.readLine().trim().replaceAll("\uFFFD", "");
            }
        } catch (IOException e) {
            System.out.println("Ошибка ввода!");
            if (!interactiveMode) throw new IncorrectScriptInputException();
        }
        return carName;
    }
}
