package team.placeholder.internalprojectsmanagementsystem.controller.api;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<ClientDto> save(@RequestBody ClientDto clientDto) {
        System.out.println(clientDto);
        return new ResponseEntity<>(clientService.save(clientDto), HttpStatus.OK);
    }

    @GetMapping("count")
    public ResponseEntity<Long> countAll() {
        Long count = clientService.countAll();
        if (count != null) {
            return ResponseEntity.ok(count);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("{projectName}")
    public ResponseEntity<ClientDto> findByProjectName(@PathVariable String projectName) {
        ClientDto client = clientService.findByProjectName(projectName);
        if (client != null) {
            return ResponseEntity.ok(client);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
