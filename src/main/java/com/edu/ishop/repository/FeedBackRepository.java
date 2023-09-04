package com.edu.ishop.repository;

import com.edu.ishop.entity.FeedBack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface FeedBackRepository extends JpaRepository<FeedBack, Integer> {
    //в дженерике сущность с которой работает репозиторий и id сущности


}
