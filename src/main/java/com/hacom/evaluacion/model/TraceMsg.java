package com.hacom.evaluacion.model;

import java.time.OffsetDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "tracemsgs")
public class TraceMsg {
	@Transient
	public static final String SEQUENCE_NAME = "trace_msg_sequence";
	@Id
	private ObjectId _id;
	private String sessionId;
	private String payload;
	private OffsetDateTime ts;

	public TraceMsg(final String sessionId, final String payload, final OffsetDateTime ts) {
		this.sessionId = sessionId;
		this.payload = payload;
		this.ts = ts;
	}
}
