package peaksoft.apis;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.CourseRequest;
import peaksoft.dto.response.Response;
import peaksoft.model.Course;
import peaksoft.service.CourseService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/course")
public class CourseApi {

    private final CourseService courseService;

    @GetMapping("/getAll")
    public List<Course> findAllCourse() {
        return courseService.findAllCourse();
    }

    @GetMapping("/getById/{id}")
    public Course getById(@PathVariable("id") Long id) {
        return courseService.getById(id);
    }

    @PostMapping("/save/{companyId}")
    public Response save(@RequestBody CourseRequest course,
                         @PathVariable("companyId")Long companyId) {
        return courseService.saveCourse(course,companyId);
    }

    @PatchMapping("/update/{id}")
    public Response update(@RequestBody CourseRequest course,
                           @PathVariable("id") Long id) {
        return courseService.updateById(id, course);
    }

    @DeleteMapping("/delete/{id}")
    public Response delete(@PathVariable("id") Long id) {
        return courseService.deleteById(id);
    }

}
