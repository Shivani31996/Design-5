// Time Complexity : O(n)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach :
/*
 * 1 - We iterate over each node in the original list and create a clone for it while maintaining the next and random pointers.
 * 2 - When we are at a particular node, we need to check if we have already created a clone for it that is why we use a 
 * HashMap so that search is O(1). 
 * 3 - Since there are random pointers, there might be a chance that some non-contiguous nodes have been previously
 * created.
 */

public class RandomPointer {
    HashMap<Node,Node> map;
    
    public Node copyRandomList(Node head) {
        if(head == null)
            return head;
        map = new HashMap<>();
        
        Node headClone = clone(head);
        Node curr = head;
        Node currClone = headClone;
        
        while(curr != null)
        {
            currClone.next = clone(curr.next);
            if(curr.random != null)
            {
                currClone.random = clone(curr.random);
            }
            curr = curr.next;
            currClone = currClone.next;
        }
        return map.get(head);
    }
    
    public Node clone(Node node){
        if(node == null)
            return null;
        if(map.containsKey(node))
            return map.get(node);
        else
        {
            Node newNode = new Node(node.val);
            map.put(node,newNode);
            return newNode;
        }
    }
}


/*
 * Approach 2 - Without HashMap
 * 1 - The idea is to traverse the original list and create a new node for each node in the list. (create a deep copy)
 * 2 - Connect the random pointers in the deep copy list
 * 3 - Separate the original and deep copy list
 */

public Node copyRandomList(Node head) {
    if(head == null)
    {
        return head;
    }
    
    Node curr = head;
    
    //create a deep copy
    while(curr != null)
    {
        Node newNode = new Node(curr.val);
        newNode.next = curr.next;
        curr.next = newNode;
        curr = curr.next.next;
    }
    
    //create random pointers for deep copy nodes
    curr = head;
    while(curr != null)
    {
        if(curr.random != null)
        {
            curr.next.random = curr.random.next;
        }
            curr = curr.next.next;
    }
    
    //separate the two lists
    curr = head;
    Node headDeep = curr.next;
    Node copyHead = headDeep;
    
    while(curr != null)
    {
        curr.next = headDeep.next;
        if(headDeep.next != null)
        {
            headDeep.next = headDeep.next.next;
        }
        curr = curr.next;
        headDeep = headDeep.next;
    }
    return copyHead;
}