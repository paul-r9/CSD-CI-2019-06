package dev.emergent;

public class ISBNFinder {

    private BookInfoProvider isbnService = null;

    public ISBNFinder() {
        this(ISBNService.getInstance());
    }

    public ISBNFinder(BookInfoProvider bookInfoProvider) {
        isbnService = bookInfoProvider;
    }

    public static boolean validateChecksum(String ISBN) {
        int checksum = 0;

        if (ISBN == null) return false;
        if (ISBN.length() == 10) {
            for (int i = 0; i < ISBN.length(); ++i) {
                char ch = ISBN.charAt(i);
                int number = Character.getNumericValue(ch);
                checksum += number * (i + 1);
            }

            return ((checksum / 11) == ISBN.charAt(ISBN.length() - 1));
        } else if (ISBN.length() == 13) {
            for (int i = 0; i < ISBN.length() - 1; ++i) {
                char ch = ISBN.charAt(i);
                int number = Character.getNumericValue(ch);

                if (i % 2 == 0) {
                    checksum += number;
                } else {
                    checksum += number * 3;
                }
            }

            return ((10 -(checksum % 10)) == Character.getNumericValue(ISBN.charAt(ISBN.length() - 1)));
        }

        return false;
    }

    public BookInfo lookup(String ISBN) {

        ISBN = ISBN.replaceAll(" ", "");
        ISBN = ISBN.replaceAll("-", "");

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
