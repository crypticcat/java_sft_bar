package sft.bar.addressbook.generators;

import sft.bar.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
    public static void main(String[] args) throws IOException {
        int count = Integer.parseInt(args[0]);
        File file = new File(args[1]);

        List<ContactData> contacts = generateContacts(count);
        save(contacts, file);
    }

    private static void save(List<ContactData> contacts, File file) throws IOException {
        System.out.println(new File(".").getAbsolutePath());
        Writer writer = new FileWriter(file);
        for (ContactData contact : contacts) {
            writer.write(String.format("%s;%s:%s\n", contact.getFirstname(), contact.getLastname(), contact.getAddress(),
                    contact.getHome(), contact.getMobile(), contact.getEmail(), contact.getEmail2(), contact.getEmail3(), contact.getGroup()));
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
                    .withGroup(String.format("NadyaTest %s", i)));
        }
        return contacts;
    }
}