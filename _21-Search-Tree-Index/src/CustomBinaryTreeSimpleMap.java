/**
 * Implementation of SimpleMap using a custom binary search tree
 */
public class CustomBinaryTreeSimpleMap<K extends Comparable<K>, V> implements SimpleMap<K, V> {

    // instantiate root Node object
    protected Node root;

    // call constructor
    public CustomBinaryTreeSimpleMap () {
        root = null;
    }

    protected class Node { // store Node objects
        protected K key;
        protected V value;

        protected Node left;
        protected Node right;

        // Node constructor
        protected Node (K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }

        // helper methods
        protected V putHelper (Node newNode) {
            if (this.key.compareTo(newNode.key) > 0) {
                // insert left if key of this Node is greater than key of newNode
                if (this.left == null) {
                    // insert Node if there is nothing on the left
                    this.left = newNode;
                } else {
                    // recur if there is another Node in the way
                    return this.left.putHelper(newNode);
                }
            } else if (this.key.compareTo(newNode.key) < 0) {
                // insert right if key of this Node is less than key of newNode
                if (this.right == null) {
                    // insert Node if there is nothing on the right
                    this.right = newNode;
                } else {
                    // recur if there is another Node in the way
                    return this.right.putHelper(newNode);
                }
            } else {
                // reassign value of Node to value of newNode if keys are equal
                V oldValue = this.value;
                this.value = newNode.value;
                return oldValue;
            }
            return null;
        }

        protected V getHelper (K key) {
            if (this.key.compareTo(key) > 0) {
                // check left if key of this Node is greater than key passed
                if (this.left == null) {
                    // return null if there is no Node to the left
                    return null;
                } else {
                    // recur if there is another Node to the left
                    return this.left.getHelper(key);
                }
            } else if (this.key.compareTo(key) < 0) {
                // check right if key of this Node is greater than key passed
                if (this.right == null) {
                    // return null if there is no Node to the right
                    return null;
                } else {
                    // recur if there is another Node to the right
                    return this.right.getHelper(key);
                }
            } else {
                // return value of Node if keys are equal
                return this.value;
            }
        }

        protected boolean containsKeyHelper (K key) {
            if (this.key.compareTo(key) > 0) {
                // check left if key of this Node is greater than key passed
                if (this.left == null) {
                    // return false if there is no Node to the left
                    return false;
                } else {
                    // recur if there is another Node to the left
                    return this.left.containsKeyHelper(key);
                }
            } else if (this.key.compareTo(key) < 0) {
                // check right if key of this Node is greater than key passed
                if (this.right == null) {
                    // return false if there is no Node to the right
                    return false;
                } else {
                    // recur if there is another Node to the right
                    return this.right.containsKeyHelper(key);
                }
            } else {
                // return true if keys are equal
                return true;
            }
        }
    } // end class Node

    @Override
    public void clear() {
        // assign null to the root and let the garbage collector do the rest
        this.root = null;
    }

    // add new key/value pair to tree
    @Override
    public V put(K key, V value) {
        if (this.root == null) {
            // create root Node if one does not exist
            this.root = new Node(key, value);
            return null;
        } else {
            // create newNode and call putHelper() to place it in correct location of map
            Node newNode = new Node(key, value);
            return root.putHelper(newNode);
        }
    }

    @Override
    public V get(K key) {
        if (this.root == null) {
            // return null if root does not exist
            return null;
        } else {
            // call getHelper() find value associated with key
            return this.root.getHelper(key);
        }
    }

    @Override
    public boolean containsKey(K key) {
        if (this.root == null) {
            // return null if root does not exist
            return false;
        } else {
            // call getHelper() find value associated with key
            return this.root.containsKeyHelper(key);
        }
    }

    @Override
    public boolean isEmpty() {
        // return true if root is null, return false if root has a value
        return this.root == null;
    }
} // end class CustomBinarySearchTree
