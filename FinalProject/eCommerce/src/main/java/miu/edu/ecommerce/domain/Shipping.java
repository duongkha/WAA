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
@Table(name = "shippings")
public class Shipping implements Serializable {
    private static final long serialVersionUID = 7359591984285268537L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shipping_id", nullable = false)
    private long id;

    @Column(name = "receiver_first_name", nullable = false)
    private String receiverFirstName;

    @Column(name = "receiver_last_name", nullable = false)
    private String receiverLastName;

    @Column(name = "receiver_phone")
    private String receiverPhone;

    @Column(name = "delivered_date")
    private LocalDate deliveredDate;

    @Column(name = "receiver_street")
    private String receiverStreet;

    @Column(name = "receiver_city")
    private String receiverCity;

    @Column(name = "receiver_state")
    private String receiverState;

    @Column(name = "receiver_country")
    private String receiverCountry;

    @Column(name = "receiver_zipcode")
    private String receiverZipcode;


//    @Embedded
//    @AttributeOverride(
//            name = "street",
//            column = @Column(name = "receiver_street")
//    )
//    @AttributeOverride(
//            name = "city",
//            column = @Column(name = "receiver_city")
//    )
//    @AttributeOverride(
//            name = "state",
//            column = @Column( name = "receiver_state" )
//    )
//    @AttributeOverride(
//            name = "country",
//            column = @Column( name = "receiver_country" )
//    )
//    @AttributeOverride(
//            name = "zipcode",
//            column = @Column( name = "receiver_zipcode" )
//    )
//    private Address receiverAddress;

}