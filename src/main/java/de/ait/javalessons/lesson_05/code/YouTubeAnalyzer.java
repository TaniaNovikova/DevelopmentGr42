package de.ait.javalessons.lesson_05.code;

public class YouTubeAnalyzer {
    // Список видео для анализа / List of videos for analysis
    private static java.util.List<de.ait.javalessons.streamapi.YoutubeVideo> videos = java.util.List.of(
            new de.ait.javalessons.streamapi.YoutubeVideo("Как научиться программировать", "IT Channel", 15000000, 12000, 720, "Образование", true),
            new de.ait.javalessons.streamapi.YoutubeVideo("Лучшие моменты матча", "Sports Channel", 500000, 8000, 600, "Спорт", false),
            new de.ait.javalessons.streamapi.YoutubeVideo("Новый трек 2025", "Music Channel", 3000000, 25000, 240, "Музыка", true),
            new de.ait.javalessons.streamapi.YoutubeVideo("Обзор новой игры", "Gaming Channel", 2000000, 15000, 900, "Игры", true),
            new de.ait.javalessons.streamapi.YoutubeVideo("Как приготовить пиццу", "Cooking Channel", 800000, 10000, 1200, "Кулинария", false),
            new de.ait.javalessons.streamapi.YoutubeVideo("Как приготовить кофе", "Cooking Channel", 100_000, 70000, 1400, "Кулинария", true)
    );

    public static void main(String[] args) {
        //System.out.println(getVideosMore1MViews());
        //System.out.println(getVideosMore10Minutes());
        //System.out.println(getUniqueCategories());
        //System.out.println(getTitlesUpperCase());
        //System.out.println(getTop3MostLikelyVideos());
        System.out.println(isMore10M(videos));
    }

    public static java.util.List<de.ait.javalessons.streamapi.YoutubeVideo>  getVideosMore1MViews(){
        java.util.List<de.ait.javalessons.streamapi.YoutubeVideo> videosMore1MViews = videos.stream()
                .filter(video -> video.getViews() > 1_000_000)
                .collect(java.util.stream.Collectors.toList());
        return videosMore1MViews;
    }

    public static java.util.List<de.ait.javalessons.streamapi.YoutubeVideo>  getVideosMore10Minutes(){
        java.util.List<de.ait.javalessons.streamapi.YoutubeVideo> videosMore10Minutes = videos.stream()
                .filter(video -> video.getDuration() > 600)
                .collect(java.util.stream.Collectors.toList());
        return videosMore10Minutes;
    }

    public static java.util.List<String> getUniqueCategories(){
        java.util.List<String> uniqueCategories = videos.stream()
                .map(de.ait.javalessons.streamapi.YoutubeVideo::getCategory)
                .distinct()
                .collect(java.util.stream.Collectors.toList());
        return uniqueCategories;
    }

    public static java.util.List<String> getTitlesUpperCase(){
        java.util.List<String> titlesUpperCase = videos.stream()
                //.map(video -> video.getTitle().toUpperCase())
                .map(de.ait.javalessons.streamapi.YoutubeVideo::getTitle)
                .map(String::toUpperCase)
                .collect(java.util.stream.Collectors.toList());
        return titlesUpperCase;
    }

    public static java.util.List<de.ait.javalessons.streamapi.YoutubeVideo> getTop3MostLikelyVideos(){
        java.util.List<de.ait.javalessons.streamapi.YoutubeVideo> top3MostLikelyVideos = videos.stream()
                .sorted(java.util.Comparator.comparingInt(de.ait.javalessons.streamapi.YoutubeVideo::getLikes).reversed())
                .limit(3)
                .collect(java.util.stream.Collectors.toList());
        return top3MostLikelyVideos;
    }

    public static boolean isMore10M(java.util.List<de.ait.javalessons.streamapi.YoutubeVideo> videosList){
        boolean result = videosList.stream()
                .anyMatch(video -> video.getViews() > 10_000_000);
        return result;
    }
}
