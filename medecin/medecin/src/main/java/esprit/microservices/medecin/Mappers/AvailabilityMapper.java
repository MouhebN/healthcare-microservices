package esprit.microservices.medecin.Mappers;

import esprit.microservices.medecin.DTO.AvailabilityDTO;
import esprit.microservices.medecin.Entity.AvailabilityEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        componentModel = "spring",
        uses = {TimeSlotMapper.class}
)public interface AvailabilityMapper {

    @Mapping(target = "id", expression = "java(UUID.randomUUID())")
    AvailabilityEntity toEntity(AvailabilityDTO dto);

    AvailabilityDTO toDTO(AvailabilityEntity entity);
}
