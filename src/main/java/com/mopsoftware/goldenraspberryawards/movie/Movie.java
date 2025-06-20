package com.mopsoftware.goldenraspberryawards.movie;

import com.mopsoftware.goldenraspberryawards.movie.producer.Producer;
import com.mopsoftware.goldenraspberryawards.movie.studio.Studio;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Movie {
    @Id
    @GeneratedValue
    Long id;
    Integer year;
    String title;
    @OneToMany(mappedBy = "movie")
    Set<Studio> studios = new HashSet<>();
    @ManyToMany
    @JoinTable(
            name = "movie_producer",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "producer_id")
    )
    Set<Producer> producers = new HashSet<>();
    Boolean winner;
}
