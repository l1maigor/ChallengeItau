package br.com.challenge.itau.controller;

import br.com.challenge.itau.model.PasswordRequestModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ChallengeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void validateStringSuccessfully() throws Exception {

        var request = MockMvcRequestBuilders
                .get("/validate/password/v2")
                .header("Content-Type", "application/json")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new PasswordRequestModel("AbTp9!fok")));

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("code").value(0))
                .andExpect(jsonPath("message").value("Password successfully validated"));
    }

    @Test
    void validateStringFailureNumeric() throws Exception {

        var request = MockMvcRequestBuilders
                .get("/validate/password/v2")
                .header("Content-Type", "application/json")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new PasswordRequestModel("aa")));

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("code").value(1))
                .andExpect(jsonPath("message").value("Validation conditions are not met"));
    }

    @Test
    void validateStringFailureLowercase() throws Exception {

        var request = MockMvcRequestBuilders
                .get("/validate/password/v2")
                .header("Content-Type", "application/json")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new PasswordRequestModel("1")));

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("code").value(1))
                .andExpect(jsonPath("message").value("Validation conditions are not met"));
    }

    @Test
    void validateStringFailureUppercase() throws Exception {

        var request = MockMvcRequestBuilders
                .get("/validate/password/v2")
                .header("Content-Type", "application/json")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new PasswordRequestModel("1a")));

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("code").value(1))
                .andExpect(jsonPath("message").value("Validation conditions are not met"));
    }

    @Test
    void validateStringFailureSpecialCharacters() throws Exception {

        var request = MockMvcRequestBuilders
                .get("/validate/password/v2")
                .header("Content-Type", "application/json")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new PasswordRequestModel("1aB")));

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("code").value(1))
                .andExpect(jsonPath("message").value("Validation conditions are not met"));
    }

    @Test
    void validateStringFailureDuplicate() throws Exception {

        var request = MockMvcRequestBuilders
                .get("/validate/password/v2")
                .header("Content-Type", "application/json")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new PasswordRequestModel("1aB!1")));

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("code").value(1))
                .andExpect(jsonPath("message").value("Validation conditions are not met"));
    }

    @Test
    void validateStringFailureSize() throws Exception {

        var request = MockMvcRequestBuilders
                .get("/validate/password/v2")
                .header("Content-Type", "application/json")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new PasswordRequestModel("1aB!")));

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("code").value(1))
                .andExpect(jsonPath("message").value("Validation conditions are not met"));
    }

    @Test
    void validateStringFailureBlank() throws Exception {

        var request = MockMvcRequestBuilders
                .get("/validate/password/v2")
                .header("Content-Type", "application/json")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new PasswordRequestModel("1aB! ")));

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("code").value(1))
                .andExpect(jsonPath("message").value("Validation conditions are not met"));
    }

    @Test
    void validateStringNull() throws Exception {

        var request = MockMvcRequestBuilders
                .get("/validate/password/v2")
                .header("Content-Type", "application/json")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new PasswordRequestModel(null)));

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("code").value(2))
                .andExpect(jsonPath("message").value("Password field cannot be null"));
    }

    @Test
    void validateStringBooleanTrue() throws Exception {

        var request = MockMvcRequestBuilders
                .get("/validate/password/v1")
                .header("Content-Type", "application/json")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new PasswordRequestModel("AbTp9!fok")));

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    void validateStringBooleanFalse() throws Exception {

        var request = MockMvcRequestBuilders
                .get("/validate/password/v1")
                .header("Content-Type", "application/json")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new PasswordRequestModel("aa")));

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }
}