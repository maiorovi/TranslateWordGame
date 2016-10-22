package domain;

import dao.WordsDao;
import domain.dto.Word;
import domain.enums.Langauage;
import com.google.common.base.Preconditions;
import validators.AnswerValidator;

import java.util.List;

public class TranslationGame {
    private Langauage source;
    private Langauage dest;
    private WordsDao wordsDao;
    private AnswerValidator answerValidator;
    private Category category;


    private TranslationGame() {

    }

    public List<Word> startGame(Category category) {
        return wordsDao.findWordsByCategory(category);
    }

    public Langauage getSource() {
        return source;
    }

    public Langauage getDest() {
        return dest;
    }

    public WordsDao getWordsDao() {
        return wordsDao;
    }

    public AnswerValidator getAnswerValidator() {
        return answerValidator;
    }

    public Category getCategory() {
        return category;
    }

    public static TranslationGameBuilder aGameBuilder() {
        return new TranslationGameBuilder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TranslationGame that = (TranslationGame) o;

        if (source != that.source) return false;
        if (dest != that.dest) return false;
        if (!wordsDao.equals(that.wordsDao)) return false;
        if (!answerValidator.equals(that.answerValidator)) return false;
        return category.equals(that.category);

    }

    @Override
    public int hashCode() {
        int result = source.hashCode();
        result = 31 * result + dest.hashCode();
        result = 31 * result + wordsDao.hashCode();
        result = 31 * result + answerValidator.hashCode();
        result = 31 * result + category.hashCode();
        return result;
    }

    public static class TranslationGameBuilder {
        private TranslationGame translationGame = new TranslationGame();

        private TranslationGameBuilder(){}

        public TranslationGameBuilder withSourceLanguage(Langauage srcLang) {
            translationGame.source = srcLang;
            return this;
        }

        public TranslationGame build() {
            Preconditions.checkNotNull(translationGame.source, "source is mandatory field");
            return translationGame;
        }

        public TranslationGameBuilder withDestinationLanguage(Langauage destLang) {
            translationGame.dest = destLang;
            return this;
        }

        public TranslationGameBuilder withWordsDao(WordsDao wordsDao) {
            translationGame.wordsDao = wordsDao;
            return this;
        }


        public TranslationGameBuilder withAnswerValidator(AnswerValidator answerValidator) {
            translationGame.answerValidator = answerValidator;
            return this;
        }

        public TranslationGameBuilder withCategory(Category category) {
            translationGame.category = category;
            return this;
        }
    }
}
