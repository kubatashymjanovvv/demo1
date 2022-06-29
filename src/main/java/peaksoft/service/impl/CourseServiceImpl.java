package peaksoft.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.CourseRequest;
import peaksoft.dto.response.Response;
import peaksoft.exseptions.BadRequestException;
import peaksoft.exseptions.NotFoundException;
import peaksoft.mapper.CourseMapper;
import peaksoft.model.Course;
import peaksoft.repository.CourseRepository;
import peaksoft.service.CompanyService;
import peaksoft.service.CourseService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

import static org.springframework.http.HttpStatus.*;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CompanyService companyService;
    private final CourseMapper courseMapper;

    @Override
    public Response saveCourse(CourseRequest courseDto, Long id) {
        String courseName = courseDto.getCourseName();
        checkCourseName(courseName);
        Course course = courseMapper.create(courseDto);
        course.setCompany(companyService.getById(id));
        Course saveCourse = courseRepository.save(course);
        return Response.builder()
                .httpStatus(CREATED)
                .message(String.format("course with courseName = %s successfully registered",
                        saveCourse.getCourseName()))
                .build();
    }

    private void checkCourseName(String courseName) {
        boolean exists = courseRepository.existsByCourseName(courseName);
        if (exists) {
            throw new BadRequestException(
                    "course with courseName = " + courseName + " already exists"
            );
        }
    }

    @Override
    public Response deleteById(Long id) {
        courseRepository.deleteById(id);
        String message = String.format("Course with id = %s has successfully deleted", id);
        return Response.builder()
                .httpStatus(OK)
                .message(message)
                .build();
    }

    @Override
    public Course getById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> {
                    throw new NotFoundException(
                            String.format("course with id = %s does not exists", id)
                    );
                });
    }

    @Override
    public List<Course> findAllCourse() {
        return courseRepository.findAll();
    }

    @Override
    @Transactional
    public Response updateById(Long id, CourseRequest newCourse) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> {
                    throw new NotFoundException(
                            String.format("course with id = %s does not exists", id)
                    );
                });

        String courseName = course.getCourseName();
        String newCourseName = newCourse.getCourseName();
        if (!Objects.equals(courseName, newCourseName)) {
            course.setCourseName(newCourseName);
        }

        String duration = course.getDuration();
        String newDuration = newCourse.getDuration();
        if (!Objects.equals(duration, newDuration)) {
            course.setDuration(newDuration);
        }

        String message = String.format("Course with companyId = %s has successfully updated", id);
        return Response.builder()
                .httpStatus(RESET_CONTENT)
                .message(message)
                .build();
    }
}
