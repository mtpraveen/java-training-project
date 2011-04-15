import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Iterator;

/**
 * Algorithm realize "breadth-first search" from graph's head (source) to required vertex.
 */

/**
 * @author Мара
 *
 */


public class Graph<E> implements Iterator<E>, Iterable<E> {
	public Vertex<E> source; // вершина
	public Vertex<E> pointer;
	public static int size=1;
	public static int p;
	//public Vertex<E> iterator;
	public Vertex<E> getSource() {
		return source;
	}

	public void setSource(Vertex<E> source) {
		this.source = source;
	}

	public Vertex<E> getPointer() {
		return pointer;
	}

	public void setPointer(Vertex<E> pointer) {
		this.pointer = pointer;
	}

	public static int getSize() {
		return size;
	}

	public void setSize(int size) {
		Graph.size = size;
	}
	
	
	public static class Vertex<E> {
		private int color; // white=0, gray=1, black=2;
		private int dlina;
		private E value; 
		public Vertex<E> next;
		public Vertex<E> parent;
		public List <Vertex<E>> links = new ArrayList<Vertex<E>>();
		
		public int getColor() {
			return color;
		}
		public void setColor(int color) {
			this.color = color;
		}
		public int getDlina() {
			return dlina;
		}
		public void setDlina(int dlina) {
			this.dlina = dlina;
		}
		public E getValue() {
			return value;
		}
		public void setValue(E value) {
			this.value = value;
		}
		public Vertex<E> getNext() {
			return next;
		}
		public void setNext(Vertex<E> next) {
			this.next = next;
		}
		public Vertex<E> getParent() {
			return parent;
		}
		public void setParent(Vertex<E> parent) {
			this.parent = parent;
		}
		public List<Vertex<E>> getLinks() {
			return links;
		}
		public void setLinks(List<Vertex<E>> links) {
			this.links = links;
		}
	}
	public Vertex<E> addNewVertex(E newVertex){
		Vertex<E> vertex = new Vertex<E>();
		vertex.setColor(0);
		vertex.setValue(newVertex);
		vertex.setDlina(100);
		vertex.setParent(null);
		pointer.setNext(vertex);
		size++;
		return vertex;
		
	}
	
	public void addNewLinks(E i, E j){
		Vertex<E> ppointer1, ppointer = source;
		while (ppointer.getValue()!=i) 
			 ppointer = ppointer.next;
		ppointer1 = ppointer;
		ppointer = source;
		while (ppointer.getValue()!=j) 
		 ppointer = ppointer.next;
		ppointer1.links.add(ppointer);
	}
	
	public void makeTree(Graph<E> graf, E ai)//(Vertex<E> source)
	{	graf.source.setColor(1);
		graf.source.setDlina(0);
		graf.source.setParent(null);
		
		LinkedList<Vertex<E>> queue = new LinkedList<Vertex<E>>();

		queue.addLast(source);
		

		Vertex<E> u = new Vertex<E>();
		Vertex<E> v = new Vertex<E>();

		try{
			while (queue.isEmpty()== false){
			u=queue.getFirst();
			for (int m=0; m<u.getLinks().size(); m++){
			  v=u.links.get(m);
			  if (v.getColor()==0){
				v.setColor(1);
				v.setDlina(u.getDlina()+1);
				v.setParent(u);
				queue.addLast(v);
			  }
			}
			u.setColor(2);
			queue.removeFirst();
		}}
		catch(NoSuchElementException e) {
			System.out.println(e);
		}
		
		v = source;
		while (v.getValue()!=ai) 
		 v = v.next;
		System.out.print("Путь из вершины " + graf.source.getValue());
		while (v != graf.getSource()){
		  if (v.getParent()==null)
				System.out.println("Путь отсутствует");
			else 
				{u = source;
				while (u.getValue()!=v.getParent().getValue()) 
				 u = u.next;}
		  	v=u;
		  }
		
		v = source;
		while (v.getValue()!=ai) 
		 v = v.next;
		System.out.println(" в вершину "+  v.getValue() +" длиной в " + v.getDlina());
		
		while (source.getValue()!=v.getParent().getValue()){ 
			System.out.println(" через "+  v.getParent().getValue());
			v=v.getParent();}
	}
	
	@Override
	public Iterator<E> iterator() {
		return this;
	}

