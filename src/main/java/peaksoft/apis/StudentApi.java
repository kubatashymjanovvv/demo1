package peaksoft.apis;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.StudentRequest;
import peaksoft.dto.response.Response;
import peaksoft.model.Student;
import peaksoft.service.GroupService;
import peaksoft.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/student")
@AllArgsConstructor
public class StudentApi {

    private final StudentService studentService;
    private final GroupService groupService;

    @PostMapping("/save/{groupId}")
    public Response saveGroup(@RequestBody StudentRequest student, @PathVariable("groupId")Long groupId){
        return studentService.saveStudent(student,groupId);
    }

    @GetMapping("/getAll")
    public List<Student> findAllStudent(){
        return studentService.findAllStudent();
    }

    @GetMapping("/getById/{id}")
    public Student getById(@PathVariable("id")Long id){
        return studentService.getById(id);
    }

    @PatchMapping("/update/{id}")
    public Response update(@RequestBody StudentRequest student, @PathVariable("id") Long id) {
        return studentService.updateById(id, student);
    }

    @DeleteMapping("/delete/{id}")
    public Response delete(@PathVariable("id") Long id) {
        return studentService.deleteById(id);
    }


}
