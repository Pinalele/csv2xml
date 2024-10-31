import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import generated.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String csvFile = "D:\\projects\\git\\csv2xml\\src\\main\\resources\\TemplateCsv.csv";
        String xmlFile = "D:\\projects\\git\\csv2xml\\src\\main\\resources\\output.xml";
        String xsdFile = "D:\\projects\\git\\csv2xml\\src\\main\\resources\\xsd\\CaaMessageAudit.xsd";

        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
            JAXBContext context = JAXBContext.newInstance(LondonCaaMessageAuditElement.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            LondonCaaMessageAuditElement rootElement = new LondonCaaMessageAuditElement();


            String[] line;
            while ((line = reader.readNext()) != null) {
                for (String val : line) {
                    String[] splitValues = val.split(";");


                    CaaMessageAuditObject londonCaaMessageAuditObject = new CaaMessageAuditObject();
                    londonCaaMessageAuditObject.setTrigram(splitValues[0]);
                    londonCaaMessageAuditObject.setTypeOfData(splitValues[1]);
                    londonCaaMessageAuditObject.setDateOfExtraction(splitValues[2]);
                    londonCaaMessageAuditObject.setBranch(splitValues[3]);

                    // Create nested CaaMessageAudit element if it exists in the CSV
                    if (splitValues.length > 4) {
                        CaaMessageAudit caaMessageAudit = new CaaMessageAudit();
                        caaMessageAudit.setEventNumber(splitValues[4]);
                        caaMessageAudit.setDestinationType(splitValues[5]);
                        caaMessageAudit.setCustodyAccount(splitValues[6]);
                        caaMessageAudit.setMessageType(splitValues[7]);
                        caaMessageAudit.setMessageFunction(splitValues[8]);
                        caaMessageAudit.setMessageDevice(splitValues[9]);
                        caaMessageAudit.setMessageAdress(splitValues[10]);
                        caaMessageAudit.setMessageReferenceOne(splitValues[11]);
                        caaMessageAudit.setMessageDate(splitValues[12]);
                        caaMessageAudit.setMessageTime(splitValues[13]);
                        caaMessageAudit.setMessageBulk(splitValues[14]);
                        londonCaaMessageAuditObject.setLondonCaaMessageAudit(caaMessageAudit);
                    }

                    rootElement.getLondonNameElement().add(londonCaaMessageAuditObject);
                }

                // Convert to XML and save to file
                marshaller.marshal(rootElement, new File(xmlFile));
            }
        } catch (IOException | JAXBException | CsvValidationException e) {
            e.printStackTrace();
        }
    }
}


//        try {
//            // Ler o schema para definir a estrutura XML
//            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//            dbFactory.setNamespaceAware(true);
//            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//            Document schemaDoc = dBuilder.parse(new File(xsdFile));
//
//            // Preparar documento XML de saída com a estrutura do schema
//            Document xmlDocument = dBuilder.newDocument();
//            Element rootElement = xmlDocument.createElement("londonAuditChangesIssueDataElement");
//            xmlDocument.appendChild(rootElement);
//
//            // Ler o CSV para popular o XML
//            try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
//                String line;
//                String csvSplitBy = ";";
//
//                while ((line = br.readLine()) != null) {
//                    String[] fields = line.split(csvSplitBy);
//
//                    // Criar o elemento londonNameElement baseado no XSD
//                    Element londonNameElement = xmlDocument.createElement("londonNameElement");
//
//                    // Adicionar os campos de auditChangesIssueDataObject
//                    populateElement(xmlDocument, londonNameElement, fields);
//
//                    // Anexar ao elemento raiz
//                    rootElement.appendChild(londonNameElement);
//                }
//            }
//
//            // Salvar o documento XML em um arquivo
//            TransformerFactory transformerFactory = TransformerFactory.newInstance();
//            Transformer transformer = transformerFactory.newTransformer();
//            DOMSource source = new DOMSource(xmlDocument);
//            StreamResult result = new StreamResult(new File(xmlFile));
//            transformer.transform(source, result);
//            System.out.println("Arquivo XML criado com sucesso a partir do schema e dados CSV!");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    private static void populateElement(Document doc, Element parent, String[] fields) {
//        // Este método assume que 'fields' contém dados na ordem certa e popula o XML
//        // Cria o elemento trigram
//        Element trigram = doc.createElement("trigram");
//        trigram.appendChild(doc.createTextNode(fields[0]));
//        parent.appendChild(trigram);
//
//        // Cria o elemento type_of_data
//        Element typeOfData = doc.createElement("type_of_data");
//        typeOfData.appendChild(doc.createTextNode(fields[1]));
//        parent.appendChild(typeOfData);
//
//        // Cria o elemento date_of_extraction
//        Element dateOfExtraction = doc.createElement("date_of_extraction");
//        dateOfExtraction.appendChild(doc.createTextNode(fields[2]));
//        parent.appendChild(dateOfExtraction);
//
//        // Cria o elemento branch
//        Element branch = doc.createElement("branch");
//        branch.appendChild(doc.createTextNode(fields[3]));
//        parent.appendChild(branch);
//
//        // Cria o elemento londonAuditChangesIssueData e adiciona os dados aninhados
//        Element auditChangesAuditData = doc.createElement("londonAuditChangesIssueData");
//
//        // Adiciona os campos internos de auditChangesAuditData
//        Element issueRef = doc.createElement("issue_ref");
//        issueRef.appendChild(doc.createTextNode(fields[4]));
//        auditChangesAuditData.appendChild(issueRef);
//
//        Element description = doc.createElement("description");
//        description.appendChild(doc.createTextNode(fields[5]));
//        auditChangesAuditData.appendChild(description);
//
//        Element dateObjectType = doc.createElement("date_object_type");
//        dateObjectType.appendChild(doc.createTextNode(fields[6]));
//        auditChangesAuditData.appendChild(dateObjectType);
//
//        Element timeObjectType = doc.createElement("time_object_type");
//        timeObjectType.appendChild(doc.createTextNode(fields[7]));
//        auditChangesAuditData.appendChild(timeObjectType);
//
//        Element userObjectType = doc.createElement("user_object_type");
//        userObjectType.appendChild(doc.createTextNode(fields[8]));
//        auditChangesAuditData.appendChild(userObjectType);
//
//        Element lastVal = doc.createElement("last_val");
//        lastVal.appendChild(doc.createTextNode(fields[9]));
//        auditChangesAuditData.appendChild(lastVal);
//
//        // Anexa auditChangesAuditData ao elemento pai
//        parent.appendChild(auditChangesAuditData);
//    }


