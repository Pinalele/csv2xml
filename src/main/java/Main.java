import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import generated.People;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String csvFile = "D:\\projects\\git\\csv2xml\\src\\main\\resources\\TemplateCsv.csv";

        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
            // Preparar JAXB contexto e marshaller
            JAXBContext context = JAXBContext.newInstance(People.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            People people = new People(); // Elemento raiz

            String[] line;
            while ((line = reader.readNext()) != null) {
                People.Person person = new People.Person();
                person.setName(line[0]);
                person.setAge(Integer.parseInt(line[1]));
                person.setOccupation(line[2]);
                people.getPerson().add(person);
            }

            // Converter para XML e salvar
            marshaller.marshal(people, new File("output.xml"));

        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }
}
