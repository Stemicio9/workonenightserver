package com.w1n.workonenightserver.configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseConfiguration {

    @Value("${firebase.credential.resource-path}")
    private String keyPath;

    @Value("client_secret_275451600489-o7k86ckj59gl2bdopkq45at5cqiu44fd.apps.googleusercontent.com.json")
    private String percorsogooglesheets;

    @Bean
    @Primary
    public void firebaseInitialization() throws IOException {
        Resource resource = new ClassPathResource(keyPath);
        FileInputStream serviceAccount = new FileInputStream(resource.getFile());
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();
        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options);
        }
    }

}
