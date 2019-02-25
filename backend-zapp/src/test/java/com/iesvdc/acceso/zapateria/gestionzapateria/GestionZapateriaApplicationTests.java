package com.iesvdc.acceso.zapateria.gestionzapateria;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import static org.hamcrest.Matchers.is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GestionZapateriaApplicationTests {

    @Test
    public void contextLoads() {

    }

    @Test
    public void testExisteClienteId1()
            throws IOException {

        // Given
        final URL url = new URL("http://localhost:8080/rest/cliente/1");
        final HttpURLConnection request = (HttpURLConnection) url.openConnection();

        // When
        request.setRequestMethod("GET");
        final int responseCode = request.getResponseCode();
        final int respuesta = 200;

        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nSending 'GET' request to URL : " + url);
        System.out.println("\n\nResponse Code : " + responseCode);

        // Then
        assertThat(responseCode, is(respuesta));

    }

}
