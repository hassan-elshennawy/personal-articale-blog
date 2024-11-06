package my_project.first.Repositories;

import my_project.first.Domain.Entities.Articles;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ArticlesRepository extends CrudRepository<Articles,Integer> {

    @Query(value = "Select a.* FROM articles a" +
            "JOIN article_tag at ON at.article_id = a.id" +
            "JOIN tag t ON t.id = at.tag_id" +
            "WHERE t.name IN (:TagNames)"+
            "GROUP BY a.id ",nativeQuery = true)
    Set<Articles> findByTags(@Param("TagNames")List<String> tag_names);
}
