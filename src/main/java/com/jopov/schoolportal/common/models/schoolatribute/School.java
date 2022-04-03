package com.jopov.schoolportal.common.models.schoolatribute;

import com.jopov.schoolportal.common.models.persons.Director;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "school")
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "address")
    private String address;

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Director director;

    public School() {
    }

    public School(String name, int capacity, String address) {
        this.name = name;
        this.capacity = capacity;
        this.address = address;
    }

    @Override
    public String toString() {
        return "School{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", capacity=" + capacity +
                ", address='" + address + '\'' +
                '}';
    }
}
