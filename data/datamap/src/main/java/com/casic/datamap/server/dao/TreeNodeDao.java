package com.casic.datamap.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import com.casic.datamap.server.dao.provider.TreeNodeProvider;
import com.casic.datamap.server.pmodel.TreeNode;

/**
 * <p>Description</p>
 * @author hourz
 * @since 2018-10-19
 */
@Mapper
public interface TreeNodeDao {

	/**
	 * <p>源系统 </P>
	 * @return 采集方式LIST
	 */
	@SelectProvider(method = "cjfs", type = TreeNodeProvider.class)
	List<TreeNode> getCjfs();
	
	/**
	 * <p>源系统 </P>
	 * @return 源系统LIST
	 */
	@SelectProvider(method = "yxt", type = TreeNodeProvider.class)
	List<TreeNode> getYxt(Integer parentId);
	
	/**
	 * <p>源系统表 </p>
	 * @return 源系统表LIST
	 */
	@SelectProvider(method = "yxtb", type = TreeNodeProvider.class)
	List<TreeNode> getYxtb(TreeNode treeNode);
	
	/**
	 * <p>标准代码主题</p>
	 * @param treeNode
	 * @return
	 */
	@SelectProvider(method = "bzdmzt", type = TreeNodeProvider.class)
	List<TreeNode> getBzdmzt();
	
	/**
	 * <p>标准代码主题表</p>
	 * @param treeNode 
	 * @return 
	 */
	@SelectProvider(method = "bzdmb", type = TreeNodeProvider.class)
	List<TreeNode> getBzdmb(TreeNode treeNode);
	
	@SelectProvider(method = "cjgflb", type = TreeNodeProvider.class)
	List<TreeNode> getCjgfSjl();
	
	
	@SelectProvider(method = "cjgfzt", type = TreeNodeProvider.class)
	List<TreeNode> getCjgfZt(Integer parentId);
	
	@SelectProvider(method = "cjgfb", type = TreeNodeProvider.class)
	List<TreeNode> getCjgfb(TreeNode treeNode);
}
