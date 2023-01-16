package tests;

import org.junit.Assert;
import org.junit.Test;
import parser.util.Node;

import java.util.List;

public class NodeTest {

    @Test
    public void testOneNode() {
        Node<Integer> n = Node.of(10);
        List<Integer> l = n.list();
        Assert.assertEquals(1, l.size());
        Assert.assertEquals(Integer.valueOf(10), l.get(0));
    }

    @Test
    public void testThreeLeafs() {
        Node<Integer> n1 = Node.of(10);
        Node<Integer> n2 = Node.of(20);
        Node<Integer> n12 = Node.of(n1, n2);
        Node<Integer> n3 = Node.of(30);
        Node<Integer> n123 = Node.of(n12, n3);
        List<Integer> l = n123.list();
        Assert.assertEquals(3, l.size());
        Assert.assertEquals(Integer.valueOf(10), l.get(0));
        Assert.assertEquals(Integer.valueOf(20), l.get(1));
        Assert.assertEquals(Integer.valueOf(30), l.get(2));
    }

}
