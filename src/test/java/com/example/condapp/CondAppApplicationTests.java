package com.example.condapp;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


//@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CondAppApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

//    @Container
    private static final GenericContainer<?> myAppDev = new GenericContainer<>("devapp")
            .withExposedPorts(8080);
//    @Container
    private static final GenericContainer<?> myAppProd = new GenericContainer<>("prodapp")
            .withExposedPorts(8081);

    @BeforeAll
    public static void setUp() {
        myAppDev.start();
        myAppProd.start();
    }

    @Test
    void contextLoads() {
        ResponseEntity<String> forEntityMyAppDev = restTemplate.getForEntity("http://localhost:" + myAppDev.getMappedPort(8080) + "/profile", String.class);
        ResponseEntity<String> forEntityMyAppProd = restTemplate.getForEntity("http://localhost:" + myAppProd.getMappedPort(8081)+ "/profile", String.class);

        System.out.println("MyAppDev: " + forEntityMyAppDev.getBody());
        System.out.println("MyAppProd: " + forEntityMyAppProd.getBody());
    }

    @Test
    public void devMessageTest() {
        String expectedText = restTemplate.getForEntity("http://localhost:" + myAppDev.getMappedPort(8080) + "/profile", String.class).getBody();

        String resultText = "Current profile is dev";

        assertThat(expectedText, resultText);
    }

    @Test
    public void prodMessageTest() {
        String expectedText = restTemplate.getForEntity("http://localhost:" + myAppDev.getMappedPort(8081) + "/profile", String.class).getBody();

        String resultText = "Current profile is production";

        assertThat(expectedText, resultText);
    }

}
