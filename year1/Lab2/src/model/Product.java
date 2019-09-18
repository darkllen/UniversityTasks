package model;

public class Product {
    public Product(int id, int group_id, String name, String description, String producer, int number, int cost) {
        this.id = id;
        this.group_id = group_id;
        this.name = name;
        this.description = description;
        this.producer = producer;
        this.number = number;
        this.cost = cost;
    }
 
    private Integer id;
    private Integer group_id;
    private String name;
    private String description;
    private String producer;
    private Integer number;
    private Integer cost;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }
}
