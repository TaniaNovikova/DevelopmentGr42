package de.ait.javalessons.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    // Публичный эндпоинт доступен всем пользователям
    @GetMapping("/public/info")
    public ResponseEntity<String> getPublicInfo(){
        return ResponseEntity.ok("User Info, public information");
    }

    // Защищённый эндпоинт для авторизованных пользователей
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/user/info")
    public ResponseEntity<String> getUserInfo(){
        return ResponseEntity.ok("User Info, secured user information");
    }

    // Защищённый эндпоинт только для администратора
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/info")
    public ResponseEntity<String> getAdminInfo(){
        return ResponseEntity.ok("Admin Info, secured admin information");
    }

}

