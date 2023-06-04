package ru.abdullaeva.informatbackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.abdullaeva.informatbackend.dto.VariantDto;

import ru.abdullaeva.informatbackend.service.interf.VariantService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/variant")
public class VariantController {

    private final VariantService variantService;

    @GetMapping("/{id}")
    public VariantDto findVariantById(@PathVariable("id") Integer id) {
        return variantService.findById(id);
    }
}
