package teste.residencia.neki.service;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import teste.residencia.neki.model.User;
import teste.residencia.neki.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;


    public List<User> obterTodos(){
        return userRepository.findAll();
    }

    public Optional<User> obterPorId(Long id){
        return userRepository.findById(id);
    }

    public Optional<User> obterPorLogin(String login){
        return userRepository.findByLogin(login);
    }

    public User adicionar(User user){
        user.setId(null);

        if(obterPorLogin(user.getLogin()).isPresent()){
            throw new InputMismatchException("Usuário já cadastrado! " + user.getLogin());
        }

        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);

        return userRepository.save(user);
    }



}
