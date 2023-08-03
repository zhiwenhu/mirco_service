package com.my.springcloud.repository;

import com.my.springcloud.entity.DictValue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DictValueRepository extends JpaRepository<DictValue, String> {
    Page<DictValue> findByDictTypeId(String id, Pageable pageable);
    List<DictValue> findByDictTypeId(String id, Sort sort);
}
