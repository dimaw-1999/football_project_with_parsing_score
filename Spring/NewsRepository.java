package com.example.reactProject.repositories;

import com.example.reactProject.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface NewsRepository extends JpaRepository<News, Long> {
    boolean existsNewsByName(String name);
    News findNewsByName(String name);
    void deleteNewsById(Long id);
}
