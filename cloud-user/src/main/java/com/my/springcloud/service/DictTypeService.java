package com.my.springcloud.service;

import com.my.springcloud.entity.DictType;
import com.my.springcloud.entity.DictValue;
import com.my.springcloud.repository.DictTypeRepository;
import com.my.springcloud.repository.DictValueRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DictTypeService {
    @Resource
    DictTypeRepository dictTypeRepository;
    @Resource
    DictValueRepository dictValueRepository;
    public DictType getDictType(String id) {
        return dictTypeRepository.findById(id).get();
    }

    public Page<DictValue> showSkillByLimit(String id, String number, String limit) {
        Pageable pageable = PageRequest.of(Integer.parseInt(number),Integer.parseInt(limit));
        return dictValueRepository.findByDictTypeId(id, pageable);
    }
    public List<DictValue> showAllSkill(String id) {
        Sort sort = new Sort(Sort.Direction.ASC, "label");
        return dictValueRepository.findByDictTypeId(id, sort);
    }
}
