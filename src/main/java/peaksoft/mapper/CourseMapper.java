package peaksoft.mapper;

import org.springframework.stereotype.Component;
import peaksoft.dto.request.CourseRequest;
import peaksoft.model.Course;

@Component
public class CourseMapper {

    public Course create(CourseRequest dto) {
        if(dto == null){
            return null;
        }
        Course course = new Course();
        course.setCourseName(dto.getCourseName());
        course.setDuration(dto.getDuration());
        return course;
    }
}
