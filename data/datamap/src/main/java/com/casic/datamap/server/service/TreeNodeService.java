package com.casic.datamap.server.service;

import com.casic.datamap.server.pmodel.TreeNode;

/**
 * <p>树接口</p>
 * @author hourz
 * @since 2018-10-19
 */
public interface TreeNodeService {

	TreeNode getTreeOfYxt(TreeNode treeNode);
	
	TreeNode getTreeOfBzdm(TreeNode treeNode);
	
	TreeNode getTreeOfCjgf(TreeNode treeNode);
}
