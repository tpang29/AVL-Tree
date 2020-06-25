public class TreeNode<T extends Comparable<T>>
{
    private int heightLeft;
    private int heightRight;
    private int copies;
    private T data;
    private TreeNode<T> leftChild;
    private TreeNode<T> rightChild;

    public TreeNode()
    {
        this.heightLeft = 0;
        this.heightRight = 0;
        this.copies = 1;
        this.data = null;
        this.leftChild = null;
        this.rightChild = null;
    }

    public TreeNode(T data)
    {
        this.heightLeft = 0;
        this.heightRight = 0;
        this.copies = 1;
        this.data = data;
        this.leftChild = null;
        this.rightChild = null;
    }

    public TreeNode(T data, TreeNode<T> leftChild, TreeNode<T> rightChild)
    {
        this.heightLeft = 0;
        this.heightRight = 0;
        this.copies = 1;
        this.data = data;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public void addCopy()
    {
        this.copies++;
    }

    public void removeCopy()
    {
        if (this.copies > 0)
        {
            this.copies--;
        }
    }

    public void adjustHeightLeftIncrease()
    {
        this.heightLeft = Math.max(this.leftChild.heightLeft, this.leftChild.heightRight) + 1;
    }

    public void adjustHeightRightIncrease()
    {
        this.heightRight = Math.max(this.rightChild.heightLeft, this.rightChild.heightRight) + 1;
    }

    public void adjustHeightLeftDecrease()
    {
        this.heightLeft = Math.max(this.leftChild.heightLeft, this.leftChild.heightRight) - 1;
    }

    public void adjustHeightRightDecrease()
    {
        this.heightRight = Math.max(this.rightChild.heightLeft, this.rightChild.heightRight) - 1;
    }

    public void increaseHeightLeft(int n)
    {
        for (int i = 0; i < n; i++)
        {
            this.heightLeft++;    
        }
    }

    public void decreaseHeightLeft(int n)
    {
        for (int i = 0; i < n; i++)
        {
            if (this.heightLeft > 0)
            {
                this.heightLeft--;
            }
        }
    }

    public void increaseHeightRight(int n)
    {
        for (int i = 0; i < n; i++)
        {
            this.heightRight++;    
        }
    }

    public void decreaseHeightRight(int n)
    {
        for (int i = 0; i < n; i++)
        {
            if (this.heightRight > 0)
            {
                this.heightRight--;
            }
        }
    }

    public void setData(T data)
    {
        this.data = data;
    }

    public T getData()
    {
        return this.data;
    }

    public void setLeftChild(TreeNode<T> leftChild)
    {
        this.leftChild = leftChild;
    }

    public TreeNode<T> getLeftChild()
    {
        return this.leftChild;
    }

    public void setRightChild(TreeNode<T> rightChild)
    {
        this.rightChild = rightChild;
    }

    public TreeNode<T> getRightChild()
    {
        return this.rightChild;
    }

    public TreeNode<T> balance()
    {
        if (isHeavyRight())
        {
            TreeNode<T> newRoot = null;

            // Double Rotation
            if (this.rightChild != null && this.rightChild.isHeavyLeft())
            {
                TreeNode<T> newChild = this.rightChild.leftChild;
                TreeNode<T> newGrandchild = this.rightChild;
                
                newGrandchild.setLeftChild(newChild.rightChild);
                newChild.setRightChild(newGrandchild);
                this.setRightChild(newChild);

                newGrandchild.decreaseHeightLeft(1);
                newChild.increaseHeightRight(1);
                this.decreaseHeightRight(2);
            }
            
            // Single Rotation
            newRoot = this.rightChild;
            this.setRightChild(newRoot.leftChild);
            newRoot.setLeftChild(this);

            newRoot.increaseHeightLeft(1);
            newRoot.leftChild.decreaseHeightRight(2);

            return newRoot;
        }
        else if (isHeavyLeft())
        {
            TreeNode<T> newRoot = null;
            
            // Double Rotation
            if (this.leftChild != null && this.leftChild.isHeavyRight())
            {
                TreeNode<T> newChild = this.leftChild.rightChild;
                TreeNode<T> newGrandchild = this.leftChild;
                
                newGrandchild.setRightChild(newChild.leftChild);
                newChild.setLeftChild(newGrandchild);
                this.setLeftChild(newChild);

                newGrandchild.decreaseHeightRight(1);
                newChild.increaseHeightLeft(1);
                this.decreaseHeightLeft(2);
            }
            
            // Single Rotation
            newRoot = this.leftChild;
            this.setLeftChild(newRoot.rightChild);
            newRoot.setRightChild(this);

            newRoot.increaseHeightRight(1);
            newRoot.rightChild.decreaseHeightLeft(2);

            return newRoot;
        }

        return this;
    }

    public boolean isUnbalanced()
    {
        return Math.abs(this.heightLeft - this.heightRight) > 1;
    }

    public boolean isHeavyLeft()
    {
        return this.heightLeft > this.heightRight;
    }

    public boolean isHeavyRight()
    {
        return this.heightRight > this.heightLeft;
    }

    @Override
    public String toString()
    {
        String dataString = String.format("%s:%6s", "Data", String.valueOf(this.data));
        String heightString = String.format("%s: (%s, %s)", "Height", this.heightLeft, this.heightRight);
        String copiesString = String.format("%s: %s", "Copies", this.copies);

        return String.format("%-15s%15s%15s", dataString, heightString, copiesString);
    }


}