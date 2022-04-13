package com.compass.fasttracker.history.repository;

import com.compass.fasttracker.history.models.Historic;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface HistoryRepository extends MongoRepository<Historic, String> {

    public Optional<Historic> findByUserId(Long id);
}
