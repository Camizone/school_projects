package project;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

class DynamicArray<T> {
    private T[] array;
    private T[] temp;
    private int count;
    private static int CAPACITY = 100;
    public int CIndex = 0;
 
 
    @SuppressWarnings("unchecked")
    public DynamicArray() {
        array = (T[]) new Object[CAPACITY];
        count = 0;
    }
    
    public int CIndex() {
		return CIndex;
    }
    
    public int CAPACITY() {
		return CAPACITY;
    }
    
    public void add(T element) {
    	if(count==CAPACITY) {
    		growSize();
    	}
        for (int i = 0; i < CAPACITY; i++) {
            if (array[i] == null) {
                array[i] = element;
                count++;
                CIndex=1;
                break;
            }
        }
    }
 
    public void add(int index, T element) {
    	if(index>=CAPACITY) {
    		while(index>=CAPACITY) {
    			growSize();
    		}
    	}
        if (index >= 0 && index < CAPACITY) {
            if (array[index] == null && element != null) {
                count++;
            } else if (array[index] != null && element == null) {
                count--;
            }
            array[index] = element;
            
        }
    }
 
    public T remove(int index) {
        if (index >= 0 && index < CAPACITY) {
            T removed = array[index];
            if (removed != null) {
                array[index] = null;
                count--;
            }
            return removed;
        }
        return null;
    }
 
    public T get(int index) {
        if (index >= 0 && index < CAPACITY) {
            return array[index];
        }
        return null;
    }
 
    @SuppressWarnings("unchecked")
	public void growSize()   
    {    
 
    temp = (T[]) new Object[CAPACITY+10];
    for (int i = 0; i < CAPACITY; i++){
    	temp[i] = array[i];   
    }   
    array = (T[]) temp;   
    CAPACITY= CAPACITY+10;   
    }   
 
    public int size() {
        return count;
    }
 
    public boolean isEmpty() {
        return count == 0;
    }
 
    public static int capacity() {
        return CAPACITY;
    }
}
 
class Book {
    private final String title;
    private final String author;
    private final String quantity;
 
    public Book(String title, String author, String quantity) {
        this.title = title;
        this.author = author;
        this.quantity = quantity;
    }
 
    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", Quantity: " + quantity;
    }
 
    public String getQuantity() {
    	return quantity;
    }
 
    public String getAuthor() {
    	return author;
    }
 
    public String getTitle() {
    	return title;
    }
}
 
 
class Library {
    private final DynamicArray<Book> books;
    public int CIndex;
 
    public int CIndex() {
    	this.CIndex = books.CIndex();
		return this.CIndex;
    }
    
    public Library() {
        books = new DynamicArray<>();
    }
 
    public void addBook(Book book) {
        books.add(book);
    }
 
    public void insertBook(int index, Book book) {
        books.add(index, book);
    }
 
    public Book removeBook(int index) {
        return books.remove(index);
    }
 
    public Book searchBook(int index) {
        return books.get(index);
    }
 
 
    public int getTotalBooks() {
        return books.size();
    }
 
    public String getAllBooks() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < DynamicArray.capacity(); i++) {
            Book book = books.get(i);
            if (book != null) {
                sb.append(i).append(": ").append(book).append("\n");
            }
        }
        return sb.toString();
    }
 
    public String sortByName() {
        // Create a list of non-null books
        List<Book> nonNullBooks = new ArrayList<>();
        for (int i = 0; i < DynamicArray.capacity(); i++) {
            Book book = books.get(i);
            if (book != null) {
                nonNullBooks.add(book);
            }
        }
 
        int n = nonNullBooks.size();
 
        // Bubble sort
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (nonNullBooks.get(j).getTitle().compareToIgnoreCase(nonNullBooks.get(j + 1).getTitle()) > 0) {
 
                    // Swap books
                    Book temp = nonNullBooks.get(j);
                    nonNullBooks.set(j, nonNullBooks.get(j + 1));
                    nonNullBooks.set(j + 1, temp);
                }
            }
        }
 
        // Build the sorted string
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nonNullBooks.size(); i++) {
            Book book = nonNullBooks.get(i);
            sb.append(book).append("\n");
        }
 
        return sb.toString();
    }
 
    public String sortByQuantity() {
    	//An array for non-null books
    	List<Book> nonNullBooks = new ArrayList<>();
    	for (int i = 0; i < DynamicArray.capacity(); i++) {
    		Book book = books.get(i);
    		if (book != null) {
    			nonNullBooks.add(book);
    		}
    	}
 
    	int n = nonNullBooks.size();
 
    	//Bubble Sort
 
    	for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                int quantity1 = Integer.parseInt(nonNullBooks.get(j).getQuantity());
                int quantity2 = Integer.parseInt(nonNullBooks.get(j + 1).getQuantity());
                if (quantity1 < quantity2) {
 
                    // Swap books
                    Book temp = nonNullBooks.get(j);
                    nonNullBooks.set(j, nonNullBooks.get(j + 1));
                    nonNullBooks.set(j + 1, temp);
                }
            }
        }
 
    	StringBuilder sb = new StringBuilder();
    	for (int i = 0; i < nonNullBooks.size(); i++) {
    		Book book = nonNullBooks.get(i);
    		sb.append(book).append("\n");
    	}
    	return sb.toString();
    }
 
    public boolean isEmpty() {
        return books.isEmpty();
    }
}
 