	@Override
	public boolean hasNext() {
		if (p != 1)
			return true;
		else
			return false;
	}

	@Override
	public E next() {
		this.setPointer(this.getSource());
		for (int h=0; h<(Graph.getSize() - p);h++) 
			this.setPointer(this.getPointer().getNext()); 
		p--;
			return pointer.getNext().getValue();
	
	}
	public List<Vertex<E>> nextLinks() {
		this.setPointer(this.getSource());
		for (int h=0; h<(Graph.getSize() - p);h++) 
			this.setPointer(this.getPointer().getNext()); 
		if (p!=0) //(Graph.getSize() - p !=0)
		return pointer.getLinks();
		else 
			return null;
	
	}
	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}

	private static int getButtonValue() {
		int val = 0, // введенное значение
		len = 0; // реальное количество введенных символов
		byte b[] = new byte[9]; // массив для хранения кодов введенных символов
		int by = 0; // вспомогат. переменная для ввода кода очередного символа
		try {
			System.in.skip(System.in.available()); // метод available
													// возвр.число символов в
													// буфере, а skip - удаляет
													// их
			while (true) // число может содержать несколько символов
			{
				by = System.in.read(); // код очередного символа
				// первым символом (len == 0) может быть символ Enter, пробел
				// или не цифра
				if (by == 13 || by == 32 || by < 48 || by > 57)
					break;
				b[len++] = (byte) (by - 48); // превращаем код символа в число
				if (len > 8)
					break;// слишком много символов в буфере клавиатуры
			}
		} catch (java.io.IOException e) {
		}
		int ten = 1; // для преобразования последовательности цифр в число 10 в
						// степени, соответствующей позиции цифры в числе
		for (int i = len - 1; i >= 0; i--) // от последней цифры - к первой
		{
			val += b[i] * ten;
			ten *= 10;
		}
		return val;
	}
	
	
	public static void main(String[] args) {
		Graph<Integer> gr = new Graph<Integer>();
		
		Vertex<Integer> ver = new Vertex<Integer>(); 
		gr.source=ver;
		
		gr.source.setValue(1);
		gr.source.setColor(0);
		gr.source.setDlina(100);
		gr.source.setParent(null);
		
		gr.setPointer(gr.getSource());
		gr.setPointer(gr.addNewVertex(2));
		gr.setPointer(gr.addNewVertex(3));
		gr.setPointer(gr.addNewVertex(4));
		gr.setPointer(gr.addNewVertex(5));
		gr.setPointer(gr.addNewVertex(6));
		
		gr.addNewLinks(1, 2); 
		gr.addNewLinks(1, 3);
		gr.addNewLinks(1, 4);
		
		gr.addNewLinks(2, 1);
		gr.addNewLinks(2, 4);
		gr.addNewLinks(2, 5);
		
		gr.addNewLinks(3, 1);
		gr.addNewLinks(3, 4);
		
		gr.addNewLinks(4, 2);
		gr.addNewLinks(4, 3);
		gr.addNewLinks(4, 5);
		
		gr.addNewLinks(5, 2);
		gr.addNewLinks(5, 4);
		gr.addNewLinks(5, 6);

		gr.addNewLinks(6, 5);
		
		
	
		p=Graph.getSize();
		System.out.println("Граф состоит из " + p + " вершин.");
		System.out.print("Вершина " + gr.source.getValue() + ": связи с ");
		for (int k=0; k<gr.source.links.size(); k++)
			System.out.print(" "+gr.next());
		
		System.out.println();
		
		p=Graph.getSize();
		while (gr.hasNext() == true)
	{System.out.print("Вершина " + gr.next() + ": связи с ");
	for (int k=0; k<gr.nextLinks().size(); k++)
		System.out.print(" "+gr.nextLinks().get(k).getValue());
	System.out.println();
	}
			
		System.out.println("");
		int j = 1;
		System.out.println("Введите номер вершины для поиска пути (из вершины 1). По окончании всех проверок введите 0.");
		j = getButtonValue();
		while (j != 0) {
			if (j==1 || j>Graph.getSize()) System.out.println("Введите другую вершину, принадлежащую графу...");
			else 
			 gr.makeTree(gr, j);
			 j = getButtonValue();
			}
		System.out.println("...Bye...");
	}


}
