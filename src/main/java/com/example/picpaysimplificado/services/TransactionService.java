package com.example.picpaysimplificado.services;

import com.example.picpaysimplificado.DTO.TransactionDTO;
import com.example.picpaysimplificado.domain.transaction.Transaction;
import com.example.picpaysimplificado.domain.user.User;
import com.example.picpaysimplificado.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private RestTemplate restTemplate;

    public void createTransaction(TransactionDTO transaction) throws Exception{
        User sender = this.userService.findUserById(transaction.senderId());

        User receiver = this.userService.findUserById(transaction.receiverId());

        userService.validationTransaction(sender, transaction.value());

        boolean isAuthorized = authorizeTransaction(sender, transaction.value());
        if (!isAuthorized){
            throw new Exception("Transação não autorizada");
        }

        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transaction.value());
        newTransaction.setSender(sender);
        newTransaction.setReceiver(receiver);
        newTransaction.setTimeStamp(LocalDateTime.now());


        sender.setBalace(sender.getBalace().subtract(transaction.value()));
        receiver.setBalace(receiver.getBalace().add(transaction.value()));

        this.transactionRepository.save(newTransaction);
        this.userService.saveUser(sender);
        this.userService.saveUser(receiver);
    }

    public boolean authorizeTransaction(User sender, BigDecimal value) {
        ResponseEntity<Map> authorizeResponse = restTemplate.getForEntity("https://util.devi.tools/api/v2/authorize", Map.class);

        if(authorizeResponse.getStatusCode() == HttpStatus.OK){
            String massage = (String) authorizeResponse.getBody().get("message");
            return "Autorizado".equalsIgnoreCase(massage);
        }else return false;
    }
}
