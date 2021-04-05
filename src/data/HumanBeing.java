package data;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


public class HumanBeing {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Boolean realHero; //Поле не может быть null
    private Boolean hasToothpick; //Поле может быть null
    private Float impactSpeed; //Значение поля должно быть больше -882, Поле может быть null
    private String soundtrackName; //Поле не может быть null
    private float minutesOfWaiting;
    private Mood mood; //Поле может быть null
    private Car car; //Поле может быть null

    public HumanBeing(String name,
                      Coordinates coordinates,
                      Boolean realHero,
                      Boolean hasToothpick,
                      Float impactSpeed,
                      String soundtrackName,
                      float minutesOfWaiting,
                      Mood mood,
                      Car car) {
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = ZonedDateTime.now();
        this.realHero = realHero;
        this.hasToothpick = hasToothpick;
        this.impactSpeed = impactSpeed;
        this.soundtrackName = soundtrackName;
        this.minutesOfWaiting = minutesOfWaiting;
        this.mood = mood;
        this.car = car;
    }

    public void setId(int id) {this.id = id;}

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setCreationDate() {
        this.creationDate = ZonedDateTime.now();
    }

    public void setRealHero(boolean realHero) {
        this.realHero = realHero;
    }

    public void setHasToothpick(Boolean hasToothpick) {
        this.hasToothpick = hasToothpick;
    }

    public void setImpactSpeed(Float impactSpeed) {
        this.impactSpeed = impactSpeed;
    }

    public void setMinutesOfWaiting(float minutesOfWaiting) {
        this.minutesOfWaiting = minutesOfWaiting;
    }

    public void setSoundtrackName(String soundtrackName) {
        this.soundtrackName = soundtrackName;
    }

    public void setMood(Mood mood) {
        this.mood = mood;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Boolean getHasToothpick() {
        return hasToothpick;
    }

    public Boolean getRealHero() {
        return realHero;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Car getCar() {
        return car;
    }

    public Float getImpactSpeed() {
        return impactSpeed;
    }

    public float getMinutesOfWaiting() {
        return minutesOfWaiting;
    }

    public Mood getMood() {
        return mood;
    }

    public String getSoundtrackName() {
        return soundtrackName;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public String toString() {
        String human = "Id: " + id + "\n";
        human += "Имя: " + name + "\n";
        human += "Координаты: " + "(" + coordinates.getX() + "; " + coordinates.getY() + ")\n";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        human += "Дата создания: " + creationDate.format(formatter) + "\n";
        human += "Реальный герой: " + realHero + "\n";
        human += "Наличие зубочистки: " + hasToothpick + "\n";
        human += "Скорость удара: " + impactSpeed + "\n";
        human += "Название песни: " + soundtrackName + "\n";
        human += "Время ожидания: " + minutesOfWaiting + "\n";
        human += "Настроение: " + (mood != null? mood.toString() : "") + "\n";
        human += "Машина: " + car.getName() + "\n";
        return human;
    }

    public void toXML(XMLStreamWriter writer) {
        try {
            writer.writeStartElement("human");

            writer.writeStartElement("id");
            writer.writeCharacters(String.valueOf(id));
            writer.writeEndElement();

            writer.writeStartElement("name");
            writer.writeCharacters(name);
            writer.writeEndElement();

            writer.writeStartElement("coordinates");

            writer.writeStartElement("coordinateX");
            writer.writeCharacters(String.valueOf(coordinates.getX()));
            writer.writeEndElement();

            writer.writeStartElement("coordinateY");
            writer.writeCharacters(String.valueOf(coordinates.getY()));
            writer.writeEndElement();

            writer.writeEndElement();

            writer.writeStartElement("creationDate");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            writer.writeCharacters(String.valueOf(creationDate.format(formatter)));
            writer.writeEndElement();

            writer.writeStartElement("realHero");
            writer.writeCharacters(String.valueOf(realHero));
            writer.writeEndElement();

            writer.writeStartElement("hasToothpick");
            writer.writeCharacters(String.valueOf(hasToothpick));
            writer.writeEndElement();

            writer.writeStartElement("impactSpeed");
            writer.writeCharacters(String.valueOf(impactSpeed));
            writer.writeEndElement();

            writer.writeStartElement("soundtrackName");
            writer.writeCharacters(soundtrackName);
            writer.writeEndElement();

            writer.writeStartElement("minutesOfWaiting");
            writer.writeCharacters(String.valueOf(minutesOfWaiting));
            writer.writeEndElement();

            if (mood.toString().length() != 0) {
                writer.writeStartElement("mood");
                writer.writeCharacters(mood.toString());
                writer.writeEndElement();
            } else
                writer.writeEmptyElement("mood");

            writer.writeStartElement("car");

            if (car.getName().length() != 0) {
                writer.writeStartElement("carName");
                writer.writeCharacters(car.getName());
                writer.writeEndElement();
            } else
                writer.writeEmptyElement("carName");

            writer.writeEndElement();

            writer.writeEndElement();
        } catch (XMLStreamException ex) {
            ex.printStackTrace();
        }
    }
}
