package com.casic.datamap.server.dao.provider;

import org.apache.ibatis.jdbc.SQL;

import com.casic.datamap.server.pmodel.TreeNode;

/**
 * <p>采集方式</p>
 * @author hourz
 * @since 2018-10-19
 */
public class TreeNodeProvider {

	// 采集方式
	public String cjfs() {
		return new SQL(){{
			SELECT("id id, mc name");
            FROM("ysj_cjfs");
		}}.toString();
	}
	// 源系统
	public String yxt() {
		return new SQL(){{
			SELECT("id id,mc name");
            FROM("ysj_yxt");
            WHERE("id_cjfs = #{parentId}");
		}}.toString();
	}
	// 源系统表
	public String yxtb(TreeNode treeNode) {
		return new SQL(){{
			SELECT("id id, zwbmc name");
            FROM("ysj_yxtb");
            if(treeNode.getName() != null && treeNode.getName() != "") {
            	WHERE("zwbmc like CONCAT(CONCAT('%', #{name}), '%') ");
            }
            WHERE("id_yxt = #{parentId}");
            // WHERE("sfcgcj = 1");
		}}.toString();
	}
	// 标准代码主题
	public String bzdmzt(){
		return new SQL(){{
			SELECT("id id,ztmc name");
            FROM("ysj_bzdmzt");
            WHERE("sfqy =1");
		}}.toString();
	}
	// 标准代码主题表
	public String bzdmb(TreeNode treeNode) {
		return new SQL(){{
			SELECT("id id,zwbmc name");
            FROM("ysj_bzdmb");
            if(treeNode.getName() != null && treeNode.getName() != "") {
            	WHERE("zwbmc like CONCAT(CONCAT('%', #{name}), '%') ");
            } 
            WHERE("id_cjgszt = #{parentId}");
            WHERE("sfcgcj = 1");
            WHERE("sfqy =1");
		}}.toString();
	}
	// 采集规范数据类
	public String cjgflb(){
		return new SQL(){{
			SELECT("id id,sjlmc name");
            FROM("ysj_cjgfsjl");
            WHERE(" sfqy =1 ");
		}}.toString();
	}
	// 采集规范主题
	public String cjgfzt(){
		return new SQL(){{
			SELECT("id id,ztmc name");
            FROM("ysj_cjgfzt");
            WHERE("id_cjgfsjl = #{parentId}  and sfqy =1  ");
		}}.toString();
	}
	// 采集规范表
	public String cjgfb(TreeNode treeNode) {
		return new SQL(){{
			SELECT("id id, zwbmc name,ywbmc ywname");
            FROM("ysj_cjgfb");
            if(treeNode.getName() != null && treeNode.getName() != "") {
            	WHERE("zwbmc like CONCAT(CONCAT('%', #{name}), '%') ");
            } 
            WHERE("id_cjgfzt = #{parentId} ");
            WHERE(" sfqy = 1 ");
            WHERE(" sfcgcj = 1 ");
		}}.toString();
	}
}
