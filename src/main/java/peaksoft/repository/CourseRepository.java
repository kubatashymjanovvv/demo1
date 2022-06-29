package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("select case when count(s) > 0 then true else false end " +
            "from Course s where s.courseName = ?1")
    boolean existsByCourseName(String courseName);
}
