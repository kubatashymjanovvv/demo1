package peaksoft.dto.request;

import lombok.Getter;
import lombok.Setter;
import peaksoft.model.enums.StudyFormat;

@Getter
@Setter
public class StudentRequest {

    private String firstName;
    private String lastName;
    private String email;
    private StudyFormat studyFormat;
}
