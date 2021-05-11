package miu.edu.ecommerce.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "shoppingcarts")
public class ShoppingCart implements Serializable {
    private static final long serialVersionUID = 7359591984285268537L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shopping_id", nullable = false)
    private long id;

    @Column(name = "cart_date", nullable = false)
    private LocalDate cartDate;

    @Column(name = "total_money")
    private Double totalMoney;

    @Column(name = "completed")
    private boolean completed;

    @OneToMany(mappedBy="cart")
    private List<ShoppingCartLine> cartLines = new ArrayList();

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name="buyer_id")
    Buyer buyer;

}
