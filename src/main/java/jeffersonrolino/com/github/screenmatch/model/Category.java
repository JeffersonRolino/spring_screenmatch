package jeffersonrolino.com.github.screenmatch.model;

public enum Category {
    ACTION("Action", "Ação"),
    COMEDY("Comedy", "Comédia"),
    CRIME("Crime", "Crime"),
    DRAMA("Drama", "Drama"),
    FANTASY("Fantasy", "Fantasia"),
    HORROR("Horror", "Horror"),
    MYSTERY("Mistery", "Mistério"),
    ROMANCE("Romance", "Romance"),
    THRILLER("Thriller", "Suspense"),
    WESTERN("Western", "Faroeste");

    private String omdbCategory;
    private String portugueseCategory;

    Category(String omdbCategory, String portugueseCategory) {
        this.omdbCategory = omdbCategory;
        this.portugueseCategory = portugueseCategory;
    }

    public static Category fromString(String text) {
        for (Category category : Category.values()) {
            if (category.omdbCategory.equalsIgnoreCase(text)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }

    public static Category fromPorgueseCategory(String text) {
        for (Category category : Category.values()) {
            if (category.portugueseCategory.equalsIgnoreCase(text)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }
}
