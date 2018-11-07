package com.casic.datamap.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.casic.datamap.server.pmodel.TreeNode;
import com.casic.datamap.server.service.TreeNodeService;
import com.casic27.common.utils.JResult;

/**
 * <p>映射接口树http接口</p>
 * @author hourz
 * @since 2018-10-19
 */
@RestController
@RequestMapping("tree")
public class TreeNodeController {
	
	@Autowired
	TreeNodeService treeNodeService;
	
	@RequestMapping(value="/yxt", method=RequestMethod.POST)
	public JResult getTreeOfYxt(@RequestBody(required=false)TreeNode treeNode){
		return new JResult(0, "", treeNodeService.getTreeOfYxt(treeNode));
	}
	
	@RequestMapping(value="/bzdm", method=RequestMethod.POST)
	public JResult getTreeOfBzdm(@RequestBody(required=false)TreeNode treeNode){
		return new JResult(0, "", treeNodeService.getTreeOfBzdm(treeNode));
	}
	
	@RequestMapping(value="/cjgf", method=RequestMethod.POST)
	public JResult getTreeOfCjgf(@RequestBody(required=false)TreeNode treeNode){
		return new JResult(0, "", treeNodeService.getTreeOfCjgf(treeNode));
	}
}
