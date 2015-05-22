package repositories;

import data.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by mmatosevic on 22.5.2015.
 */
public interface UserRepository extends CrudRepository<User, Long> {
    Page<User> findAll(Pageable pageable);
}
