package peaksoft.service;

import peaksoft.dto.request.StudentRequest;
import peaksoft.dto.response.Response;
import peaksoft.model.Student;

import java.util.List;

public interface StudentService {
    Response saveStudent(StudentRequest student, Long id);

    Response deleteById(Long id);

    Student getById(Long id);

    List<Student> findAllStudent();

    Response updateById(Long id, StudentRequest student);
}
