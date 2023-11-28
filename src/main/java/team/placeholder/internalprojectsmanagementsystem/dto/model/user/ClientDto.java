package team.placeholder.internalprojectsmanagementsystem.dto.model.user;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
public class ClientDto implements Serializable {
    private long id;
    private String name,email,phone;

    public static class ClientDtoBuilder {
        private ClientDto clientDto;

        public ClientDtoBuilder() {
            this.clientDto = new ClientDto();
        }

        public ClientDtoBuilder id(long id) {
            this.clientDto.setId(id);
            return this;
        }

        public ClientDtoBuilder name(String name) {
            this.clientDto.setName(name);
            return this;
        }

        public ClientDtoBuilder email(String email) {
            this.clientDto.setEmail(email);
            return this;
        }

        public ClientDtoBuilder phone(String phone) {
            this.clientDto.setPhone(phone);
            return this;
        }

        public ClientDto build() {
            return this.clientDto;
        }
    }
}
