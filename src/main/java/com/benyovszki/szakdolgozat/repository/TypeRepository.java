package com.benyovszki.szakdolgozat.repository;

import com.benyovszki.szakdolgozat.model.Type;
import com.benyovszki.szakdolgozat.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {
}
