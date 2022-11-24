import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class BookManager {
    public ArrayList<Book> bookArray;

    public BookManager() {
    	this.bookArray = new ArrayList<>();
    }

    public ArrayList<Book> getBooks() {
        return this.bookArray;
    }

    public void loadFromFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("books.txt"));
        System.out.println("Loading books...");
        while (sc.hasNext()) {
            int index = sc.nextInt();
            String line = sc.nextLine().trim();
            String name = line.substring(0, 45).trim();
            Double price = Double.parseDouble(line.substring(46).trim());
            Book book = new Book(index, name, price);
            this.bookArray.add(book);
        }
    }

    public void printBooks(ArrayList<Book> bookArray) {
        if (!bookArray.isEmpty()) {
            System.out.printf("%-5s %-45s %-10s\n", "ID", "Name", "Price");
            for (Book book : bookArray) {
                System.out.println(book);
            }
        } else {
            System.out.println("(empty)");
        }
    }

    public boolean add(Book book) {
        for (Book addedBook : this.bookArray) {
            if ((book.getId() == addedBook.getId())) {
                return false;
            }
        }
        this.bookArray.add(book);
        return true;
    }

    public Book getBookById(int id) {
        for (Book currentBook : this.bookArray) {
            if (currentBook.getId() == id) {
                return currentBook;
            }
        }
        return null;
    }

    public void delete(Book book) {
        this.bookArray.remove(book);
    }

    public void sortDescByPrice() {
    	int i, j;
		for (i = 0; i < bookArray.size(); i++) {
			for (j = i + 1; j < bookArray.size(); j++) {
				if (bookArray.get(i).getPrice() < bookArray.get(j).getPrice()) {
					Collections.swap(bookArray,i,j);
				}
			}
		}
    }

    public ArrayList<Book> searchByName(String keyword) {
        ArrayList<Book> matches = new ArrayList<>();
        for (Book book : this.bookArray) {
			if (book.getName().contains(keyword)) {
				matches.add(book);
			}
		}
        return matches;
    }

    public void saveToFile() {
    	try {
			File file = new File("books.txt");
			Scanner reader = new Scanner(file);
			PrintWriter writer = new PrintWriter("books.txt");

			for(Book book:bookArray) {
				writer.println(book.toString());
			}
			reader.close();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
