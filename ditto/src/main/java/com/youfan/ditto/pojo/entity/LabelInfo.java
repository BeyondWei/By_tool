package com.youfan.ditto.pojo.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "label_info")
public class LabelInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 标签主键
     */
    @Column(name = "label_uuid")
    private String labelUuid;

    /**
     * 标签名称
     */
    @Column(name = "label_name")
    private String labelName;

    /**
     * 标签说明
     */
    private String instructions;

    /**
     * 父级标签
     */
    @Column(name = "parent_uuid")
    private String parentUuid;

    /**
     * 标签类型
     */
    @Column(name = "label_type")
    private String labelType;

    /**
     * 创建时间
     */
    @Column(name = "gmt_create")
    private Date gmtCreate;

    /**
     * 更新时间
     */
    @Column(name = "gmt_modified")
    private Date gmtModified;

    /**
     * 0 正常  1删除
     */
    @Column(name = "is_delete")
    private Byte isDelete;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取标签主键
     *
     * @return label_uuid - 标签主键
     */
    public String getLabelUuid() {
        return labelUuid;
    }

    /**
     * 设置标签主键
     *
     * @param labelUuid 标签主键
     */
    public void setLabelUuid(String labelUuid) {
        this.labelUuid = labelUuid;
    }

    /**
     * 获取标签名称
     *
     * @return label_name - 标签名称
     */
    public String getLabelName() {
        return labelName;
    }

    /**
     * 设置标签名称
     *
     * @param labelName 标签名称
     */
    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    /**
     * 获取标签说明
     *
     * @return instructions - 标签说明
     */
    public String getInstructions() {
        return instructions;
    }

    /**
     * 设置标签说明
     *
     * @param instructions 标签说明
     */
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    /**
     * 获取父级标签
     *
     * @return parent_uuid - 父级标签
     */
    public String getParentUuid() {
        return parentUuid;
    }

    /**
     * 设置父级标签
     *
     * @param parentUuid 父级标签
     */
    public void setParentUuid(String parentUuid) {
        this.parentUuid = parentUuid;
    }

    /**
     * 获取标签类型
     *
     * @return label_type - 标签类型
     */
    public String getLabelType() {
        return labelType;
    }

    /**
     * 设置标签类型
     *
     * @param labelType 标签类型
     */
    public void setLabelType(String labelType) {
        this.labelType = labelType;
    }

    /**
     * 获取创建时间
     *
     * @return gmt_create - 创建时间
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * 设置创建时间
     *
     * @param gmtCreate 创建时间
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * 获取更新时间
     *
     * @return gmt_modified - 更新时间
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * 设置更新时间
     *
     * @param gmtModified 更新时间
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * 获取0 正常  1删除
     *
     * @return is_delete - 0 正常  1删除
     */
    public Byte getIsDelete() {
        return isDelete;
    }

    /**
     * 设置0 正常  1删除
     *
     * @param isDelete 0 正常  1删除
     */
    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }
}