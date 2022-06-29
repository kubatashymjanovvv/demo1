package peaksoft.service;

import peaksoft.dto.request.GroupRequest;
import peaksoft.dto.response.Response;
import peaksoft.model.Group;

import java.util.List;

public interface GroupService {

    Response saveGroup(GroupRequest group, Long id);

    Response deleteById(Long id);

    Group getById(Long id);

    List<Group> findAllGroup();

    Response updateById(Long id, GroupRequest group);
}
