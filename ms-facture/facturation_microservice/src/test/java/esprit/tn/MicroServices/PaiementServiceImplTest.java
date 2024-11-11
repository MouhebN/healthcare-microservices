package esprit.tn.MicroServices;

import esprit.microservices.facturation.Entity.Paiement;
import esprit.microservices.facturation.Repository.PaiementRepository;
import esprit.microservices.facturation.Services.PaiementServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class PaiementServiceImplTest {

    @Mock
    private PaiementRepository paiementRepository;

    @InjectMocks
    private PaiementServiceImpl paiementService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSavePaiement() {
        Paiement paiement = new Paiement();
        paiement.setMontant(100.0);

        when(paiementRepository.save(paiement)).thenReturn(paiement);

        Paiement result = paiementService.savePaiement(paiement);
        assertEquals(paiement, result);
        verify(paiementRepository, times(1)).save(paiement);
    }

    @Test
    void testGetPaiementById() {
        Long id = 1L;
        Paiement paiement = new Paiement();
        paiement.setId(id);

        when(paiementRepository.findById(id)).thenReturn(Optional.of(paiement));

        Paiement result = paiementService.getPaiementById(id);
        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(paiementRepository, times(1)).findById(id);
    }

    @Test
    void testGetPaiementById_NotFound() {
        Long id = 1L;
        when(paiementRepository.findById(id)).thenReturn(Optional.empty());

        Paiement result = paiementService.getPaiementById(id);
        assertNull(result);
        verify(paiementRepository, times(1)).findById(id);
    }

    @Test
    void testGetAllPaiements() {
        Paiement paiement1 = new Paiement();
        Paiement paiement2 = new Paiement();

        List<Paiement> paiements = Arrays.asList(paiement1, paiement2);

        when(paiementRepository.findAll()).thenReturn(paiements);

        List<Paiement> result = paiementService.getAllPaiements();
        assertEquals(2, result.size());
        verify(paiementRepository, times(1)).findAll();
    }

    @Test
    void testUpdatePaiement() {
        Long id = 1L;
        Paiement existingPaiement = new Paiement();
        existingPaiement.setId(id);
        existingPaiement.setMontant(100.0);

        Paiement updatedPaiement = new Paiement();
        updatedPaiement.setMontant(200.0);

        when(paiementRepository.findById(id)).thenReturn(Optional.of(existingPaiement));
        when(paiementRepository.save(existingPaiement)).thenReturn(existingPaiement);

        Paiement result = paiementService.updatePaiement(id, updatedPaiement);
        assertNotNull(result);
        assertEquals(200.0, result.getMontant());
        verify(paiementRepository, times(1)).findById(id);
        verify(paiementRepository, times(1)).save(existingPaiement);
    }

    @Test
    void testUpdatePaiement_NotFound() {
        Long id = 1L;
        Paiement updatedPaiement = new Paiement();
        updatedPaiement.setMontant(200.0);

        when(paiementRepository.findById(id)).thenReturn(Optional.empty());

        Paiement result = paiementService.updatePaiement(id, updatedPaiement);
        assertNull(result);
        verify(paiementRepository, times(1)).findById(id);
        verify(paiementRepository, never()).save(any(Paiement.class));
    }

    @Test
    void testDeletePaiement() {
        Long id = 1L;

        paiementService.deletePaiement(id);
        verify(paiementRepository, times(1)).deleteById(id);
    }
}