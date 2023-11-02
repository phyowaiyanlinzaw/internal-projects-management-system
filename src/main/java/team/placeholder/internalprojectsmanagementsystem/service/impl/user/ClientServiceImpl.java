package team.placeholder.internalprojectsmanagementsystem.service.impl.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import team.placeholder.internalprojectsmanagementsystem.dto.mapper.user.ClientMapper;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.ClientDto;
import team.placeholder.internalprojectsmanagementsystem.model.user.Client;
import team.placeholder.internalprojectsmanagementsystem.repository.user.ClientRepository;
import team.placeholder.internalprojectsmanagementsystem.service.user.ClientService;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    @Override
    public ClientDto save(ClientDto clientDto) {
        Client client = new Client();
        client.setName(clientDto.getName());
        client.setEmail(clientDto.getEmail());
        client.setPhone(clientDto.getPhone());
        clientRepository.save(client);
        return ClientMapper.toClientDto(clientRepository.save(client));
    }

    @Override
    public List<ClientDto> getAllClient() {
        List<Client> clients = clientRepository.findAll();
        return clients.stream()
                .map(ClientMapper::toClientDto)
                .collect(java.util.stream.Collectors.toList());

    }

    @Override
    public Long countAll() {
        return clientRepository.count();
    }

    @Override
    public ClientDto findByProjectName(String projectName) {
        Client client = clientRepository.findByProjectName(projectName);
        return ClientMapper.toClientDto(client);
    }
}
