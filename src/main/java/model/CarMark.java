package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table (name = "mark")
public class CarMark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarModel> allModel = new ArrayList<>();

    public CarMark() {
    }

    public CarMark(String name) {
        this.name = name;
    }

    public CarMark(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addModel(CarModel model) {
        this.allModel.add(model);
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

    public List<CarModel> getList() {
        return allModel;
    }

    public void setList(List<CarModel> list) {
        this.allModel = list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CarMark carMark = (CarMark) o;
        return id == carMark.id && Objects.equals(name, carMark.name) && Objects.equals(allModel, carMark.allModel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, allModel);
    }

    @Override
    public String toString() {
        return "CarMark{" + "id=" + id + ", name='" + name + '\''
                + ", list=" + allModel + '}';
    }
}
