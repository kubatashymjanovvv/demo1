package peaksoft.mapper;

import org.springframework.stereotype.Component;
import peaksoft.dto.request.TeacherRequest;
import peaksoft.model.Teacher;

@Component
public class  TeacherMapper {

    public Teacher createTeacher(TeacherRequest dto) {

        if(dto == null){
            return null;
        }

        Teacher teacher = new Teacher();

        teacher.setFirstName(dto.getFirstName());
        teacher.setLastName(dto.getLastName());
        teacher.setEmail(dto.getEmail());

        return teacher;
    }
}
