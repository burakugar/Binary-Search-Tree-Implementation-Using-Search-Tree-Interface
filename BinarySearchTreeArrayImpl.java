package BinarySearchTreeArray;

public class BinarySearchTreeArrayImpl {

    public static class BST<T extends Comparable<T>> implements SearchTree {
        T[] tree;
        public BST(int size) {
            tree = (T[]) new Comparable[size];
        }

        /***
         * Inserts item where it belongs in the tree. Returns true if item is inserted; false if it isn't (already in tree)
         * @param d The item to be added
         * @return True if item is inserted; false if not
         */
        @Override
        public boolean add(Comparable d){
            int i = 0; //Counter to traverse the array.
            while (i < tree.length && tree[i] != null) { //End of Array or null location found.
                if (d.compareTo(tree[i]) < 0) //Incoming data is smaller than data present in the array.
                    i = (2 * i) + 1; //Move to left side of the tree.
                else
                    i = (2 * i) + 2; //Move to right side of the tree
            }
            tree[i] = (T) d;
            return true;
        }

        /**
         * Returns true if the target is found in the tree
         * @param d The item searched for
         * @return True if target is found; false if not
         */
        @Override
        public boolean contains(Comparable d) {
            int i = 0; //Counter to traverse the array.
            boolean found = false;
            while (i < tree.length) { //End of Array or 'd' found.
                if (d.compareTo(tree[i]) < 0) //Data to be searched is smaller than data present in the array.
                    i = (2 * i) + 1; //Move to left side of the tree.
                else if (d.compareTo(tree[i]) > 0)
                    i = (2 * i) + 2; //Move to right side of the tree
                else {//'d' is same as tree[i] i.e. d found.
                    found = true;
                    break;
                }
            }
            if (found) //'d' found.
               return true;
            else //'d' not found.
                return false;
        }

        /**
         *
         * @param d is the element to be finded in the tree
         * @return the values itself
         */
        @Override
        public T find(Comparable d) {
            int[] indices = new int[2];
            boolean found = false;
            int c = 0; //Child
            int p = 0; //Parent
            while (c < tree.length && tree[c] != null) { //End of Array or 'd' found.
                if (d.compareTo(tree[c]) < 0) { //Data to be searched is smaller than data present in the array.
                    p = c; //Parent index
                    c = (2 * c) + 1; //Move to left side of the tree.
                }
                else if (d.compareTo(tree[c]) > 0) {
                    p = c; //Parent index
                    c = (2 * c) + 2; //Move to right side of the tree
                }
                else { //'d' is same as tree[i] i.e. d found.
                    found = true;
                    break;
                }
            }
            if (found) {
                indices[0] = p;
                indices[1] = c;
                return (T) d;
            }
            else
                return null;
        }

        /**
         * if the tree does not contain the element, delete the child
         * @param child_index child index to be deleted
         */
        public void delNoChild(int child_index) {
            tree[child_index] = null;
        }

        /**
         *
         * @param d is the element to be deleted
         * @return an integer array contains the x and y coordinates of the node
         */
        public int[] findDel(T d) {
            int[] indices = new int[2];
            boolean found = false;
            int c = 0; //Child
            int p = 0; //Parent
            while (c < tree.length && tree[c] != null) { //End of Array or 'd' found.
                if (d.compareTo(tree[c]) < 0) { //Data to be searched is smaller than data present in the array.
                    p = c; //Parent index
                    c = (2 * c) + 1; //Move to left side of the tree.
                }
                else if (d.compareTo(tree[c]) > 0) {
                    p = c; //Parent index
                    c = (2 * c) + 2; //Move to right side of the tree
                }
                else { //'d' is same as tree[i] i.e. d found.
                    found = true;
                    break;
                }
            }
            if (found) {
                indices[0] = p;
                indices[1] = c;
                return indices;
            }
            else
                return null;
        }

