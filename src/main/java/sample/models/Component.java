package sample.models;

import java.util.Date;

public class Component {
    private Long id;
    private Manafacturer manafacturer;
    private String title;
    private String description;
    private Type type;
    private Date createdAt;


    public static enum Type {
        GPU,
        CPU,
        MOBO,
        RAM,
        PSU
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Manafacturer getManafacturer() {
        return manafacturer;
    }

    public void setManafacturer(Manafacturer manafacturer) {
        this.manafacturer = manafacturer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
    public String getCompany() {
        return this.manafacturer.getCompany();
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
