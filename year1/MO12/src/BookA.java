
import java.util.*;
import java.util.regex.*;
import java.util.stream.*;

public class BookA {
    private String[] authors; // Authors of book (array)
    private String titleName; // Name of book
    private int pages;        // Size of book (in pages)
    private String[] types;   // Types of book (array)
    private String pubName;   // Publisher of book

    public BookA(){}
    public BookA(String input){
        read(input);
    }

    private void read(String input){
        String[] token = input.trim().split("\\s*,\\s*");
        authors = takeComp(token[0]);
        titleName=token[1];
        Pattern nm = Pattern.compile("[+-]?\\d+");
        if(Pattern.matches("[+-]?\\d+", token[2]))
            pages = Integer.parseInt(token[2]);
        types =  takeComp(token[3]);
        pubName=token[4];
    }
    private String[] takeComp(String inp){
        String[] au = inp.split("\\s*[:]\\s*");
        return au;
    }
    public String[] getAuthors() { return authors; }
    public String getTitleName() { return titleName; }
    public String[] getTypes() { return types; }
    public int getPages() {	return pages;}
    public String getPubName() { return pubName;	}

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        BookA other = (BookA) obj;
        //if (!Arrays.asList(authors).equals(Arrays.asList(other.authors))) return false;
        if(!Arrays.equals(authors,other.authors))return false;
        if(!titleName.equals(other.titleName))return false;
        if(pages != other.pages)return false;
        //if (!Arrays.asList(types).equals(Arrays.asList(other.types))) return false;
        if(!Arrays.equals(types,other.types))return false;
        if(!pubName.equals(other.pubName))return false;
        return true;
    }

    public String toString() {
        return "Book [authors=" + Arrays.toString(authors) + ", titleName=" +
                titleName + ", "	+ "pages=" + pages + ", types=" + Arrays.toString(types)
                + ", pubName=" + pubName + "]";
    }

    public Stream <BookA> library() { return Stream.of(libraryStr()).map(BookA::new); }

    public static String[] libraryStr(){
        String[] informS =
                {"Buchman S. : Heydemark W. , 1977! , 140,  history: biography , Abatis Publishers ",
                        "Buchman S. , 200 Years of German Humor , 107, history  , Schadenfreude Press ",
                        "Kells C. , Ask Your System Administrator , 1226, computer: dataBase , Core Dump Books ",
                        "Hull H. : Hull K. , But I Did It Unconsciously , 510  , psychology  , Abatis Publishers ",
                        "Heydemark W. , How About Never? , 473,  biography , Abatis Publishers ",
                        "Heydemark W. : Hull K. , I Blame My Mother , 333, biography  , Schadenfreude Press ",
                        "Kellsey , Just Wait Until After School , 86,  children : fantasy , Abatis Publishers ",
                        "Kellsey , Kiss My Boo-Boo , 55,  children , Abatis Publishers ",
                        "Heydemark W. , Not Without My Faberge Egg , 523,  history: biography , Abatis Publishers ",
                        " Kellsey : Hull H. : Hull K. , Perhaps It's a Glandular Problem , 826  , psychology  , Abatis Publishers ",
                        "Heydemark W. , Spontaneous Not Annoying , 507 ,  biography , Abatis Publishers ",
                        "Buchman S. , What Are The Civilian Applications? , 802, history : fantasy  , Schadenfreude Press "
                };
        return informS;
    }
}