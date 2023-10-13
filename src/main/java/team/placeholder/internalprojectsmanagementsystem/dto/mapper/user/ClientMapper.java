package team.placeholder.internalprojectsmanagementsystem.dto.mapper.user;

import team.placeholder.internalprojectsmanagementsystem.dto.model.user.ClientDto;
import team.placeholder.internalprojectsmanagementsystem.model.user.Client;

public class ClientMapper {
    public static ClientDto toClientDto(Client client){
        if(client == null){
            return null;
        }
        ClientDto clientDto = new ClientDto();
        clientDto.setId(client.getId());
        clientDto.setName(client.getName());
        clientDto.setEmail(client.getEmail());
        clientDto.setPhone(client.getPhone());
        return clientDto;
    }
}
