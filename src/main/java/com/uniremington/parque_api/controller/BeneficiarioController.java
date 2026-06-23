package com.uniremington.parque_api.controller;

import com.uniremington.parque_api.entity.Beneficiario;
import com.uniremington.parque_api.service.BeneficiarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
// 🌟 Quitamos la ruta global para poder manejar la seguridad de forma independiente por método
@RequestMapping 
public class BeneficiarioController {

    private final BeneficiarioService service;

    public BeneficiarioController(BeneficiarioService service) {
        this.service = service;
    }

    /* * 🌐 ENDPOINT PÚBLICO:
     * Cambiamos la ruta a '/auth/beneficiarios' (o la que tengas libre en tu SecurityConfig)
     * para que cualquier ciudadano se registre desde el formulario público de Angular.
     */
    @PostMapping("/auth/beneficiarios")
    public Beneficiario crear(@RequestBody Beneficiario b) {
        return service.crear(b);
    }

    /* * 🔐 ENDPOINTS PROTEGIDOS:
     * Todos estos inician con '/operador/' para que Spring Security valide el JWT y los Roles.
     */
    @GetMapping("/operador/beneficiarios")
    public List<Beneficiario> listar() {
        return service.listar();
    }

    @PutMapping("/operador/beneficiarios/{id}")
    public Beneficiario actualizar(@PathVariable Long id, @RequestBody Beneficiario b) {
        return service.actualizar(id, b);
    }

    @DeleteMapping("/operador/beneficiarios/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}