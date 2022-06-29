package peaksoft.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Getter @Setter
@RequiredArgsConstructor
public class Company {

    @Id
    @GeneratedValue
            (strategy = GenerationType.SEQUENCE, generator = "company_gen")
    @SequenceGenerator
            (name = "company_gen", sequenceName = "company_seq", allocationSize = 1)
    private Long id;

    @NotBlank
    private String companyName;

    @NotBlank
    public String locatedCountry;

    @OneToMany(mappedBy = "company",
            cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    public List<Course> course;


}
