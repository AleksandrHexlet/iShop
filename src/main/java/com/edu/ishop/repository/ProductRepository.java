package com.edu.ishop.repository;

import com.edu.ishop.entity.Category;
import com.edu.ishop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
    Category findByName(String name);
    Category findByUrl(String url_image);
}


//    Объявить репозиторий для сущности 'Товар', добавить необходимые методы
//        Сервисы и Контроллеры