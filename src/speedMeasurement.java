import java.util.*;
import java.lang.*;
import java.io.*;


class Book
{
    String author;
    String title;

    public Book(String author, String title) {
        this.author = author;
        this.title = title;
    }


    public String getAuthor() {
        return this.author;
    }

    public String getTitle() {
        return this.title;
    }

    public String toString() {
        return "Title: \"" + title + "\", author: " + author;
    }

    public int hashCode() {
        return getAuthor().length();
    }

    public boolean equals(Object o) {
        final Book b = (Book) o;
        return this.author.equals(b.author) && this.title.equals(b.title);
    }
}


class Reader
{
    private String firstName;
    private String lastName;

    public Reader(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public String getFirstName(){
        return this.firstName;
    }

    public String getLastName(){
        return this.lastName;
    }


    public int hashCode() {
        return getFirstName().length() + getLastName().length();
    }

    public boolean equals(Object o) {
        final Reader r = (Reader) o;
        return this.firstName.equals(r.firstName) && this.lastName.equals(r.lastName);
    }

    public String toString() {
        return firstName + " " + lastName;
    }
}



class SpeedMeasurement
{
    public static void main(String[] args) throws java.lang.Exception
    {
        List<Book> books = new LinkedList<>();

        Book firstBook = null;
        Book lastBook = null;

        for( int i = 0; i < 6000; i++) {
            Book book = new Book("Author" + i, "Title " + i);
            books.add(book);
            if( i == 0) {
                firstBook = book;
            }
            lastBook = book;
        }

        // usuwanie z początku
        long startTime = System.currentTimeMillis();
        books.remove(firstBook);
        long endTime = System.currentTimeMillis();
        System.out.println("Removing first element has taken: " + (endTime-startTime) + "ms");

        // usuwanie z końca
        startTime = System.currentTimeMillis();
        books.remove(lastBook);
        endTime = System.currentTimeMillis();
        System.out.println("Removing last element has taken: " + (endTime-startTime) + "ms");

        // wstawianie na początek
        startTime = System.currentTimeMillis();
        books.add(0, new Book("abc", "dxa"));
        endTime = System.currentTimeMillis();
        System.out.println("Adding first element has taken: " + (endTime-startTime) + "ms");

        // wstawienie na koniec
        startTime = System.currentTimeMillis();
        books.add(books.size() - 1, new Book("abc", "dxa"));
        endTime = System.currentTimeMillis();
        System.out.println("Adding last element has taken: " + (endTime-startTime) + "ms");


        Map<Reader, List<Book>> readerBookMap = new HashMap<>();
        Random random = new Random();
        Reader toFind = null;

        for(int i = 0; i < 1000; i++) {
            Reader reader = new Reader("Reader's first name is: " + i, "Reader's last name is: " + i);
            if( i == 1) {
                toFind = reader;
            }
            int bookToGenerate = random.nextInt(10);
            List<Book> books2 = generateBooks(bookToGenerate);
        }


        for(Map.Entry<Reader, List<Book>> readerBooks : readerBookMap.entrySet()){
            System.out.println("The Value is: " + readerBooks.getKey());
            for( Book book : readerBooks.getValue() ) {
                System.out.println("* " + book);
            }
        }

        //searching element by the key
        startTime = System.currentTimeMillis();
        readerBookMap.get(toFind);
        endTime = System.currentTimeMillis();
        System.out.println("Searching element by the key has taken: " + (endTime-startTime) + "ms");

        //adding an element to the HashMap
        startTime = System.currentTimeMillis();
        readerBookMap.put(toFind, books);
        endTime = System.currentTimeMillis();
        System.out.println("Adding element by the key has taken: " + (endTime-startTime) + "ms");

        //deleting an element from the HashMap   
        startTime = System.currentTimeMillis();
        readerBookMap.remove(toFind);
        endTime = System.currentTimeMillis();
        System.out.println("Deleting element in the HashMap has taken: " + (endTime-startTime) + "ms");
    }

    public static List<Book> generateBooks(int count) {
        List<Book> books = new ArrayList<>();
        for(int i = 0; i < count; i++) {
            books.add(new Book("Author " + i,   "Title " + i));
        }
        return books;
    }
}