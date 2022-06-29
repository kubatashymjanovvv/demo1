package peaksoft.mapper;

import org.springframework.stereotype.Component;
import peaksoft.dto.request.GroupRequest;
import peaksoft.model.Group;

@Component
public class GroupMapper {

    public Group create(GroupRequest dto) {
        if(dto == null){
            return null;
        }

        Group group = new Group();

        group.setGroupName(dto.getGroupName());
        group.setDateOfStart(dto.getDateOfStart());
        group.setDateOfFinish(dto.getDateOfFinish());

        return group;
    }
}
