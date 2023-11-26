package team.placeholder.internalprojectsmanagementsystem.model.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="client")
@Getter
@Setter
@NoArgsConstructor
public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String phone;
    @Column(unique = true)
    private String email;

    public static AssociationOverride builder() {

        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client other = (Client) o;
        return id == other.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    public static class ClientBuilder {
        private Client client;

        public ClientBuilder() {
            this.client = new Client();
        }

        public ClientBuilder id(long id) {
            this.client.setId(id);
            return this;
        }

        public ClientBuilder name(String name) {
            this.client.setName(name);
            return this;
        }

        public ClientBuilder phone(String phone) {
            this.client.setPhone(phone);
            return this;
        }

        public ClientBuilder email(String email) {
            this.client.setEmail(email);
            return this;
        }

        public Client build() {
            return this.client;
        }
    }


}
