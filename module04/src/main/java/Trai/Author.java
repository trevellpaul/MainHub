package Trai;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Author {
	
	@Id
	@GeneratedValue
	private int author_id;

	@Column(name = "title")
	private String full_name;

	@OneToMany(cascade = CascadeType.ALL)
	private Set<Book> Books = new HashSet<>();
}

