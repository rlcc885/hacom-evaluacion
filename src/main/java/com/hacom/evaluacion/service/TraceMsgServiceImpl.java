package com.hacom.evaluacion.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hacom.evaluacion.dto.DateRangeRequest;
import com.hacom.evaluacion.model.TraceMsg;
import com.hacom.evaluacion.repository.TraceMsgRepository;

import reactor.core.publisher.Flux;

@Service
public class TraceMsgServiceImpl implements TraceMsgServiceInterface {
	@Autowired
	TraceMsgRepository traceRepository;
	@Autowired
	SequenceGeneratorService sequenceGeneratorService;

	private static final Logger logger = LogManager.getLogger(TraceMsgServiceImpl.class);

	@Override
	public void createTraceMsg(TraceMsg traceMsg) {
		logger.info("received data from request {}", traceMsg.toString());
		sequenceGeneratorService.generateSequence(TraceMsg.SEQUENCE_NAME);
		traceRepository.save(traceMsg).subscribe();
	}

	@Override
	public Flux<TraceMsg> findByDateRange(DateRangeRequest dateRangeRequest) {
		return traceRepository.findByTsBetween(dateRangeRequest.getFrom(), dateRangeRequest.getTo());
	}

}
