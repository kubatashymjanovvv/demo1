package peaksoft.service;

import peaksoft.dto.request.TeacherRequest;
import peaksoft.dto.response.Response;
import peaksoft.model.Teacher;

import java.util.List;

public interface TeacherService {
    Response saveTeacher(TeacherRequest teacher, Long id);

    Response deleteById(Long id);

    Teacher getById(Long id);

    List<Teacher> findAllTeacher();

    Response updateById(Long id, TeacherRequest teacher);
}
