package peaksoft.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.GroupRequest;
import peaksoft.dto.response.Response;
import peaksoft.exseptions.NotFoundException;
import peaksoft.mapper.GroupMapper;
import peaksoft.model.Group;
import peaksoft.repository.GroupRepository;
import peaksoft.service.CourseService;
import peaksoft.service.GroupService;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static org.springframework.http.HttpStatus.*;

@Service
@AllArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;
    private final CourseService courseService;

    @Override
    public Response saveGroup(GroupRequest groupDto, Long id) {
        Group group = groupMapper.create(groupDto);
        group.setCourse(courseService.getById(id));
        Group saveGroup = groupRepository.save(group);
        return Response.builder()
                .httpStatus(CREATED)
                .message(String.format("Group with groupName = %s successfully registered",
                        saveGroup.getGroupName()))
                .build();
    }

    @Override
    public Response deleteById(Long id) {
        groupRepository.deleteById(id);
        String message = String.format("Group with id = %s has successfully deleted", id);
        return Response.builder()
                .httpStatus(OK)
                .message(message)
                .build();
    }

    @Override
    public Group getById(Long id) {
        return groupRepository.findById(id)
                .orElseThrow(() -> {
                    throw new NotFoundException(
                            String.format("group with id = %s does not exists", id)
                    );
                });
    }

    @Override
    public List<Group> findAllGroup() {
        return groupRepository.findAll();
    }

    @Override
    @Transactional
    public Response updateById(Long id, GroupRequest newGroup) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> {
                    throw new NotFoundException(
                            String.format("group with id = %s does not exists", id)
                    );
                });

        String groupName = group.getGroupName();
        String newGroupName = newGroup.getGroupName();
        if (!Objects.equals(groupName, newGroupName)) {
            group.setGroupName(newGroupName);
        }

        LocalDate dateOfStart = group.getDateOfStart();
        LocalDate newDateOfStart = newGroup.getDateOfStart();
        if (!Objects.equals(dateOfStart, newDateOfStart)) {
            group.setDateOfStart(newDateOfStart);
        }

        LocalDate dateOfFinish = group.getDateOfFinish();
        LocalDate newDateOfFinish = newGroup.getDateOfFinish();
        if (!Objects.equals(dateOfFinish, newDateOfFinish)) {
            group.setDateOfFinish(newDateOfFinish);
        }
        String message = String.format("Group with groupId = %s has successfully updated", id);
        return Response.builder()
                .httpStatus(RESET_CONTENT)
                .message(message)
                .build();
    }
}
