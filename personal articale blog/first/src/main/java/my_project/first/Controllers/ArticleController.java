package my_project.first.Controllers;

import jakarta.websocket.server.PathParam;
import my_project.first.Domain.DTOs.ArticlesDTO;
import my_project.first.Domain.DTOs.RequestArticleDTO;
import my_project.first.Domain.Entities.Articles;
import my_project.first.Mappers.Impl.ArticleMapper;
import my_project.first.Mappers.Mapper;
import my_project.first.Services.ArticlesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class ArticleController {
    private ArticlesService articlesService;
    private Mapper<Articles,ArticlesDTO> articleMapper;
    public ArticleController(ArticlesService articlesService, Mapper<Articles,ArticlesDTO> articleMapper){
        this.articlesService=articlesService;
        this.articleMapper = articleMapper;
    }
    @PostMapping(path = "/article")
    public ResponseEntity<ArticlesDTO> create(@RequestBody RequestArticleDTO articleDTO){
        // Articles Article = articleMapper.mapTo(articleDTO);
         Articles SavedArticle = articlesService.create(articleDTO);
         return new ResponseEntity<>(articleMapper.mapFrom(SavedArticle), HttpStatus.CREATED);
    }

    @GetMapping(path = "/articles")
    public List<ArticlesDTO> getAll(){
        List<Articles> articles = articlesService.findAll();
        return articles.stream().map(articleMapper::mapFrom).collect(Collectors.toList());
    }

    @PostMapping(path = "/delete/{Id}")
    public ResponseEntity<String> deleteArticle(@PathVariable int Id){
        Boolean is_deleted = articlesService.deleteById(Id);
        if(is_deleted){return new ResponseEntity<>("Article deleted successfully",HttpStatus.OK);}
        else
            return new ResponseEntity<>("No article with such Id",HttpStatus.NOT_FOUND);
    }
    @PatchMapping(path = "update/{Id}")
    public ResponseEntity<ArticlesDTO> updateArticle(@PathVariable int Id,@RequestBody ArticlesDTO article){
        Articles updatedArticle = articlesService.update(Id,article);
        return new ResponseEntity<>(articleMapper.mapFrom(updatedArticle),HttpStatus.OK);
    }

    @GetMapping(path = "/article/{Id}")
    public ResponseEntity<ArticlesDTO> getById(@PathVariable int Id){
        Articles article = articlesService.findById(Id);
        return new ResponseEntity<>(articleMapper.mapFrom(article),HttpStatus.OK);
    }
    @GetMapping(path = "article/tags")
    public ResponseEntity<Set<ArticlesDTO>> getByTags(@RequestParam List<String> tag_names){
        Set<Articles> filterd_articles = articlesService.findByTags(tag_names);
        return new ResponseEntity<>(filterd_articles.stream().map(articleMapper::mapFrom).collect(Collectors.toSet()),HttpStatus.OK);
    }
}
