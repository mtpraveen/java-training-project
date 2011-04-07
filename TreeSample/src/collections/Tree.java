package collections;

import java.util.Iterator;
import java.util.LinkedList;

public class Tree<E> implements Iterator<E>, Iterable<E> {
	private enum Traversal {
		DIRECT {
			@Override
			public TraversalPosition[] getRule() {
				return new TraversalPosition[] { TraversalPosition.ROOT,
						TraversalPosition.LEFT, TraversalPosition.RIGHT };
			}
		},
		SYMETRIC {
			@Override
			public TraversalPosition[] getRule() {
				return new TraversalPosition[] { TraversalPosition.LEFT,
						TraversalPosition.ROOT, TraversalPosition.RIGHT };
			}
		},
		REVERSE {
			@Override
			public TraversalPosition[] getRule() {
				return new TraversalPosition[] { TraversalPosition.LEFT,
						TraversalPosition.RIGHT, TraversalPosition.ROOT };
			}
		};
		public abstract TraversalPosition[] getRule();
	}

	/**
	 * Enumeration which encapsulates 3 possible direction during traversal
	 * 
	 * @author Pechko_E
	 * 
	 */
	private enum TraversalPosition {
		LEFT, RIGHT, ROOT;
	}

	public static class Node<E> {
		private E value;
		private Node<E> parent;
		private Node<E> left;
		private Node<E> right;

		public Node(E value, Node<E> parent) {
			this.parent = parent;
			this.value = value;
		}

		public Node<E> addLeft(E value) {
			Node<E> node = new Node<E>(value, this);
			this.left = node;
			return node;
		}

		public Node<E> addRight(E value) {
			Node<E> node = new Node<E>(value, this);
			this.right = node;
			return node;
		}

		public boolean removeLeft() {
			boolean result = left != null;
			left = null;
			return result;
		}

		public boolean removeRight() {
			boolean result = right != null;
			right = null;
			return result;
		}

		public E getValue() {
			return value;
		}

		public void setValue(E value) {
			this.value = value;
		}

		public Node<E> getParent() {
			return parent;
		}

		public void setParent(Node<E> parent) {
			this.parent = parent;
		}

		public Node<E> getLeft() {
			return left;
		}

		public void setLeft(Node<E> left) {
			this.left = left;
		}

		public Node<E> getRight() {
			return right;
		}

		public void setRight(Node<E> right) {
			this.right = right;
		}

		@Override
		public String toString() {
			return value.toString();
		}

	}

	private Node<E> root;
	private Node<E> current;
	private LinkedList<Pair<Node<E>, TraversalPosition>> nodesToBeVisited;
	private TraversalPosition[] rule;

	public void throwException() {
		throw new NullPointerException();
	}

	public void throwCheckedException() throws Exception {
		throw new Exception();
	}

	public Tree() {
		this(null);
	}

	public Tree(E value) {
		root = new Node<E>(value, null);
		current = root;
	}

	public Node<E> addLeft(E value) {
		return current.addLeft(value);
	}

	public void removeLeft() {
		current.removeLeft();
	}

	public Node<E> addRight(E value) {
		return current.addRight(value);
	}

	public void removeRight() {
		current.removeRight();
	}

	public Node<E> getRoot() {
		return root;
	}

	@Override
	public Iterator<E> iterator() {
		return setup(Traversal.DIRECT);
	}

	private Iterator<E> setup(Traversal traversal) {
		rule = traversal.getRule();
		nodesToBeVisited = new LinkedList<Pair<Node<E>, Tree.TraversalPosition>>();
		updateQueue(root);
		return this;
	}

	public Iterator<E> symetricIterator() {
		return setup(Traversal.SYMETRIC);
	}

	public Iterator<E> reverseIterator() {
		return setup(Traversal.REVERSE);
	}

	private void updateQueue(Node<E> head) {
		for (int i = 2; i >= 0; i--) {
			TraversalPosition tp = rule[i];
			Node<E> node = getNode(head, tp);
			if (node != null) {
				nodesToBeVisited.addFirst(new Pair<Node<E>, TraversalPosition>(
						node, tp));
			}
		}
	}

	private Node<E> getNode(Node<E> head, TraversalPosition tp) {
		switch (tp) {
		case LEFT:
			return head.getLeft();
		case RIGHT:
			return head.getRight();
		default:
			return head;
		}
	}

	@Override
	public boolean hasNext() {
		return nodesToBeVisited.size() != 0;
	}

	@Override
	public E next() {
		if (nodesToBeVisited.isEmpty())
			return null;
		while (nodesToBeVisited.peek().getRight() != TraversalPosition.ROOT) {
			updateQueue(nodesToBeVisited.pop().getLeft());
		}
		return nodesToBeVisited.pop().getLeft().getValue();
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub

	}

	public static void main(String args[]) {
		Tree<String> tree = new Tree<String>("A");
		tree.addLeft("B").addLeft("D").addRight("G");
		Node<String> c = tree.getRoot().addRight("C");
		c.addRight("F");
		Node<String> e = c.addLeft("E");
		e.addLeft("H");
		e.addRight("I");
		System.out.println("iterator");
		Iterator<String> iterator = tree.iterator();
		while (iterator.hasNext()) {
			System.out.print(iterator.next());
		}
		System.out.println("\nsymetricIterator");
		iterator = tree.symetricIterator();
		while (iterator.hasNext()) {
			System.out.print(iterator.next());
		}
		System.out.println("\nreverseIterator");
		iterator = tree.reverseIterator();
		while (iterator.hasNext()) {
			System.out.print(iterator.next());
		}

	}
}
