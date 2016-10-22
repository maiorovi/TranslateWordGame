package domain;

import dao.WordsDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.internal.matchers.Null;
import org.mockito.runners.MockitoJUnitRunner;
import validators.AnswerValidator;

import java.util.function.Function;

import static domain.TranslationGame.TranslationGameBuilder;
import static org.assertj.core.api.Assertions.assertThat;
import static domain.enums.Langauage.ENGLISH;
import static domain.enums.Langauage.RUSSIAN;

@RunWith(MockitoJUnitRunner.class)
public class TranslationGameBuilderTest {

    private TranslationGameBuilder gameBuilder;
    @Mock private WordsDao wordsDao;
    @Mock private AnswerValidator validator;
    @Mock private Category category;

    @Before
    public void setUp() {
        gameBuilder = TranslationGame.aGameBuilder();
    }

    @Test
    public void populatesCorretlySourceLanguage() {
        TranslationGame translationGame = buildGame(builder -> builder.withSourceLanguage(ENGLISH));

        assertThat(translationGame.getSource()).isEqualTo(ENGLISH);
    }

    @Test
    public void pupulatesCorrectlyDestLanguage() {
        TranslationGame translationGame = buildGame(builder -> builder.withDestinationLanguage(RUSSIAN));

        assertThat(translationGame.getDest()).isEqualTo(RUSSIAN);
    }

    @Test
    public void populatesCorretlyWordsDao() throws Exception {
        TranslationGame translationGame = buildGame( builder -> builder.withWordsDao(wordsDao));

        assertThat(translationGame.getWordsDao()).isNotNull();
    }

    @Test
    public void populatesAnswerValidatorCorrectly() throws Exception {
        TranslationGame translationGame = buildGame( builder -> builder.withAnswerValidator(validator));

        assertThat(translationGame.getAnswerValidator()).isNotNull();
    }

    @Test
    public void populatesCategoryCorrectly() throws Exception {
        TranslationGame translationGame = buildGame( builder -> builder.withCategory(category));

        assertThat(translationGame.getCategory()).isNotNull();
    }

    @Test(expected = NullPointerException.class)
    public void sourceLangCantBeNullOrExceptionIsThrown() throws Exception {
        TranslationGame translationGame = buildGame( builder -> builder);
    }

    private TranslationGame buildGame(Function<TranslationGameBuilder, TranslationGameBuilder> function) {
        return function.apply(gameBuilder).build();
    }
}
