package uz.muydinov.planit.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uz.muydinov.planit.entity.User;
import uz.muydinov.planit.playload.LoginPayload;
import uz.muydinov.planit.playload.RegisterPayload;
import uz.muydinov.planit.service.JWTService;
import uz.muydinov.planit.service.UserService;

@RestController
public class UsersController {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTService jwtService;

    /**
     * Handles user registration.
     *
     * @param registerPayload payload containing user registration data.
     * @return the registered user.
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterPayload registerPayload) {
        try {
            User registeredUser = userService.register(registerPayload);
            return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * Handles user login and JWT generation.
     *
     * @param loginPayload payload containing login data.
     * @param session      the HTTP session to store the JWT.
     * @return success message or error response.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginPayload loginPayload, HttpSession session) {
        try {
            String jwtToken = userService.verify(loginPayload);
            session.setAttribute("jwt", jwtToken);
            return ResponseEntity.ok(jwtToken);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    /**
     * Protected endpoint that requires a valid JWT for access.
     *
     * @param session the HTTP session containing the JWT.
     * @return secure data or unauthorized response.
     */
    @GetMapping("/secure-endpoint")
    public ResponseEntity<?> getSecureData(HttpSession session) {
        String jwt = (String) session.getAttribute("jwt");

        if (jwt == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: No JWT found in session");
        }

        // Get the current authenticated user (UserDetails) from the SecurityContext
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Validate the JWT with the extracted UserDetails
        if (!jwtService.validateToken(jwt, userDetails)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: Invalid or expired token");
        }

        String email = jwtService.extractUserName(jwt);
        return ResponseEntity.ok("Hello, " + email + ". This is a secure endpoint.");
    }
}
