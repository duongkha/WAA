package miu.edu.ecommerce.repository;

import miu.edu.ecommerce.domain.Seller;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellerRepository extends CrudRepository<Seller,Long> {
    public List<Seller> findAll();

    public Seller getSellerById(Long id);

}
