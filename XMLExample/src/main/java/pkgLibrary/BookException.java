package pkgLibrary;

public class BookException extends Exception{
	private String bookID;
	public BookException(String bookID) {
		super("Book"+bookID+"can not be found");
	}
}