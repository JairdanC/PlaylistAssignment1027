public class DLLNode<T> {
    private T element;
    private DLLNode<T> next;
    private DLLNode<T> previous;

    public DLLNode(T element) {
        this.element = element;
        this.next = null;
        this.previous = null;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public DLLNode<T> getNext() {
        return next;
    }

    public void setNext(DLLNode<T> next) {
        this.next = next;
    }

    public DLLNode<T> getPrevious() {
        return previous;
    }

    public void setPrevious(DLLNode<T> previous) {
        this.previous = previous;
    }
}
