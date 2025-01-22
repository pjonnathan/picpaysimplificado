package com.example.picpaysimplificado.services;

import com.example.picpaysimplificado.DTO.UserDTO;
import com.example.picpaysimplificado.domain.user.UserType;
import com.example.picpaysimplificado.domain.user.User;
import com.example.picpaysimplificado.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public void validationTransaction(User sender, BigDecimal amount) throws Exception {
        if(sender.getUserType() == UserType.MARCHANT){
            throw new Exception("Usuário do tipo Lojista não pode realizar transações");
        }

        if (sender.getBalace().compareTo(amount) < 0){
            throw new Exception("Saldo insuficiente");
        }
    }

    public User findUserById(Long id) throws Exception{
        return this.repository.findUserById(id).orElseThrow(() -> new Exception("Usuario não encontrado"));
    }

    public void saveUser(User user){
        this.repository.save(user);
    }

    public User createUser(UserDTO data){
        User newUser = new User(data);
        this.saveUser(newUser);
        return newUser;
    }

    public List<User> getAllUser(){
        return this.repository.findAll();
    }
}
