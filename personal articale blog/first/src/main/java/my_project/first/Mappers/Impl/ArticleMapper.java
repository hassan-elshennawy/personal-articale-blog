package my_project.first.Mappers.Impl;

import my_project.first.Domain.DTOs.ArticlesDTO;
import my_project.first.Domain.Entities.Articles;
import my_project.first.Mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ArticleMapper implements Mapper<Articles, ArticlesDTO> {
    private ModelMapper modelMapper;
    public  ArticleMapper(ModelMapper modelMapper){
        this.modelMapper=modelMapper;
    }
    @Override
    public Articles mapTo(ArticlesDTO articlesDTO) {
        return modelMapper.map(articlesDTO,Articles.class);
    }

    @Override
    public ArticlesDTO mapFrom(Articles articles) {
        return modelMapper.map(articles,ArticlesDTO.class);
    }
}
