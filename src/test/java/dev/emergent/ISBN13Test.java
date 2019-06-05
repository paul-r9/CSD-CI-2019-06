package dev.emergent;

import org.junit.Test;

import static org.junit.Assert.*;

public class ISBN13Test {

    @Test
    public void ISBN_shorterThan13Characters_ReturnsInvalidBookInfo() {

        // Arrange
        String shortISBN = "12345";

        // Act
        ISBNFinder sut = new ISBNFinder();
        BookInfo actual = sut.lookup(shortISBN);

        // Assert
        assertEquals("ISBN must be 10 or 13 characters in length", actual.title);
    }

    @Test
    public void ISBN_longerThan13Characters_ReturnsInvalidBookInfo() {

        // Arrange
        String longISBN = "1234567890BCDEF";

        // Act
        ISBNFinder sut = new ISBNFinder();
        BookInfo actual = sut.lookup(longISBN);

        // Assert
        assertEquals("ISBN must be 10 or 13 characters in length", actual.title);
    }

    @Test
    public void ISBN_BookNotFound() {

        String unknownISBN = "0553562614";

        ISBNFinder sut = new ISBNFinder();
        BookInfo actual = sut.lookup(unknownISBN);

        assertEquals("Title not found", actual.title);
    }

    @Test
    public void ISBN_BookFound() {
        String ISBN = "9780321146533";

        ISBNFinder sut = new ISBNFinder();
        BookInfo actual = sut.lookup(ISBN);

        BookInfo expected = new BookInfo("Test Driven Development by Example", "Kent Beck", "0321146530", "9780321146533");

        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void ISBN_BookFound_WithSpaces(){
        String ISBN = "978 032 1146533";

        ISBNFinder sut = new ISBNFinder();
        BookInfo actual = sut.lookup(ISBN);

        BookInfo expected = new BookInfo("Test Driven Development by Example", "Kent Beck", "0321146530", "9780321146533");

        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void ISBN_BookFound_WithDashes(){
        String ISBN = "978-032-1146533";

        ISBNFinder sut = new ISBNFinder();
        BookInfo actual = sut.lookup(ISBN);

        BookInfo expected = new BookInfo("Test Driven Development by Example", "Kent Beck", "0321146530", "9780321146533");

        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void ISBN_BookFound_ValidateCheckSum(){
        String ISBN = "9780321146533";

        ISBNFinder sut = new ISBNFinder();
        boolean actual = sut.validateChecksum(ISBN);

        assertTrue(actual);
    }
}
