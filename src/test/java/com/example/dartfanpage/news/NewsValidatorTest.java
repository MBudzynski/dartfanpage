package com.example.dartfanpage.news;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class NewsValidatorTest {

    @Autowired
    private NewsValidator validator;

    @Test
    void shouldNotAddError() {
        //given
        NewsDto newsDto = populateNewsDto();
        //when
        Map<String, String> errors = validator.isValid(newsDto);
        //than
        assertThat(errors.isEmpty()).isTrue();
    }

    @Test
    void shouldAddErrorWhenAuthorIsBlank() {
        //given
        NewsDto newsDto = populateNewsDto();
        newsDto.setAuthor("      ");
        //when
        Map<String, String> errors = validator.isValid(newsDto);
        //than
        assertThat(errors.values().stream().findFirst().get()).isEqualTo("Author of article is required");
    }

    @Test
    void shouldAddErrorWhenAuthorIsNull() {
        //given
        NewsDto newsDto = populateNewsDto();
        newsDto.setAuthor(null);
        //when
        Map<String, String> errors = validator.isValid(newsDto);
        //than
        assertThat(errors.values().stream().findFirst().get()).isEqualTo("Author of article is required");
    }


    @Test
    void shouldAddErrorWhenAuthorIsLowerThan3Char() {
        //given
        NewsDto newsDto = populateNewsDto();
        newsDto.setAuthor("Ds");
        //when
        Map<String, String> errors = validator.isValid(newsDto);
        //than
        assertThat(errors.values().stream().findFirst().get()).isEqualTo("Author of article is required");
    }

    @Test
    void shouldAddErrorWhenTitleIsBlank() {
        //given
        NewsDto newsDto = populateNewsDto();
        newsDto.setTitle("                   ");
        //when
        Map<String, String> errors = validator.isValid(newsDto);
        //than
        assertThat(errors.values().stream().findFirst().get()).isEqualTo("Title must have between 10 and 100 chars");
    }

    @Test
    void shouldAddErrorWhenTitleIsBull() {
        //given
        NewsDto newsDto = populateNewsDto();
        newsDto.setTitle(null);
        //when
        Map<String, String> errors = validator.isValid(newsDto);
        //than
        assertThat(errors.values().stream().findFirst().get()).isEqualTo("Title must have between 10 and 100 chars");
    }

    @Test
    void shouldAddErrorWhenTitleIsTooShort() {
        //given
        NewsDto newsDto = populateNewsDto();
        newsDto.setTitle("ADk");
        //when
        Map<String, String> errors = validator.isValid(newsDto);
        //than
        assertThat(errors.values().stream().findFirst().get()).isEqualTo("Title must have between 10 and 100 chars");
    }

    @Test
    void shouldAddErrorWhenTitleIsOver100Chars() {
        //given
        NewsDto newsDto = populateNewsDto();
        newsDto.setTitle("This is very long titleeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"+
                "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"+
                "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
        //when
        Map<String, String> errors = validator.isValid(newsDto);
        //than
        assertThat(errors.values().stream().findFirst().get()).isEqualTo("Title must have between 10 and 100 chars");
    }

    @Test
    void shouldAddErrorWhenHeadlineIsBlank() {
        //given
        NewsDto newsDto = populateNewsDto();
        newsDto.setHeadline("         ");
        //when
        Map<String, String> errors = validator.isValid(newsDto);
        //than
        assertThat(errors.values().stream().findFirst().get()).isEqualTo("Headline must have between 10 and 300 chars");
    }

    @Test
    void shouldAddErrorWhenHeadlineIsNull() {
        //given
        NewsDto newsDto = populateNewsDto();
        newsDto.setHeadline(null);
        //when
        Map<String, String> errors = validator.isValid(newsDto);
        //than
        assertThat(errors.values().stream().findFirst().get()).isEqualTo("Headline must have between 10 and 300 chars");
    }

    @Test
    void shouldAddErrorWhenHeadlineIsTooShort() {
        //given
        NewsDto newsDto = populateNewsDto();
        newsDto.setHeadline("ADk");
        //when
        Map<String, String> errors = validator.isValid(newsDto);
        //than
        assertThat(errors.values().stream().findFirst().get()).isEqualTo("Headline must have between 10 and 300 chars");
    }

    @Test
    void shouldAddErrorWhenHeadlineIsOver300Chars() {
        //given
        NewsDto newsDto = populateNewsDto();
        newsDto.setHeadline("This is very long head lineeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"+
                "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"+
                "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
        //when
        Map<String, String> errors = validator.isValid(newsDto);
        //than
        assertThat(errors.values().stream().findFirst().get()).isEqualTo("Headline must have between 10 and 300 chars");
    }


    @Test
    void shouldAddErrorWhenTextIsBlank() {
        //given
        NewsDto newsDto = populateNewsDto();
        newsDto.setText("         ");
        //when
        Map<String, String> errors = validator.isValid(newsDto);
        //than
        assertThat(errors.values().stream().findFirst().get()).isEqualTo("Text of article must have between 10 and 1000 chars");
    }

    @Test
    void shouldAddErrorWhenTextIsNull() {
        //given
        NewsDto newsDto = populateNewsDto();
        newsDto.setText(null);
        //when
        Map<String, String> errors = validator.isValid(newsDto);
        //than
        assertThat(errors.values().stream().findFirst().get()).isEqualTo("Text of article must have between 10 and 1000 chars");
    }

    @Test
    void shouldAddErrorWhenTextIsTooShort() {
        //given
        NewsDto newsDto = populateNewsDto();
        newsDto.setText("ADk");
        //when
        Map<String, String> errors = validator.isValid(newsDto);
        //than
        assertThat(errors.values().stream().findFirst().get()).isEqualTo("Text of article must have between 10 and 1000 chars");
    }

    @Test
    void shouldAddErrorWhenTextIsOver1000Chars() {
        //given
        NewsDto newsDto = populateNewsDto();
        newsDto.setText("This is very long texttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt"+
                "tttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttet"+
                "ttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt" +
                "ttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt" +
                "ttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt" +
                "ttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt" +
                "ttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt" +
                "ttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt" +
                "ttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt" +
                "ttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt" +
                "ttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt");
        //when
        Map<String, String> errors = validator.isValid(newsDto);
        //than
        assertThat(errors.values().stream().findFirst().get()).isEqualTo("Text of article must have between 10 and 1000 chars");
    }


    private NewsDto populateNewsDto() {
        NewsDto newsDto = new NewsDto();
        newsDto.setAuthor("Mike");
        newsDto.setTitle("Krzysztof Ratajski przechodzi do ćwierćfinału World Grand Prix");
        newsDto.setHeadline("Jonny Clayton triumfatorem World Grand Prix 2021. W sobotnim finale turnieju pewnie pokonał Gerwyna Price'a 5:1. ");
        newsDto.setText("Clayton miał przewagę ale Price nie dawał za wygraną. Price trzeciego i czwartego seta przegrał w piątym legu. Price utrzymywał w nim średnią na poziomie 115, a Clayton 111 punktów. Jednak Clayton miał zdecydowanie większą przewagę na podwójnych." +
              "Clayton prowadził 3:0 i tak naprawdę miał wszystko pod kontrolą. Czwarty set wygrał Price, jednakże w pozostałej części pojedynku grał już słabo przegrywając po czterech legach. Clayton zakończył mecz checkoutem na poziomie 152 punktów.");
        return newsDto;
    }

}