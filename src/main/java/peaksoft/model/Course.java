package peaksoft.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@RequiredArgsConstructor
public class  Course {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_gen")
    @SequenceGenerator(
            name = "course_gen",
            sequenceName = "course_seq",
            allocationSize = 1)
    private Long id;

    @NotBlank
    private String courseName;
    @NotBlank
    private String duration;

    @ManyToOne
    @JsonIgnore
    private Company company;

    @ManyToMany(mappedBy = "courseList",
            cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private List<Group> groupList;

    @OneToOne(mappedBy = "course",
            cascade = CascadeType.REMOVE)
    private Teacher teacher;

    @JsonIgnore
    public void setGroup(Group group) {
        if (groupList == null) {
            groupList = new ArrayList<>();
        }
        groupList.add(group);
    }
}
