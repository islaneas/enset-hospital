package ma.enset.ensethospital;

import ma.enset.ensethospital.entities.Patient;
import ma.enset.ensethospital.repositories.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class EnsetHospitalApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnsetHospitalApplication.class, args);
    }

    @Bean
    CommandLineRunner start(PatientRepository patientRepository){
        return args -> {
            patientRepository.save(new Patient(null,"Asmae",new Date(),false,123));
            patientRepository.save(new Patient(null,"Nassima",new Date(),false,365));
            patientRepository.save(new Patient(null,"Youssef",new Date(),false,189));
            patientRepository.save(new Patient(null,"Meriem",new Date(),false,212));

        };
    }
}
