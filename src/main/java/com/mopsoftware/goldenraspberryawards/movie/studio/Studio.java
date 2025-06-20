package com.mopsoftware.goldenraspberryawards.movie.studio;

import com.mopsoftware.goldenraspberryawards.movie.Movie;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Studio {
    @Id
    @GeneratedValue
    Long id;
    String name;
    @ManyToOne
    Movie movie;

    public Studio(String name) {
        this.name = name;
    }
}
