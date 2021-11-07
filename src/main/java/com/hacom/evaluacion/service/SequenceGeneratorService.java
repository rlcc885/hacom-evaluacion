package com.hacom.evaluacion.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hacom.evaluacion.model.DatabaseSequence;
import com.hacom.evaluacion.repository.DatabaseSequenceRepository;

import reactor.core.publisher.Mono;

@Service
public class SequenceGeneratorService {
	@Autowired
	private DatabaseSequenceRepository databaseSequenceRepository;
	private static final Logger logger = LogManager.getLogger(SequenceGeneratorService.class);

	public void generateSequence(String seqName) {
		Mono<DatabaseSequence> counterMono = databaseSequenceRepository.findById(seqName)
				.switchIfEmpty(Mono.just(new DatabaseSequence(seqName, 0)));
		counterMono.flatMap(counter -> {
			DatabaseSequence newCounter = new DatabaseSequence();
			BeanUtils.copyProperties(counter, newCounter);
			newCounter.setSeq(newCounter.getSeq() + 1);
			logger.info("new value of sequence {} is {}", seqName, newCounter.getSeq() + 1);
			return Mono.just(newCounter);
		}).flatMap(this.databaseSequenceRepository::save)
		.map(DatabaseSequence::getSeq).subscribe();
	}
}
