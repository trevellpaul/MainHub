package Trai;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Book {
	@Id
	@GeneratedValue
	private int bookID;
	
	@Column(name = "title")
	private String title;

	@ManyToOne(cascade = CascadeType.ALL)
	private Set<Author> author = new HashSet<>();
	
}
