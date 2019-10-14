package com.youfan.ditto.pojo.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "configuration_info")
public class ConfigurationInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 配置主键
     */
    @Column(name = "configuration_uuid")
    private String configurationUuid;

    /**
     * 配置名称
     */
    @Column(name = "configuration_name")
    private String configurationName;

    /**
     * 功能说明
     */
    private String instructions;

    /**
     * 创建者
     */
    @Column(name = "user_uuid")
    private String userUuid;

    /**
     * 标签类型
     */
    @Column(name = "configuration_type")
    private String configurationType;

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
     * @return configuration_uuid - 配置主键
     */
    public String getConfigurationUuid() {
        return configurationUuid;
    }

    /**
     * 设置配置主键
     *
     * @param configurationUuid 配置主键
     */
    public void setConfigurationUuid(String configurationUuid) {
        this.configurationUuid = configurationUuid;
    }

    /**
     * 获取配置名称
     *
     * @return configuration_name - 配置名称
     */
    public String getConfigurationName() {
        return configurationName;
    }

    /**
     * 设置配置名称
     *
     * @param configurationName 配置名称
     */
    public void setConfigurationName(String configurationName) {
        this.configurationName = configurationName;
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
     * 获取创建者
     *
     * @return user_uuid - 创建者
     */
    public String getUserUuid() {
        return userUuid;
    }

    /**
     * 设置创建者
     *
     * @param userUuid 创建者
     */
    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    /**
     * 获取标签类型
     *
     * @return configuration_type - 标签类型
     */
    public String getConfigurationType() {
        return configurationType;
    }

    /**
     * 设置标签类型
     *
     * @param configurationType 标签类型
     */
    public void setConfigurationType(String configurationType) {
        this.configurationType = configurationType;
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