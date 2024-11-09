package esprit.microservices.medecin.Mappers;


import esprit.microservices.medecin.DTO.CreateMedecinRequest;
import esprit.microservices.medecin.DTO.CreateMedecinResponse;
import esprit.microservices.medecin.Entity.MedecinEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, componentModel = "spring")
public interface MedecinMapper {

    @Mapping(target = "id", expression = "java(UUID.randomUUID())")
    @Mapping(target = "availability", ignore = true)
    MedecinEntity toEntity(CreateMedecinRequest createMedecinRequest);

    CreateMedecinResponse toResponse(MedecinEntity medecin);
}