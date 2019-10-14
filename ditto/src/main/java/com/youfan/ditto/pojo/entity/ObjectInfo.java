package com.youfan.ditto.pojo.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "object_info")
public class ObjectInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 对象主键
     */
    @Column(name = "object_uuid")
    private String objectUuid;

    /**
     * 对象名称
     */
    @Column(name = "object_name")
    private String objectName;

    /**
     * 对象说明
     */
    private String instructions;

    /**
     * 对象标签类型
     */
    @Column(name = "object_type")
    private String objectType;

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
     * 获取对象主键
     *
     * @return object_uuid - 对象主键
     */
    public String getObjectUuid() {
        return objectUuid;
    }

    /**
     * 设置对象主键
     *
     * @param objectUuid 对象主键
     */
    public void setObjectUuid(String objectUuid) {
        this.objectUuid = objectUuid;
    }

    /**
     * 获取对象名称
     *
     * @return object_name - 对象名称
     */
    public String getObjectName() {
        return objectName;
    }

    /**
     * 设置对象名称
     *
     * @param objectName 对象名称
     */
    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    /**
     * 获取对象说明
     *
     * @return instructions - 对象说明
     */
    public String getInstructions() {
        return instructions;
    }

    /**
     * 设置对象说明
     *
     * @param instructions 对象说明
     */
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    /**
     * 获取对象标签类型
     *
     * @return object_type - 对象标签类型
     */
    public String getObjectType() {
        return objectType;
    }

    /**
     * 设置对象标签类型
     *
     * @param objectType 对象标签类型
     */
    public void setObjectType(String objectType) {
        this.objectType = objectType;
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