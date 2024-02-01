package io.github.cursodsousa.msclientes.application;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("clientes")
public class ClientesResource {
    @GetMapping
    public String status() {
        // método para saber se o serviço está ok
        return "ok";
    }

    @PostMapping
    public void store() {

    }

    @GetMapping
    public void findByCPF() {

    }

}
