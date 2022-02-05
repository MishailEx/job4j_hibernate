package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "marka")
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(mappedBy = "mark")
    private List<Car> allModel = new ArrayList<>();

    public Mark() {
    }

    public Mark(String name) {
        this.name = name;
    }

    public Mark(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addModel(Car model) {
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

    public List<Car> getAllModel() {
        return allModel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Mark mark = (Mark) o;
        return id == mark.id && Objects.equals(name, mark.name) && Objects.equals(allModel, mark.allModel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, allModel);
    }

    @Override
    public String toString() {
        return "Mark{ id=" + id + ", name='" + name + '\''
                + ", allModel=" + allModel + '}';
    }
}
