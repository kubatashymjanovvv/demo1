package peaksoft.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "groups")
@RequiredArgsConstructor
public class Group {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "group_gen")
    @SequenceGenerator(
            name = "group_gen",
            sequenceName = "group_seq",
            allocationSize = 1)
    private Long id;

    @NotBlank
    private String groupName;
    private LocalDate dateOfStart;
    private LocalDate dateOfFinish;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.MERGE)
    private List<Course> courseList;

    @OneToMany(mappedBy = "groups",
            cascade = CascadeType.REMOVE)
    private List<Student> students;

    @JsonIgnore
    public void setCourse(Course courseId) {
        if (courseList == null) {
            courseList = new ArrayList<>();
        }
        courseList.add(courseId);
        courseId.setGroup(this);
    }
}

