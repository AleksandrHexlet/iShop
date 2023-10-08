package com.edu.ishop.helpers.repository;

import com.edu.ishop.helpers.entity.FeedBack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedBackRepository extends JpaRepository<FeedBack, Integer> {
    //в дженерике сущность с которой работает репозиторий и id сущности


}
