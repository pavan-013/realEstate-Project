package com.resp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "rentposts")
public class Rentposts {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private String image;
	private Double sqft;
	private String propertytype;
	private String projectname;
	private String ownership;
	private String location;
	private String status;
	private int bedrooms;
	private String carparking;
	private int propertyfloor; 
	private int totalfloors;
	private Double rent;
	private String facing;
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
}
