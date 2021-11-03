package com.example.dartfanpage.web;

import com.example.dartfanpage.news.News;
import com.example.dartfanpage.news.NewsDto;
import com.example.dartfanpage.news.NewsRepository;
import com.example.dartfanpage.news.comment.Comment;
import com.example.dartfanpage.news.comment.CommentRepository;
import com.example.dartfanpage.news.picture.Picture;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class MainPageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private NewsRepository repository;

    @Autowired
    private CommentRepository comRepository;

    @Test
    @Transactional
    void shouldDisplayMainPageWithNews() throws Exception {
        //given
        News newNews = createNewsWithOneCommentAndEmptyPicturesList();
        repository.save(newNews);
        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(view().name("main.html"))
                .andExpect(model().attributeExists("news"))
                .andExpect(model().attributeExists("userName"))
                .andExpect(model().attributeExists("activePage"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn();
        //than
        List<NewsDto> newsList = objectMapper.convertValue(mvcResult
                .getModelAndView().getModel().get("news"), new TypeReference<List<NewsDto>>() {});
        NewsDto newsDto = newsList.stream().filter(news -> news.getId().equals(newNews.getId())).findFirst().get();

        assertThat(newsDto).isNotNull();
        assertThat(newsDto.getId()).isEqualTo(newNews.getId());
        assertThat(newsDto.getAuthor()).isEqualTo(newNews.getAuthor());
        assertThat(newsDto.getText()).isEqualTo(newNews.getText());
        assertThat(newsDto.getHeadline()).isEqualTo(newNews.getHeadline());
        assertThat(newsDto.getTitle()).isEqualTo(newNews.getTitle());
        assertThat(newsDto.getComments().get(0).getText()).isEqualTo(newNews.getComments().get(0).getText());
        assertThat(newsDto.getComments().get(0).getAuthor()).isEqualTo(newNews.getComments().get(0).getAuthor());
        assertThat(newsDto.getComments().get(0).getDataTime()).isEqualTo(newNews.getComments().get(0).getDataTime());
        assertThat(newsDto.getComments().get(0).getId()).isEqualTo(newNews.getComments().get(0).getId());
    }

    @Test
    @Transactional
    @WithMockUser(username="admin",roles={"USER","ADMIN"})
    void shouldDisplayAddArticlePageWithNewNews() throws Exception {
        //given
        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/addArticle"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(view().name("addArticle.html"))
                .andExpect(model().attributeExists("news"))
                .andExpect(model().attributeExists("activePage"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn();
        //than
        NewsDto newsDto = objectMapper.convertValue(mvcResult
                .getModelAndView().getModel().get("news"), NewsDto.class);
        assertThat(newsDto).isNotNull();
        assertThat(newsDto.getId()).isNull();
        assertThat(newsDto.getAuthor()).isNull();
        assertThat(newsDto.getText()).isNull();
        assertThat(newsDto.getTitle()).isNull();
        assertThat(newsDto.getHeadline()).isNull();
    }

    @Test
    @Transactional
    void shouldRedirectToMainPageIfNewsWithIdNotExist() throws Exception {
        //given
        News newNews = createNewsWithOneCommentAndEmptyPicturesList();
        repository.save(newNews);
        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/news/" + newNews.getId()+100L))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(redirectedUrl("/"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andReturn();
        //than
    }

    @Test
    @Transactional
    void shouldDisplayPageWithNewsIfExist() throws Exception {
        //given
        News newNews = createNewsWithOneCommentAndEmptyPicturesList();
        repository.save(newNews);
        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/news/" + newNews.getId()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(view().name("newsPage.html"))
                .andExpect(model().attributeExists("news"))
                .andExpect(model().attributeExists("userName"))
                .andExpect(model().attributeExists("activePage"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn();
        //than
        NewsDto newsDto = objectMapper.convertValue(mvcResult
                .getModelAndView().getModel().get("news"), NewsDto.class);

        assertThat(newsDto).isNotNull();
        assertThat(newsDto.getId()).isEqualTo(newNews.getId());
        assertThat(newsDto.getAuthor()).isEqualTo(newNews.getAuthor());
        assertThat(newsDto.getText()).isEqualTo(newNews.getText());
        assertThat(newsDto.getHeadline()).isEqualTo(newNews.getHeadline());
        assertThat(newsDto.getTitle()).isEqualTo(newNews.getTitle());
        assertThat(newsDto.getComments().get(0).getText()).isEqualTo(newNews.getComments().get(0).getText());
        assertThat(newsDto.getComments().get(0).getAuthor()).isEqualTo(newNews.getComments().get(0).getAuthor());
        assertThat(newsDto.getComments().get(0).getDataTime()).isEqualTo(newNews.getComments().get(0).getDataTime());
        assertThat(newsDto.getComments().get(0).getId()).isEqualTo(newNews.getComments().get(0).getId());
    }

    @Test
    @Transactional
    @WithMockUser(username="admin",roles={"USER","ADMIN"})
    void shouldAddNewCommentToArticle() throws Exception {
        //given
        News newNews = createNewsWithOneCommentAndEmptyPicturesList();
        repository.save(newNews);
        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/addComment")
                        .param("id", newNews.getId().toString())
                        .param("text", "This is new comment"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(redirectedUrl("/news/" + newNews.getId()))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andReturn();
        //than
        News news = repository.findById(newNews.getId()).get();
        assertThat(news).isNotNull();
        assertThat(news.getId()).isEqualTo(newNews.getId());
        assertThat(news.getAuthor()).isEqualTo(newNews.getAuthor());
        assertThat(news.getText()).isEqualTo(newNews.getText());
        assertThat(news.getTitle()).isEqualTo(newNews.getTitle());
        assertThat(news.getHeadline()).isEqualTo(newNews.getHeadline());
        assertThat(news.getComments().get(1).getText()).isEqualTo("This is new comment");
    }

    @Test
    @Transactional
    @WithMockUser(username="admin",roles={"USER","ADMIN"})
    void shouldNotAddBlankCommentAndShowErrorMessage() throws Exception {
        //given
        News newNews = createNewsWithOneCommentAndEmptyPicturesList();
        repository.save(newNews);
        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/addComment")
                        .param("id", newNews.getId().toString())
                        .param("text", "    "))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(view().name("newsPage.html"))
                .andExpect(model().attributeExists("news"))
                .andExpect(model().attributeExists("commentTextError"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();
        //than
        News news = repository.findById(newNews.getId()).get();
        assertThat(news).isNotNull();
        assertThat(news.getId()).isEqualTo(newNews.getId());
        assertThat(mvcResult.getModelAndView().getModel().get("commentTextError")).isEqualTo("Treść komentarza jest wymagana");
        assertThat(news.getText()).isEqualTo(newNews.getText());
        assertThat(news.getTitle()).isEqualTo(newNews.getTitle());
        assertThat(news.getHeadline()).isEqualTo(newNews.getHeadline());
    }



    private News createNewsWithOneCommentAndEmptyPicturesList(){
        List<Picture> pictureList = new ArrayList<>();
        List<Comment> commentList = new ArrayList<>();
        commentList.add(new Comment().toBuilder().author("Michał").dataTime(LocalDateTime.now()).text("Gratulacje").build());
        News newNews = new News().toBuilder()
                .author("Michal")
                .headline("Add new news")
                .pictures(pictureList)
                .title("News is created")
                .text("lorem iposume .... and something else").publicationDate(LocalDate.now())
                .comments(commentList).build();
        return newNews;
    }

}