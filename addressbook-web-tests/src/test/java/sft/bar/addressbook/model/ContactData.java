package sft.bar.addressbook.model;

import com.google.common.base.Objects;
import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@XStreamAlias("contact")
@Entity
@Table (name = "addressbook")
public class ContactData {
    @XStreamOmitField
    @Id
    @Column(name = "id")//can omit if variaable name is the same as the table column name
    private int id = Integer.MAX_VALUE;

    @Expose
    @Column(name = "firstname")
    private String firstname;

    @Expose
    private String middlename;

    @Expose
    @Column(name = "lastname")
    private String lastname;

    @Expose
    private String nickname;

    @Expose
    private String company;

    @Expose
    private String title;

    @Expose
    @Type(type = "text")
    private String address;

    @Expose
    @Column(name = "mobile")
    @Type(type = "text")
    private String mobile;

    @Expose
    @Column(name = "home")
    @Type(type = "text")
    private String home;

    @Expose
    @Column(name = "work")
    @Type(type = "text")
    private String work;

    @Expose
    @Type(type = "text")
    private String fax;

    @Expose
    @Type(type = "text")
    private String email;
    @Expose
    @Type(type = "text")
    private String email2;
    @Expose
    @Type(type = "text")
    private String email3;

    @Expose
    @Type(type = "byte")
    private byte bday;
    @Expose
    @Type(type = "string")
    private String bmonth;
    @Expose
    @Type(type = "string")
    private String byear;

    @Expose
    @Type(type = "byte")
    private byte aday;

    @Expose
    @Type(type = "string")
    private String amonth;

    @Expose
    @Type(type = "string")
    private String ayear;

    @Expose
    @Transient//will be omitted
    private String group;

    @Transient
    private String allPhones;

    @Transient
    private String allEmails;

    public String getMiddlename() {
        return middlename;
    }

    public String getNickname() {
        return nickname;
    }

    public String getCompany() {
        return company;
    }

    public String getTitle() {
        return title;
    }

    public String getFax() {
        return fax;
    }

    public byte getBday() {
        return bday;
    }

    public String getBmonth() {
        return bmonth;
    }

    public String getByear() {
        return byear;
    }

    public byte getAday() {
        return aday;
    }

    public String getAmonth() {
        return amonth;
    }

    public String getAyear() {
        return ayear;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public String getAllEmails() {
        return allEmails;
    }

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getEmail() {
        return email;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public String getGroup() {
        return group;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAddress() {
        return address;
    }

    public String getMobile() {
        return mobile;
    }

    public String getHome() {
        return home;
    }

    public String getWork() {
        return work;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public ContactData withHome(String home) {
        this.home = home;
        return this;
    }

    public ContactData withWork(String work) {
        this.work = work;
        return this;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

    public ContactData withMiddlename(String middlename) {
        this.middlename = middlename;
        return this;
    }

    public ContactData withNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public ContactData withCompany(String company) {
        this.company = company;
        return this;
    }

    public ContactData withTitle(String title) {
        this.title = title;
        return this;
    }

    public ContactData withFax(String fax) {
        this.fax = fax;
        return this;
    }

    public ContactData withBday(byte bday) {
        this.bday = bday;
        return this;
    }

    public ContactData withBmonth(String bmonth) {
        this.bmonth = bmonth;
        return this;
    }

    public ContactData withByear(String byear) {
        this.byear = byear;
        return this;
    }

    public ContactData withAday(byte aday) {
        this.aday = aday;
        return this;
    }

    public ContactData withAmonth(String amonth) {
        this.amonth = amonth;
        return this;
    }

    public ContactData withAyear(String ayear) {
        this.ayear = ayear;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id &&
                Objects.equal(firstname, that.firstname) &&
                Objects.equal(lastname, that.lastname) &&
                Objects.equal(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, firstname, lastname, address);
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
