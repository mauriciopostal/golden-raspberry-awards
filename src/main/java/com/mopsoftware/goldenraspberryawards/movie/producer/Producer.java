package com.mopsoftware.goldenraspberryawards.movie.producer;

import com.mopsoftware.goldenraspberryawards.movie.Movie;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Producer {
    @Id
    @GeneratedValue
    Long id;
    String name;
    @ManyToMany(mappedBy = "producers")
    Set<Movie> movies = new HashSet<>();

    public Producer(String name) {
        this.name = name;
    }
}
