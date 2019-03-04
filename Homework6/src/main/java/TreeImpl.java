import java.util.Stack;

public class TreeImpl<E extends Comparable<? super E>> implements Tree<E> {

    private Node<E> rootNode;

//6786
    @Override
    public void add(E value) {
        Node<E> newNode = new Node<>(value);
        if (isEmpty()) {
            rootNode = newNode;
            rootNode.setLevel(1);
            System.out.println(rootNode.getLevel() + " level");
            return;
        }

        Node<E> current = rootNode;
        Node<E> previous = null;
        while (current != null) {
            previous = current;
            if (current.getValue().equals(value)) {
                return;
            }

            if (current.shouldLeftChild(value)) {   //проверяем меньше текущего значения или нет
                current = current.getLeftChild();   //присваеваем ссылку лефтЧайлд


            } else {
                current = current.getRightChild();


            }
        }

        if (previous.shouldLeftChild(value) && previous.getLevel()<4) {
            previous.setLeftChild(newNode);
            if(previous.getLeftChild()!=null){
                previous.getLeftChild().setLevel(previous.getLevel()+1);
           // System.out.println(previous.getLeftChild().getLevel() + " Level " + value + " value");
            }
        } else if (previous.getLevel()<4){
            previous.setRightChild(newNode);
            if(previous.getRightChild()!=null){
                previous.getRightChild().setLevel(previous.getLevel()+1);
               // System.out.println(previous.getRightChild().getLevel() + " Level " + value + " value");
            }
        }
    }

    @Override
    public boolean remove(E value) {

        //Поиск удаляемого элемента и его родителя
        Node<E> current = rootNode;
        Node<E> parent = null;
        while (current != null) {
            if (current.getValue().equals(value)) {
                break;
            }
            parent = current;

            if (current.shouldLeftChild(value)) {
                current = current.getLeftChild();
            }
            else {
                current = current.getRightChild();
            }
        }

        if (current == null) {//Нет удаляемого элемента
            return false;
        }

        if (isLeaf(current)) {
            if (current == rootNode) {
                rootNode = null;
            }
            else if (parent.getLeftChild().equals(current)) {
                parent.setLeftChild(null);
            }
            else {
                parent.setRightChild(null);
            }
        }
        else if (hasOnlySingleChildElement(current)) {
            Node<E> childNode = current.getLeftChild() != null
                    ? current.getLeftChild()
                    : current.getRightChild();

            if (current == rootNode) {
                rootNode = childNode;
            }
            else if (parent.getLeftChild().equals(current)) {
                parent.setLeftChild(childNode);
            }
            else {
                parent.setRightChild(childNode);
            }
        }
        else {
            Node<E> successor = getSuccessor(current);
            if (current == rootNode) {
                rootNode = successor;
            }
            else if (parent.getLeftChild().equals(current)) {
                parent.setLeftChild(successor);
            }
            else {
                parent.setRightChild(successor);
            }
            successor.setLeftChild(current.getLeftChild());
        }

        return false;
    }


    private Node<E> getSuccessor(Node<E> removedNode) {
        Node<E> successor = removedNode;
        Node<E> successorParent = null;
        Node<E> current = removedNode.getRightChild();

        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.getLeftChild();
        }

        if (successor != removedNode.getRightChild()) {
            //Не забываем о правых потомках нашего кандидата на замену
            successorParent.setLeftChild(successor.getRightChild());
            successor.setRightChild(removedNode.getRightChild());
        }

        return successor;

    }

    private boolean hasOnlySingleChildElement(Node<E> current) {
        return current.getLeftChild() == null || current.getRightChild() == null;
    }

    private boolean isLeaf(Node<E> current) {
        return current.getLeftChild() == null && current.getRightChild() == null;
    }

    @Override
    public boolean find(E value) {
        Node<E> current = rootNode;
        while (current != null) {
            if (current.getValue().equals(value)) {
                return true;
            }
            if (current.shouldLeftChild(value)) {
                current = current.getLeftChild();
            } else {
                current = current.getRightChild();
            }
        }

        return false;
    }

    @Override
    public void display() {
        Stack<Node> globalStack = new Stack();
        globalStack.push(rootNode);
        int nBlanks = 64;

        boolean isRowEmpty = false;
        System.out.println("................................................................");

        while (!isRowEmpty) {
            Stack<Node> localStack = new Stack<>();

            isRowEmpty = true;
            for (int i = 0; i < nBlanks; i++) {
                System.out.print(" ");
            }

            while (!globalStack.isEmpty()) {
                Node tempNode = globalStack.pop();
                if (tempNode != null) {
                    System.out.print(tempNode.getValue());
                    localStack.push(tempNode.getLeftChild());
                    localStack.push(tempNode.getRightChild());
                    if (tempNode.getLeftChild() != null || tempNode.getRightChild() != null) {
                        isRowEmpty = false;
                    }
                } else {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < nBlanks * 2 - 2; j++) {
                    System.out.print(" ");
                }
            }

            System.out.println();

            while (!localStack.isEmpty()) {
                globalStack.push(localStack.pop());
            }

            nBlanks /= 2;
        }
        System.out.println("................................................................");
    }

    @Override
    public boolean isEmpty() {
        return rootNode == null;
    }

    @Override
    public void traverse(TraverseMode traverseMode) {
        switch (traverseMode) {
            case IN_ORDER:
                inOrder(rootNode);
                break;
            case PRE_ORDER:
                preOrder(rootNode);
                break;
            case POST_ORDER:
                postOrder(rootNode);
                break;
            default:
                throw new IllegalArgumentException("Unknown traverse mode " + traverseMode);
        }
    }

    private void inOrder(Node<E> node) {
        if (node == null)
            return;

        inOrder(node.getLeftChild());
        System.out.println(node);
        inOrder(node.getRightChild());
    }

    private void preOrder(Node<E> node) {
        if (node == null)
            return;

        System.out.println(node);
        preOrder(node.getLeftChild());
        preOrder(node.getRightChild());
    }

    private void postOrder(Node<E> node) {
        if (node == null)
            return;

        postOrder(node.getLeftChild());
        postOrder(node.getRightChild());
        System.out.println(node);
    }
}