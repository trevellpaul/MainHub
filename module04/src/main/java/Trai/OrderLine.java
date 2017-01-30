package Trai;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class OrderLine {
	
	private int CustomerID;
	private int bookID;
	
	@Column(name = "book_quantity")
	private int bookQuantity;
}
	