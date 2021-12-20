## Chapter 17: Binary Trees

### This file contains my implementation for all exercises of the Chapter 17: Binary Trees.

#### BJP5 Exercise 17.1: countLeftNodes
```
public int countLeftNodes() {
    return countLeftNodes(overallRoot);
}

public int countLeftNodes(IntTreeNode root) {
    if (root == null) {
        return 0;
    } else if (root.left != null) {
        return 1 + countLeftNodes(root.left) + countLeftNodes(root.right);
    } else {
        return countLeftNodes(root.right);
    }  
}
```
#### BJP5 Exercise 17.2: countEmpty
```
public int countEmpty() {
    return countEmpty(overallRoot);
}

public int countEmpty(IntTreeNode root) {
    if (root == null) {
        return 1;
    } else {
        return countEmpty(root.left) + countEmpty(root.right);
    }
}
```
#### BJP5 Exercise 17.3: depthSum
```
public int depthSum() {
    return depthSum(overallRoot, 1);
}

public int depthSum(IntTreeNode root, int depth) {
    if (root == null) {
        return 0;
    } else {
        return root.data * depth + depthSum(root.left, depth + 1) + depthSum(root.right, depth + 1);
    }
}
```
#### BJP5 Exercise 17.4: countEvenBranches
```
public int countEvenBranches() {
    return countEvenBranches(overallRoot);
}

public int countEvenBranches(IntTreeNode root) {
    if (root == null) {
        return 0;
    } else if (root.left == null && root.right == null) {
        return 0;
    } else {
    
        if (root.data % 2 == 0) {
            return 1 + countEvenBranches(root.left) + countEvenBranches(root.right);
        } else {
            return countEvenBranches(root.left) + countEvenBranches(root.right);
        }  
        
    }
}
```
#### BJP5 Exercise 17.5: printLevel
```
public void printLevel(int level) {
    if (level <= 0) {
        throw new IllegalArgumentException();
    } else {
        printLevel(overallRoot, level, 1);
    }
}

public void printLevel(IntTreeNode root, int level, int depth) {
    if (root != null) {
    
        if (level == depth) {
            System.out.println(root.data);
        } else if (level > depth) {
            printLevel(root.left, level, depth + 1);
            printLevel(root.right, level, depth + 1);
        }
        
    }
}
```
#### BJP5 Exercise 17.6: printLeaves
```
public void printLeaves() {
    if (overallRoot == null) {
        System.out.println("no leaves");
    } else {
        System.out.print("leaves:");
        printLeaves(overallRoot);
    }
}

public void printLeaves(IntTreeNode root) {
    if (root == null) {
        return;
    } else if (root.left == null && root.right == null) {
        System.out.print(" " + root.data);
    } else {
        printLeaves(root.right);
        printLeaves(root.left);
    }
}
```
#### BJP5 Exercise 17.7: isFull
```
public boolean isFull() {
    return isFull(overallRoot);
}

public boolean isFull(IntTreeNode root) {
    if (root == null) {
        return true;
    } else if (root.left == null && root.right != null) {
        return false;
    } else if (root.right == null && root.left != null) {
        return false;
    } else {
        return isFull(root.left) && isFull(root.right);
    }
}
```
#### BJP5 Exercise 17.8: toString
```
public String toString2() {
    return toString2(overallRoot);
}

public String toString2(IntTreeNode root) {
    if (root == null) {
        return "empty";
    } else if (root.left == null && root.right == null) {
        return "" + root.data;
    } else {
        return "(" + root.data + ", " + toString2(root.left) + ", " + toString2(root.right) + ")";
    }
}
```
#### BJP5 Exercise 17.9: equals
```
public boolean equals2(IntTree tree) {
    return equals2(overallRoot, tree.overallRoot);
}

public boolean equals2(IntTreeNode node1, IntTreeNode node2) {
    if (node1 == null && node2 == null) {
        return true;
    } else if (node1.data != node2.data) {
        return false;
    } else {
        return equals2(node1.left, node2.left) && equals(node1.right, node2.right);
    }
}
```
#### BJP5 Exercise 17.10: doublePositives
```
public void doublePositives() {
    doublePositives(overallRoot);
}

public void doublePositives(IntTreeNode root) {
    if (root != null) {
        int num = root.data;
        if (num > 0) {
            root.data = 2 * num;
        }
        doublePositives(root.left);
        doublePositives(root.right);
    }
}
```
#### BJP5 Exercise 17.11: numberNodes
```
public int numberNodes() {
    return numberNodes(overallRoot, 1);
}

public int numberNodes(IntTreeNode root, int num) {
    if (root == null) {
        return 0;
    } else {
        root.data = num;
        int countLeft = numberNodes(root.left, num + 1);
        int countRight = numberNodes(root.right, num + countLeft + 1);
        return 1 + countLeft + countRight;
    }
}
```
#### BJP5 Exercise 17.12: removeLeaves
```
public void removeLeaves() {
    overallRoot = removeLeaves(overallRoot);
}

public IntTreeNode removeLeaves(IntTreeNode root) {
    if (root == null || (root.left == null && root.right == null)) {
        return null;
    } else {
        root.left = removeLeaves(root.left);
        root.right = removeLeaves(root.right);
        return root;
    }
}
```
#### BJP5 Exercise 17.14: completeToLevel
```
public void completeToLevel(int level) {
    if (level < 1) {
        throw new IllegalArgumentException();
    } else {
        overallRoot = completeToLevel(overallRoot, level);
    }
}

public IntTreeNode completeToLevel(IntTreeNode root, int level) {
    if (root == null) {
        root = new IntTreeNode(-1);
    }
    
    if (level == 1) {
        return root;
    } 
    root.left = completeToLevel(root.left, level - 1);
    root.right = completeToLevel(root.right, level - 1);
    return root;
}
```
#### BJP5 Exercise 17.15: trim
```
public void trim(int min, int max) {
    overallRoot = trim(overallRoot, min, max);
}

public IntTreeNode trim(IntTreeNode root, int min, int max) {
    if (root == null) {
        return null;
    } else if (root.data < min) {
        return trim(root.right, min, max);
    } else if (root.data > max) {
        return trim(root.left, min, max);
    } else {
        root.left  = trim(root.left, min, max);
        root.right = trim(root.right, min, max);
        return root;
    }
}
```
#### BJP5 Exercise 17.16: tighten
```
public void tighten() {
    overallRoot = tighten(overallRoot);
}

private IntTreeNode tighten(IntTreeNode root) {
    if (root == null) {
        return null;
    } else if (root.left == null && root.right != null) {
        return tighten(root.right);
    } else if (root.left != null && root.right == null) {
        return tighten(root.left);
    } else {
        root.left = tighten(root.left);
        root.right = tighten(root.right);
        return root;
    }
}
```
#### BJP5 Exercise 17.17: combineWith
```
public IntTree combineWith(IntTree t2) {
    IntTree t3 = new IntTree();
    t3.overallRoot = combineWith(overallRoot, t2.overallRoot);
    return t3;
}

private IntTreeNode combineWith(IntTreeNode root1, IntTreeNode root2) {
    if (root1 == null && root2 == null) {
        return null;
    } else if (root1 == null && root2 != null) {
        IntTreeNode node = new IntTreeNode(2);
        node.left = combineWith(null, root2.left);
        node.right = combineWith(null, root2.right);
        return node;
    } else if (root1 != null && root2 == null) {
        IntTreeNode node = new IntTreeNode(1);
        node.left = combineWith(root1.left, null);
        node.right = combineWith(root1.right, null);
        return node;
    } else {
        IntTreeNode node = new IntTreeNode(3);
        node.left = combineWith(root1.left, root2.left);
        node.right = combineWith(root1.right, root2.right);
        return node;
    }
}
```
#### BJP5 Exercise 17.18: inOrderList
```
public List inOrderList() {
    return inOrderList(overallRoot, new LinkedList<Integer>());
}

private List inOrderList(IntTreeNode root, List<Integer> list) {
    if (root == null) {
        return list;
    } else {
        inOrderList(root.left, list);
        list.add(root.data);
        inOrderList(root.right, list);
        return list;
    }
}
```
#### BJP5 Exercise 17.19: evenLevels
```
public void evenLevels() {
    overallRoot = evenLevels(overallRoot, 1);
}

private IntTreeNode evenLevels(IntTreeNode root, int level) {
    if (root == null) {
        return null;
    } else if (level % 2 == 1 && (root.left == null && root.right == null)) {
        return null;
    } else {
        root.left = evenLevels(root.left, level + 1);
        root.right = evenLevels(root.right, level + 1);
        return root;
    }
}
```
#### BJP5 Exercise 17.20: makePerfect
```
// WRITE YOUR CODE HERE

public void makePerfect() {
    overallRoot = makePerfect(overallRoot, height());
}

public IntTreeNode makePerfect(IntTreeNode root, int height) {
    if (root == null) {
        root = new IntTreeNode(0);
    } 
    if (height == 0) {
        return null;
    } else {
        root.left = makePerfect(root.left, height - 1);
        root.right = makePerfect(root.right, height - 1);
        return root;
    }
}



// LEAVE THESE METHODS HERE, TO USE AS HELPERS
public int height() {
    return height(overallRoot);
}

private int height(IntTreeNode root) {
    if (root == null) {
        return 0;
    } else {
        return 1 + Math.max(height(root.left), height(root.right));
    }
}
```
#### BJP5 Exercise 17.21: matches
```
int matches(IntTree tree2) {
    return matches(tree2.overallRoot, this.overallRoot);
}
int matches(IntTreeNode tree1, IntTreeNode node2)
{
    int left=0, right=0, count =0;
    if(tree1 == null && this != null || this == null && tree1 != null) { return 0; }
     count = tree1.data == node2.data ? 1 : 0;
    if(tree1.left != null && node2.left !=null){
     left = matches(tree1.left, node2.left);}
    if(tree1.right != null && node2.right !=null){
     right = matches(tree1.right, node2.right);}
    return count + left + right;
}
```
