package dev.emergent;

public class ISBNFinder {

    private BookInfoProvider isbnService = null;

    public ISBNFinder() {
        this(ISBNService.getInstance());
    }

    public ISBNFinder(BookInfoProvider bookInfoProvider) {
        isbnService = bookInfoProvider;
    }

    public BookInfo lookup(String ISBN) {

        ISBN = ISBN.replaceAll(" ","");
        ISBN = ISBN.replaceAll("-","");

        if (ISBN.length() == 13 || ISBN.length() == 10) {
            BookInfo bookInfo = isbnService.retrieve(ISBN);
            //return isbnService.retrieve(ISBN);

            if (null == bookInfo) {
                return new BookInfo("Title not found");
            }

            return bookInfo;
        } else {
            return new BookInfo("ISBN must be 10 or 13 characters in length");
        }



      }
    }
