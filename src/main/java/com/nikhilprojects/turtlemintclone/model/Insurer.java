package com.nikhilprojects.turtlemintclone.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
@Data
public class Insurer {
    @NotBlank
    private String name;
    @NotBlank
    private String premium;

    public Insurer(String name, String premium) {
        this.name = name;
        this.premium = premium;
    }
}
