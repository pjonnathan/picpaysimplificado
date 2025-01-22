package com.example.picpaysimplificado.services;

import com.example.picpaysimplificado.DTO.NotificationDTO;
import com.example.picpaysimplificado.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {

    @Autowired
    private RestTemplate restTemplate;

    public void sendNotificatio(User user, String msg) throws Exception{
        String email = user.getEmail();

        NotificationDTO notificationRequest = new NotificationDTO(email, msg);
        ResponseEntity<String> notificationResponse = restTemplate.postForEntity("https://util.devi.tools/api/v1/notify", notificationRequest, String.class);

        if(!(notificationResponse.getStatusCode() == HttpStatus.OK)) {
            System.out.println("Erro ao enviar o Email");
            throw new Exception("Service de notificação está fora do ar");
        }
    }
}
