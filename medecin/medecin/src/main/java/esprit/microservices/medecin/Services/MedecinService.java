package esprit.microservices.medecin.Services;


import esprit.microservices.medecin.DTO.CreateMedecinRequest;
import esprit.microservices.medecin.DTO.CreateMedecinResponse;
import esprit.microservices.medecin.Entity.MedecinEntity;
import esprit.microservices.medecin.Mappers.MedecinMapper;
import esprit.microservices.medecin.Repository.MedecinRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MedecinService {

    private final MedecinRepository medecinRepository;
    private final MedecinMapper medecinMapper;


    public MedecinService(MedecinRepository medecinRepository,MedecinMapper medecinMapper) {
        this.medecinRepository = medecinRepository;
        this.medecinMapper = medecinMapper;
    }

    public CreateMedecinResponse createMedecin(CreateMedecinRequest request) {
        MedecinEntity medecin = medecinMapper.toEntity(request);
        MedecinEntity savedMedecin = medecinRepository.save(medecin);
        return medecinMapper.toResponse(savedMedecin);
    }

    public Optional<CreateMedecinResponse> getMedecinById(UUID id) {
        return medecinRepository.findById(id)
                .map(medecinMapper::toResponse);
    }

    public List<CreateMedecinResponse> getAllMedecins() {
        return medecinRepository.findAll().stream()
                .map(medecinMapper::toResponse)
                .toList();
    }

    public CreateMedecinResponse updateMedecin(UUID id, CreateMedecinRequest request) {
        if (!medecinRepository.existsById(id)) {
            throw new RuntimeException("Medecin not found");
        }
        MedecinEntity updatedMedecin = medecinMapper.toEntity(request);
        updatedMedecin.setId(id);
        MedecinEntity savedMedecin = medecinRepository.save(updatedMedecin);
        return medecinMapper.toResponse(savedMedecin);
    }
}
