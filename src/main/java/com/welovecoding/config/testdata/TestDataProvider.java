package com.welovecoding.config.testdata;

import com.welovecoding.entities.Author;
import com.welovecoding.entities.Category;
import com.welovecoding.entities.Difficulty;
import com.welovecoding.entities.LanguageCode;
import com.welovecoding.entities.Playlist;
import com.welovecoding.entities.Provider;
import com.welovecoding.entities.Video;
import com.welovecoding.service.CategoryService;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

/**
 *
 * @author Michael Koppen
 */
@Singleton
@Startup
public class TestDataProvider {

  @EJB
  CategoryService categoryService;

  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

  @PostConstruct
  private void init() {
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    InputStream stream = classLoader.getResourceAsStream("production.properties");
    if (stream != null) {
      try {
        Properties p = new Properties();
        p.load(stream);

        try {
          String genTestData = p.getProperty("GENERATE_TEST_DATA", "false");
          if (genTestData.equalsIgnoreCase("true")) {
            insertData();
            Logger.getLogger(TestDataProvider.class.getName()).log(Level.INFO, "TestData is generated!");
          } else {
            Logger.getLogger(TestDataProvider.class.getName()).log(Level.INFO, "No TestData is generated!");
          }
        } catch (ParseException ex) {
          Logger.getLogger(TestDataProvider.class.getName()).log(Level.SEVERE, "Cannot parse TimeStamps!", ex);
        } catch (Exception e) {
          Logger.getLogger(TestDataProvider.class.getName()).log(Level.SEVERE, null, e);
        }

      } catch (IOException ex) {
        Logger.getLogger(TestDataProvider.class.getName()).log(Level.SEVERE, null, ex);
      }
    }

  }

