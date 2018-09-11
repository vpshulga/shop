package com.gmail.vpshulgaa.dao.students.dao.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "T_RECORD_BOOK")
public class RecordBook implements Serializable{
    @GenericGenerator(
            name = "generator",
            strategy = "foreign",
            parameters = @Parameter(name = "property", value = "student")
    )
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "F_STUDENT_ID", unique = true, nullable = false)
    private long studentId;
    @Column(name = "F_INV_NUMBER", length = 10)
    private String invNumber;
    @Column(name = "F_AVG_MARK", precision = 4,  scale = 2)
    private BigDecimal avgMark;
    @Column(name = "F_LAST_CHANGE")
    private LocalDateTime lastChange;
    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Student student;
}
