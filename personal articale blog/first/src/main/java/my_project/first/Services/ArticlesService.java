package my_project.first.Services;

import my_project.first.Domain.DTOs.ArticlesDTO;
import my_project.first.Domain.DTOs.RequestArticleDTO;
import my_project.first.Domain.Entities.Articles;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ArticlesService {
     Articles create(RequestArticleDTO article);
     List<Articles> findAll();
     Articles findById(int id);
     Boolean deleteById(int id);
     Articles update(int id, ArticlesDTO article);
     Set<Articles>findByTags(List<String> tag_names);


}
