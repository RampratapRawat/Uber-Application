package com.example.Entity;

import org.locationtech.jts.geom.Point;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.*;

@Data
@Entity
@Table(name="Rider")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Rider {
      
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name="user_id")
	private User user;
	
	private Double rating;
	
	
}
