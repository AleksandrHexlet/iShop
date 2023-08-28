package com.edu.ishop.repository;

import com.edu.ishop.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
    Category findByName(String name);
    Category findByUrl(String url);
}

// TODO 1)  В инете найди схему БД интернет магазина
//      2)  Создай Entity Product( по аналогии с категорией), потом создай репозиторий
//      для продуктов.
//      3) В сервисах создай продуктСервис( по аналогии с категорияСервис ) и наполни БД.
//      Сначала продукты добавь в List а потом через saveAll сохрани в БД ( как предавать лист в saveAll
//      можно посмотреть в JavaDoc