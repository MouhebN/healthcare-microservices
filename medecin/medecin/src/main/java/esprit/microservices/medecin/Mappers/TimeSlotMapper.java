package esprit.microservices.medecin.Mappers;

import esprit.microservices.medecin.DTO.TimeSlotDTO;
import esprit.microservices.medecin.Entity.TimeSlotEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, componentModel = "spring")
public interface TimeSlotMapper {
    @Mapping(target = "id", expression = "java(dto.id() != null ? dto.id() : UUID.randomUUID())")
    TimeSlotEntity toEntity(TimeSlotDTO dto);

    TimeSlotDTO toDTO(TimeSlotEntity entity);
}