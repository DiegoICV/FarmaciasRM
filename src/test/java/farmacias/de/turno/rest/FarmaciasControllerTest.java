package farmacias.de.turno.rest;


import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import farmacias.de.turno.FarmaciasApplication;


@SpringBootTest(classes = FarmaciasApplication.class)
@AutoConfigureMockMvc
@WebMvcTest
public class FarmaciasControllerTest {

}
