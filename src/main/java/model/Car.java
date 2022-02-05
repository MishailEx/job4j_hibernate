package model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "mark_id")
    private Mark mark;

    public Car() {
    }

    public Car(String name, Mark mark) {
        this.name = name;
        this.mark = mark;
    }

    public Car(String name) {
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
        Car car = (Car) o;
        return id == car.id && Objects.equals(name, car.name) && Objects.equals(mark, car.mark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, mark);
    }

    @Override
    public String toString() {
        return "Car - name='" + name + '\'';
    }
}
