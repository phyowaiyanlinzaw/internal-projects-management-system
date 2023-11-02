package team.placeholder.internalprojectsmanagementsystem.controller.api;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.ClientDto;
import team.placeholder.internalprojectsmanagementsystem.service.impl.user.ClientServiceImpl;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/client/")
public class ClientController {

    private final ClientServiceImpl clientService;


    @GetMapping("list")
    public ResponseEntity<List<ClientDto>> getAllClients() {
        List<ClientDto> clients = clientService.getAllClient();
        if (clients != null) {
            return ResponseEntity.ok(clients);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("save")
    public ResponseEntity<String> save(ClientDto clientDto) {
        ClientDto savedClient = clientService.save(clientDto);
        if (savedClient != null) {
            return ResponseEntity.ok("Client saved successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to save client");
        }
    }


}
