package br.com.clusterlab.credentials;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import br.com.clusterlab.credentials.dto.Credential;
import br.com.clusterlab.credentials.service.Credentials;
import br.com.clusterlab.credentials.service.Scrypt;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class CredentialsBuilder implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(CredentialsBuilder.class);
        application.setWebApplicationType(WebApplicationType.NONE);
        application.run(args);
    }

    @Override
    public void run(String[] args) throws JsonProcessingException {
        String encoder = "scrypt";

        List<Credential> customCredentials = new ArrayList<>();

        Credential user1 = new Credential();
        user1.setUsername("damato");
        user1.setEncoder(encoder);
        user1.setPassword("{"+encoder+"}"+Scrypt.encrypty("123mudar"));
        user1.setRole("admin");
        customCredentials.add(user1);

//        Credential user2 = new Credential();
//        user2.setUsername("rundeck");
//        user2.setEncoder("SCRYPT");
//        user2.setPassword(Scrypt.encrypty("123mudar"));
//        user2.setRole("admin");
//        customCredentials.add(user2);
//
//
//        Credential user3 = new Credential();
//        user3.setUsername("guest");
//        user3.setEncoder("SCRYPT");
//        user3.setPassword(Scrypt.encrypty("123mudar"));
//        user3.setRole("view");
//        customCredentials.add(user3);

        ObjectMapper mapper = new ObjectMapper();
        String customCredentialsString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(customCredentials);
        System.out.println(customCredentialsString);
        System.out.println(Base64.getEncoder().encodeToString(customCredentialsString.getBytes()));

        for (Credential cred: Credentials.getCredentials(Base64.getEncoder().encodeToString(customCredentialsString.getBytes()))){
            System.out.println("User found, " + cred.getUsername() +
                    " with role " + cred.getRole());
        }



    }
}
