package commands;

import exceptions.WrongAmountOfElementsException;
import manager.CollectionManager;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.io.*;

public class Save implements Executable {
    private CollectionManager collectionManager;
    File xmlFile;

    public Save(CollectionManager collectionManager, File xmlFile) {
        this.collectionManager = collectionManager;
        this.xmlFile = xmlFile;
    }

    public boolean execute(String str) {
        try {
            if (str.length() != 0) {
                throw new WrongAmountOfElementsException("Неправильное количество аргументов для команды");
            }
            OutputStreamWriter fos = new OutputStreamWriter(new FileOutputStream(xmlFile));
            collectionManager.saveCollection(fos);
            return true;
        } catch (WrongAmountOfElementsException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (XMLStreamException e) {
            System.out.println("Error");
            return false;
        }
    }
}
