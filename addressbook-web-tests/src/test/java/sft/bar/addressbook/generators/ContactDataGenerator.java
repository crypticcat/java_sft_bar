package sft.bar.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.thoughtworks.xstream.XStream;
import sft.bar.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Contact count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-df", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {
        //object with the attributes to be filled
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander.newBuilder()
                .addObject(generator)
                .build()
                .parse(args);
        generator.run();

    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        if (format.equals("csv")) {
            saveAsCsv(contacts, new File(file));
        } else if (format.equals("xml")) {
            saveAsXml(contacts, new File(file));
        //} else if (format.equals("json")) {
        //    saveAsJson(contacts, new File(file));
        } else {
            System.out.println("Unrecognized format " + format);
        }
    }

    private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactData.class);
        xstream.alias("contact", ContactData.class);
        String xml = xstream.toXML(contacts);
        Writer writer = new FileWriter(file);
        writer.write(xml);
        writer.close();
    }

    private static void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
        System.out.println(new File(".").getAbsolutePath());
        Writer writer = new FileWriter(file);
        for (ContactData contact : contacts) {
            writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n", contact.getFirstname(), contact.getLastname(), contact.getAddress(),
                    contact.getHome(), contact.getMobile(), contact.getWork(),
                    contact.getEmail(), contact.getEmail2(), contact.getEmail3(), contact.getGroup()));
        }
        writer.close();
    }

    private static List<ContactData> generateContacts (int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData().withFirstname(String.format("Firstname %s", i)).withLastname(String.format("Lastname %s", i))
                    .withAddress(String.format("Address %s", i))
                    .withHome(String.format("Home %s", i)).withMobile(String.format("Mobile %s", i)).withWork(String.format("Work %s", i))
                    .withEmail(String.format("Email %s", i)).withEmail2(String.format("Email2 %s", i)).withEmail3(String.format("Email3 %s", i))
                    .withGroup(String.format("test %s", i)));
        }
        return contacts;
    }
}
