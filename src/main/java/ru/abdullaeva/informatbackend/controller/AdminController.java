package ru.abdullaeva.informatbackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.abdullaeva.informatbackend.dto.web.AdminJournalDto;
import ru.abdullaeva.informatbackend.dto.UserDto;
import ru.abdullaeva.informatbackend.dto.web.WebNewUserDto;
import ru.abdullaeva.informatbackend.dto.web.WebUserDto;
import ru.abdullaeva.informatbackend.dto.web.WebVariantDto;
import ru.abdullaeva.informatbackend.service.interf.AdminService;
import ru.abdullaeva.informatbackend.service.interf.UserService;
import ru.abdullaeva.informatbackend.service.interf.VariantService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final AdminService adminService;
    private final UserService userService;
    private final VariantService variantService;

    @GetMapping()
    public List<WebUserDto> admin() {
       return userService.getAll();
    }

    @GetMapping("/journal")
    public AdminJournalDto adminJournal() {
        return adminService.getAdminJournal();
    }

    @PostMapping("/registration")
    public boolean createUser(@RequestBody WebNewUserDto user) {
        return adminService.createUser(user);
    }

    @GetMapping("/ban/{id}")
    public boolean userBan(@PathVariable("id") Integer id) {
        return adminService.banUser(id);
    }

    @GetMapping("/edit/{id}")
    public boolean resetPassword(@PathVariable("id") Integer id) {
        return adminService.resetPass(id);
    }

    @PostMapping("/edit")
    public String userEdit(@RequestBody UserDto user) {
        if(adminService.editUser(user)) {
            return "User update";
        }
        return "User not update";
    }

    @PostMapping("/create/variant")
    public boolean createVariant(@RequestBody WebVariantDto variant) {
        return variantService.createVariant(variant);
    }

}
