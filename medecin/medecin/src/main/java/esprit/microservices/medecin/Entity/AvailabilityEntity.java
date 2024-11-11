package esprit.microservices.medecin.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "availability")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class AvailabilityEntity {
    @Id
    private UUID id;
    @NonNull
    private LocalDate availableDate;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "availability_id")
    private List<TimeSlotEntity> timeSlots;
}
