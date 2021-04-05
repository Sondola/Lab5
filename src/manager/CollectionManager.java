package manager;

import javanet.staxutils.IndentingXMLStreamWriter;
import comparators.CarComparator;
import comparators.SoundtrackNameComparator;
import data.*;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class CollectionManager {
    private LinkedHashSet<HumanBeing> humanBeings;
    private LocalDateTime lastSave;
    private LocalDateTime lastInit;

    public CollectionManager(File xmlFile) {
        humanBeings = new LinkedHashSet<>();
        lastInit = LocalDateTime.now();
        lastSave = null;
        loadFile(xmlFile);
    }

    public void loadFile(File xmlFile) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            // Здесь мы определили анонимный класс, расширяющий класс DefaultHandler
            AdvancedXMLHandler handler = new AdvancedXMLHandler();
            parser.parse(xmlFile, handler);

            humanBeings = handler.getHumans();
        } catch (SAXException | ParserConfigurationException | IOException e) {

        }
    }

    public LinkedHashSet<HumanBeing> getCollection() {
        return this.humanBeings;
    }

    public void setCollection(LinkedHashSet<HumanBeing> humans) {
        this.humanBeings = humans;
    }

    public int collectionSize() {
        return humanBeings.size();
    }

    public LocalDateTime getLastSave(){
        return this.lastSave;
    }

    public LocalDateTime getLastInit(){
        return this.lastInit;
    }

    public Class<? extends LinkedHashSet> collectionType() {
        return humanBeings.getClass();
    }

    public int getNewId() {
        int id = 1;
        for (HumanBeing human : humanBeings) {
            if (human.getId() > id) id = human.getId() + 1;
        }
        return id;
    }

    public HumanBeing getById(int id) {
        for (HumanBeing human : humanBeings) {
            if (human.getId() == id) return human;
        }
        return null;
    }

    public float getMaxImpactSpeed() {
        float max = -883;
        for (HumanBeing human : humanBeings) {
            if (human.getImpactSpeed() > max) max = human.getImpactSpeed();
        }
        return max;
    }

    public float getMinImpactSpeed() {
        float min = Float.MAX_VALUE;
        for (HumanBeing human : humanBeings) {
            if (human.getImpactSpeed() < min) min = human.getImpactSpeed();
        }
        return min;
    }

    public void add(HumanBeing human) {
        human.setId(getNewId());
        humanBeings.add(human);
    }

    public void clearCollection() {
        humanBeings.clear();
    }

    public void showCollection() {
        if (humanBeings.size() != 0)
            for (HumanBeing human : humanBeings) {
                System.out.println(human.toString());
            }
        else System.out.println("Коллекция пуста!");
    }

    public void showCollection(LinkedHashSet<HumanBeing> humanBeings2) {
        if (humanBeings2.size() != 0)
            for (HumanBeing human : humanBeings2) {
                System.out.println(human.toString());
            }
        else System.out.println("Коллекция пуста!");
    }

    public void setNewHuman(int id, HumanBeing human) {
        for (HumanBeing humanBeing : humanBeings) {
            if (humanBeing.getId() == id) humanBeing = human;
            break;
        }
    }

    public void removeById(int id) {
        humanBeings.remove(getById(id));
    }

    public void removeGreater(HumanBeing human) {
        for (HumanBeing humanBeing : humanBeings) {
            if (Float.compare(humanBeing.getImpactSpeed(), human.getImpactSpeed()) == 1)
                humanBeings.remove(humanBeing);
        }
    }

    public void removeByCar(Car car) {
        CarComparator carComparator = new CarComparator();
        for (HumanBeing humanBeing : humanBeings) {
            if (carComparator.compare(humanBeing.getCar(), car) == 0)
                humanBeings.remove(humanBeing);
        }
    }

    public HumanBeing maxByCreationDate() {
        HumanBeing human = null;
        for (HumanBeing humanBeing : humanBeings) {
            if (human == null) {
                human = humanBeing;
                continue;
            }
            if (humanBeing.getCreationDate().compareTo(human.getCreationDate()) != -1)
                human = humanBeing;
        }
        return human;
    }

    public LinkedHashSet<HumanBeing> filterGreaterThanSoundtrackName(String soundtrackName) {
        LinkedHashSet<HumanBeing> humans = new LinkedHashSet<>();
        SoundtrackNameComparator soundtrackNameComparator = new SoundtrackNameComparator();
        for (HumanBeing human : humanBeings) {
            if (soundtrackNameComparator.compare(human.getSoundtrackName(), soundtrackName) == 1)
                humans.add(human);
        }
        return humans;
    }

    public void saveCollection(OutputStreamWriter fos) throws XMLStreamException, XMLStreamException {
        XMLOutputFactory output = XMLOutputFactory.newInstance();
        XMLStreamWriter writer = new IndentingXMLStreamWriter(output.createXMLStreamWriter(fos));

        writer.writeStartDocument("1.0");
        writer.writeStartElement("humanBeing");

        for (HumanBeing human : humanBeings) {
            human.toXML(writer);
        }

        writer.writeEndElement();
        // Закрываем XML-документ
        writer.writeEndDocument();
        writer.flush();
    }
}
