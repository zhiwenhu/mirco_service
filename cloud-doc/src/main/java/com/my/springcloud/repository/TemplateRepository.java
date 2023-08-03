package com.my.springcloud.repository;

import com.my.springcloud.entity.Template;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemplateRepository extends JpaRepository<Template,String> {
    public Page<Template> findByNameAndType(Pageable pageable, String name, Integer type);
}
