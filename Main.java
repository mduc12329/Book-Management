import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static BookManager bookMan = new BookManager();

    public static void main(String[] args) {
        try {
			bookMan.loadFromFile();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        boolean flag = false;
        while (!flag) {
            printMenu();
            System.out.print("Your option: ");
            int input = sc.nextInt();
            sc.nextLine();
            switch (input) {
                case 1:
                    bookMan.printBooks(bookMan.getBooks());
                    break;
                case 2:
                    if (bookMan.add(create())) {
                        System.out.println("Added successfully.");
                    } else {
                        System.out.println("Duplicated ID!");
                    }
                    break;
                case 3:
                    if (edit()) {
                        System.out.println("Updated successfully.");
                    } else {
                        System.out.println("Invalid ID!");
                    }
                    break;
                case 4:
                    if (delete()) {
                        System.out.println("Deleted successfully.");
                    } else {
                        System.out.println("Invalid ID!");
                    }
                    break;
                case 5:
                    search();
                    break;
                case 6:
                    bookMan.sortDescByPrice();
                    System.out.println("After sorting:");
                    bookMan.printBooks(bookMan.getBooks());
                    break;
                case 0:
                    System.out.println("Saving to file...");
                    bookMan.saveToFile();
                    System.out.println("Bye!");
                    flag = true;
                    break;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    public static void printMenu() {
        System.out.println("-----------------------------------");
        System.out.println("1. list all books");
        System.out.println("2. add a new book");
        System.out.println("3. edit book");
        System.out.println("4. delete a book");
        System.out.println("5. search books by name");
        System.out.println("6. sort books descending by price");
        System.out.println();
        System.out.println("0. save & exit");
        System.out.println("-----------------------------------");
    }

    public static Book create() {
        System.out.print("Enter book id: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter book name: ");
        String name = sc.nextLine();
        System.out.print("Enter book price: ");
        double price = sc.nextDouble();
        sc.nextLine();
        return new Book(id, name, price);
    }

    public static boolean edit() {
        System.out.print("Enter book id: ");
        int id = sc.nextInt();
        sc.nextLine();
        Book editBook;
        if ((editBook = bookMan.getBookById(id)) != null) {
            System.out.print("Enter book name: ");
            String name = sc.nextLine();
            editBook.setName(name);
            System.out.print("Enter book price: ");
            double price = sc.nextDouble();
            sc.nextLine();
            editBook.setPrice(price);
            return true;
        }
        return false;
    }

    public static boolean delete() {
        System.out.print("Enter book id: ");
        int id = sc.nextInt();
        sc.nextLine();
        Book deletingBook;
        if ((deletingBook = bookMan.getBookById(id)) != null) {
            bookMan.delete(deletingBook);
            return true;
        }
        return false;
    }

    public static void search() {
        System.out.print("Enter keyword: ");
        String searchWord = sc.nextLine();
        ArrayList<Book> listBook;
        if ((listBook = bookMan.searchByName(searchWord)) != null) {
            bookMan.printBooks(listBook);
        } else {
            System.out.println("(empty)");
        }
    }
}