  private void insertData() throws ParseException {

    Author a1 = buildAuthor(1l, "https://www.YOUTUBE.com/user/apfelbenny", null, "", null, "Benny Neugebauer", "http://www.bennyn.de/");
    Author a2 = buildAuthor(2l, "https://www.YOUTUBE.com/user/tomwendelger", null, "", null, "Tom Wendel", "http://tomwendelblog.wordpress.com/");
    Author a3 = buildAuthor(3l, "https://www.YOUTUBE.com/user/JoernLoviscach", null, "", null, "Prof. Dr. Jörn Loviscach", "http://www.j3L7h.de/");
    Author a4 = buildAuthor(4l, null, null, "", null, "Lars Vogel", "http://www.vogella.com/");
    Author a5 = buildAuthor(5l, null, null, "", null, "Tom Wendel & Marius Musch", null);
    Author a6 = buildAuthor(6l, null, null, "", null, "Tom Wendel, Kai Jäger & Felix Rieseberg", null);
    Author a7 = buildAuthor(7l, null, null, "", null, "Tom Wendel & Felix Rieseberg", null);
    Author a8 = buildAuthor(8l, "http://vimeo.com/wolter", null, "", null, "Sascha Wolter", "http://www.wolter.biz/");
    Author a9 = buildAuthor(9l, null, null, "", null, "Alex Garrett", "http://phpacademy.org/");
    Author a10 = buildAuthor(10l, "https://www.YOUTUBE.com/user/thenewboston", null, "", null, "Bucky Roberts", "http://thenewboston.org/");
    Author a11 = buildAuthor(11l, "http://www.YOUTUBE.com/user/iTzAdam5X", null, "", null, "iTzAdam5X", null);
    Author a12 = buildAuthor(12l, "http://www.YOUTUBE.com/user/VISUALWORLDGmbH", null, "", null, "Géraud Reichert", "http://www.visual-world.de/");
    Author a13 = buildAuthor(13l, "http://www.YOUTUBE.com/user/johnpapaYT", null, "", null, "John Papa", "http://www.johnpapa.net/");
    Author a14 = buildAuthor(14l, null, null, "", null, "Adam Bien", "http://adam-bien.tv/");
    Author a15 = buildAuthor(15l, null, null, "", null, "Travis", null);
    Author a16 = buildAuthor(16l, "https://www.YOUTUBE.com/user/NixiePixel", null, "", null, "Nixie Pixel", "http://www.nixiepixel.com/");
    Author a17 = buildAuthor(17l, "https://www.YOUTUBE.com/user/wwwTheTutorialde", null, "", null, "Christian Simons", "http://www.thetutorial.de/");
    Author a18 = buildAuthor(18l, null, null, "", null, "Andy Beaulieau", "http://www.andybeaulieu.com/");
    Author a19 = buildAuthor(19l, null, null, "", null, "Bob Tabor", "http://www.bobtabor.com/");
    Author a20 = buildAuthor(20l, null, null, "", null, "Dan Fernandez", null);
    Author a21 = buildAuthor(21l, null, null, "", null, "Rick Barraza", null);
    Author a22 = buildAuthor(22l, "https://www.YOUTUBE.com/user/codegearguru", null, "", null, "Alister Christie", "http://www.learndelphi.tv/");
    Author a23 = buildAuthor(23l, "https://www.YOUTUBE.com/user/theSkyLapse", null, "", null, "Moritz Gunz & Leo Bernard", "http://skylapse.de/");
    Author a24 = buildAuthor(24l, "https://www.YOUTUBE.com/user/iBasicTutorials", null, "", null, "iBasicTutorials", null);
    Author a25 = buildAuthor(25l, "https://www.YOUTUBE.com/user/tsjDEV86", null, "", null, "Thomas Sebastian Jensen", "http://www.tsjdev.de/");
    Author a26 = buildAuthor(26l, "https://www.YOUTUBE.com/user/CsDoctore", null, "", null, "Doctore", null);

    List<Category> categoryList
            = Arrays.asList(new Category[]{
              buildCategory(
                      1l, "#19A2DE", null, null, "windows-phone", "Windows Phone",
                      Arrays.asList(new Playlist[]{
                        buildPlaylist(
                                1l,
                                "406285BEFEA47E54z",
                                sdf.parse("2014-04-02 14:18:51"),
                                "Mitschnitt der MMT30-Konferenz zum Thema \"Apps entwickeln für Windows Phone\".",
                                Difficulty.EASY,
                                true,
                                sdf.parse("2014-04-02 14:18:51"),
                                "Windows Phone Workshop (MMT30)",
                                "windows-phone-workshop-mmt30",
                                LanguageCode.de,
                                Provider.YOUTUBE,
                                a7,
                                Arrays.asList(new Video[]{
                                  buildVideo(
                                          1l,
                                          "LaLqAmP_FSI",
                                          sdf.parse("2013-08-31 12:17:14"),
                                          null,
                                          sdf.parse("2013-08-31 12:17:14"),
                                          "Architektur, Frameworks & APIs",
                                          null,
                                          null,
                                          null
                                  )
                                })
                        ),
                        buildPlaylist(
                                2l,
                                "5EA5B1829771349A",
                                sdf.parse("2014-04-02 14:18:51"),
                                "In einer Reihe von superkurzen Speedrun-Videos werden grundlegende Funktionen von Windows Phone 7 gezeigt. Beginnend mit den ersten Schritten der Phone Entwicklung über SMS-Versand, Sounds, Telefonanrufe oder GPS-Daten.",
                                Difficulty.EASY,
                                true,
                                sdf.parse("2014-04-02 14:18:51"),
                                "Windows Phone 7 Dev Speedruns",
                                "windows-phone-7-dev-speedruns",
                                LanguageCode.de,
                                Provider.YOUTUBE,
                                a5,
                                Arrays.asList(new Video[]{})
                        ),
                        buildPlaylist(
                                3l,
                                "5E86BAF9ADD21755",
                                sdf.parse("2013-08-31 12:17:10"),
                                null,
                                Difficulty.EASY,
                                true,
                                sdf.parse("2013-08-31 12:17:10"),
                                "Windows Phone Coding Camp",
                                "windows-phone-coding-camp",
                                LanguageCode.de,
                                Provider.YOUTUBE,
                                a6,
                                Arrays.asList(new Video[]{})
                        ),
                        buildPlaylist(
                                52l,
                                "undefined",
                                sdf.parse("2013-08-31 12:17:11"),
                                null,
                                Difficulty.EASY,
                                true,
                                sdf.parse("2013-08-31 12:17:11"),
                                "\"Hello World\" für Windows Phone 8",
                                "hello-world-fuer-windows-phone-8",
                                LanguageCode.de,
                                Provider.YOUTUBE,
                                a25,
                                Arrays.asList(new Video[]{})
                        )
                      })
              ),
              buildCategory(
                      2l, "#E61400", null, null, "java", "Java",
                      Arrays.asList(new Playlist[]{
                        buildPlaylist(
                                4l,
                                "0F32AF56C8A1A695",
                                sdf.parse("2013-08-31 12:17:10"),
                                null,
                                Difficulty.EASY,
                                true,
                                sdf.parse("2013-08-31 12:17:10"),
                                "Java Tutorials von Anfang an",
                                "java-tutorials-von-anfang-an",
                                LanguageCode.de,
                                Provider.YOUTUBE,
                                a1,
                                Arrays.asList(new Video[]{})
                        ),
                        buildPlaylist(
                                5l,
                                "F544CEEC9432BF67",
                                sdf.parse("2013-08-31 12:17:10"),
                                "In diesen Videos wird gezeigt, wie man die Verbindung zu einer MySQL-Datenbank herstellt und Persistent Entites generiert. Außerdem wird gezeigt, wie auf diese Persistent Entities über eine Session Bean innerhalb eines Web-Servlets zugegriffen werden kann.",
                                Difficulty.EASY,
                                true,
                                sdf.parse("2013-08-31 12:17:10"),
                                "Praktischer Einstieg in die Java Enterprise Edition (JEE)",
                                "praktischer-einstieg-in-die-java-enterprise-edition-jee",
                                LanguageCode.de,
                                Provider.YOUTUBE,
                                a1,
                                Arrays.asList(new Video[]{})
                        ),
                        buildPlaylist(
                                6l,
                                "undefined",
                                sdf.parse("2013-08-31 12:17:10"),
                                null,
                                Difficulty.EASY,
                                true,
                                sdf.parse("2013-08-31 12:17:10"),
                                "JEE with Adam Bien",
                                "jee-with-adam-bien",
                                LanguageCode.en,
                                Provider.YOUTUBE,
                                a14,
                                Arrays.asList(new Video[]{})
                        ),
                        buildPlaylist(
                                26l,
                                "ECFE2CE09D83EE3E28",
                                sdf.parse("2013-08-31 12:17:11"),
                                null,
                                Difficulty.EASY,
                                true,
                                sdf.parse("2013-08-31 12:17:11"),
                                "Java (Beginner) Programming Tutorials",
                                "java-beginner-programming-tutorials",
                                LanguageCode.en,
                                Provider.YOUTUBE,
                                a10,
                                Arrays.asList(new Video[]{})
                        ),
                        buildPlaylist(
                                27l,
                                "EC27BCE863B6A864E3",
                                sdf.parse("2013-08-31 12:17:11"),
                                null,
                                Difficulty.EASY,
                                true,
                                sdf.parse("2013-08-31 12:17:11"),
                                "Java (Intermediate) Tutorials",
                                "java-intermediate-tutorials",
                                LanguageCode.en,
                                Provider.YOUTUBE,
                                a10,
                                Arrays.asList(new Video[]{})
                        ),
                        buildPlaylist(
                                28l,
                                "ECA331A6709F40B79D",
                                sdf.parse("2013-08-31 12:17:11"),
                                null,
                                Difficulty.EASY,
                                true,
                                sdf.parse("2013-08-31 12:17:11"),
                                "Java Game Development Tutorials",
                                "java-game-development-tutorials",
                                LanguageCode.en,
                                Provider.YOUTUBE,
                                a10,
                                Arrays.asList(new Video[]{})
                        ),
                        buildPlaylist(
                                49l,
                                "PLgo4jImQNCoEp8B-MGesACl-PK_y63236",
                                sdf.parse("2013-08-31 12:17:11"),
                                null,
                                Difficulty.EASY,
                                true,
                                sdf.parse("2013-08-31 12:17:11"),
                                "Einführung in Java mit BlueJ",
                                "einfuehrung-in-java-mit-bluej",
                                LanguageCode.de,
                                Provider.YOUTUBE,
                                a23,
                                Arrays.asList(new Video[]{})
                        ),
                        buildPlaylist(
                                51l,
                                "PLgo4jImQNCoHqannapoiywSpykKBfeVxf",
                                sdf.parse("2013-08-31 12:17:11"),
                                null,
                                Difficulty.EASY,
                                true,
                                sdf.parse("2013-08-31 12:17:11"),
                                "Java in IntelliJ",
                                "java-in-intellij",
                                LanguageCode.de,
                                Provider.YOUTUBE,
                                a23,
                                Arrays.asList(new Video[]{})
                        )
                      })
              ),
              buildCategory(
                      3l, "#643EBF", null, null, "php", "PHP",
                      Arrays.asList(new Playlist[]{
                        buildPlaylist(
                                7l,
                                "EC442FA2C127377F07",
                                sdf.parse("2013-08-31 12:17:10"),
                                null,
                                Difficulty.EASY,
                                true,
                                sdf.parse("2013-08-31 12:17:10"),
                                "PHP Beginner Tutorials",
                                "php-beginner-tutorials",
                                LanguageCode.en,
                                Provider.YOUTUBE,
                                a9,
                                Arrays.asList(new Video[]{})
                        ),
                        buildPlaylist(
                                8l,
                                "2CC78DADA62AFEE3",
                                sdf.parse("2013-08-31 12:17:10"),
                                null,
                                Difficulty.EASY,
                                true,
                                sdf.parse("2013-08-31 12:17:10"),
                                "Zend Framework Tutorial für Anfänger",
                                "zend-framework-tutorial-fuer-anfaenger",
                                LanguageCode.de,
                                Provider.YOUTUBE,
                                a1,
                                Arrays.asList(new Video[]{})
                        )
                      })
              ),
              buildCategory(
                      4l, "#9C5100", null, null, "theorie", "Theorie",
                      Arrays.asList(new Playlist[]{
                        buildPlaylist(
                                9l,
                                "BF98E901C0DA48E6",
                                sdf.parse("2013-08-31 12:17:10"),
                                null,
                                Difficulty.EASY,
                                true,
                                sdf.parse("2013-08-31 12:17:10"),
                                "Formale Sprachen und Automatentheorie",
                                "formale-sprachen-und-automatentheorie",
                                LanguageCode.de,
                                Provider.YOUTUBE,
                                a1,
                                Arrays.asList(new Video[]{})
                        ),
                        buildPlaylist(
                                11l,
                                "undefined",
                                sdf.parse("2013-08-31 12:17:10"),
                                null,
                                Difficulty.EASY,
                                true,
                                sdf.parse("2013-08-31 12:17:10"),
                                "Zahlensysteme",
                                "zahlensysteme",
                                LanguageCode.de,
                                Provider.YOUTUBE,
                                a1,
                                Arrays.asList(new Video[]{})
                        ),
                        buildPlaylist(
                                15l,
                                "undefined",
                                sdf.parse("2013-08-31 12:17:11"),
                                null,
                                Difficulty.EASY,
                                true,
                                sdf.parse("2013-08-31 12:17:11"),
                                "Datenstrukturen und Algorithmen",
                                "datenstrukturen-und-algorithmen",
                                LanguageCode.de,
                                Provider.YOUTUBE,
                                a3,
                                Arrays.asList(new Video[]{})
                        )
                      })
              ),
              buildCategory(
                      5l, "#2672EC", null, null, "windows-7", "Windows 7",
                      Arrays.asList(new Playlist[]{
                        buildPlaylist(
                                10l,
                                "E8E1CC24350C894F",
                                sdf.parse("2013-08-31 12:17:10"),
                                null,
                                Difficulty.EASY,
                                true,
                                sdf.parse("2013-08-31 12:17:10"),
                                "Windows 7",
                                "windows-7",
                                LanguageCode.de,
                                Provider.YOUTUBE,
                                a1,
                                Arrays.asList(new Video[]{})
                        ),
                        buildPlaylist(
                                12l,
                                "undefined",
                                sdf.parse("2013-08-31 12:17:10"),
                                "Der Einsteigerkurs für die Programmierung von Windows Anwendungen.",
                                Difficulty.EASY,
                                true,
                                sdf.parse("2013-08-31 12:17:10"),
                                "Windows-Anwendungen programmieren (WPF)",
                                "windows-anwendungen-programmieren-wpf",
                                LanguageCode.de,
                                Provider.YOUTUBE,
                                a2,
                                Arrays.asList(new Video[]{})
                        )
                      })
              ),
              buildCategory(
                      7l, "#EF9608", null, null, "c", "C",
                      Arrays.asList(new Playlist[]{
                        buildPlaylist(
                                13l,
                                "undefined",
                                sdf.parse("2013-08-31 12:17:10"),
                                null,
                                Difficulty.EASY,
                                true,
                                sdf.parse("2013-08-31 12:17:10"),
                                "Erste Schritte in C",
                                "erste-schritte-in-c",
                                LanguageCode.de,
                                Provider.YOUTUBE,
                                a3,
                                Arrays.asList(new Video[]{})
                        ),
                        buildPlaylist(
                                14l,
                                "undefined",
                                sdf.parse("2013-08-31 12:17:10"),
                                null,
                                Difficulty.EASY,
                                true,
                                sdf.parse("2013-08-31 12:17:10"),
                                "Operatoren in C",
                                "operatoren-in-c",
                                LanguageCode.de,
                                Provider.YOUTUBE,
                                a3,
                                Arrays.asList(new Video[]{})
                        )
                      })
              ),
              buildCategory(
                      8l, "#8CBE29", null, null, "android", "Android",
                      Arrays.asList(new Playlist[]{
                        buildPlaylist(
                                16l,
                                "undefined",
                                sdf.parse("2013-08-31 12:17:11"),
                                null,
                                Difficulty.EASY,
                                true,
                                sdf.parse("2013-08-31 12:17:11"),
                                "Android in 90 Minuten",
                                "android-in-90-minuten",
                                LanguageCode.de,
                                Provider.YOUTUBE,
                                a4,
                                Arrays.asList(new Video[]{})
                        ),
                        buildPlaylist(
                                29l,
                                "EC2F07DBCDCC01493A",
                                sdf.parse("2013-08-31 12:17:11"),
                                null,
                                Difficulty.EASY,
                                true,
                                sdf.parse("2013-08-31 12:17:11"),
                                "Android Application Development Tutorials",
                                "android-application-development-tutorials",
                                LanguageCode.en,
                                Provider.YOUTUBE,
                                a15,
                                Arrays.asList(new Video[]{})
                        ),
                        buildPlaylist(
                                50l,
                                "PL8E64DC3326CAE48E",
                                sdf.parse("2013-08-31 12:17:11"),
                                null,
                                Difficulty.EASY,
                                true,
                                sdf.parse("2013-08-31 12:17:11"),
                                "Android Apps programmieren",
                                "android-apps-programmieren",
                                LanguageCode.de,
                                Provider.YOUTUBE,
                                a24,
                                Arrays.asList(new Video[]{})
                        )
                      })
              ),
              buildCategory(
                      9l, "#F8CA00", null, null, "c++", "C++",
                      Arrays.asList(new Playlist[]{
                        buildPlaylist(
                                17l,
                                "PLD0D54219E5F2544D",
                                sdf.parse("2013-08-31 12:17:11"),
                                null,
                                Difficulty.EASY,
                                true,
                                sdf.parse("2013-08-31 12:17:11"),
                                "C++ GUI with Qt Playlist",
                                "c++-gui-with-qt-playlist",
                                LanguageCode.en,
                                Provider.YOUTUBE,
                                a10,
                                Arrays.asList(new Video[]{})
                        ),
                        buildPlaylist(
                                30l,
                                "ECAE85DE8440AA6B83",
                                sdf.parse("2013-08-31 12:17:11"),
                                null,
                                Difficulty.EASY,
                                true,
                                sdf.parse("2013-08-31 12:17:11"),
                                "C++ Programming Tutorials",
                                "c++-programming-tutorials",
                                LanguageCode.en,
                                Provider.YOUTUBE,
                                a10,
                                Arrays.asList(new Video[]{})
                        )
                      })
              ),
              buildCategory(
                      10l, "#95040B", null, null, "ruby", "Ruby",
                      Arrays.asList(new Playlist[]{
                        buildPlaylist(
                                18l,
                                "PL1512BD72E7C9FFCA",
                                sdf.parse("2013-08-31 12:17:11"),
                                null,
                                Difficulty.EASY,
                                true,
                                sdf.parse("2013-08-31 12:17:11"),
                                "Ruby Programming Tutorials",
                                "ruby-programming-tutorials",
                                LanguageCode.en,
                                Provider.YOUTUBE,
                                a10,
                                Arrays.asList(new Video[]{})
                        )
                      })
              ),
              buildCategory(
                      11l, "#00C2D6", null, null, "c-sharp", "C#",
                      Arrays.asList(new Playlist[]{
                        buildPlaylist(
                                19l,
                                "PL0EE421AE8BCEBA4A",
                                sdf.parse("2013-08-31 12:17:11"),
                                null,
                                Difficulty.EASY,
                                true,
                                sdf.parse("2013-08-31 12:17:11"),
                                "C# Beginners Tutorials",
                                "c-sharp-beginners-tutorials",
                                LanguageCode.en,
                                Provider.YOUTUBE,
                                a11,
                                Arrays.asList(new Video[]{})
                        ),
                        buildPlaylist(
                                37l,
                                "PL0EE421AE8BCEBA4A",
                                sdf.parse("2013-08-31 12:17:11"),
                                null,
                                Difficulty.EASY,
                                true,
                                sdf.parse("2013-08-31 12:17:11"),
                                "C# Fundamentals for Absolute Beginners",
                                "c-sharp-fundamentals-for-absolute-beginners",
                                LanguageCode.en,
                                Provider.CHANNEL9,
                                a19,
                                Arrays.asList(new Video[]{})
                        ),
                        buildPlaylist(
                                54l,
                                "PLvvL1HRuCBItyw45XnCqEXzuegKQd3MfL",
                                sdf.parse("2013-08-31 12:17:11"),
                                null,
                                Difficulty.EASY,
                                true,
                                sdf.parse("2013-08-31 12:17:11"),
                                "C# Tutorial",
                                "c-sharp-tutorial",
                                LanguageCode.de,
                                Provider.YOUTUBE,
                                a26,
                                Arrays.asList(new Video[]{})
                        )
                      })
              ),
              buildCategory(
                      12l, "#F14A29", null, null, "html5", "HTML5",
                      Arrays.asList(new Playlist[]{
                        buildPlaylist(
                                20l,
                                "PL081AC329706B2953",
                                sdf.parse("2013-08-31 12:17:11"),
                                null,
                                Difficulty.EASY,
                                true,
                                sdf.parse("2013-08-31 12:17:11"),
                                "HTML5 Tutorials",
                                "html5-tutorials",
                                LanguageCode.en,
                                Provider.YOUTUBE,
                                a10,
                                Arrays.asList(new Video[]{})
                        ),
                        buildPlaylist(
                                47l,
                                "undefined",
                                sdf.parse("2013-08-31 12:17:11"),
                                null,
                                Difficulty.EASY,
                                true,
                                sdf.parse("2013-08-31 12:17:11"),
                                "HTML5 & CSS3 Fundamentals: Development for Absolute Beginners",
                                "html5-css3-fundamentals-development-for-absolute-beginners",
                                LanguageCode.en,
                                Provider.CHANNEL9,
                                a19,
                                Arrays.asList(new Video[]{})
                        )
                      })
              ),
              buildCategory(
                      13l, "#FF6D34", null, null, "mysql", "MySQL",
                      Arrays.asList(new Playlist[]{
                        buildPlaylist(
                                21l,
                                "EC32BC9C878BA72085",
                                sdf.parse("2013-08-31 12:17:11"),
                                null,
                                Difficulty.EASY,
                                true,
                                sdf.parse("2013-08-31 12:17:11"),
                                "MySQL Database Tutorials",
                                "mysql-database-tutorials",
                                LanguageCode.en,
                                Provider.YOUTUBE,
                                a10,
                                Arrays.asList(new Video[]{})
                        )
                      })
              ),
              buildCategory(
                      14l, "#00ADEF", null, null, "windows-store", "Windows Store",
                      Arrays.asList(new Playlist[]{
                        buildPlaylist(
                                22l,
                                "PLm7iF2-ucDpE",
                                sdf.parse("2013-08-31 12:17:11"),
                                null,
                                Difficulty.EASY,
                                true,
                                sdf.parse("2013-08-31 12:17:11"),
                                "Windows 8 Apps mit C# und XAML",
                                "windows-8-apps-mit-c-sharp-und-xaml",
                                LanguageCode.de,
                                Provider.YOUTUBE,
                                a12,
                                Arrays.asList(new Video[]{})
                        ),
                        buildPlaylist(
                                23l,
                                "PL8u_8DUtFba8",
                                sdf.parse("2013-08-31 12:17:11"),
                                null,
                                Difficulty.EASY,
                                true,
                                sdf.parse("2013-08-31 12:17:11"),
                                "Introduction to Building Windows 8 apps",
                                "introduction-to-building-windows-8-apps",
                                LanguageCode.en,
                                Provider.YOUTUBE,
                                a13,
                                Arrays.asList(new Video[]{})
                        ),
                        buildPlaylist(
                                36l,
                                "undefined",
                                sdf.parse("2013-08-31 12:17:11"),
                                null,
                                Difficulty.EASY,
                                true,
                                sdf.parse("2013-08-31 12:17:11"),
                                "Windows Store apps for Absolute Beginners with C#",
                                "windows-store-apps-for-absolute-beginners-with-c-sharp",
                                LanguageCode.en,
                                Provider.CHANNEL9,
                                a19,
                                Arrays.asList(new Video[]{})
                        ),
                        buildPlaylist(
                                41l,
                                "undefined",
                                sdf.parse("2013-08-31 12:17:11"),
                                null,
                                Difficulty.EASY,
                                true,
                                sdf.parse("2013-08-31 12:17:11"),
                                "First Windows Store app with JavaScript",
                                "first-windows-store-app-with-javascript",
                                LanguageCode.en,
                                Provider.CHANNEL9,
                                a20,
                                Arrays.asList(new Video[]{})
                        ),
                        buildPlaylist(
                                42l,
                                "undefined",
                                sdf.parse("2013-08-31 12:17:11"),
                                null,
                                Difficulty.EASY,
                                true,
                                sdf.parse("2013-08-31 12:17:11"),
                                "First Windows Store app with C#",
                                "first-windows-store-app-with-c-sharp",
                                LanguageCode.en,
                                Provider.CHANNEL9,
                                a20,
                                Arrays.asList(new Video[]{})
                        ),
                        buildPlaylist(
                                43l,
                                "undefined",
                                sdf.parse("2013-08-31 12:17:11"),
                                null,
                                Difficulty.EASY,
                                true,
                                sdf.parse("2013-08-31 12:17:11"),
                                "First Windows Store app with Visual Basic",
                                "first-windows-store-app-with-visual-basic",
                                LanguageCode.en,
                                Provider.CHANNEL9,
                                a20,
                                Arrays.asList(new Video[]{})
                        ),
                        buildPlaylist(
                                44l,
                                "undefined",
                                sdf.parse("2013-08-31 12:17:11"),
                                null,
                                Difficulty.EASY,
                                true,
                                sdf.parse("2013-08-31 12:17:11"),
                                "Migrating apps from Windows Phone to Windows 8",
                                "migrating-apps-from-windows-phone-to-windows-8",
                                LanguageCode.en,
                                Provider.CHANNEL9,
                                a21,
                                Arrays.asList(new Video[]{})
                        ),
                        buildPlaylist(
                                53l,
                                "undefined",
                                sdf.parse("2013-08-31 12:17:11"),
                                null,
                                Difficulty.EASY,
                                true,
                                sdf.parse("2013-08-31 12:17:11"),
                                "\"Hello World\" für Windows 8",
                                "hello-world-fuer-windows-8",
                                LanguageCode.de,
                                Provider.YOUTUBE,
                                a25,
                                Arrays.asList(new Video[]{})
                        )
                      })
              ),
              buildCategory(
                      15l, "#0093F8", null, null, "javascript", "JavaScript",
                      Arrays.asList(new Playlist[]{
                        buildPlaylist(
                                24l,
                                "EC46F0A159EC02DF82",
                                sdf.parse("2013-08-31 12:17:11"),
                                null,
                                Difficulty.EASY,
                                true,
                                sdf.parse("2013-08-31 12:17:11"),
                                "JavaScript Tutorial",
                                "javascript-tutorial",
                                LanguageCode.en,
                                Provider.YOUTUBE,
                                a10,
                                Arrays.asList(new Video[]{})
                        ),
                        buildPlaylist(
                                45l,
                                "undefined",
                                sdf.parse("2013-08-31 12:17:11"),
                                null,
                                Difficulty.EASY,
                                true,
                                sdf.parse("2013-08-31 12:17:11"),
                                "Javascript Fundamentals: Development for Absolute Beginners",
                                "javascript-fundamentals-development-for-absolute-beginners",
                                LanguageCode.en,
                                Provider.CHANNEL9,
                                a19,
                                Arrays.asList(new Video[]{})
                        ),
                        buildPlaylist(
                                46l,
                                "undefined",
                                sdf.parse("2013-08-31 12:17:11"),
                                null,
                                Difficulty.EASY,
                                true,
                                sdf.parse("2013-08-31 12:17:11"),
                                "Getting Started with jQuery",
                                "getting-started-with-jquery",
                                LanguageCode.en,
                                Provider.CHANNEL9,
                                a19,
                                Arrays.asList(new Video[]{})
                        )
                      })
              ),
              buildCategory(
                      16l, "#3C4B6B", null, null, "python", "Python",
                      Arrays.asList(new Playlist[]{
                        buildPlaylist(
                                25l,
                                "ECEA1FEF17E1E5C0DA",
                                sdf.parse("2013-08-31 12:17:11"),
                                null,
                                Difficulty.EASY,
                                true,
                                sdf.parse("2013-08-31 12:17:11"),
                                "Python Programming Tutorial",
                                "python-programming-tutorial",
                                LanguageCode.en,
                                Provider.YOUTUBE,
                                a10,
                                Arrays.asList(new Video[]{})
                        )
                      })
              ),
              buildCategory(
                      17l, "#999999", null, null, "iphone", "iPhone",
                      Arrays.asList(new Playlist[]{
                        buildPlaylist(
                                31l,
                                "EC53038489615793F7",
                                sdf.parse("2013-08-31 12:17:11"),
                                null,
                                Difficulty.EASY,
                                true,
                                sdf.parse("2013-08-31 12:17:11"),
                                "iPhone Development Tutorial",
                                "iphone-development-tutorial",
                                LanguageCode.en,
                                Provider.YOUTUBE,
                                a10,
                                Arrays.asList(new Video[]{})
                        )
                      })
              ),
              buildCategory(
                      18l, "#999999", null, null, "objective-c", "Objective-C",
                      Arrays.asList(new Playlist[]{
                        buildPlaylist(
                                32l,
                                "EC640F44F1C97BA581",
                                sdf.parse("2013-08-31 12:17:11"),
                                null,
                                Difficulty.EASY,
                                true,
                                sdf.parse("2013-08-31 12:17:11"),
                                "Objective C Programming Tutorial",
                                "objective-c-programming-tutorial",
                                LanguageCode.en,
                                Provider.YOUTUBE,
                                a10,
                                Arrays.asList(new Video[]{})
                        )
                      })
              ),
              buildCategory(
                      19l, "#669900", null, null, "linux", "Linux",
                      Arrays.asList(new Playlist[]{
                        buildPlaylist(
                                33l,
                                "PLC57C60F699A5C44D",
                                sdf.parse("2013-08-31 12:17:11"),
                                null,
                                Difficulty.EASY,
                                true,
                                sdf.parse("2013-08-31 12:17:11"),
                                "Linux Tutorial",
                                "linux-tutorial",
                                LanguageCode.en,
                                Provider.YOUTUBE,
                                a16,
                                Arrays.asList(new Video[]{})
                        ),
                        buildPlaylist(
                                34l,
                                "PLAA664B6F46A825A2",
                                sdf.parse("2013-08-31 12:17:11"),
                                null,
                                Difficulty.EASY,
                                true,
                                sdf.parse("2013-08-31 12:17:11"),
                                "Diverse Linux Tutorials",
                                "diverse-linux-tutorials",
                                LanguageCode.de,
                                Provider.YOUTUBE,
                                a17,
                                Arrays.asList(new Video[]{})
                        )
                      })
              ),
              buildCategory(
                      20l, "#0099CC", null, null, "visual-basic", "Visual Basic",
                      Arrays.asList(new Playlist[]{
                        buildPlaylist(
                                40l,
                                "undefined",
                                sdf.parse("2013-08-31 12:17:11"),
                                null,
                                Difficulty.EASY,
                                true,
                                sdf.parse("2013-08-31 12:17:11"),
                                "Visual Basic Fundamentals: Development for Absolute Beginners",
                                "visual-basic-fundamentals-development-for-absolute-beginners",
                                LanguageCode.en,
                                Provider.CHANNEL9,
                                a19,
                                Arrays.asList(new Video[]{})
                        )
                      })
              ),
              buildCategory(
                      21l, "#7B9FCF", null, null, "delphi", "Delphi",
                      Arrays.asList(new Playlist[]{
                        buildPlaylist(
                                48l,
                                "undefined",
                                sdf.parse("2013-08-31 12:17:11"),
                                null,
                                Difficulty.EASY,
                                true,
                                sdf.parse("2013-08-31 12:17:11"),
                                "Delphi Programming Tutorial",
                                "delphi-programming-tutorial",
                                LanguageCode.en,
                                Provider.YOUTUBE,
                                a22,
                                Arrays.asList(new Video[]{})
                        )
                      })
              )});

    try {
      for (Category category : categoryList) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Category>> categoryConstraintViolations = validator.validate(category);
        for (ConstraintViolation<Category> constraintViolation1 : categoryConstraintViolations) {
          throw new RuntimeException("Category Validation: " + constraintViolation1.getPropertyPath() + " " + constraintViolation1.getMessage());
        }

        for (Playlist playlist : category.getPlaylists()) {
          Set<ConstraintViolation<Playlist>> playlistConstraintViolations = validator.validate(playlist);
          for (ConstraintViolation<Playlist> constraintViolation2 : playlistConstraintViolations) {
            throw new RuntimeException("Playlist Validation: " + constraintViolation2.getPropertyPath() + " " + constraintViolation2.getMessage());
          }
          for (Video video : playlist.getVideos()) {
            if (video != null) {
              Set<ConstraintViolation<Video>> videoConstraintViolations = validator.validate(video);
              for (ConstraintViolation<Video> constraintViolation3 : videoConstraintViolations) {
                throw new RuntimeException("Video Validation: " + constraintViolation3.getPropertyPath() + " " + constraintViolation3.getMessage());
              }
            }
          }
        }
      }

      categoryService.batchEdit(categoryList);

    } catch (Exception e) {
      Logger.getLogger(TestDataProvider.class
              .getName()).log(Level.SEVERE, "EX", e);
    }

  }

  private Category buildCategory(Long id, String color, Date created, Date lastModified, String slug, String name, List<Playlist> playlists) {
    Category c = new Category();
    c.setId(id);
    c.setName(name);
    c.setColor(color);
    c.setSlug(slug);

    //other entities
    for (Playlist playlist : playlists) {
      playlist.setCategory(c);
    }
    c.setPlaylists(playlists);

    c.setCreated(created);
    c.setLastModified(lastModified);

    return c;
  }

  private Playlist buildPlaylist(Long id, String code, Date created, String desc, Difficulty diff, boolean enabled, Date lastModified, String name, String slug, LanguageCode lang, Provider prov, Author author, List<Video> videos) {
    Playlist e = new Playlist();
    e.setId(id);
    e.setName(name);
    e.setCode(code);
    e.setDescription(desc);
    e.setDifficulty(diff);
    e.setEnabled(enabled);
    e.setLanguageCode(lang);
    e.setSlug(slug);
    e.setProvider(prov);

    //other entities
    e.setAuthor(author);
    for (Video video : videos) {
      if (video != null) {
        video.setPlaylist(e);
      }
    }
    e.setVideos(videos);

    e.setCreated(created);
    e.setLastModified(lastModified);

    return e;
  }

  //(`ID`, `CODE`, `CREATED`, `DESCRIPTION`, `LASTMODIFIED`, `NAME`, `PLAYLIST_ID`)
  private Video buildVideo(Long id, String code, Date created, String desc, Date lastModified, String name, String prevImgUrl, String downUrl, String perm) {
    Video e = new Video();
    e.setId(id);
    e.setName(name);
    e.setCode(code);
    e.setDescription(desc);
    e.setDownloadUrl(downUrl);
    e.setPermalink(perm);
    e.setPreviewImageUrl(prevImgUrl);

    //other entities
    e.setCreated(created);
    e.setLastModified(lastModified);

    return e;
  }

  //(`ID`, `CHANNELURL`, `CREATED`, `DESCRIPTION`, `LASTMODIFIED`, `NAME`, `WEBSITE`) 
  private Author buildAuthor(Long id, String channelUrl, Date created, String desc, Date lastModified, String name, String website) {
    Author e = new Author();
    e.setId(id);
    e.setName(name);
    e.setDescription(desc);
    e.setWebsite(website);
    e.setChannelUrl(channelUrl);
    e.setCreated(created);
    e.setLastModified(lastModified);

    return e;
  }

}
