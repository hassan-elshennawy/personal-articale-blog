package my_project.first.Domain.DTOs;

import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import my_project.first.Domain.Entities.Articles;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TagDTO {

    private int Id;
    private String title;
    private Set<Articles> articles = new HashSet<>();
}
