package esprit.microservices.medecin.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
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

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "availability_id")
    private List<TimeSlotEntity> timeSlots;
}
