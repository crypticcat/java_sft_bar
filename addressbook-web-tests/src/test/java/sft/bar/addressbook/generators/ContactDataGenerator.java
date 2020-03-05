package sft.bar.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
        } else if (format.equals("json")) {
            saveAsJson(contacts, new File(file));
        } else {
            System.out.println("Unrecognized format " + format);
        }
    }

    private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        try (Writer writer = new FileWriter(file)) {
            writer.write(json);
        }
    }

    private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactData.class);
        xstream.alias("contact", ContactData.class);
        String xml = xstream.toXML(contacts);
        try (Writer writer = new FileWriter(file)) {
            writer.write(xml);
        }
    }

    private static void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
        System.out.println(new File(".").getAbsolutePath());
        try (Writer writer = new FileWriter(file)) {
            for (ContactData contact : contacts) {
                writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n", contact.getFirstname(),
                        contact.getLastname(), contact.getMiddlename(), contact.getNickname(),
                        contact.getCompany(), contact.getTitle(),
                        contact.getAddress(),
                        contact.getHome(), contact.getMobile(), contact.getWork(), contact.getFax(),
                        contact.getEmail(), contact.getEmail2(), contact.getEmail3(),
                        contact.getBday(), contact.getBmonth(), contact.getByear(),
                        contact.getAday(), contact.getAmonth(), contact.getAyear()));
                        //contact.getGroup()));
            }
        }
    }

    private static List<ContactData> generateContacts (int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData().withFirstname(String.format("Firstname %s", i))
                    .withMiddlename(String.format("Middlename %s", i))
                    .withLastname(String.format("Lastname %s", i))
                    .withNickname(String.format("Nickname %s", i))
                    .withCompany(String.format("Company %s", i))
                    .withTitle(String.format("Title %s", i))
                    .withAddress(String.format("Address %s", i))
                    .withHome(String.format("+7981800309%s", i)).withMobile(String.format("+798180030%s1", i))
                    .withWork(String.format("+7812800309%s", i)).withFax(String.format("+7812800307%s", i))
                    .withEmail(String.format("email%s@gmail.com", i)).withEmail2(String.format("email2%s@gmail.com", i))
                    .withEmail3(String.format("email3%s@gmail.com", i))
                    .withBday((byte) 12).withBmonth("January").withByear(String.format("198%s", i))
                    .withAday((byte) 18).withAmonth("January").withAyear(String.format("201%s", i)));
                            //.withGroup(String.format("test %s", i)));
        }
        return contacts;
    }
}
