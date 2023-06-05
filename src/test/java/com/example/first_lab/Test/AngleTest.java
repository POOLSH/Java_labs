package com.example.first_lab.Test;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
class AngleTest {

        @Autowired
        private MockMvc mockMvc;

        @Test
        public void messageDefault() throws Exception{
            this.mockMvc.perform(get("/angle"))
                    .andDo(print())
                    .andExpect(status().isOk());
        }

        @Test
        public void messagePI() throws Exception{
            double degree = 180;
            this.mockMvc.perform(get("/angle?degree=" + degree))
                    .andDo(print())
                    .andExpect(status().isOk());

        }

        @Test
        public void Bad_request400() throws Exception{
            double request = -1;
            this.mockMvc.perform(get("/angle?degree=" + request))
                    .andDo(print())
                    .andExpect(status().isBadRequest());
        }

        @Test
        public void Internal500() throws Exception{
            String request = "NaN";
            this.mockMvc.perform(get("/angle?degree=" + request))
                    .andDo(print())
                    .andExpect(status().isInternalServerError());

        }

}

