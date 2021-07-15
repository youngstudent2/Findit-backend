package cn.edu.nju.FindIt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.edu.nju.FindIt.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
