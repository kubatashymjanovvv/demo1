package peaksoft.apis;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.GroupRequest;
import peaksoft.dto.response.Response;
import peaksoft.model.Group;
import peaksoft.service.GroupService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/group")
public class GroupApi {

    private final GroupService groupService;

    @PostMapping("/save/{courseId}")
    public Response saveGroup(@RequestBody GroupRequest group, @PathVariable("courseId") Long courseId) {
       return groupService.saveGroup(group,courseId);
    }

    @GetMapping("/getAll")
    public List<Group> findAllGroup() {
        return groupService.findAllGroup();
    }

    @GetMapping("/getById/{id}")
    public Group getById(@PathVariable("id") Long id) {
        return groupService.getById(id);
    }

    @PatchMapping("/update/{id}")
    public Response update(@RequestBody GroupRequest group, @PathVariable("id") Long id) {
        return groupService.updateById(id, group);
    }

    @DeleteMapping("/delete/{id}")
    public Response delete(@PathVariable("id") Long id) {
        return groupService.deleteById(id);
    }

}
