package com.example.demo.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comments")
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "users_id")
	private User user;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "store_id")
	private Store store;
	
	@OneToMany(mappedBy = "comment", cascade = CascadeType.ALL)
	private List<CommentImage> commentImages = new ArrayList<CommentImage>();
	
	@Column(name = "comment", length = 256, nullable = false)
	private String comment;

	private int rating;

	private Date date;
}
