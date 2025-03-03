package de.ait.javalessons.model;

public class Car {
    private final String id; //final чтобы один раз сгенерированный id уже никогда не менялся
    private String name;

    public Car(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
