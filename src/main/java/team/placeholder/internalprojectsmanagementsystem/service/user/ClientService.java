package team.placeholder.internalprojectsmanagementsystem.service.user;

import team.placeholder.internalprojectsmanagementsystem.dto.model.user.ClientDto;

import java.util.List;

public interface ClientService {

    ClientDto save(ClientDto clientDto);

    List<ClientDto> getAllClient();


}
