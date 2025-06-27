package com.centouro.centouro.controllers;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private Map<Long, String> fakeUserDb = new HashMap<>();
    private long userIdSequence = 1;

    @GetMapping("/hello")
    public String getUsers() {
        return "Hello world";
    }

    // GET /users
    @GetMapping
    public List<String> getAllUsers() {
        return new ArrayList<>(fakeUserDb.values());
    }

    // GET /users/{id}
    @GetMapping("/{id}")
    public String getUserById(@PathVariable Long id) {
        return fakeUserDb.getOrDefault(id, "Usuário não encontrado");
    }

    // POST /users
    @PostMapping
    public String createUser(@RequestBody String name) {
        fakeUserDb.put(userIdSequence, name);
        return "Usuário criado com ID: " + userIdSequence++;
    }

    // PUT /users/{id}
    @PutMapping("/{id}")
    public String updateUser(@PathVariable Long id, @RequestBody String newName) {
        if (!fakeUserDb.containsKey(id)) {
            return "Usuário não encontrado";
        }
        fakeUserDb.put(id, newName);
        return "Usuário atualizado";
    }

    // DELETE /users/{id}
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        if (fakeUserDb.remove(id) == null) {
            return "Usuário não encontrado";
        }
        return "Usuário removido com sucesso";
    }
}
