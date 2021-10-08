package com.example.dartfanpage;

import com.example.dartfanpage.downloadFiles.FileToDownload;
import com.example.dartfanpage.downloadFiles.FileToDownloadRepository;
import com.example.dartfanpage.news.News;
import com.example.dartfanpage.news.NewsDto;
import com.example.dartfanpage.news.NewsRepository;
import com.example.dartfanpage.news.comment.Comment;
import com.example.dartfanpage.news.picture.Picture;
import com.example.dartfanpage.news.picture.PictureDto;
import com.example.dartfanpage.users.Role;
import com.example.dartfanpage.users.RoleRepository;
import com.example.dartfanpage.users.User;
import com.example.dartfanpage.users.UserRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataSeed implements InitializingBean {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @Autowired
    private FileToDownloadRepository fileToDownloadRepository;

    @Override
    public void afterPropertiesSet() throws Exception {
        populateRole();
        populateUsers();
        populateFileToDownload();
        populateArticle();
    }

    private void populateArticle() {
        if (newsRepository.count() > 0) {
            return;
        }

        List<Picture> pictures1 = new ArrayList<>();

        News news0 = new News().toBuilder().author("Admin")
                .publicationDate(LocalDate.of(2021,9,17))
                .mainPicture("maxresdefault.jpg")
                .pictures(pictures1).title("Zakończenie Nordic Darts Masters 2021")
                .headline("Jutro odbęda się ćwierćfinały, półfinały i finał Nordic Darts Masters 2021!!!")
                .text("W dniu jutrzejszym czeka nas duża dawka emocji. W jednym dniu będą rozgrywane ćwierćfinały, półfinały i finał Nordic Darts Masters 2021. " +
                        "Będzie to bardzo duże obciążenie dla zawodników. Niestety w zawodach zabraknie Krzysztofa Ratajskiego. Serdecznie zapraszamy do oglądania!").build();

        News news1 = new News().toBuilder().author("Admin")
                .title("Rozpoczęcie World Grand Prix 2021")
                .publicationDate(LocalDate.of(2021,10,1))
                .pictures(pictures1)
                .mainPicture("Darts-World-Grand-Prix-Prize-Money.jpg")
                .headline("W najbliższą niedzielę o godzinie 19:00 rozpocznie się kolejna edycja World Grand Prix 2021")
                .text("Transmisja będzie prowadzona na żywo na kanale TVP Sport. Wśród uczestników pojawi się również Krzysztof Ratajski." +
                        "Zapraszamy wszystkich fanów Darta do oglądanie i kibicowania.").build();

        News news2 = new News().toBuilder().author("Admin")
                .title("World Grand Prix 2021 rozpoczęte! Rundę 1 czas zacząć!")
                .publicationDate(LocalDate.of(2021,10,3))
                .pictures(pictures1)
                .mainPicture("Darts-World-Grand-Prix-Prize-Money.jpg")
                .headline("Dzisiaj o godzinie 19:00 rozpocznie się 1 Runda World Grand Prix 2021")
                .text("Transmisja będzie prowadzona na żywo na kanale TVP Sport. W dniu dzisiejszym będziemy oglądać pojedynki 8 par graczy." +
                        "Gra będzie prowadzona z zasadą double in. W dniu dzisiejszym będziemy mogli oglądać Gerwyna Price ubiegłorocznego mistrza." +
                        "Ciąg dalszy Rundy 1 odbędzie się 04.10.2021 (poniedziałek) o godzinie 19:00 w TVP Sport. W tym dniu będzie grał Krzysztof Ratajski." +
                        "Zapraszamy wszystkich fanów Darta do oglądanie i kibicowania.").build();

        List<Comment> comments = new ArrayList<>();
        News news3 = new News().toBuilder().author("Admin")
                .title("Krzysztof Ratajski przechodzi do Rundy 2!!!")
                .publicationDate(LocalDate.of(2021,10,4))
                .mainPicture("KRatajski.jpg")
                .pictures(addPictures())
                .comments(comments)
                .headline("Polak po zażatrej walce z Nathan Aspinall ostatecznie odnosi zwycięstwo i przechodzi do Rundy 2")
                .text("Pierwszy set należał do Aspinalla jednakże Ratajski walczył do samego końca. W piątym secie Aspinall ustrzelił najpierw 180, a w kolejnych rzutach 140 punktów. Odskoczył tym samym Ratajskiemu wygrywając partię 3:2. " +
                        "Drugi set został dobrze rozpoczęty przez Ratajskiego, w początkowej fazie gry wypracował on przewagę 2:0. Niestety późniejsze błędy na doublach sprawiły, ze Aspinall odrobił stratę. " +
                        "W piątym legu Ratajski zachował spokój, wykorzystał okazję do wygrania seta na podwójnej szesnastce." +
                        "Trzeci set również korzystnie rozpoczą się dla Polaka. Jak zauważyli komentatorzy: Aspinall rozsypał się totalnie. Można zbierać go ze sceny i rozsiewać po polu. Polak ostatecznie wykorzystał szanse i odniósł zwycięstwo." +
                        " Jego rywalem w kolejnej rundzie będzie Rob Cross. Transmisja z tego meczu w TVP Sport.").build();

        Comment comment = new Comment().toBuilder()
                .dataTime(LocalDateTime.of(LocalDate.of(2021,10,05), LocalTime.of(10,31)))
                .author("Admin")
                .text("Gratulacje!!! Tylko tak dalej!!!")
                .news(news3).build();
        comments.add(comment);

        News news4 = new News().toBuilder().author("Admin")
                .title("Dwóch czołowych zawodników odpada w 1 Rudnie World Grand Prix 2021 !!!")
                .publicationDate(LocalDate.of(2021,10,4))
                .pictures(pictures1)
                .mainPicture("Darts-World-Grand-Prix-Prize-Money.jpg")
                .headline("Peter Wright i Michael van Gerwen odpadają z 1 Rundy World Grand Prix 2021")
                .text("Peter Wright zajmujący drugie miejsce w światowym rankingu przegrywa swój pojedynek z Robem Cross. " +
                        "Pomimo zażartej walki między zawodnikami Cross nie dał żadnych szans Wrightowi i wygrywa 1 Rundę 2:0." +
                        "Podobna sytuacja spotkała Michael van Gerwena (który zajmuje 3 miejsce w rankingu światowym) który przegrał" +
                        "swój pojedynek z Danny Noppertem. Nopert nie zostawił rywalowi szansy na wygraną i zakończył pierwszą rundę z wynikiem 2:0." +
                        " Runda 2 będzie transmitowana 05.10.2021 w TVP Sport o godzinie 20:00. Zapraszamy do kibicowania!!!"
                ).build();


        newsRepository.save(news0);
        newsRepository.save(news1);
        newsRepository.save(news2);
        newsRepository.save(news3);
        newsRepository.save(news4);

    }

    private List<Picture> addPictures() {
        List<Picture> pictures = new ArrayList<>();
        Picture picture1 = new Picture().toBuilder().pictureName("KRatajski1.jpg").build();
        Picture picture2 = new Picture().toBuilder().pictureName("KRatajski2.jpg").build();

        pictures.add(picture1);
        pictures.add(picture2);
        return pictures;
    }


    private void populateFileToDownload() {
        if (fileToDownloadRepository.count() > 0) {
            return;
        }

        FileToDownload fileToDownload = new FileToDownload("Regulamin strony Dart Polska.docx","Regulamin obowiązujący na stronie Dart Polska");

        fileToDownloadRepository.save(fileToDownload);
    }

    private void populateRole() {
        if (roleRepository.count() > 0) {
            return;
        }

        Role user = new Role(Role.USER);
        Role admin = new Role(Role.ADMIN);

        roleRepository.save(user);
        roleRepository.save(admin);
    }

    private void populateUsers() {
        if (userRepository.count() > 0) {
            return;
        }
        User user = new User("user","Adam", "Błaszczykiewicz", "user@user.pl", passwordEncoder.encode("user123"),
                "Lublin", "20-601",
                "krakowskie", "1233-12-12", "123456");

        User admin = new User("admin","Adam", "Nowak", "michalbu92@gmail.com", passwordEncoder.encode("admin123"),
                "Lublin", "20-601",
                "krakowskie", "1233-12-12", "123456");

        user.addRole(roleRepository.findByRoleName(Role.USER).orElseThrow(() -> new RuntimeException()));
        admin.addRole(roleRepository.findByRoleName(Role.ADMIN).orElseThrow(() -> new RuntimeException()));

        userRepository.save(user);
        userRepository.save(admin);
    }

}
