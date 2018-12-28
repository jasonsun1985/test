package com.collect.util;

import java.util.ArrayList;  
import java.util.List;  
  
public class TestTree {  
  public Node a;  
  
  public TestTree() {  
    a = new Node("a");  
    Node b = a.createChildNode("b");  
    Node c = a.createChildNode("c");  
    Node d = b.createChildNode("d");  
    Node e = c.createChildNode("e");  
    Node f = c.createChildNode("f");  
    Node g = e.createChildNode("g");  
    Node h = e.createChildNode("h");  
  }  
  
  public void testDFS() {  
    System.out.println("Testing DFS");  
    visitDFS(a);   
  }  
  
  public void visitDFS(Node nodeStart) {  
    for(Node child : nodeStart.children) {  
      visitDFS(child); 
    }  
    System.out.println("DFS: " + nodeStart.nodeName);  
  }  
  
  public void testBFS() {  
    System.out.println("Testing BFS");  
    visitBFS(a);   
  }  
  
  public void visitBFS(Node nodeStart) {  
    List<Node> pendingExplore = new ArrayList<Node>(); 
    pendingExplore.add(nodeStart);
    while(pendingExplore.size() > 0) {  
      Node currentNode = pendingExplore.remove(0);  
      System.out.println("BFS: " + currentNode.nodeName);  
      pendingExplore.addAll(currentNode.children);  
    }  
  }  
  
  // Application entry point  
  public static void main(String[] args) {  
    TestTree testTree = new TestTree();  
    testTree.testBFS();  
    testTree.testDFS();  
  }  
  
  // Node class  
  public static class Node {
    public Node parent;  
    public List<Node> children = new ArrayList<Node>();  
  
    public String nodeName;
  
    public Node(String nodeName) {
      this.nodeName = nodeName;  
    }  
  
    public Node createChildNode(String childNodeName) {  
      Node child = new Node(childNodeName);  
      child.parent = this;
      children.add(child);
      return child;
    } 
  }
}
