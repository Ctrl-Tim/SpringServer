package com.salon.SpringServer;

import com.salon.SpringServer.model.Client;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Assertions;
import com.salon.SpringServer.service.ClientService;

import java.util.List;

@SpringBootTest
public class ClientTests {
    private static final Logger log = LoggerFactory.getLogger(SpringServerApplication.class);

    @Autowired
    private ClientService clientService;

    @Test
    void testClientCreate() {
        List<Client> clientList = clientService.getClients();
        for (Client c : clientList) {
            clientService.deleteClient(c.getId());
        }
        Client new_client = new Client();
        new_client.setLogin("Ivan1998");
        new_client.setName("Ivan");
        new_client.setSurname("Ivanov");
        new_client.setPassword("1234");
        final Client client = clientService.addClient(new_client);
        log.info(client.toString());
        Assertions.assertNotNull(client.getId());
    }

    @Test
    void testClientReadAll() {
        List<Client> clientList = clientService.getClients();
        for (Client c : clientList) {
            clientService.deleteClient(c.getId());
        }
        Client new_client = new Client();
        new_client.setLogin("Ivan1998");
        new_client.setName("Ivan");
        new_client.setSurname("Ivanov");
        new_client.setPassword("1234");
        final Client client = clientService.addClient(new_client);
        Client new_client_2 = new Client();
        new_client_2.setLogin("OlgaKiss");
        new_client_2.setName("Olga");
        new_client_2.setSurname("Savchenko");
        new_client_2.setPassword("0000");
        final Client client_2 = clientService.addClient(new_client_2);
        final List<Client> clients = clientService.getClients();
        log.info(clients.toString());
        Assertions.assertEquals(clients.size(), 2);
    }
}
