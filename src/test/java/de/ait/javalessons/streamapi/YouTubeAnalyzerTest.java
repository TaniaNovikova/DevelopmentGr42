package de.ait.javalessons.streamapi;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;


class YouTubeAnalyzerTest {
    List<YoutubeVideo> testVideos1 = List.of(
            new YoutubeVideo("Обычное видео", "Regular Channel", 500_000, 8000, 600, "Спорт", false),
            new YoutubeVideo("Ещё одно обычное видео", "Another Channel", 800_000, 10000, 1200, "Кулинария", false)
    );

    List<YoutubeVideo> testVideos2 = List.of(
            new YoutubeVideo("Популярное видео", "Top Channel", 15_000_000, 12000, 720, "Образование", true),
            new YoutubeVideo("Обычное видео", "Regular Channel", 500_000, 8000, 600, "Спорт", false)
    );
    @Test
    void testIsMore10M_WhenVideoHasMoreThan10MViews() {
        // Arrange
       YouTubeAnalyzer.videos=testVideos2;

        // Act
        boolean result = YouTubeAnalyzer.isMore10M();

        // Assert
        assertTrue(result);
    }

    @Test
    void testIsMore10M_WhenNoVideoHasMoreThan10MViews() {
        // Arrange

YouTubeAnalyzer.videos=testVideos1;
        // Act
        boolean result = YouTubeAnalyzer.isMore10M();

        // Assert
        assertFalse(result);
    }

    @Test
    void testGetVideosMore1MViews() {
        // Arrange
        List<YoutubeVideo> testVideos = List.of(
                new YoutubeVideo("Vid1", "Chan1", 2_000_000, 5000, 300, "Образование", true),
                new YoutubeVideo("Vid2", "Chan2", 100_000, 2000, 200, "Спорт", false)
        );
        YouTubeAnalyzer.videos = testVideos;

        // Act
        List<YoutubeVideo> result = YouTubeAnalyzer.getVideosMore1MViews();

        // Assert
        assertEquals(1, result.size());
        assertEquals("Vid1", result.get(0).getTitle());
    }

    @Test
    void testGetVideosMore10Minutes() {
        // Arrange
        List<YoutubeVideo> testVideos = List.of(
                new YoutubeVideo("Vid1", "Chan1", 10_000_000, 23000, 650, "Education", true),
                new YoutubeVideo("Vid2", "Chan2", 300_000, 2000, 300, "Sport", false)
        );
        YouTubeAnalyzer.videos = testVideos;

        // Act
        List<YoutubeVideo> result = YouTubeAnalyzer.getVideosMore10Minutes();

        // Assert
        assertEquals(1, result.size());
        assertEquals("Vid1", result.get(0).getTitle());
    }

    @Test
    void testGetUniqueCategories() {
        // Arrange
        List<YoutubeVideo> testVideos = List.of(
                new YoutubeVideo("Vid1", "Chan1", 1_000_000, 5000, 900, "Education", true),
                new YoutubeVideo("Vid2", "Chan2", 300_000, 2000, 300, "Cooking", false),
                new YoutubeVideo("Vid3", "Chan3", 400_000, 3000, 400, "Education", true)
        );

        YouTubeAnalyzer.videos = testVideos;

        // Act
        List<String> result = YouTubeAnalyzer.getUniqueCategories();

        // Assert
        assertEquals(2, result.size());
        assertTrue(result.contains("Education"));
        assertTrue(result.contains("Cooking"));
    }

    @Test
    void testGetTitlesUpperCase() {
        // Arrange
        List<YoutubeVideo> testVideos = List.of(
                new YoutubeVideo("такое видео", "Chan1", 1_000_000, 5000, 900, "Образование", true),
                new YoutubeVideo("другое видео", "Chan2", 500_000, 2000, 300, "Спорт", false)
        );
        YouTubeAnalyzer.videos = testVideos;

        // Act
        List<String> result = YouTubeAnalyzer.getTitlesUpperCase();

        // Assert
        assertEquals(2, result.size());
        assertEquals("ТАКОЕ ВИДЕО", result.get(0));
        assertEquals("ДРУГОЕ ВИДЕО", result.get(1));
    }

    @Test
    void testGetTop3MostLikelyVideos() {
        // Arrange
        List<YoutubeVideo> testVideos = List.of(
                new YoutubeVideo("Vid1", "Chan1", 11_000_000, 5000, 900, "Образование", true),
                new YoutubeVideo("Vid2", "Chan2", 300_000, 8000, 300, "Спорт", false),
                new YoutubeVideo("Vid3", "Chan3", 400_000, 3000, 400, "Музыка", true),
                new YoutubeVideo("Vid4", "Chan4", 50_000, 10000, 500, "Игры", true)
        );
        YouTubeAnalyzer.videos = testVideos;

        // Act
        List<YoutubeVideo> result = YouTubeAnalyzer.getTop3MostLikelyVideos();

        // Assert
        assertEquals(3, result.size());
        assertEquals("Vid4", result.get(0).getTitle());
        assertEquals("Vid2", result.get(1).getTitle());
        assertEquals("Vid1", result.get(2).getTitle());
    }
}
