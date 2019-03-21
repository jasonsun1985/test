package com.tec.tree;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

/**  
 * @ClassName: Test  
 * @Description: (这里用一句话描述这个类的作用)  
 * @author sunlei
 *    
 */
public class Test {
    public static void main(String[] args) {
        //TODO
        //页面传参 tableName,选用哪种生成方式,哪个字段作为菜单id,哪个字段作为菜单父类id,,哪个字段作为菜单名称name
        List<TreeNode> listTree = Lists.newArrayList(
                new TreeNode("root","","全国"),
                new TreeNode("tj", "root", "天津"),
                new TreeNode("bj", "root", "北京"),
                new TreeNode("hbs", "root","河北省"),
                new TreeNode("baoding", "hbs","保定"),
                new TreeNode("boaixian", "baoding","博爱县")
        );
        //第一种方式直接输出json list数据结构
        System.out.println(JSON.toJSONString(listTree));
        //////////////////////////////////////////////////////////////////////////
        //第二种，深度树形结构
        //listTree 从数据库查询实际数据
        TreeNode t = GenerateTree.build(listTree);
        System.out.println(JSON.toJSONString(t));
        
    }
}
