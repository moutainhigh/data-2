package com.casic.datamap.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casic.datamap.server.dao.TreeNodeDao;
import com.casic.datamap.server.pmodel.TreeNode;
import com.casic.datamap.server.service.TreeNodeService;

/**
 * <p>树实现接口</p>
 * @author hourz
 * @since 2018-10-19
 */
@Service
public class TreeNodeServiceImpl implements TreeNodeService {

	// 注入源头数据、采集规范、标准代码树Dao层接口
	@Autowired
	TreeNodeDao treeNodeDao;
	// 源头数据树
	@Override
	public TreeNode getTreeOfYxt(TreeNode treeNode) {
		List<TreeNode> treeList = new ArrayList<TreeNode>();
		List<TreeNode> oldCjfs = getCjfs();
		for(int i=0; i<oldCjfs.size(); i++) {
			if(i == 0) {
				if(treeNode != null) {
					// 设置第一节点展开
					treeList.add(packCjfsFirst(oldCjfs.get(i), treeNode.getName()));
				} else {
					treeList.add(packCjfsFirst(oldCjfs.get(i), null));
				}
			} else {
				if(treeNode != null) {
					treeList.add(packCjfs(oldCjfs.get(i), treeNode.getName()));
				} else {
					treeList.add(packCjfs(oldCjfs.get(i), null));
				}
			}
		}
		return new TreeNode(0, "源数据资源", null, 
				"1", true, "open", null, treeList);
	}
	// 标准代码树
	@Override
	public TreeNode getTreeOfBzdm(TreeNode treeNode) {
		List<TreeNode> treeList = new ArrayList<TreeNode>();
		List<TreeNode> oldCjfs = getBzdmzt();
		for(int i=0; i<oldCjfs.size(); i++) {
			if(i == 0) {
				// 设置第一节点展开
				if(treeNode != null) {
					treeList.add(packBzdmztFirst(oldCjfs.get(i), treeNode.getName()));
				}else {
					treeList.add(packBzdmztFirst(oldCjfs.get(i), null));
				}
			} else {
				if(treeNode != null) {
					treeList.add(packBzdmzt(oldCjfs.get(i), treeNode.getName()));
				}else {
					treeList.add(packBzdmzt(oldCjfs.get(i), null));
				}
			}
		}
		return new TreeNode(0, "标准代码", null, 
				"1", true, "open", null, treeList);
	}
	// 采集规范树
	@Override
	public TreeNode getTreeOfCjgf(TreeNode treeNode) {
		List<TreeNode> treeList = new ArrayList<TreeNode>();
		List<TreeNode> oldCjgfSjls = getCjgfSjl();
		for(int i=0; i<oldCjgfSjls.size(); i++) {
			if(i == 0) {
				// 设置第一节点展开
				if(treeNode != null) {
					treeList.add(packCjgfSjlFirst(oldCjgfSjls.get(i), treeNode.getName()));
				}else {
					treeList.add(packCjgfSjlFirst(oldCjgfSjls.get(i), null));
				}
			} else {
				if(treeNode != null) {
					treeList.add(packCjgfSjl(oldCjgfSjls.get(i), treeNode.getName()));
				}else {
					treeList.add(packCjgfSjl(oldCjgfSjls.get(i), null));
				}
			}
		}
		return new TreeNode(0, "采集规范", null, 
				"1", true, "open", null, treeList);
	}
	
	// 封装采集方式
	private TreeNode packCjfs(TreeNode node, String yxtb) {
		return new TreeNode(node.getId(), node.getName(), node.getYwname(), 
				"1", true, "closed", node.getParentId(), childYxt(node, yxtb));
	}
	// 封装采集方式
	private TreeNode packCjfsFirst(TreeNode node, String yxtb) {
		return new TreeNode(node.getId(), node.getName(), node.getYwname(), 
				"1", true, "closed", node.getParentId(), childYxt(node, yxtb));
	}
	// 获取采集规范子节点
	private List<TreeNode> childYxt(TreeNode node, String yxtb) {
		List<TreeNode> listYxts = new ArrayList<TreeNode>();
		List<TreeNode> oldYxt=getYxt(node.getId());
		for(TreeNode nodeYxt: oldYxt) {
			listYxts.add(packYxt(nodeYxt, yxtb));
		}
		return listYxts;
	}
	// 获取源系统
	private List<TreeNode> getYxt(Integer parentId){
		return treeNodeDao.getYxt(parentId);
	}
	// 封装源系统表
	private TreeNode packYxt(TreeNode node, String yxtb) {
		// 设置源系统表查询条件
		TreeNode childYxtb = new TreeNode();
		childYxtb.setParentId(node.getId());
		childYxtb.setName(yxtb);
		return new TreeNode(node.getId(), node.getName(), node.getYwname(), 
				"1", true, "closed", node.getParentId(), childYxtb(childYxtb));
	}
	// 获取源系统子节点
	private List<TreeNode> childYxtb(TreeNode childYxtb) {
		List<TreeNode> listYxtbs = new ArrayList<TreeNode>();
		List<TreeNode> oldYxtbs = getYxtb(childYxtb);
		for(TreeNode node: oldYxtbs) {
			listYxtbs.add(packYxtb(node));
		}
		return listYxtbs;
	}
	// 封装源系统表 
	private TreeNode packYxtb(TreeNode node) {
		return new TreeNode(node.getId(), node.getName(), node.getYwname(), 
				"1", true, "closed", node.getParentId(), null);
	}
	// 获取源系统表
	private List<TreeNode> getYxtb(TreeNode node) {
		return treeNodeDao.getYxtb(node);
	}
	// 获取采集方式
	private List<TreeNode> getCjfs(){
		return treeNodeDao.getCjfs();
	}
	
