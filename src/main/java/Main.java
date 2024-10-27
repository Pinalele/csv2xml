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
            String[] line;
            while ((line = reader.readNext()) != null) {
                // Imprime os dados do CSV (linha por linha)
                for (String value : line) {
                    System.out.print(value + " ");
                }
                System.out.println();
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }
}
