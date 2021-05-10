package miu.edu.ecommerce.repository;

import miu.edu.ecommerce.domain.Buyer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuyerRepository extends CrudRepository<Buyer,Long> {
    List<Buyer> findAll();
    Buyer findBuyerById(Long id);
}