	// 获取标准代码主题
	private List<TreeNode> getBzdmzt() {
		return treeNodeDao.getBzdmzt();
	}
	// 封装标准代码主题
	private TreeNode packBzdmzt(TreeNode node, String bzdmb) {
		// 封装标准代码主题条件
		TreeNode childBzdmb = new TreeNode();
		childBzdmb.setParentId(node.getId());
		childBzdmb.setName(bzdmb);
		return new TreeNode(node.getId(), node.getName(), node.getYwname(), 
			"1", true, "closed", node.getParentId(), childBzdmb(childBzdmb));
	}

	// 封装标准代码主题
	private TreeNode packBzdmztFirst(TreeNode node, String bzdmb) {
		// 封装标准代码主题表条件
		TreeNode childBzdmb = new TreeNode();
		childBzdmb.setParentId(node.getId());
		childBzdmb.setName(bzdmb);
		return new TreeNode(node.getId(), node.getName(), node.getYwname(), 
			"1", true, "closed", node.getParentId(), childBzdmb(childBzdmb));
	}
	// 获取标准代码表
	private Object childBzdmb(TreeNode childBzdmb) {
		List<TreeNode> listYxtbs = new ArrayList<TreeNode>();
		List<TreeNode> oldBzdmbs = getBzdmb(childBzdmb);
		for(TreeNode node: oldBzdmbs) {
			listYxtbs.add(packBzdmb(node));
		}
		return listYxtbs;
	}
	// 封装标准代码表
	private TreeNode packBzdmb(TreeNode node) {
		return  new TreeNode(node.getId(), node.getName(), node.getYwname(), 
				"1", true, "closed", node.getParentId(), null);
	}
	// 获取标准代码表
	private List<TreeNode> getBzdmb(TreeNode childBzdmb){
		return treeNodeDao.getBzdmb(childBzdmb);
	}
	
	// 封装采集规范
	private TreeNode packCjgfSjlFirst(TreeNode node, String cjgfb) {
		return new TreeNode(node.getId(), node.getName(), node.getYwname(), 
				"1", true, "open", node.getParentId(), childCjgfzt(node, cjgfb));
	}
	//  封装采集规范
	private TreeNode packCjgfSjl(TreeNode node, String cjgfb) {
		return new TreeNode(node.getId(), node.getName(), node.getYwname(), 
				"1", true, "closed", node.getParentId(), childCjgfzt(node, cjgfb));
	}
	// 获取采集规范
	private List<TreeNode> getCjgfSjl() {
		return treeNodeDao.getCjgfSjl();
	}
	// 查询采集规范主题
	private List<TreeNode> childCjgfzt(TreeNode node, String cjgfb) {
		List<TreeNode> treeList = new ArrayList<TreeNode>();
		List<TreeNode> oldCjgfzts = getCjgfzt(node.getParentId());
		for(int i=0; i<oldCjgfzts.size(); i++) {
			treeList.add(packCjgfzt(oldCjgfzts.get(i), cjgfb));
		}
		return treeList;
	}
	// 获取采集规范主题
	private List<TreeNode> getCjgfzt(Integer parentId) {
		return treeNodeDao.getCjgfZt(parentId);
	}
	// 封装采集规范主题
	private TreeNode packCjgfzt(TreeNode node, String cjgfb) {
		return new TreeNode(node.getId(), node.getName(), node.getYwname(), 
				"1", true, "open", node.getParentId(), childCjgfb(node, cjgfb));
	}
	// 查询采集规范表
	private List<TreeNode> childCjgfb(TreeNode node, String cjgfb) {
		List<TreeNode> treeList = new ArrayList<TreeNode>();
		TreeNode childCjgfb = new TreeNode();
		childCjgfb.setParentId(node.getId());
		childCjgfb.setName(cjgfb);
		List<TreeNode> oldCjgfbs = getCjgfb(node);
		for(int i=0; i<oldCjgfbs.size(); i++) {
			treeList.add(packCjgfb(oldCjgfbs.get(i), cjgfb));
		}
		return treeList;
	}
	// 获取采集规范表
	private List<TreeNode> getCjgfb(TreeNode node) {
		return treeNodeDao.getCjgfb(node);
	}
	// 封装采集规范表
	private TreeNode packCjgfb(TreeNode node, String cjgfb) {
		return new TreeNode(node.getId(), node.getName(), node.getYwname(), 
				"1", true, "open", node.getParentId(), null);
	}
}
