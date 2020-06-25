public class Driver
{
    public static void main(String[] args)
    {
        AVLTree<Integer> numberTree = new AVLTree<>();
        AVLTree<Character> characterTree = new AVLTree<>();
        AVLTree<String> stringTree = new AVLTree<>();

        Integer[] numArray = {40, 60, 20, 34, 40, 43, 10, 60, 0, -10, 45, 83, 77};
        Character[] charArray = {'f', 't', 'm', 'z', 'a', 'a', 'e', 'r', 'm', 'g', 'f'};
        String[] stringArray = {"Hello", "World", "How", "Are", "You", "Today", "Hello", "World"};

        insertAndPrintElements(numberTree, numArray);
        insertAndPrintElements(characterTree, charArray);
        insertAndPrintElements(stringTree, stringArray);
    }

    private static <T extends Comparable<T>> void insertAndPrintElements(AVLTree<T> tree, T[] array)
    {
        for (T element : array)
        {
            tree.insert(element);
            tree.inorder();
            System.out.println();
        }
    }
}