package za.ac.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.util.JwtUtil;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {

    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil();
    }

    @Test
    void generateTokenAndExtractEmail() {
        String email = "test@example.com";
        String role = "MEMBER";

        String token = jwtUtil.generateToken(email, role);
        assertNotNull(token);

        String extractedEmail = jwtUtil.extractEmail(token);
        assertEquals(email, extractedEmail);
    }

    @Test
    void extractRole() {
        String email = "admin@example.com";
        String role = "ADMIN";

        String token = jwtUtil.generateToken(email, role);
        assertNotNull(token);

        String extractedRole = jwtUtil.extractRole(token);
        assertEquals(role, extractedRole);
    }

    @Test
    void isTokenValid() {
        String token = jwtUtil.generateToken("user@example.com", "MEMBER");
        assertTrue(jwtUtil.isTokenValid(token));
        assertFalse(jwtUtil.isTokenValid("invalid.token.here"));
    }
}
