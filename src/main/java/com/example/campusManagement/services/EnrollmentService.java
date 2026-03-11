package com.example.campusManagement.services;

import com.example.campusManagement.dto.request.EnrollmentRequestDto;
import com.example.campusManagement.dto.response.EnrollmentResponseDto;
import com.example.campusManagement.models.Course;
import com.example.campusManagement.models.Enrollment;
import com.example.campusManagement.models.Student;
import com.example.campusManagement.repo.CourseRepo;
import com.example.campusManagement.repo.EnrollmentRepo;
import com.example.campusManagement.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnrollmentService extends BaseService<Enrollment, EnrollmentRepo> {

    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private CourseRepo courseRepo;
    public EnrollmentService(EnrollmentRepo repository) {
        super(repository);
    }

    public List<EnrollmentResponseDto> findAllDto() {
        return findAll().stream().map(this::toDto).collect(Collectors.toList());
    }
    public EnrollmentResponseDto findByIdDto(Long id) {
        return toDto(findById(id));
    }

    public EnrollmentResponseDto addStudentToCourse(EnrollmentRequestDto dto) {
        Student s=studentRepo.findById(dto.getStudentId()).orElseThrow(() -> new RuntimeException("Student not found"));

        Course c=courseRepo.findById(dto.getCourseId()).orElseThrow(() -> new RuntimeException("Course not found"));

        Enrollment e=new Enrollment(dto.getEnrollmentYear(), c, s);

        return toDto(save(e));
    }
    public EnrollmentResponseDto updateDto(Long id, EnrollmentRequestDto dto) {
        Enrollment enrollment = findById(id);
        if (enrollment == null) throw new RuntimeException("Enrollment not found");

        Student student = studentRepo.findById(dto.getStudentId()).orElseThrow();
        Course course = courseRepo.findById(dto.getCourseId()).orElseThrow();

        enrollment.setEnrollmentYear(dto.getEnrollmentYear());
        enrollment.setStudent(student);
        enrollment.setCourse(course);

        return toDto(save(enrollment));
    }
    private EnrollmentResponseDto toDto(Enrollment e) {
        return new EnrollmentResponseDto(
                e.getId(),
                e.getEnrollmentYear(),
                e.getStudent().getFirstName() + " " + e.getStudent().getLastName(),
                e.getCourse().getTitle()
        );
    }
}

