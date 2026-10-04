package com.lab.jpa.gestaovagas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestaoVagasApplication {

    public static void main(String[] args) {
        // Inicializa o servidor web embutido (Tomcat na porta 8080)
        SpringApplication.run(GestaoVagasApplication.class, args);
    }
}
