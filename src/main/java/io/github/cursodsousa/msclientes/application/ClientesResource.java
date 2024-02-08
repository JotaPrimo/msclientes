package io.github.cursodsousa.msclientes.application;

import io.github.cursodsousa.msclientes.application.representation.ClienteSaveRequest;
import io.github.cursodsousa.msclientes.domain.Cliente;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClientesResource {

    private final ClienteService service;
    @GetMapping
    public String status() {
        // método para saber se o serviço está ok
        return "ok";
    }

    @PostMapping
    public ResponseEntity store(@RequestBody ClienteSaveRequest request) {
        Cliente cliente = request.toModel();
        service.save(cliente);
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("cpf={cpf}")
                .buildAndExpand(cliente.getCpf())
                .toUri();

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(params = "cpf")
    public ResponseEntity dadosCliente(@RequestParam String cpf) {
        Optional<Cliente> optionalCliente = service.getByCPF(cpf);

        if (optionalCliente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(optionalCliente);
    }

}
