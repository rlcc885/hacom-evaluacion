package com.hacom.evaluacion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hacom.evaluacion.dto.DateRangeRequest;
import com.hacom.evaluacion.model.TraceMsg;
import com.hacom.evaluacion.service.TraceMsgServiceInterface;

import reactor.core.publisher.Flux;

@RestController
public class TraceMsgController {
	@Autowired
	private TraceMsgServiceInterface traceMsgService;

	@PostMapping("/create/tracemsg")
	@ResponseStatus(HttpStatus.CREATED)
	public void createEmp(@RequestBody TraceMsg traceMsg) {
		traceMsgService.createTraceMsg(traceMsg);
	}

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Flux<TraceMsg>> findByRange(DateRangeRequest dateRangeRequest) {
		Flux<TraceMsg> traceMsgList = traceMsgService.findByDateRange(dateRangeRequest);
		return new ResponseEntity<Flux<TraceMsg>>(traceMsgList,
				traceMsgList.hasElements() != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}

}
