package peaksoft.apis;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.TeacherRequest;
import peaksoft.dto.response.Response;
import peaksoft.model.Teacher;
import peaksoft.service.TeacherService;

import java.util.List;

@RestController
@RequestMapping("/api/teacher")
@AllArgsConstructor
public class  TeacherApi {

    private final TeacherService teacherService;

    @PostMapping("/save/{teacherId}")
    public Response saveGroup(@RequestBody TeacherRequest teacher, @PathVariable("teacherId")Long id){
        return teacherService.saveTeacher(teacher,id);
    }

    @GetMapping("/getAll")
    public List<Teacher> findAllTeacher(){
        return teacherService.findAllTeacher();
    }

    @GetMapping("/getById/{id}")
    public Teacher getById(@PathVariable("id")Long id){
        return teacherService.getById(id);
    }

    @PatchMapping("/update/{id}")
    public Response update(@RequestBody TeacherRequest teacher, @PathVariable("id") Long id) {
        return teacherService.updateById(id, teacher);
    }

    @DeleteMapping("/delete/{id}")
    public Response delete(@PathVariable("id") Long id) {
        return teacherService.deleteById(id);
    }

}
