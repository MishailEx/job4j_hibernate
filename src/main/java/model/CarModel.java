package model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "model")
public class CarModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    public CarModel() {
    }

    public CarModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public CarModel(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CarModel carModel = (CarModel) o;
        return id == carModel.id && Objects.equals(name, carModel.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "CarModel{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}
