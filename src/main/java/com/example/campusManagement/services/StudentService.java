package com.example.campusManagement.services;

import com.example.campusManagement.dto.StudentFilterDto;
import com.example.campusManagement.dto.request.StudentRequestDto;
import com.example.campusManagement.dto.response.StudentResponseDto;
import com.example.campusManagement.models.Address;
import com.example.campusManagement.models.Club;
import com.example.campusManagement.models.Student;
import com.example.campusManagement.repo.ClubRepo;
import com.example.campusManagement.repo.StudentFilterRepo;
import com.example.campusManagement.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService extends BaseService<Student, StudentRepo> {

    @Autowired
    private StudentFilterRepo studentFilterRepo;
    @Autowired
    private ClubRepo clubRepo;

    public StudentService(StudentRepo repository) {
        super(repository);
    }


    public List<StudentResponseDto> findAllDto() {
        return findAll().stream().map(this::toDto).collect(Collectors.toList());
    }
    public StudentResponseDto create(StudentRequestDto dto) {
        Student student = new Student(dto.getFirstName(), dto.getLastName(), dto.getEmail(), dto.getFacultyNumber());
        Address address = new Address(dto.getStreet(), dto.getCity(), dto.getCountry());

        student.setAddress(address);
        address.setStudent(student);

        return toDto(save(student));
    }
    public StudentResponseDto findByIdDto(Long id) {
        return toDto(findById(id));
    }

    public List<StudentResponseDto>findStudents(StudentFilterDto filter){
        List<Student>students=studentFilterRepo.filterStudents(filter);
        return students.stream().map(this::toDto)
                .collect(Collectors.toList());
    }

    public StudentResponseDto updateDto(Long id, StudentRequestDto dto) {
        Student student = findById(id);
        if (student == null) throw new RuntimeException("Student not found");

        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setEmail(dto.getEmail());
        student.setFacultyNumber(dto.getFacultyNumber());

        if (student.getAddress() != null) {
            student.getAddress().setCity(dto.getCity());
            student.getAddress().setStreet(dto.getStreet());
            student.getAddress().setCountry(dto.getCountry());
        }

        return toDto(save(student));
    }


    public StudentResponseDto toDto(Student s){
        String city = (s.getAddress() != null) ? s.getAddress().getCity() : "No address provided";
        return new StudentResponseDto(s.getId(),s.getFirstName(),s.getLastName(),s.getEmail(),s.getFacultyNumber(),city,s.getClubs().stream().map(Club::getName).collect(Collectors.toList()));
    }

    public void addStudentToClub(Long studentId,Long clubId){
        Club c=clubRepo.findById(clubId).orElseThrow();
        Student s=getRepository().findById(studentId).orElseThrow();

        s.getClubs().add(c);
        c.getStudents().add(s);
        save(s);
    }

}