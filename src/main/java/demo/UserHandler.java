package demo;

import java.util.List;

//import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface UserHandler extends CrudRepository<UserEntity, String> {

    public List<UserEntity> findAllItemsByBirthdate(
            @Param("year") String birthdate,
            Pageable of);
}
