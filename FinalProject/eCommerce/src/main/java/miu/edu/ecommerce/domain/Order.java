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
@Table(name = "orders")
public class Order implements Serializable {
    private static final long serialVersionUID = 7359591984285268537L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    private long id;

    @Column(name = "order_date", nullable = false)
    private LocalDate orderDate;

    @Column(name = "total_money", nullable = false, columnDefinition = "double default 0.0")
    private Double totalMoney;

    @Column(name = "current_status", nullable = false)
    private String currentStatus;


    @OneToMany(mappedBy="order")
    private List<OrderLine> cartLines = new ArrayList();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="buyer_id")
    Buyer buyer;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="payment_id")
    Payment payment;
}
