package com.gmail.vpshulgaa.dao.students.dao.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "T_SUBJECT")
public class Subject  implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "F_ID", updatable = false, nullable = false)
    private Long id;
    @Column(name = "F_NAME", length = 30)
    private String name;
    @Column(name = "F_DIFFICULTY", precision = 4, scale = 2)
    private BigDecimal difficulty;
    @ManyToMany(cascade =  {CascadeType.ALL})
    @JoinTable(
            name = "T_STUDENT_SUBJECT",
            joinColumns = {@JoinColumn(name = "F_SUBJECT_ID")},
            inverseJoinColumns = {@JoinColumn(name = "F_STUDENT_ID")}
    )
    private Set<Student> students = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subject subject = (Subject) o;

        if (id != null ? !id.equals(subject.id) : subject.id != null) return false;
        if (name != null ? !name.equals(subject.name) : subject.name != null) return false;
        return difficulty != null ? difficulty.equals(subject.difficulty) : subject.difficulty == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (difficulty != null ? difficulty.hashCode() : 0);
        return result;
    }
}
