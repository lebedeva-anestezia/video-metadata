package org.interview.dao;

import javax.persistence.*;

@Entity
@Table(name = "SUBGENRE")
public class SubgenreDAO {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @PrimaryKeyJoinColumn
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
