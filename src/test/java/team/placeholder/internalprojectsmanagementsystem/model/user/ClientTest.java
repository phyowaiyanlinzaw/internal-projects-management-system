package team.placeholder.internalprojectsmanagementsystem.model.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import team.placeholder.internalprojectsmanagementsystem.model.project.Project;
import team.placeholder.internalprojectsmanagementsystem.model.user.Client;
import team.placeholder.internalprojectsmanagementsystem.repository.user.ClientRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class ClientTest {

    @Mock
    private Client client;

    @Mock
    private ClientRepository clientRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        client = new Client();
    }

    @Test
    public void testId() {
        long clientId = 1;
        client.setId(clientId);
        assertEquals(clientId, client.getId());
    }

    @Test
    public void testName() {
        String name = "Ei Ei Phyo";
        client.setName(name);
        assertEquals(name, client.getName());
    }

    @Test
    public void testPhone() {
        String phone = "09-123456789";
        client.setPhone(phone);
        assertEquals(phone, client.getPhone());
    }

    @Test
    public void testEmail() {
        String email = "eep@example.com";
        client.setEmail(email);
        assertEquals(email, client.getEmail());
    }



}






