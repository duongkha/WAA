package miu.edu.ecommerce.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Address implements Serializable {
    private String street;
    private String city;
    private String state;
    private String country;
    private String zipCode;

}