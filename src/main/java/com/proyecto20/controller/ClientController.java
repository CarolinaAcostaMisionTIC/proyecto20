package com.proyecto20.controller;

import com.proyecto20.model.Client;
import com.proyecto20.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/Client")
@CrossOrigin(origins = "*")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void createClient(@RequestBody Client client){
        clientService.create(client);
    }

    @GetMapping("/all")
    public List<Client> getClients(){
        return clientService.clients();
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateClient(@RequestBody Client client){
        clientService.update(client);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClient(@PathVariable ("id") Integer id){
        clientService.delete(id);
    }
}
