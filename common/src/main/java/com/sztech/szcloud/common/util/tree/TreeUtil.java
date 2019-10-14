package com.sztech.szcloud.common.util.tree;

import com.sztech.szcloud.common.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 操作树的工具
 */
public class TreeUtil {

    /**
     * 生成树
     *
     * @param list
     * @return
     */
    public static <T> List<TreeNode<T>> getTree(List<TreeNode<T>> list) {
        List<TreeNode<T>> treeList = new ArrayList<>();
        //找到根节点
        List<TreeNode<T>> tList = list.stream().filter(item -> StringUtils.isEmpty(item.getParent())).collect(Collectors.toList());
        if (tList.size() == 0) {
            throw new BusinessException("33", "没有找到根节点");
        }
        for (TreeNode<T> root : tList) {
            root.setRoot(root.getMe());
            TreeNode<T> treeNode = root.setChildrens(addChildren(list, root));
            treeList.add(treeNode);
        }
        return treeList;
    }

    private static <T> List<TreeNode<T>> addChildren(List<TreeNode<T>> list, TreeNode<T> root) {
        List<TreeNode<T>> treeNodeList = new ArrayList<>();
        for (TreeNode<T> node : list) {
            if (root.getMe().equals(node.getParent())) {
                node.setRoot(root.getRoot());
                node.setChildrens(addChildren(list, node));
                treeNodeList.add(node);
            }
        }
        return treeNodeList;
    }


}
