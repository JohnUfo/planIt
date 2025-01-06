package uz.muydinov.planit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import uz.muydinov.planit.entity.User;
import uz.muydinov.planit.playload.LoginPayload;
import uz.muydinov.planit.playload.RegisterPayload;
import uz.muydinov.planit.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private JWTService jwtService;

    public User register(RegisterPayload registerPayload) {
        User user = new User();
        user.setFullname(registerPayload.getFullname());
        user.setEmail(registerPayload.getEmail());
        user.setPassword(encoder.encode(registerPayload.getPassword()));
        return userRepository.save(user);
    }

    public String verify(LoginPayload user) {
        Authentication authentication = authManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getEmail());
        } else {
            return "Fail";

        }
    }
}
