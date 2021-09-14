package com.example.dartfanpage;

import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Version;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@EqualsAndHashCode(of = "id")
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Long id;

    public Long getId() {
        return id;
    }

    public BaseEntity() {
    }

    public BaseEntity(Long id) {
        this.id = id;
    }
}
