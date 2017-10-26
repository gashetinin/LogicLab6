import Lab6.NDlab6Application;

import groovy.util.logging.Slf4j;
import org.apache.http.HttpStatus;
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

import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;

import java.io.FileReader;

import static org.junit.Assert.assertEquals;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = NDlab6Application.class)
@AutoConfigureMockMvc
public class LogicControllerMamdaniTest {

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
            funcs = parser.parse(new FileReader("/home/grigory/Desktop/NDlab6/LogicLab6/LogicLab6/funcsMamdani.json"));
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
        ResponseEntity responseEntity = restTemplate.postForEntity("/mamdani/vars", vars, Object.class);
        assertEquals(org.springframework.http.HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testSetFuncs() {
        ResponseEntity responseEntity = restTemplate.postForEntity("/mamdani/funcs", funcs, Object.class);
        assertEquals(org.springframework.http.HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testSetRules() {
        ResponseEntity responseEntity = restTemplate.postForEntity("/mamdani/rules", rules, Object.class);
        assertEquals(org.springframework.http.HttpStatus.OK, responseEntity.getStatusCode());
    }


    @Test
    public void test1() {
        ResponseEntity responseEntity = restTemplate.postForEntity("/mamdani/vars", vars, Object.class);
        assertEquals(org.springframework.http.HttpStatus.OK, responseEntity.getStatusCode());
        responseEntity = restTemplate.postForEntity("/mamdani/funcs", funcs, Object.class);
        assertEquals(org.springframework.http.HttpStatus.OK, responseEntity.getStatusCode());
        responseEntity = restTemplate.postForEntity("/mamdani/rules", rules, Object.class);
        assertEquals(org.springframework.http.HttpStatus.OK, responseEntity.getStatusCode());

        responseEntity = restTemplate.getForEntity("/mamdani/checkLogic/10/20", Object.class);
        assertEquals(org.springframework.http.HttpStatus.OK, responseEntity.getStatusCode());

        responseEntity = restTemplate.getForEntity("/mamdani/getVarGraphics", Object.class);
        //       log.info("graphics ${responseEntity.getBody()}");
        assertEquals(org.springframework.http.HttpStatus.OK, responseEntity.getStatusCode());
        responseEntity = restTemplate.getForEntity("/mamdani/getRuleGraphics", Object.class);
        //     log.info("graphics ${responseEntity.getBody()}");
        assertEquals(org.springframework.http.HttpStatus.OK, responseEntity.getStatusCode());
        responseEntity = restTemplate.getForEntity("/mamdani/getResultGraphics", Object.class);
        assertEquals(org.springframework.http.HttpStatus.OK, responseEntity.getStatusCode());
        //   log.info("graphics ${responseEntity.getBody()}");

    }
}