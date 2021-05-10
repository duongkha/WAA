package miu.edu.ecommerce.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "productvariants")
public class ProductVariant implements Serializable {
    private static final long serialVersionUID = 7359591984285268537L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "variant_id", nullable = false)
    private long id;

    @Column(name = "color")
    private String color;

    @Column(name = "size", length=5)
    private String size;

    @Column(name = "price" )
    private Double price;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "quantity_in_stock")
    private int quantityInStock;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="product_id")
    Product product;

}
