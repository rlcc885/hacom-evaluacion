package com.hacom.evaluacion.repository;

import java.time.OffsetDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.hacom.evaluacion.model.TraceMsg;

import reactor.core.publisher.Flux;

@Repository
public interface TraceMsgRepository extends ReactiveMongoRepository<TraceMsg, ObjectId> {
	Flux<TraceMsg> findByTsBetween(OffsetDateTime startDate, OffsetDateTime endDate);
}
