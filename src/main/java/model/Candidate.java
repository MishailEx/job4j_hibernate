package model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "candidate")
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String experience;
    private int salary;
    @OneToOne(fetch = FetchType.LAZY)
    private BaseVacancy baseVacancy;

    public Candidate() {
    }

    public Candidate(String name, String experience, int salary) {
        this.name = name;
        this.experience = experience;
        this.salary = salary;
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

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public BaseVacancy getBaseVacancy() {
        return baseVacancy;
    }

    public void setBaseVacancy(BaseVacancy baseVacancy) {
        this.baseVacancy = baseVacancy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Candidate candidate = (Candidate) o;
        return id == candidate.id && salary == candidate.salary && Objects.equals(name, candidate.name) && Objects.equals(experience, candidate.experience) && Objects.equals(baseVacancy, candidate.baseVacancy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, experience, salary, baseVacancy);
    }

    @Override
    public String toString() {
        return "Candidate{ id=" + id + ", name='" + name + '\''
                + ", experience='" + experience + '\''
                + ", salary=" + salary + ", baseVacancy=" + baseVacancy + '}';
    }
}
