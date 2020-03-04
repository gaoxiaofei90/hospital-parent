package com.llw.hospital.bs.common.util;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;

public class TreeBuilder {

	@SuppressWarnings("unchecked")
	public List<MenuNode> buildListToTree(List<MenuNode> dirs) {
		List<MenuNode> roots = findRoots(dirs);
		List<MenuNode> notRoots = (List<MenuNode>) CollectionUtils.subtract(dirs, roots);
		for (MenuNode root : roots) {
			List<MenuNode> child = findChildren(root, notRoots);
			root.setChildren(child);
		}
		return roots;
	}

	private List<MenuNode> findRoots(List<MenuNode> allNodes) {
		List<MenuNode> results = new ArrayList<MenuNode>();
		for (MenuNode node : allNodes) {
			boolean isRoot = true;
			for (MenuNode comparedOne : allNodes) {
				if (node.getPid().equals(comparedOne.getId())) {
					isRoot = false;
					break;
				}
			}
			if (isRoot) {
				results.add(node);
				node.setTopid(node.getId());
			}
		}
		return results;
	}

	@SuppressWarnings("unchecked")
	private List<MenuNode> findChildren(MenuNode root, List<MenuNode> allNodes) {
		List<MenuNode> children = new ArrayList<MenuNode>();

		for (MenuNode comparedOne : allNodes) {
			if (comparedOne.getPid().equals(root.getId())) {
				children.add(comparedOne);
			}
		}

		List<MenuNode> notChildren = (List<MenuNode>) CollectionUtils.subtract(allNodes, children);
		for (MenuNode child : children) {
			List<MenuNode> tmpChildren = findChildren(child, notChildren);
			child.setChildren(tmpChildren);
		}
		return children;
	}
	
	
	
	
	/**
	 * @function treeSelect 树形下拉选择器树生成
	 * @param dirs
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TreeSelectNode> buildListToTreeSelect(List<TreeSelectNode> list) {
		List<TreeSelectNode> roots = findTreeSelectRoots(list);
		List<TreeSelectNode> notRoots = (List<TreeSelectNode>) CollectionUtils.subtract(list, roots);
		for (TreeSelectNode root : roots) {
			List<TreeSelectNode> child = findTreeSelectChildren(root, notRoots);
			root.setChildren(child);
		}
		return roots;
	}

	/**
	 * @function treeSelect 树形下拉选择器树生成查询根节点
	 * @param allNodes
	 * @return
	 */
	private List<TreeSelectNode> findTreeSelectRoots(List<TreeSelectNode> allNodes) {
		List<TreeSelectNode> results = new ArrayList<TreeSelectNode>();
		for (TreeSelectNode node : allNodes) {
			boolean isRoot = true;
			for (TreeSelectNode comparedOne : allNodes) {
				if (node.getPid().equals(comparedOne.getId())) {
					isRoot = false;
					break;
				}
			}
			if (isRoot) {
				results.add(node);
			}
		}
		return results;
	}

	/**
	 * @function treeSelect 树形下拉选择器树生成查询子节点
	 * @param root
	 * @param allNodes
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<TreeSelectNode> findTreeSelectChildren(TreeSelectNode root, List<TreeSelectNode> allNodes) {
		List<TreeSelectNode> children = new ArrayList<TreeSelectNode>();

		for (TreeSelectNode comparedOne : allNodes) {
			if (comparedOne.getPid().equals(root.getId())) {
				children.add(comparedOne);
			}
		}

		List<TreeSelectNode> notChildren = (List<TreeSelectNode>) CollectionUtils.subtract(allNodes, children);
		for (TreeSelectNode child : children) {
			List<TreeSelectNode> tmpChildren = findTreeSelectChildren(child, notChildren);
			child.setChildren(tmpChildren);
		}
		return children;
	}


}