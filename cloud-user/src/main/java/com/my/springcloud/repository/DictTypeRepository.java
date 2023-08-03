package com.my.springcloud.repository;

import com.my.springcloud.entity.DictType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DictTypeRepository  extends JpaRepository<DictType, String> {
}
