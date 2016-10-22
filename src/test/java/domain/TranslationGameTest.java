package domain;

import dao.WordsDao;
import domain.dto.Word;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import validators.AnswerValidator;

import java.util.Arrays;
import java.util.List;

import static domain.enums.Langauage.ENGLISH;
import static domain.enums.Langauage.RUSSIAN;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TranslationGameTest {
    @Mock private WordsDao wordsDao;
    @Mock private Category category;
    @Mock private AnswerValidator answerValidator;

    @Test
    public void loadsWordsForGivenLanguageAndCategoryFromDatabase() throws Exception {
        TranslationGame game = buildTranslationGame();
        List<Word> wordsList = Arrays.asList(new Word("One",category), new Word("Dog",category));
        when(wordsDao.findWordsByCategory(category)).thenReturn(wordsList);

        assertThat(game.startGame(category))
                .extracting(w -> w.getWord())
                .containsExactly("One", "Dog");
    }


    private TranslationGame buildTranslationGame() {
        return TranslationGame.aGameBuilder()
                .withSourceLanguage(ENGLISH)
                .withDestinationLanguage(RUSSIAN)
                .withWordsDao(wordsDao)
                .withAnswerValidator(answerValidator)
                .withCategory(category)
                .build();
    }
}
