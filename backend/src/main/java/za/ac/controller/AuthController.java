//package za.ac.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.beans.factory.annotation.Value;
//import za.ac.domain.AppUser;
//import za.ac.domain.Enums.Role;
//import za.ac.repository.IAppUserRepository;
//import za.ac.security.JwtUtil;
//import za.ac.util.Helper;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.UUID;
//
//@RestController
//@RequestMapping("api/auth")
//public class AuthController {
//
//    private final IAppUserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final AuthenticationManager authenticationManager;
//    private final JwtUtil jwtUtil;
//    @Value("${praise.leader.signup-code:}")
//    private String leaderSignupCode;
//
//    @Autowired
//    public AuthController(
//            IAppUserRepository userRepository,
//            PasswordEncoder passwordEncoder,
//            AuthenticationManager authenticationManager,
//            JwtUtil jwtUtil
//    ) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//        this.authenticationManager = authenticationManager;
//        this.jwtUtil = jwtUtil;
//    }
//
//    @PostMapping("/register")
//    public ResponseEntity<Map<String, Object>> register(@RequestBody Map<String, String> request) {
//        String email = request.get("email") == null ? null : request.get("email").trim().toLowerCase();
//        String userType = request.get("userType");
//        String accessCode = request.get("accessCode");
//        String password = request.get("password");
//        String firstName = request.get("firstName");
//        String lastName = request.get("lastName");
//
//        if (Helper.isNullOrEmpty(email) || Helper.isNullOrEmpty(password)) {
//            Map<String, Object> error = new HashMap<>();
//            error.put("error", "Email and password are required");
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
//        }
//
//        if (userRepository.existsByEmail(email)) {
//            Map<String, Object> error = new HashMap<>();
//            error.put("error", "Email is already registered");
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
//        }
//
//        Role role = Role.MEMBER;
//        if ("LEADER".equalsIgnoreCase(userType)) {
//            if (Helper.isNullOrEmpty(leaderSignupCode) || !leaderSignupCode.equals(accessCode)) {
//                Map<String, Object> error = new HashMap<>();
//                error.put("error", "Invalid leader access code");
//                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
//            }
//            role = Role.LEADER;
//        }
//
//        AppUser user = new AppUser.Builder()
//                .setUserId(UUID.randomUUID().toString())
//                .setEmail(email)
//                .setPassword(passwordEncoder.encode(password))
//                .setRole(role)
//                .setFirstName(firstName)
//                .setLastName(lastName)
//                .build();
//
//        AppUser savedUser = userRepository.save(user);
//
//        String token = jwtUtil.generateToken(savedUser.getEmail(), savedUser.getRole().name());
//
//        Map<String, Object> response = new HashMap<>();
//        response.put("token", token);
//        response.put("email", savedUser.getEmail());
//        response.put("role", savedUser.getRole().name());
//        response.put("firstName", savedUser.getFirstName());
//        response.put("lastName", savedUser.getLastName());
//
//        return ResponseEntity.ok(response);
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> request) {
//        String email = request.get("email") == null ? null : request.get("email").trim().toLowerCase();
//        String userType = request.get("userType");
//        String password = request.get("password");
//
//        if (Helper.isNullOrEmpty(email) || Helper.isNullOrEmpty(password)) {
//            Map<String, Object> error = new HashMap<>();
//            error.put("error", "Email and password are required");
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
//        }
//
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(email, password)
//            );
//        } catch (BadCredentialsException e) {
//            Map<String, Object> error = new HashMap<>();
//            error.put("error", "Invalid email or password");
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
//        }
//
//        AppUser user = userRepository.findByEmail(email)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        if (!Helper.isNullOrEmpty(userType) && user.getRole() != Role.ADMIN
//                && !user.getRole().name().equalsIgnoreCase(userType)) {
//            Map<String, Object> error = new HashMap<>();
//            error.put("error", "This account is registered as a " + user.getRole().name().toLowerCase()
//                    + ". Please select the correct account type.");
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
//        }
//
//        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());
//
//        Map<String, Object> response = new HashMap<>();
//        response.put("token", token);
//        response.put("email", user.getEmail());
//        response.put("role", user.getRole().name());
//        response.put("firstName", user.getFirstName());
//        response.put("lastName", user.getLastName());
//
//        return ResponseEntity.ok(response);
//    }
//}
