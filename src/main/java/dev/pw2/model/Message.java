package dev.pw2.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
@Setter
@Getter
public class Message extends PanacheEntity {

    private String text;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
