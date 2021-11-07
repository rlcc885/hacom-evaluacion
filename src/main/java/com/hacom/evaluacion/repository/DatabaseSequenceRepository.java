package com.hacom.evaluacion.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.hacom.evaluacion.model.DatabaseSequence;

@Repository
public interface DatabaseSequenceRepository extends ReactiveMongoRepository<DatabaseSequence, String> {

}
