package model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "basevacancy")
public class BaseVacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Vacancy> vacancySet;

    public BaseVacancy() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Vacancy> getVacancySet() {
        return vacancySet;
    }

    public void setVacancySet(Set<Vacancy> vacancySet) {
        this.vacancySet = vacancySet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BaseVacancy that = (BaseVacancy) o;
        return id == that.id && Objects.equals(vacancySet, that.vacancySet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, vacancySet);
    }

    @Override
    public String toString() {
        return "BaseVacancy{ id=" + id + ", vacancySet="
                + vacancySet + '}';
    }
}
