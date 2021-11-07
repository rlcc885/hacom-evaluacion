package com.hacom.evaluacion.config;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;

@Configuration
@ConfigurationProperties
@EnableReactiveMongoRepositories(basePackages = "com.hacom.evaluacion.repository")
public class MongoConfig {
	@Value("${mongodbDatabase}")
	private String mongodbDatabase;
	@Value("${mongodbUri}")
	private String mongodbUri;

	@Bean
	public MongoClient mongo() {
		ConnectionString connectionString = new ConnectionString(mongodbUri + "/" + mongodbDatabase);
		MongoClientSettings mongoClientSettings = MongoClientSettings.builder().applyConnectionString(connectionString)
				.build();
		return MongoClients.create(mongoClientSettings);
	}

	@Bean
	public MongoCustomConversions mongoCustomConversions() {
		return new MongoCustomConversions(
				List.of(new OffsetDateTimeReadConverter(), new OffsetDateTimeWriteConverter()));
	}

	static class OffsetDateTimeWriteConverter implements Converter<OffsetDateTime, String> {
		@Override
		public String convert(OffsetDateTime source) {
			return source.toInstant().atZone(ZoneOffset.UTC).toString();
		}
	}

	static class OffsetDateTimeReadConverter implements Converter<String, OffsetDateTime> {
		@Override
		public OffsetDateTime convert(String source) {
			return OffsetDateTime.parse(source);
		}
	}
}