@SuppressWarnings("serial")
public class LibraryManagementSystemGUI extends JFrame {
    private final Library library;
    private final JTextArea displayArea;
    private final JTextField titleField, authorField, quantityField, indexField;
    Stack<Book> CurrentBook = new Stack<>();
    Stack<Integer> CurrentIndex = new Stack<>();
 
    public LibraryManagementSystemGUI() {
        library = new Library();
        titleField = new JTextField();
        authorField = new JTextField();
        quantityField = new JTextField();
        indexField = new JTextField();
        displayArea = new JTextArea();
        initializeUI();
    }
 
    private void initializeUI() {
        setTitle("Library Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
 
        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        inputPanel.add(new JLabel("Title:"));
        inputPanel.add(titleField);
        inputPanel.add(new JLabel("Author:"));
        inputPanel.add(authorField);
        inputPanel.add(new JLabel("Quantity:"));
        inputPanel.add(quantityField);
        inputPanel.add(new JLabel("Index:"));
        inputPanel.add(indexField);
 
        // Buttons
        JButton addButton = new JButton("Add Book");
        JButton removeButton = new JButton("Remove Book");
        JButton undoButton = new JButton("Undo");
        JButton searchButton = new JButton("Search Book");
        JButton sortButton = new JButton("Sort Books");
        JButton displayAllButton = new JButton("Display All Books");
        JButton totalBooksButton = new JButton("Total Books");
 
        inputPanel.add(addButton);
        inputPanel.add(removeButton);
 
 
        add(inputPanel, BorderLayout.NORTH);
 
        // Display Area
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        add(scrollPane, BorderLayout.CENTER);
 
        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(searchButton);
        buttonPanel.add(displayAllButton);
        buttonPanel.add(totalBooksButton);
        buttonPanel.add(sortButton);
        buttonPanel.add(undoButton);
        add(buttonPanel, BorderLayout.SOUTH);
 
        // Action Listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String author = authorField.getText();
                String quantity = quantityField.getText();
                String indexText = indexField.getText();
 
                if (!title.isEmpty() && !author.isEmpty() && !quantity.isEmpty()) {
                	Book Empty = new Book("random", "random", "0");
                    Book book = new Book(title, author, quantity);
 
 
 
                    if (!indexText.isEmpty()) {
                        try {
                            int index = Integer.parseInt(indexText);
                            if(library.searchBook(index)==null) {
                            	CurrentBook.push(Empty);
                                CurrentIndex.push(index);
                            } else {
                                CurrentBook.push(library.searchBook(index));
                                CurrentIndex.push(index);
                            }
                            library.insertBook(index, book);
                            displayArea.setText("Book inserted at index " + index + " successfully.");
             
                        } catch (NumberFormatException ex) {
                            library.addBook(book);
                            displayArea.setText("Invalid index. Book added to the end.");
                        }
                    } else {
                        library.addBook(book);
                        CurrentBook.push(Empty);
                        CurrentIndex.push(library.CIndex);
                        displayArea.setText("Book added to the end successfully.");
                    }
 
                    clearInputFields();
                    updateDisplay();
                } else {
                    displayArea.setText("Please fill all fields.");
                }
            }
        });
 
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int index = Integer.parseInt(indexField.getText());
                    int tempQ;
                    String tempQ2;
                    String tempT;
                    String tempA;
                    Book book = library.searchBook(index);
                    int Rquantity = Integer.parseInt(quantityField.getText());
                    if (book != null) {
                    	tempQ = Integer.parseInt(book.getQuantity());
                    	tempT = book.getTitle();
                    	tempA = book.getAuthor();
                    	tempQ = tempQ - Rquantity;
                    	tempQ2 = String.valueOf(tempQ);
                    	CurrentBook.push(book);
                        CurrentIndex.push(index);
                    	Book Replacebook = new Book(tempT, tempA, tempQ2);
                		library.insertBook(index, Replacebook);
                    } else {
                        displayArea.setText("No book found at this index.");
                    }
                    updateDisplay();
                } catch (NumberFormatException ex) {
                    displayArea.setText("Please enter a valid index.");
                }
            }
        });
 
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int index = Integer.parseInt(indexField.getText());
                    Book book = library.searchBook(index);
                    if (book != null) {
                        displayArea.setText("Book found: " + book);
                    } else {
                        displayArea.setText("No book found at this index.");
                    }
                } catch (NumberFormatException ex) {
                    displayArea.setText("Please enter a valid index.");
                }
            }
        });
 
        displayAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateDisplay();
            }
        });
 
        totalBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	int CAP = DynamicArray.capacity();
            	int temp = 0;
            	int sum = 0;
 
            	for(int i=0; i<=CAP; i++ ) {
            		Book book = library.searchBook(i);
            		if(book!=null) {
            			temp = Integer.parseInt(book.getQuantity());
                		sum += temp;
            		}
            	}
                displayArea.setText("Total number of unique books: " + library.getTotalBooks()
                		+ "\nTotal number of books: "+sum);
            }
        });
        
        
        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(!(CurrentBook.isEmpty()) && !(CurrentIndex.isEmpty())) {
                	library.insertBook(CurrentIndex.pop(), CurrentBook.pop());
            	}
            	updateDisplay();
            }
        });
        
 
        sortButton.addActionListener(new ActionListener() {
 
			@Override
			public void actionPerformed(ActionEvent e) {
				 String[] options = { "By Name", "By Quantity" };
				    var selection = JOptionPane.showOptionDialog(null, "How do you wanna sort it?:", "Sorting", 
				                                                      0, 3, null, options, options[0]);
				    if (selection == 0) {
	                    JOptionPane.showMessageDialog(null, "Sorted by Name.");
	                    sortByNameDisplay();
				    }
				    if (selection == 1) { 
				    	JOptionPane.showMessageDialog(null, "Sorted by Quantity");
				    	sortByQuantityDisplay();
				    }
			}
		});
    }
 
 
 
    private void clearInputFields() {
        titleField.setText("");
        authorField.setText("");
        quantityField.setText("");
        indexField.setText("");
    }
 
    private void updateDisplay() {
    	ScanQuantityZero();
        String allBooks = library.getAllBooks();
        if (allBooks.isEmpty()) {
            displayArea.setText("The library is empty.");
        } else {
            displayArea.setText(allBooks);
        }
    }
 
    private void sortByNameDisplay() {
    	String books = library.sortByName();
    	if (books.isEmpty()) {
    		displayArea.setText("There is no books here stupid.");
    	} else {
    		displayArea.setText(books);
    	}
    }
 
    private void sortByQuantityDisplay() {
    	String books = library.sortByQuantity();
    	if (books.isEmpty()) {
    		displayArea.setText("Where did you put the books or did they even exist?");
    	} else {
    		displayArea.setText(books);
    	}
    }
    
	void ScanQuantityZero() {
    	for(int i=0; i<DynamicArray.capacity(); i++) {
    		Book ScannedBook = library.searchBook(i);
    		if(ScannedBook!=null) {
    			if(Integer.parseInt(ScannedBook.getQuantity())<=0) {
        			library.removeBook(i);
        		}
    		}
    	}
    }
	
	void ClearAll() {
		for(int i=0; i<DynamicArray.capacity(); i++) {
			library.removeBook(i);
    	}
	}
 
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LibraryManagementSystemGUI().setVisible(true));
    }
}
