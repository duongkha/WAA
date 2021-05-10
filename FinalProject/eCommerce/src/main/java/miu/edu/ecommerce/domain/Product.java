package miu.edu.ecommerce.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product implements Serializable {
    private static final long serialVersionUID = 7359591984285268537L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private long id;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "producer")
    private String producer;

    @Column(name = "note", length=500)
    private String note;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="category_id")
    Category category;

    @OneToMany(mappedBy="product")
    private List<ProductVariant> productVariants = new ArrayList();

    @OneToMany(mappedBy="product")
    private List<Review> reviews = new ArrayList();


}
