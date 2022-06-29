package peaksoft.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.TeacherRequest;
import peaksoft.dto.response.Response;
import peaksoft.exseptions.BadRequestException;
import peaksoft.exseptions.NotFoundException;
import peaksoft.mapper.TeacherMapper;
import peaksoft.model.Teacher;
import peaksoft.repository.TeacherRepository;
import peaksoft.service.CourseService;
import peaksoft.service.TeacherService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@Service
@AllArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final CourseService courseService;
    private final TeacherMapper teacherMapper;

    @Override
    public Response saveTeacher(TeacherRequest teacherDto, Long id) {
        String email = teacherDto.getEmail();
        Optional<Teacher> teacherOptional = teacherRepository.findByEmail(email);
        if (teacherOptional.isPresent()) {
            throw new BadRequestException(
                    String.format("Teacher with  Email = %s already exists", email)
            );
        }
        Teacher teacher = teacherMapper.createTeacher(teacherDto);
        teacher.setCourse(courseService.getById(id));
        Teacher saveTeacher = teacherRepository.save(teacher);
        return Response.builder()
                .httpStatus(CREATED)
                .message(String.format("Teacher with email = %s successfully registered",
                        saveTeacher.getEmail()))
                .build();
    }

    @Override
    public Response deleteById(Long id) {
        teacherRepository.deleteById(id);
        String message = String.format("Teacher with id = %s has successfully deleted", id);
        return Response.builder()
                .httpStatus(OK)
                .message(message)
                .build();
    }

    @Override
    public Teacher getById(Long id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> {
                    throw new NotFoundException(
                            String.format("teacher with id = %s does not exists", id)
                    );
                });
    }

    @Override
    public List<Teacher> findAllTeacher() {
        return teacherRepository.findAll();
    }

    @Override
    @Transactional
    public Response updateById(Long id, TeacherRequest newTeacher) {

        Teacher teacher = teacherRepository.getById(id);
            String firstName = teacher.getFirstName();
        String newFirstName = newTeacher.getFirstName();
        if (!Objects.equals(firstName, newFirstName)) {
            teacher.setFirstName(newFirstName);
        }

        String lastName = teacher.getLastName();
        String newLastName = newTeacher.getLastName();
        if (!Objects.equals(lastName, newLastName)) {
            teacher.setLastName(newLastName);
        }
        String email = teacher.getEmail();
        String newEmail = newTeacher.getEmail();
        if (!Objects.equals(email, newEmail)) {
            teacher.setEmail(newEmail);
        }

        String message = String.format("Teacher with teacherId = %s has successfully updated", id);
        return Response.builder()
                .httpStatus(RESET_CONTENT)
                .message(message)
                .build();
    }
}