package com.my.springcloud.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Table(name="tb_dict_value")
@Entity
@Data
public class DictValue {

    /**主键ID**/
    @Id
    @Column(length=60)
    private String id;

    private String label;		// 标签名
    private String value;		// 键值
    private String sort;		// 排序

    /**创建者**/
    private String create_by;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="dict_type_id", referencedColumnName = "id")
    private DictType dictType;

    public DictValue(){

    }

}
