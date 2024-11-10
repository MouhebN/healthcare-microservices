package esprit.microservices.Fiche;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/medicalRecords")
public class MedicalRecordController {

    @Autowired
    private MedicalRecordService service;

    @PostMapping
    public MedicalRecordEntity createRecord(@RequestBody MedicalRecordEntity record) {
        return service.save(record);
    }

    @GetMapping
    public List<MedicalRecordEntity> getAllRecords() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<MedicalRecordEntity> getRecordById(@PathVariable Long id) {
        return service.findById(id);
    }


    @PutMapping("/{id}")
    public MedicalRecordEntity updateRecord(@PathVariable Long id, @RequestBody MedicalRecordEntity updatedRecord) {
        Optional<MedicalRecordEntity> existingRecord = service.findById(id);
        if (existingRecord.isPresent()) {
            updatedRecord.setRecordId(id);
            return service.save(updatedRecord);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteRecord(@PathVariable Long id) {
        service.deleteById(id);
    }
}
