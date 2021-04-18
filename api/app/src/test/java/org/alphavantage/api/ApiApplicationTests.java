package org.alphavantage.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ApiApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void shouldReturnInfo() throws Exception {
		this.mockMvc.perform(get("http://localhost:8888/api/v1/json/info"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content()
						.string(containsString("\"data\":{\"alphaVantageApi\":\"http://localhost:8888/api/v1/json\"}}")));
	}

	@Test
	void shouldReturnCompanies() throws Exception {
		this.mockMvc.perform(get("http://localhost:8888/api/v1/json/query?function=SYMBOL_SEARCH&keywords=AA"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content()
						.string(containsString("bestMatches\":[{\"symbol\":\"TESO\",\"name\":\"Tesco Corporation USA\",\"type\":\"Equity\",\"region\":\"United States\",\"marketOpen\":\"09:30\",\"marketClose\":\"16:00\",\"timezone\":\"UTC-04\",\"currency\":\"USD\",\"matchScore\":\"0.8889\"}]}")));
	}

	@Test
	void shouldReturnDaily() throws Exception {
		this.mockMvc.perform(get("http://localhost:8888/api/v1/json/query?function=TIME_SERIES_DAILY"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content()
						.string(containsString("{\"prices\":[{\"date\":\"2021-04-13\",\"open\":\"133.0000\",\"high\":\"133.6200\",\"low\":\"130.3800\",\"close\":\"131.1800\",\"volume\":\"8033530\"}]}")));
	}
	
}
