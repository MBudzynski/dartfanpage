package com.example.dartfanpage.news.comment;

import com.example.dartfanpage.news.NewsDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CommentValidatorTest {

    @Autowired
    private CommentValidator validator;

    @Test
    void shouldNotAddError() {
        //given
        //when
        Map<String, String> errors = validator.isValid("This is a new comment");
        //than
        assertThat(errors.isEmpty()).isTrue();
    }

    @Test
    void shouldAddErrorWhenAuthorIsBlank() {
        //given
        //when
        Map<String, String> errors = validator.isValid("        ");
        //than
        assertThat(errors.values().stream().findFirst().get()).isEqualTo("Text of comment is required!");
    }

    @Test
    void shouldAddErrorWhenAuthorIsEmpty() {
        //given
        //when
        Map<String, String> errors = validator.isValid("");
        //than
        assertThat(errors.values().stream().findFirst().get()).isEqualTo("Text of comment is required!");
    }

    @Test
    void shouldAddErrorWhenAuthorIsNull() {
        //given
        //when
        Map<String, String> errors = validator.isValid(null);
        //than
        assertThat(errors.values().stream().findFirst().get()).isEqualTo("Text of comment is required!");
    }

}