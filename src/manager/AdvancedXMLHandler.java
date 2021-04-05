package manager;

import data.Car;
import data.Coordinates;
import data.HumanBeing;
import data.Mood;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.time.ZonedDateTime;
import java.util.LinkedHashSet;

public class AdvancedXMLHandler extends DefaultHandler{
    private LinkedHashSet<HumanBeing> humanBeings = new LinkedHashSet<>();
    private int id = 1;
    private String name;
    private Coordinates coordinates = new Coordinates(null, 0);
    private Boolean realHero;
    private Boolean hasToothpick;
    private Float impactSpeed;
    private String soundtrackName;
    private float minutesOfWaiting;
    private Mood mood = Mood.NULL;
    private Car car = new Car("");
    private String lastElementName;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        lastElementName = qName;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String information = new String(ch, start, length);

        information = information.replace("\n", "").trim();

        try {
            if (!information.isEmpty()) {
                if (lastElementName.equals("name"))
                    name = information;
                if (lastElementName.equals("coordinateX")) {
                    int intInformation = Integer.parseInt(information);
                    coordinates.setX(intInformation);
                }
                if (lastElementName.equals("coordinateY")) {
                    float intInformation = Float.parseFloat(information);
                    coordinates.setY(intInformation);
                }
                if (lastElementName.equals("realHero")) {
                    realHero = information.equals("true")? true : false;
                }
                if (lastElementName.equals("hasToothpick")) {
                    hasToothpick = information.equals("true")? true : false;
                }
                if (lastElementName.equals("impactSpeed")) {
                    float floatInformation = Float.parseFloat(information);
                    impactSpeed = floatInformation;
                }
                if (lastElementName.equals("soundtrackName"))
                    soundtrackName = information;
                if (lastElementName.equals("minutesOfWaiting")) {
                    float floatInformation = Float.parseFloat(information);
                    minutesOfWaiting = floatInformation;
                }
                if (lastElementName.equals("mood")) {
                    mood = information.length() != 0? Mood.valueOf(information) : Mood.NULL;
                }
                if (lastElementName.equals("carName")) {
                    car.setName(information);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка при чтении файла! Введите корректные данные!");
            System.exit(0);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        //if ((name != null && !name.isEmpty())&&(coordinates != null)
                //&&(realHero != null)
                //&&(impactSpeed != null)&&(soundtrackName != null && !soundtrackName.isEmpty())
                //&&(minutesOfWaiting != 0)&&(mood != null)&&(car != null)) {
        if (qName.equals("human")) {
            HumanBeing human = new HumanBeing(name, coordinates, realHero, hasToothpick, impactSpeed, soundtrackName, minutesOfWaiting, mood, car);
            human.setId(id);
            id++;
            human.setCreationDate();
            humanBeings.add(human);
            name = null;
            //coordinates.setX(null);
            //coordinates.setY(0);
            coordinates = new Coordinates(null, 0);
            realHero = null;
            hasToothpick = null;
            impactSpeed = null;
            soundtrackName = null;
            minutesOfWaiting = 0;
            mood = Mood.NULL;
            //car.setName("");
            car = new Car("");
        }
    }

    public LinkedHashSet<HumanBeing> getHumans() {
        return this.humanBeings;
    }
}
