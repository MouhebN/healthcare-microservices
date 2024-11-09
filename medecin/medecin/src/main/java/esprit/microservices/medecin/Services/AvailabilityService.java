package esprit.microservices.medecin.Services;


import esprit.microservices.medecin.DTO.AvailabilityDTO;
import esprit.microservices.medecin.DTO.TimeSlotDTO;
import esprit.microservices.medecin.Entity.AvailabilityEntity;
import esprit.microservices.medecin.Entity.MedecinEntity;
import esprit.microservices.medecin.Entity.TimeSlotEntity;
import esprit.microservices.medecin.Mappers.AvailabilityMapper;
import esprit.microservices.medecin.Mappers.TimeSlotMapper;
import esprit.microservices.medecin.Repository.AvailabilityRepository;

import esprit.microservices.medecin.Repository.MedecinRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class AvailabilityService {

    private final AvailabilityRepository availabilityRepository;
    private final MedecinRepository medecinRepository;
    private final AvailabilityMapper availabilityMapper;
    private final TimeSlotMapper timeSlotMapper;



    public AvailabilityService(AvailabilityRepository availabilityRepository, MedecinRepository medecinRepository, AvailabilityMapper availabilityMapper, TimeSlotMapper timeSlotMapper) {
        this.availabilityRepository = availabilityRepository;
        this.medecinRepository = medecinRepository;
        this.availabilityMapper = availabilityMapper;
        this.timeSlotMapper = timeSlotMapper;
    }

    public AvailabilityDTO createAvailability(UUID medecinId, AvailabilityDTO availabilityDTO) {
        Optional<MedecinEntity> medecin = medecinRepository.findById(medecinId);

        if (medecin.isPresent()) {
            AvailabilityEntity availabilityEntity = availabilityMapper.toEntity(availabilityDTO);
            availabilityEntity = availabilityRepository.save(availabilityEntity);

            // Add the new availability to the Medecin's availability list
            MedecinEntity medecinEntity = medecin.get();
            medecinEntity.getAvailability().add(availabilityEntity);
            medecinRepository.save(medecinEntity);  // Save the updated MedecinEntity

            return availabilityMapper.toDTO(availabilityEntity);
        } else {
            throw new IllegalArgumentException("Medecin with ID " + medecinId + " not found.");
        }
    }

    public List<AvailabilityDTO> getAvailabilityForMedecin(UUID medecinId) {
        Optional<MedecinEntity> medecin = medecinRepository.findById(medecinId);

        if (medecin.isPresent()) {
            List<AvailabilityEntity> availabilityList = medecin.get().getAvailability();
            return availabilityList.stream()
                    .map(availabilityMapper::toDTO)
                    .toList();
        } else {
            throw new IllegalArgumentException("Medecin with ID " + medecinId + " not found.");
        }
    }

    public void deleteAvailability(UUID availabilityId) {
        Optional<AvailabilityEntity> existingAvailability = availabilityRepository.findById(availabilityId);

        if (existingAvailability.isPresent()) {
            availabilityRepository.delete(existingAvailability.get());
        } else {
            throw new IllegalArgumentException("Availability with ID " + availabilityId + " not found.");
        }
    }

    public AvailabilityDTO updateAvailability(UUID id, AvailabilityDTO availabilityDTO) {
        AvailabilityEntity existingAvailability = availabilityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Availability not found with id: " + id));


        existingAvailability.setDayOfWeek(availabilityDTO.dayOfWeek());

        updateTimeSlots(existingAvailability, availabilityDTO.timeSlots());

        AvailabilityEntity savedAvailability = availabilityRepository.save(existingAvailability);
        return availabilityMapper.toDTO(savedAvailability);
    }

    private void updateTimeSlots(AvailabilityEntity availability, List<TimeSlotDTO> newTimeSlots) {
        if (newTimeSlots == null) {
            availability.getTimeSlots().clear();
            return;
        }

        List<TimeSlotEntity> updatedTimeSlots = newTimeSlots.stream()
                .map(timeSlotMapper::toEntity)
                .sorted(this::compareTimeSlots)
                .collect(Collectors.toList());

        // Validate time slots don't overlap
        validateTimeSlots(updatedTimeSlots);

        // Clear and set new time slots
        availability.getTimeSlots().clear();
        availability.getTimeSlots().addAll(updatedTimeSlots);
    }

    private int compareTimeSlots(TimeSlotEntity ts1, TimeSlotEntity ts2) {
        return LocalTime.parse(ts1.getStartTime())
                .compareTo(LocalTime.parse(ts2.getStartTime()));
    }

    private void validateTimeSlots(List<TimeSlotEntity> timeSlots) {
        if (timeSlots.isEmpty()) {
            return;
        }

        // Validate each time slot's time format and range
        timeSlots.forEach(this::validateTimeSlot);

        // Validate no overlaps
        for (int i = 0; i < timeSlots.size() - 1; i++) {
            TimeSlotEntity current = timeSlots.get(i);
            TimeSlotEntity next = timeSlots.get(i + 1);

            LocalTime currentEnd = LocalTime.parse(current.getEndTime());
            LocalTime nextStart = LocalTime.parse(next.getStartTime());

            if (!currentEnd.isBefore(nextStart)) {
                throw new IllegalArgumentException(String.format(
                        "Time slots cannot overlap: %s-%s overlaps with %s-%s",
                        current.getStartTime(), current.getEndTime(),
                        next.getStartTime(), next.getEndTime()
                ));
            }
        }
    }

    private void validateTimeSlot(TimeSlotEntity timeSlot) {
        try {
            LocalTime start = LocalTime.parse(timeSlot.getStartTime());
            LocalTime end = LocalTime.parse(timeSlot.getEndTime());

            if (!start.isBefore(end)) {
                throw new IllegalArgumentException(
                        "End time must be after start time: " +
                                timeSlot.getStartTime() + " - " + timeSlot.getEndTime()
                );
            }

            // Optional: Add business hours validation
            LocalTime businessStart = LocalTime.of(8, 0);
            LocalTime businessEnd = LocalTime.of(18, 0);

            if (start.isBefore(businessStart) || end.isAfter(businessEnd)) {
                throw new IllegalArgumentException(
                        "Time slot must be within business hours (08:00-18:00)"
                );
            }

        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(
                    "Invalid time format. Use HH:mm format: " +
                            timeSlot.getStartTime() + " - " + timeSlot.getEndTime()
            );
        }
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }
}