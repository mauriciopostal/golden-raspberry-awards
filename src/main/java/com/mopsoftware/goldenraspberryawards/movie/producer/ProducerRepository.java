package com.mopsoftware.goldenraspberryawards.movie.producer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProducerRepository extends JpaRepository<Producer, Long> {
    Optional<Producer> findByName(String name);

    @Query(value = """
WITH producer_wins AS (
    SELECT
        p.id AS producer_id,
        p.name,
        m.year
    FROM producer p
             JOIN movie_producer mp ON p.id = mp.producer_id
             JOIN movie m ON m.id = mp.movie_id
    WHERE m.winner = TRUE
),
     intervals AS (
         SELECT
             pw1.producer_id,
             pw1.name,
             pw2.year AS previous_win_year,
             pw1.year AS following_win_year,
             pw1.year - pw2.year AS win_gap
         FROM producer_wins pw1
                  JOIN producer_wins pw2
                       ON pw1.producer_id = pw2.producer_id
                           AND pw2.year < pw1.year
                           AND NOT EXISTS (
                               SELECT 1
                               FROM producer_wins pw3
                               WHERE pw3.producer_id = pw1.producer_id
                                 AND pw3.year > pw2.year
                                 AND pw3.year < pw1.year
                           )
     ),
     max_gap AS (
         SELECT MAX(win_gap) AS max_val FROM intervals
     ),
     min_gap AS (
         SELECT MIN(win_gap) AS min_val FROM intervals
     )
SELECT
    'MAX' AS type,
    i.producer_id,
    i.name,
    i.win_gap,
    i.previous_win_year AS previousWin,
    i.following_win_year AS followingWin
FROM intervals i
         JOIN max_gap mg ON i.win_gap = mg.max_val

UNION ALL

SELECT
    'MIN' AS type,
    i.producer_id,
    i.name,
    i.win_gap,
    i.previous_win_year AS previousWin,
    i.following_win_year AS followingWin
FROM intervals i
         JOIN min_gap mg ON i.win_gap = mg.min_val
""", nativeQuery = true)
    List<ProducerAwardIntervalDTO> findProducersWithInterval();
}
