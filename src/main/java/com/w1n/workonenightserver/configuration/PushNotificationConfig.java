package com.w1n.workonenightserver.configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;

@Configuration
public class PushNotificationConfig {

    @Value("${firebase.credential.resource-path}")
    private String keyPath;

    @Bean
    FirebaseMessaging firebaseMessaging() throws IOException {
        Resource resource = new ClassPathResource(keyPath);
        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(resource.getInputStream());
        FirebaseOptions firebaseOptions = FirebaseOptions
                .builder()
                .setCredentials(googleCredentials)
                .build();
        FirebaseApp app = FirebaseApp.initializeApp(firebaseOptions);
        return FirebaseMessaging.getInstance(app);
    }

}
