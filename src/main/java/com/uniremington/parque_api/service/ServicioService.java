package com.uniremington.parque_api.service;

import com.uniremington.parque_api.dto.ServicioRequest;
import com.uniremington.parque_api.entity.*;
import com.uniremington.parque_api.repository.*;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ServicioService {

    private final ServicioRepository repo;
    private final BeneficiarioRepository benRepo;
    private final EstudianteRepository estRepo;
    private final UsuarioRepository userRepo;
    private final RecursoRepository recursoRepo;
    private final SeguimientoRepository casoRepo;

    public ServicioService(
            ServicioRepository repo,
            BeneficiarioRepository benRepo,
            EstudianteRepository estRepo,
            UsuarioRepository userRepo,
            RecursoRepository recursoRepo,
            SeguimientoRepository casoRepo) {
        this.repo = repo;
        this.benRepo = benRepo;
        this.estRepo = estRepo;
        this.userRepo = userRepo;
        this.recursoRepo = recursoRepo;
        this.casoRepo = casoRepo;
    }

    public Servicio crear(ServicioRequest req) {
        Servicio s = new Servicio();
        s.setBeneficiario(benRepo.findById(req.getBeneficiarioId()).orElseThrow());
        s.setEstudiante(estRepo.findById(req.getEstudianteId()).orElseThrow());
        s.setProfesor(userRepo.findById(req.getProfesorId()).orElseThrow());
        s.setTipoServicio(req.getTipoServicio());
        s.setFacultad(req.getFacultad());
        s.setDescripcion(req.getDescripcion());
        s.setTiempoHorasEstimadas(req.getTiempoHorasEstimadas());
        s.setResultado(req.getResultado());
        s.setObservaciones(req.getObservaciones());
        return repo.save(s);
    }

    public List<Servicio> listar() {
        return repo.findAll();
    }

    public Map<String, Object> obtenerEstadisticasJornada() {
        Map<String, Object> stats = new HashMap<>();
        
        // Indicadores básicos
        stats.put("totalServicios", repo.count());
        stats.put("totalBeneficiarios", benRepo.count());
        stats.put("totalRecursos", recursoRepo.findAll().stream()
                .mapToDouble(r -> r.getValor().doubleValue()).sum());
        
        // Indicadores estratégicos (usando las queries que definimos en los repositorios)
        stats.put("porFacultad", repo.obtenerConteoPorFacultad());
        stats.put("porEstudiante", repo.contarServiciosPorEstudiante());
        stats.put("porMunicipio", repo.contarServiciosPorMunicipio());
        stats.put("estadosCasos", casoRepo.contarPorEstado()); 
        
        return stats;
    }
}