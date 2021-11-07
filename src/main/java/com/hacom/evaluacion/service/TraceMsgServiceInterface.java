package com.hacom.evaluacion.service;

import com.hacom.evaluacion.dto.DateRangeRequest;
import com.hacom.evaluacion.model.TraceMsg;

import reactor.core.publisher.Flux;

public interface TraceMsgServiceInterface {
	void createTraceMsg(TraceMsg traceMsg);
	Flux<TraceMsg> findByDateRange(DateRangeRequest dateRangeRequest);
}
