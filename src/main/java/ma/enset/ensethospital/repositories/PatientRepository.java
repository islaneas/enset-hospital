package ma.enset.ensethospital.repositories;

import ma.enset.ensethospital.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,Long> {
    public Page<Patient> findByNomContains(String kw, Pageable pageable);

}
