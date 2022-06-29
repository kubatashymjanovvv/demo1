package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.model.Role;


public interface RoleRepository extends JpaRepository<Role, Long> {


}