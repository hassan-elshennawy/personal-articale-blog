package my_project.first.Repositories;

import my_project.first.Domain.Entities.Tag;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends CrudRepository<Tag,Integer> {

    @Query(value = "SELECT t.* FROM tag t WHERE t.title = :title",nativeQuery = true)
    Optional<Tag> findByTitle(@Param("title") String title);
}
