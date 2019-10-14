package com.youfan.ditto.pojo.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "process_rel_object")
public class ProcessRelObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 节点
     */
    @Column(name = "node_uuid")
    private String nodeUuid;

    /**
     * 配置主键
     */
    @Column(name = "configuration_info")
    private String configurationInfo;

    /**
     * 对象开始
     */
    @Column(name = "start_object_uuid")
    private String startObjectUuid;

    /**
     * 对象结束
     */
    @Column(name = "end_object_uuid")
    private String endObjectUuid;

    /**
     * 对象之间的关系
     */
    @Column(name = "relation_uuid")
    private String relationUuid;

    /**
     * 附加条件
     */
    private String addition;

    /**
     * 父级节点
     */
    @Column(name = "parent_node")
    private String parentNode;

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
     * 获取节点
     *
     * @return node_uuid - 节点
     */
    public String getNodeUuid() {
        return nodeUuid;
    }

    /**
     * 设置节点
     *
     * @param nodeUuid 节点
     */
    public void setNodeUuid(String nodeUuid) {
        this.nodeUuid = nodeUuid;
    }

    /**
     * 获取配置主键
     *
     * @return configuration_info - 配置主键
     */
    public String getConfigurationInfo() {
        return configurationInfo;
    }

    /**
     * 设置配置主键
     *
     * @param configurationInfo 配置主键
     */
    public void setConfigurationInfo(String configurationInfo) {
        this.configurationInfo = configurationInfo;
    }

    /**
     * 获取对象开始
     *
     * @return start_object_uuid - 对象开始
     */
    public String getStartObjectUuid() {
        return startObjectUuid;
    }

    /**
     * 设置对象开始
     *
     * @param startObjectUuid 对象开始
     */
    public void setStartObjectUuid(String startObjectUuid) {
        this.startObjectUuid = startObjectUuid;
    }

    /**
     * 获取对象结束
     *
     * @return end_object_uuid - 对象结束
     */
    public String getEndObjectUuid() {
        return endObjectUuid;
    }

    /**
     * 设置对象结束
     *
     * @param endObjectUuid 对象结束
     */
    public void setEndObjectUuid(String endObjectUuid) {
        this.endObjectUuid = endObjectUuid;
    }

    /**
     * 获取对象之间的关系
     *
     * @return relation_uuid - 对象之间的关系
     */
    public String getRelationUuid() {
        return relationUuid;
    }

    /**
     * 设置对象之间的关系
     *
     * @param relationUuid 对象之间的关系
     */
    public void setRelationUuid(String relationUuid) {
        this.relationUuid = relationUuid;
    }

    /**
     * 获取附加条件
     *
     * @return addition - 附加条件
     */
    public String getAddition() {
        return addition;
    }

    /**
     * 设置附加条件
     *
     * @param addition 附加条件
     */
    public void setAddition(String addition) {
        this.addition = addition;
    }

    /**
     * 获取父级节点
     *
     * @return parent_node - 父级节点
     */
    public String getParentNode() {
        return parentNode;
    }

    /**
     * 设置父级节点
     *
     * @param parentNode 父级节点
     */
    public void setParentNode(String parentNode) {
        this.parentNode = parentNode;
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