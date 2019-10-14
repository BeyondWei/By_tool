package com.youfan.ditto.pojo.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "relation_info")
public class RelationInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 配置主键
     */
    @Column(name = "relation_uuid")
    private String relationUuid;

    /**
     * 配置名称
     */
    @Column(name = "relation_name")
    private String relationName;

    /**
     * 功能说明
     */
    private String instructions;

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
     * 获取配置主键
     *
     * @return relation_uuid - 配置主键
     */
    public String getRelationUuid() {
        return relationUuid;
    }

    /**
     * 设置配置主键
     *
     * @param relationUuid 配置主键
     */
    public void setRelationUuid(String relationUuid) {
        this.relationUuid = relationUuid;
    }

    /**
     * 获取配置名称
     *
     * @return relation_name - 配置名称
     */
    public String getRelationName() {
        return relationName;
    }

    /**
     * 设置配置名称
     *
     * @param relationName 配置名称
     */
    public void setRelationName(String relationName) {
        this.relationName = relationName;
    }

    /**
     * 获取功能说明
     *
     * @return instructions - 功能说明
     */
    public String getInstructions() {
        return instructions;
    }

    /**
     * 设置功能说明
     *
     * @param instructions 功能说明
     */
    public void setInstructions(String instructions) {
        this.instructions = instructions;
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