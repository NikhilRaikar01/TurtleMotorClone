package com.nikhilprojects.turtlemintclone.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Document (collection = "Profiles")
@Data
public class Profile {

	@Transient
    public static final String SEQUENCE_NAME = "users_sequence";

	@Id
	private long id;

    @Size(max=100)
	private String Vertical;

	private String Make;

	private String Model;

	public Profile(String Vertical, String Make, String Model) {
		this.Vertical = Vertical;
		this.Make = Make;
		this.Model = Model;
	}

	@Override
	public String toString() {
		return "Profiles [id=" + id + ", Vertical=" + Vertical + ", Make=" + Make + ", Model" + Model
				+ "]";
	}	
}
