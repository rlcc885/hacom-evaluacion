package com.hacom.evaluacion.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "hacom.test.developer.insert.rx")
public class DatabaseSequence {

	@Id
	private String id;
	private long seq;
}
