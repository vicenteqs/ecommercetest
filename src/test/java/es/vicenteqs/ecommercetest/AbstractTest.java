package es.vicenteqs.ecommercetest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractTest {

    protected String mapToJsonNoException(Object obj) {
        try {
            return this.mapToJson(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }

	protected String jsonResourceAsString(String file) throws IOException {
		String result;

		try (InputStream resource = new ClassPathResource(file).getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(resource))) {
			result = reader.lines().collect(Collectors.joining("\n"));
		}

		return result;
	}

}
