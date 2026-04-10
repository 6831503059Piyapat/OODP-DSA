package Book;

public class bookS {
   private String genre;
   private String title;
   private boolean isAvailable;
   private String id;
    public bookS(String title, String genre, String id, boolean isAvailable) {
        this.title = title;
        this.genre = genre;
        this.id = id;
        this.isAvailable = isAvailable;
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
      public boolean isAvailable() {
         return isAvailable;
      }
      


  
}
