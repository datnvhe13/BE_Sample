package com.vti.testing.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "`name`", length = 50, nullable = false, unique = true)
    private String name;
    @Column(name = "total_member")
    private int total_Number;
    @Column(name = "`type`", nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createDate;

    @OneToMany(mappedBy = "department")
    private List<Account> accounts;

}
