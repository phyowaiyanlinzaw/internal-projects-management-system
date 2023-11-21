package team.placeholder.internalprojectsmanagementsystem.service.impl.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import team.placeholder.internalprojectsmanagementsystem.dto.mapper.user.ClientMapper;
import team.placeholder.internalprojectsmanagementsystem.dto.model.department.DepartmentDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.issue.IssueDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.ClientDto;
import team.placeholder.internalprojectsmanagementsystem.dto.model.user.UserDto;
import team.placeholder.internalprojectsmanagementsystem.model.department.Department;
import team.placeholder.internalprojectsmanagementsystem.model.issue.Issue;
import team.placeholder.internalprojectsmanagementsystem.model.user.Client;
import team.placeholder.internalprojectsmanagementsystem.repository.user.ClientRepository;
import team.placeholder.internalprojectsmanagementsystem.service.user.ClientService;

import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    private final ModelMapper modelMapper;


    @Override
    public ClientDto save(ClientDto clientDto) {
        Client client = new Client();
        client.setName(clientDto.getName());
        client.setEmail(clientDto.getEmail());
        client.setPhone(clientDto.getPhone());
        Client savedClient = clientRepository.save(client);
        return modelMapper.map(savedClient, ClientDto.class);
    }

    @Override
    public List<ClientDto> getAllClient() {
        List<Client> clientList = clientRepository.findAll();

        for(Client client : clientList) {
            System.out.println(client.getId());
        }

        List<ClientDto> clientDtos = new ArrayList<>();

        for(Client client : clientList ) {
            ClientDto clientDto = modelMapper.map(client, ClientDto.class);
            clientDtos.add(clientDto);

        }
        return clientDtos;

    }

    @Override
    public Long countAll() {
        return clientRepository.count();
    }

    @Override
    public ClientDto findByProjectName(String projectName) {
        return null;
    }
}
