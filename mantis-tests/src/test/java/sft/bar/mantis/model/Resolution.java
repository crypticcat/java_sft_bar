package sft.bar.mantis.model;

public class Resolution {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public Resolution withId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Resolution withName(String name) {
        this.name = name;
        return this;
    }
}
