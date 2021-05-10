package miu.edu.ecommerce.repository;

import miu.edu.ecommerce.domain.Account;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository implements CrudRepository<Account,Integer> {
    public Account getUser
}
