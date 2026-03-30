package Book;

public class bookS {
   private String genre;
   private String title;
   private boolean isBorrow;
   private String id;
    public bookS(String title, String genre, String id, boolean isBorrow) {
        this.title = title;
        this.genre = genre;
        this.id = id;
        this.isBorrow = isBorrow;
    }
    public String getGenre() {
        return genre;
    }
      public String getTitle() {
         return title;
      }
      public String getId() {
         return id;
      }

    public boolean isBorrow() {
        return isBorrow;
    }


  
}
