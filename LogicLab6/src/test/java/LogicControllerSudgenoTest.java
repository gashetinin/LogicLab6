import Lab6.NDlab6Application;
import org.json.simple.parser.JSONParser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = NDlab6Application.class)
@AutoConfigureMockMvc
public class LogicControllerSudgenoTest {

    Object vars;
    Object rules;
    Object funcs;

    @Value("${local.server.port}")
    int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @Before
    public void setUp() {
        JSONParser parser = new JSONParser();
        try
        {
            funcs = parser.parse(new FileReader("/home/grigory/Desktop/NDlab6/LogicLab6/LogicLab6/funcsSudgeno.json"));
            vars = parser.parse(new FileReader("/home/grigory/Desktop/NDlab6/LogicLab6/LogicLab6/vars.json"));
            rules = parser.parse(new FileReader("/home/grigory/Desktop/NDlab6/LogicLab6/LogicLab6/rules.json"));

        }
        catch(FileNotFoundException fe)
        {
            fe.printStackTrace();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void testSetVars() {
        ResponseEntity responseEntity = restTemplate.postForEntity("/sudgeno/vars", vars, Object.class);
        assertEquals(org.springframework.http.HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testSetFuncs() {
        ResponseEntity responseEntity = restTemplate.postForEntity("/sudgeno/funcs", funcs, Object.class);
        assertEquals(org.springframework.http.HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testSetRules() {
        ResponseEntity responseEntity = restTemplate.postForEntity("/sudgeno/rules", rules, Object.class);
        assertEquals(org.springframework.http.HttpStatus.OK, responseEntity.getStatusCode());
    }




}