        /**
         *
         * @param child_index index of the child
         * @param left left node
         * @param right right node
         */
        public void delOneChild(int child_index, int left, int right) {
            if (tree[right] == null) { //The one child is at the left
                tree[child_index] = tree[left]; //The place to be deleted is replaced by the immediate left node
                int right_temp = (2 * left) + 2; //A variable used to store the right side of the immediate left node
                int left_temp = (2 * left) + 1; //A variable used to store the left side of the immediate left node
                while (right_temp < tree.length && left_temp < tree.length) { //Until the end of tree
                    tree[left] = tree[left_temp]; //Moving up one level on the left side
                    left = left_temp; //A sort of pointer being updated for the left side
                    tree[right] = tree[right_temp]; //Moving up one level on the right side
                    right = right_temp; //A sort of pointer being updated for the right side

                    right_temp = (2 * right) + 2; //Right-most node of updated right
                    left_temp = (2 * left) + 1; //Left-most node of updated left
                }
                //Setting the last nodes as null as all the preceding nodes have moved up one level
                tree[left] = null;
                tree[right] = null;

            }
            else if (tree[left] == null) { //The one child is at the right
                tree[child_index] = tree[right]; //The place to be deleted is replaced by the immediate right node
                int right_temp = (2 * right) + 2; //A variable used to store the right side of the immediate right node
                int left_temp = (2 * right) + 1; //A variable used to store the left side of the immediate right node
                while (right_temp < tree.length && left_temp < tree.length) { //Until the end of tree
                    tree[left] = tree[left_temp]; //Moving up one level on the left side
                    left = left_temp; //A sort of pointer being updated for the left side
                    tree[right] = tree[right_temp]; //Moving up one level on the right side
                    right = right_temp; //A sort of pointer being updated for the right side

                    right_temp = (2 * right) + 2; //Right-most node of updated right
                    left_temp = (2 * left) + 1; //Left-most node of updated left
                }
                //Setting the last nodes as null as all the preceding nodes have moved up one level
                tree[left] = null;
                tree[right] = null;
            }
        }
        /**
         * Removes target (if found) from tree and returns true; otherwise returns false
         * @param d The target we want to remove
         * @return True if removed; false otherwise
         */
        @Override
        public boolean remove(Comparable d) {
            if (findDel((T) d) == null) {
                System.out.printf("\nKey not found, can't delete.\nThe list remains unchanged\n");
                return false;
            }
            else {
                int[] ref = findDel((T) d);
                int c = ref[1];
                int right = (2 * c) + 2;
                int left = (2 * c) + 1;
                System.out.printf("\nDeleting " + tree[c] + "\n");
                if ((right > tree.length || left > tree.length) || (tree[left] == null && tree[right] == null)) {//No child case
                    delNoChild(c);
                } else if ((tree[right] == null && tree[left] != null) || (tree[right] != null && tree[left] == null)) { //One child case
                    delOneChild(c, left, right);
                } else { //Two Child Case
                    int temp = right; //Move to the right of the node to be deleted
                    int temp2 = 0;
                    while (tree[temp] != null) { //Until the right is empty
                        temp2 = temp; //Because temp will be null at the end of the loop, a variable to store the previous value
                        temp = (2 * temp) + 1; //Move to the left
                    }
                    tree[c] = tree[temp2]; //The value at temp2 copied at the node to be deleted
                    c = temp2; //Child updated to carry out deletion of no child/one child at temp2
                    //right and left updated to carry out deletion at temp2
                    right = (2 * c) + 2;
                    left = (2 * c) + 1;
                    if ((right > tree.length || left > tree.length) || (tree[left] == null && tree[right] == null)) { //No child case
                        delNoChild(c);
                    } else if ((tree[right] == null && tree[left] != null) || (tree[right] != null && tree[left] == null)) { //One child case
                        delOneChild(c, left, right);
                    }
                }
            }
            return true;
        }

        /**
         * traverses through the tree
         * @param i value for traversal
         */
        public void traverse(int i) { //In-order implementation
            if (i < tree.length && tree[i] != null) { //Root is not null
                traverse((2 * i) + 1); //Left side of the list
                System.out.print(tree[i] + " ");
                traverse((2 * i) + 2); //Right side of the list
            }
        }
    }
}
