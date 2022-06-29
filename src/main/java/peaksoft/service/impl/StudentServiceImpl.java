package peaksoft.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.StudentRequest;
import peaksoft.dto.response.Response;
import peaksoft.exseptions.BadRequestException;
import peaksoft.exseptions.NotFoundException;
import peaksoft.mapper.StudentMapper;
import peaksoft.model.Student;
import peaksoft.model.enums.StudyFormat;
import peaksoft.repository.StudentRepository;
import peaksoft.service.GroupService;
import peaksoft.service.StudentService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final GroupService groupService;

    @Override
    public Response saveStudent(StudentRequest studentDto, Long id) {
        String email = studentDto.getEmail();
        Optional<Student> studentOptional = studentRepository.findByEmail(email);
        if (studentOptional.isPresent()){
            throw new BadRequestException(
                    String.format("Student with  Email = %s already exists", email)
            );
        }
        Student student = studentMapper.create(studentDto);
        student.setGroups(groupService.getById(id));
        Student saveStudent = studentRepository.save(student);
        return Response.builder()
                .httpStatus(CREATED)
                .message(String.format("Student with Email = %s successfully registered", saveStudent.getEmail()))
                .build();
    }

    @Override
    public Response deleteById(Long id) {
        studentRepository.deleteById(id);
        String message = String.format("Student with id = %s has successfully deleted", id);
        return Response.builder()
                .httpStatus(OK)
                .message(message)
                .build();
    }

    @Override
    public Student getById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> {
                    throw new NotFoundException(
                            String.format("student with id = %s does not exists", id)
                    );
                });
    }

    @Override
    public List<Student> findAllStudent() {
        return studentRepository.findAll();
    }

    @Override
    @Transactional
    public Response updateById(Long id, StudentRequest newStudent) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> {
                    throw new NotFoundException(
                            String.format("student with id = %s does not exists", id)
                    );
                });

        String studentFirstName = student.getFirstName();
        String newFirstName = newStudent.getFirstName();
        if (!Objects.equals(studentFirstName, newFirstName)) {
            student.setFirstName(newFirstName);
        }

        String lastName = student.getLastName();
        String newLastName = newStudent.getLastName();
        if (!Objects.equals(lastName, newLastName)) {
            student.setLastName(newLastName);
        }

        String email = student.getEmail();
        String newEmail = newStudent.getEmail();
        if (!Objects.equals(email, newEmail)) {
            student.setEmail(newEmail);
        }

        StudyFormat studyFormat = student.getStudyFormat();
        StudyFormat newStudyFormat = newStudent.getStudyFormat();
        if (!Objects.equals(studyFormat, newStudyFormat)) {
            student.setStudyFormat(newStudyFormat);
        }

        String message = String.format("Student with studentId = %s has successfully updated", id);
        return Response.builder()
                .httpStatus(RESET_CONTENT)
                .message(message)
                .build();
    }
}
