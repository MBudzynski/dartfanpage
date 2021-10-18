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

        List<Picture> pictures = new ArrayList<>();

        News news0 = new News().toBuilder().author("Admin")
                .publicationDate(LocalDate.of(2021,9,17))
                .mainPicture("maxresdefault.jpg")
                .pictures(pictures)
                .title("Zakończenie Nordic Darts Masters 2021")
                .headline("Jutro odbęda się ćwierćfinały, półfinały i finał Nordic Darts Masters 2021!!!")
                .text("W dniu jutrzejszym czeka nas duża dawka emocji. W jednym dniu będą rozgrywane ćwierćfinały, półfinały i finał Nordic Darts Masters 2021. " +
                        "Będzie to bardzo duże obciążenie dla zawodników. Niestety w zawodach zabraknie Krzysztofa Ratajskiego. Serdecznie zapraszamy do oglądania!").build();

        News news1 = new News().toBuilder().author("Admin")
                .title("Rozpoczęcie World Grand Prix 2021")
                .publicationDate(LocalDate.of(2021,10,1))
                .pictures(pictures)
                .mainPicture("Darts-World-Grand-Prix-Prize-Money.jpg")
                .headline("W najbliższą niedzielę o godzinie 19:00 rozpocznie się kolejna edycja World Grand Prix 2021")
                .text("Transmisja będzie prowadzona na żywo na kanale TVP Sport. Wśród uczestników pojawi się również Krzysztof Ratajski." +
                        "Zapraszamy wszystkich fanów Darta do oglądanie i kibicowania.").build();

        News news2 = new News().toBuilder().author("Admin")
                .title("World Grand Prix 2021 rozpoczęte! Rundę 1 czas zacząć!")
                .publicationDate(LocalDate.of(2021,10,3))
                .pictures(pictures)
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
                .pictures(addPictures("KRatajski1.jpg", "KRatajski2.jpg"))
                .comments(comments)
                .headline("Polak po zażatrej walce z Nathan Aspinall ostatecznie odnosi zwycięstwo i przechodzi do Rundy 2")
                .text("Pierwszy set należał do Aspinalla jednakże Ratajski walczył do samego końca. W piątym secie Aspinall ustrzelił najpierw 180, a w kolejnych rzutach 140 punktów. Odskoczył tym samym Ratajskiemu wygrywając partię 3:2. " +
                        "Drugi set został dobrze rozpoczęty przez Ratajskiego, w początkowej fazie gry wypracował on przewagę 2:0. Niestety późniejsze błędy na doublach sprawiły, ze Aspinall odrobił stratę. " +
                        "W piątym legu Ratajski zachował spokój, wykorzystał okazję do wygrania seta na podwójnej szesnastce." +
                        "Trzeci set również korzystnie rozpoczą się dla Polaka. Jak zauważyli komentatorzy: Aspinall rozsypał się totalnie. Można zbierać go ze sceny i rozsiewać po polu. Polak ostatecznie wykorzystał szanse i odniósł zwycięstwo." +
                        " Jego rywalem w kolejnej rundzie będzie Rob Cross. Transmisja z tego meczu w TVP Sport.").build();

        Comment comment = new Comment().toBuilder()
                .dataTime(LocalDateTime.of(LocalDate.of(2021,10,5), LocalTime.of(10,31)))
                .author("Admin")
                .text("Gratulacje!!! Tylko tak dalej!!!")
                .news(news3).build();
        comments.add(comment);

        News news4 = new News().toBuilder().author("Admin")
                .title("Dwóch czołowych zawodników odpada w 1 Rudnie World Grand Prix 2021 !!!")
                .publicationDate(LocalDate.of(2021,10,4))
                .mainPicture("Darts-World-Grand-Prix-Prize-Money.jpg")
                .pictures(pictures)
                .headline("Peter Wright i Michael van Gerwen odpadają z 1 Rundy World Grand Prix 2021")
                .text("Peter Wright zajmujący drugie miejsce w światowym rankingu przegrywa swój pojedynek z Robem Cross. " +
                        "Pomimo zażartej walki między zawodnikami Cross nie dał żadnych szans Wrightowi i wygrywa 1 Rundę 2:0." +
                        "Podobna sytuacja spotkała Michael van Gerwena (który zajmuje 3 miejsce w rankingu światowym) który przegrał" +
                        "swój pojedynek z Danny Noppertem. Nopert nie zostawił rywalowi szansy na wygraną i zakończył pierwszą rundę z wynikiem 2:0." +
                        " Runda 2 będzie transmitowana 05.10.2021 w TVP Sport o godzinie 20:00. Zapraszamy do kibicowania!!!"
                ).build();

        News news5 = new News().toBuilder().author("Admin")
                .title("Krzysztof Ratajski przechodzi do ćwierćfinału World Grand Prix")
                .publicationDate(LocalDate.of(2021,10,7))
                .mainPicture("RatajskiCross1.jpg")
                .pictures(pictures)
                .headline("Ratajski w pięknym stylu pokonuje Roba Crossa 3:1 i wywalcza sobie awans do ćwierćfinału!")
                .text("Peter Wright zajmujący drugie miejsce w światowym rankingu przegrywa swój pojedynek z Robem Cross. " +
                        "Od pierwszego seta ratajski grał na najwyższym poziomie i nie dawał Crossowi szans na chwilę oddechu. Pierwszy set zakończył się 3:1 dla Polaka" +
                        "Drugi set był słabszy w wykonaniu naszego rodaka. Ratajski miał problem przy wejściu na dablach oraz nie był wstanie wykorzystywać pomyłek Creossa. Spowodowało to, że Anglik zremisował w setach 1:1." +
                        "W trzecim secie oglądaliśmy wyrównaną walkę pomiędzy Polakiem i Anglikiem jednakże to ratajski okazał się być lepszy i wykorzystywał błędy popełnione przez Anglika. " +
                        "W czwartym secie obaj zawodnicy utrzymywali swoje legi. W decydującym momencie spotkania Cross trafił trzy single, co wykorzystał Polak, w kolejnych podejściach do tarczy i w pięknym stylu kończąc pojedynek. Ratajski awansował do najlepszej ósemki turnieju! W następnym etapie Ratajski zmierzy się z Claytonem. "
                ).build();


        News news6 = new News().toBuilder().author("Admin")
                .title("Polak przegrywa w ćwierćfinale turnieju World Grand Prix 2021")
                .publicationDate(LocalDate.of(2021,10,8))
                .mainPicture("RatajskiVSClayton.jpg")
                .pictures(pictures)
                .headline("Ratajski po zaciekłej walce przegrywa starcie z Claytonem i odpada z turnieju!")
                .text("Pierwszy set był dla Ratajski dosyć nerwowy, chociaż z rzutu na rzut trafiał w coraz lepsze wartości. Najważniejszy okazał się trzeci leg. Polak miał okazję, by objąć prowadzenie, ale zaprezentował bardzo słabą skuteczność na podwójnych, co wykorzystał Clayton zdobywając prowadzenie w setach 1:0. " +
                        "W drugim secie Polak od wejścia szybko zbudował przewagę. Jednakże Clayton nie dał za wygraną i doprowadził do stanu 2:2. Błąd Walijczyka otworzył Polakowi drogę do wygrania seta. " +
                        "W trzecim secie oglądaliśmy wyrównaną grę. Obaj zawodnicy mieli problemy ze skutecznością i kolejne legi wygrywali ci, którzy jako pierwsi rozpoczynali grę. " +
                        "W Czwartym secie Polak miał ogromne problemy z podwójnymi wartościami, przez co nie mógł rozpocząć rozgrywki. Clayton był natomiast prawie bezbłędnie otwierał i kończył legi, dzięki czemu gładko wygrał seta remisując 2:2. " +
                        "W piątym Ratajski ponownie zaczął słabo i już przy pierwszej okazji dał się przełamać. W dwóch ostatnich legach miał ogromne problemy z wejściem, co wykorzystał rywal, kończąc spotkanie i awansując do półfinału. "
                ).build();


        News news7 = new News().toBuilder().author("Admin")
                .title("Jonny Clayton wygrał World Grand Prix 2021. ")
                .publicationDate(LocalDate.of(2021,10,9))
                .pictures(addPictures("ClaytonPrice1.jpg", "ClaytonPrice2.jpg", "ClaytonPrice3.jpg", "ClaytonPrice4.jpg"))
                .mainPicture("ClaytonPrice.jpg")
                .headline("Jonny Clayton triumfatorem World Grand Prix 2021. W sobotnim finale turnieju pewnie pokonał Gerwyna Price'a 5:1. ")
                .text("Clayton miał przewagę ale Price nie dawał za wygraną. Price trzeciego i czwartego seta przegrał w piątym legu. Price utrzymywał w nim średnią na poziomie 115, a Clayton 111 punktów. Jednak Clayton miał zdecydowanie większą przewagę na podwójnych.  " +
                        "Clayton prowadził 3:0 i tak naprawdę miał wszystko pod kontrolą. Czwarty set wygrał Price, jednakże w pozostałej części pojedynku grał już słabo przegrywając po czterech legach. Clayton zakończył mecz checkoutem na poziomie 152 punktów."
                ).build();



        newsRepository.save(news0);
        newsRepository.save(news1);
        newsRepository.save(news2);
        newsRepository.save(news3);
        newsRepository.save(news4);
        newsRepository.save(news5);
        newsRepository.save(news6);
        newsRepository.save(news7);


    }

    private List<Picture> addPictures(String... picture) {
        List<Picture> pictures = new ArrayList<>();
        for (int i = 0; i < picture.length ; i++) {
            pictures.add(new Picture().toBuilder().pictureName(picture[i]).build());
        }

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

        User admin = new User("admin","Adam", "Nowak", "admin@admin.pl", passwordEncoder.encode("admin123"),
                "Lublin", "20-601",
                "krakowskie", "1233-12-12", "123456");

        user.addRole(roleRepository.findByRoleName(Role.USER).orElseThrow(() -> new RuntimeException()));
        admin.addRole(roleRepository.findByRoleName(Role.ADMIN).orElseThrow(() -> new RuntimeException()));

        userRepository.save(user);
        userRepository.save(admin);
    }

}
