package sft.bar.addressbook.model;

import com.google.common.base.Objects;
import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@XStreamAlias("group")
@Entity//this annotation declares that GroupData is bound with the database
@Table(name = "group_list")
public class GroupData {
    @XStreamOmitField
    @Id
    @Column(name = "group_id")
    private int id = Integer.MAX_VALUE;

    @Expose
    @Column(name = "group_name")
    private String name;

    @Expose
    @Column(name = "group_header")
    @Type(type = "text")
    private String header;

    @Expose
    @Column(name = "group_footer")
    @Type(type = "text")
    private String footer;

    public Contacts getContacts() {
        return new Contacts(contacts);
    }

    //@ManyToMany(mappedBy = "groups")
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable (name = "address_in_groups",
            joinColumns = @JoinColumn (name = "group_id"), inverseJoinColumns = @JoinColumn (name = "id"))
    private Set<ContactData> contacts = new HashSet<ContactData>();

    public int getId() {
        return id;
    }

    public String getHeader() {
        return header;
    }

    public String getFooter() {
        return footer;
    }

    public String getName() {
        return name;
    }

    public GroupData withId(int id) {
        this.id = id;
        return this;
    }

    public GroupData withName(String name) {
        this.name = name;
        return this;
    }

    public GroupData withHeader(String header) {
        this.header = header;
        return this;
    }

    public GroupData withFooter(String footer) {
        this.footer = footer;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupData groupData = (GroupData) o;
        return id == groupData.id &&
                Objects.equal(name, groupData.name) &&
                Objects.equal(header, groupData.header) &&
                Objects.equal(footer, groupData.footer);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, name, header, footer);
    }

    @Override
    public String toString() {
        return "GroupData{" +
                "name='" + name + '\'' +
                '}';
    }

}
