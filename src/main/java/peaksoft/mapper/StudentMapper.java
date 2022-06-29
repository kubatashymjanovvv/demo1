package peaksoft.mapper;

import org.springframework.stereotype.Component;
import peaksoft.dto.request.StudentRequest;
import peaksoft.model.Student;

@Component
public class StudentMapper {
    public Student create(StudentRequest dto) {

        if(dto == null){
            return null;
        }

        Student student = new Student();

        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setEmail(dto.getEmail());
        student.setStudyFormat(dto.getStudyFormat());

        return student;
    }
}


