package LibraryBooksAPITests.payload;

public class LibraryBookPayloads {

    public static String addBookBody(String bookName,String isbn, String aisle, String author) {
        String payload;
        payload =  "{\n" +
                "  \"name\" : \""+bookName+"\",\n" +
                "  \"isbn\": \""+isbn+"\",\n" +
                "  \"aisle\":\""+aisle+"\",\n" +
                "  \"author\":\""+author+"\"\n" +
                "}";
        return payload;
    }
}
