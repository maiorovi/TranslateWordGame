package domain.dto;

import domain.Category;

public class Word {
    private String word;
    private Category category;

    public Word(String word, Category category) {
        this.word = word;
        this.category = category;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
