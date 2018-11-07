package com.casic.datamap.server.pmodel;

/**
 * <p>树公用对象</p>
 * @author hourz
 * @since 2018-10-19
 */
public class TreeNode {

	private Integer id;			// 主键
	private String name;		// 名称
	private String ywname;  	// 英文名称 
	private String state;		// 状态 open/closed
	private boolean checked;	// 是否选择中
	private String attributes;	// 所属等级
	private Integer parentId;	// 上级节点ID
	private Object children;	// 子节点
	/**
	 * <p>无参构造器</P>
	 */
	public TreeNode() {
		super();
	}
	/**
	 * <p>有参构造器</P>
	 */
	public TreeNode(Integer id, String name, String ywname, String state, boolean checked, String attributes, 
			Integer parentId, Object children) {
		super();
		this.id = id;
		this.name = name;
		this.ywname = ywname;
		this.state = state;
		this.checked = checked;
		this.attributes = attributes;
		this.parentId = parentId;
		this.children = children;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public String getAttributes() {
		return attributes;
	}
	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Object getChildren() {
		return children;
	}
	public void setChildren(Object children) {
		this.children = children;
	}
	public String getYwname() {
		return ywname;
	}
	public void setYwname(String ywname) {
		this.ywname = ywname;
	}
}
