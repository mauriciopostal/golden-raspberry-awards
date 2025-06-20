package com.mopsoftware.goldenraspberryawards.movie;

import com.mopsoftware.goldenraspberryawards.movie.producer.Producer;
import com.mopsoftware.goldenraspberryawards.movie.producer.ProducerRepository;
import com.mopsoftware.goldenraspberryawards.movie.studio.Studio;
import com.mopsoftware.goldenraspberryawards.movie.studio.StudioRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CsvLoader {

    private final MovieRepository movieRepository;
    private final ProducerRepository producerRepository;
    private final StudioRepository studioRepository;

    @Value("${csvFileName}")
    public String csvFileName;

    @PostConstruct
    @Transactional
    public void loadData() throws Exception {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(csvFileName)), StandardCharsets.UTF_8))) {

            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] cols = line.split(";");
                int year = Integer.parseInt(cols[0]);
                String title = cols[1].trim();
                String studiosField = cols[2];
                String producersField = cols[3];
                boolean isWinner = cols.length > 4 && "yes".equalsIgnoreCase(cols[4]);

                Movie movie = new Movie();
                movie.setYear(year);
                movie.setTitle(title);
                movie.setWinner(isWinner);

                getStudios(studiosField, movie);
                getProducers(producersField, movie);

                movieRepository.save(movie);
            }
        }
    }

    private void getStudios(String studiosField, Movie movie) {
        for (String studioName : studiosField.split(",| and ")) {
            String name = studioName.trim();
            if (!name.isEmpty()) {
                Studio studio = studioRepository.findByName(name)
                        .orElseGet(() -> studioRepository.save(new Studio(name)));
                movie.getStudios().add(studio);
                studio.setMovie(movie);
            }
        }
    }

    private void getProducers(String producersField, Movie movie) {
        for (String producerName : producersField.split(",| and ")) {
            String name = producerName.trim();
            if (!name.isEmpty()) {
                Producer producer = producerRepository.findByName(name)
                        .orElseGet(() -> producerRepository.saveAndFlush(new Producer(name)));

                movie.getProducers().add(producer);
            }
        }
    }
}