public class AVLTree<T extends Comparable<T>>
{
    private TreeNode<T> root;

    public AVLTree()
    {
        this.root = null;
    }

    public AVLTree(TreeNode<T> root)
    {
        this.root = root;
    }

    public void setRoot(TreeNode<T> root)
    {
        this.root = root;
    }

    public TreeNode<T> getRoot()
    {
        return this.root;
    }

    public void insert(T data)
    {
        this.root = insert(this.root, data);
    }

    private TreeNode<T> insert(TreeNode<T> root, T data)
    {
        if (root == null)
        {
            TreeNode<T> node = new TreeNode<>(data);
            return node;
        }
        else 
        {
            if (data.compareTo(root.getData()) < 0)
            {
                if (root.getLeftChild() != null)
                {
                    root.setLeftChild(insert(root.getLeftChild(), data));
                    root.adjustHeightLeftIncrease();

                    if (root.isUnbalanced())
                    {
                        root = root.balance();   
                    }

                    return root;
                }
                else
                {
                    TreeNode<T> node = new TreeNode<>(data);
                    root.setLeftChild(node);
                    root.increaseHeightLeft(1);
                    return root;
                }
            }
            else if (data.compareTo(root.getData()) > 0)
            {
                if (root.getRightChild() != null)
                {
                    root.setRightChild(insert(root.getRightChild(), data));
                    root.adjustHeightRightIncrease();

                    if (root.isUnbalanced())
                    {
                        root = root.balance();   
                    }

                    return root;
                }
                else
                {
                    TreeNode<T> node = new TreeNode<>(data);
                    root.setRightChild(node);
                    root.increaseHeightRight(1);
                    return root;
                }
            }
            else
            {
                root.addCopy();
                return root;
            }
        }
    }

    public <T extends Comparable<T>> void inorder()
    {
        inorder(this.root);
    }

    private <T extends Comparable<T>> void inorder(TreeNode<T> localRoot)
    {
        if (localRoot == null)
        {
            return;
        }
        else
        {
            inorder(localRoot.getLeftChild());
            System.out.println(localRoot);
            inorder(localRoot.getRightChild());
            return;
        }
    }
}