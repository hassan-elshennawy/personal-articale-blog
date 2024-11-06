package my_project.first.Services.Impl;

import jakarta.persistence.EntityNotFoundException;
import my_project.first.Domain.DTOs.ArticlesDTO;
import my_project.first.Domain.DTOs.RequestArticleDTO;
import my_project.first.Domain.Entities.Articles;
import my_project.first.Domain.Entities.Tag;
import my_project.first.Repositories.ArticlesRepository;
import my_project.first.Repositories.TagRepository;
import my_project.first.Services.ArticlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ArticlesServiceImpl implements ArticlesService {
    private final ArticlesRepository articlesRepository;
    private final TagRepository tagRepository;

    public ArticlesServiceImpl(ArticlesRepository articlesRepository,TagRepository tagRepository){
        this.articlesRepository = articlesRepository;
        this.tagRepository = tagRepository;
    }
    @Override
    public Articles create(RequestArticleDTO article) {
        Articles ar = new Articles();
        ar.setTitle(article.getTitle());
        ar.setContent(article.getContent());
        ar.setPublished_at(article.getPublished_at());
        Set<Tag> articleTags = new HashSet<>();
        for(String tagName : article.getTags()){
            Tag tag = tagRepository.findByTitle(tagName).orElseGet(()->{
                Tag newTag = new Tag();
                newTag.setTitle(tagName);
                return tagRepository.save(newTag);
            });
            articleTags.add(tag);
        }
        ar.setTags(articleTags);

        return articlesRepository.save(ar) ;
    }

    @Override
    public List<Articles> findAll() {
        return StreamSupport.stream(articlesRepository.findAll().spliterator(),false).collect(Collectors.toList());
    }

    @Override
    public Articles findById(int id) {
        Optional<Articles> aa = articlesRepository.findById(id);
        if(!aa.isPresent()){throw new EntityNotFoundException("user  with ID "+ id +" not found");}
        return aa.get();
    }

    @Override
    public Boolean deleteById(int id) {
        Optional<Articles> optionalArticle = articlesRepository.findById(id);
        if(!optionalArticle.isPresent()){ return false;}
        articlesRepository.deleteById(id);
        return true;
    }

    @Override
    public Articles update(int id, ArticlesDTO article) {
        Optional<Articles> optionalArticle = articlesRepository.findById(id);
        if(!optionalArticle.isPresent()){throw new EntityNotFoundException("user  with ID "+ id +" not found");
        }
        Articles existingArticle = optionalArticle.get();
        if(article.getTitle()!=null){existingArticle.setTitle(article.getTitle());}
        if(article.getContent()!=null){existingArticle.setContent(article.getContent());}
        if(article.getPublished_at()!=null){existingArticle.setPublished_at(article.getPublished_at());}
        return articlesRepository.save(existingArticle);
    }

    @Override
    public Set<Articles> findByTags(List<String> tag_names) {
        return articlesRepository.findByTags(tag_names);
    }
}
