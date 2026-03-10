package com.example.campusManagement.repo;

import com.example.campusManagement.dto.StudentFilterDto;
import com.example.campusManagement.models.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class StudentFilterRepo {
    @Autowired
    EntityManager em;

    public List<Student> filterStudents(StudentFilterDto filter){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Student> query=cb.createQuery(Student.class);
        Root<Student> studentRoot=query.from(Student.class);

        List<Predicate>predicates=new ArrayList<>();
        Join<Student, Address> address=studentRoot.join("address",JoinType.LEFT);
        Join<Student, Club>club=studentRoot.join("clubs",JoinType.LEFT);
        Join<Student, Enrollment>enrollment=studentRoot.join("enrollments",JoinType.LEFT);
        if(StringUtils.hasText(filter.getStudentName())){
            predicates.add(cb.like(studentRoot.get("firstName"),"%"+ filter.getStudentName()+ "%"));
        }

        if (StringUtils.hasText(filter.getCity())){
            predicates.add(cb.like(address.get("city"),"%"+filter.getCity()+ "%"));
        }
        if(StringUtils.hasText(filter.getClubName())){

            predicates.add(cb.like(club.get("name"), "%" + filter.getClubName() + "%"));

        }
        if(StringUtils.hasText(filter.getCourseName())){
            Join<Enrollment, Course>course=enrollment.join("course");
            predicates.add(cb.equal(course.get("title"),"%"+filter.getCourseName().trim()+ "%"));
        }
        if (filter.getEnrollmentYear() != 0) {
            predicates.add(cb.equal(enrollment.get("enrollmentYear"), filter.getEnrollmentYear()));
        }
        if (!predicates.isEmpty()) {
            Predicate and = cb.and(predicates.toArray(new Predicate[0]));
            query.where(and);


        }

        TypedQuery<Student> finalQuery = em.createQuery(query);
        return finalQuery.getResultList();
    }
}
