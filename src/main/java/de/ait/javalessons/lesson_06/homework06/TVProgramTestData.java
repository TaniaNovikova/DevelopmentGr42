package de.ait.javalessons.lesson_06.homework06;

import java.util.Arrays;
import java.util.List;
import java.util.Comparator;
import java.util.Optional;
import java. util. OptionalDouble;
import java.util.Map;
import java.util.stream.Collectors;

public class TVProgramTestData {
    public static List<TVProgram> getTVProgramList() {
        return Arrays.asList(
                new TVProgram("Channel One", "Morning News", 30, true, 7.8),
                new TVProgram("Channel One", "Late Show", 45, false, 8.1),
                new TVProgram("SportsTV", "Football Match", 120, true, 9.0),
                new TVProgram("MovieMax", "Action Movie", 110, false, 8.5),
                new TVProgram("MovieMax", "Romantic Comedy", 100, false, 6.9),
                new TVProgram("EduChannel", "Science Doc", 60, false, 7.5),
                new TVProgram("ComedyFun", "Stand-up Special", 90, false, 8.2),
                new TVProgram("ComedyFun", "Improv Show", 25, true, 7.3)
        );
    }

    public static void main(String[] args) {
        List<TVProgram> tvProgramList = getTVProgramList();

        /* ******************1. Filtering by Rating*************************/
//1)Find all programs with a rating above a specified threshold (e.g., > 8.0).
        List<String> tvProgramList1=tvProgramList.stream()
                .filter(p->p.getRating()>8)
                .map(p->p.getProgramName())
                .collect(Collectors.toList());
        System.out.println("1) Programs with a rating above 8.0: "+ " "+tvProgramList1);


        /*****************2. Transforming Data Using `map`***************************/
//2)Transform `TVProgram` objects into user-friendly strings. For example,
//format the string as:
//"Channel: [channel] | Program: [programName] | Rating: [rating]"

        List<String> formattedPrograms1 = TVProgramTestData.getTVProgramList().stream()
                .map(tv -> String.format("Channel: %s | Program: %s | Rating: %.1f",
                        tv.getChannel(), tv.getProgramName(), tv.getRating()))
                .toList();
        formattedPrograms1.forEach(System.out::println);
        System.out.println("*************************************");

        List<String> formattedPrograms2 = TVProgramTestData.getTVProgramList().stream()
                .map(tv -> "Channel: " + tv.getChannel() +
                        " | Program: " + tv.getProgramName() +
                        " | Rating: " + tv.getRating())
                .toList();

        formattedPrograms2.forEach(System.out::println);

        /****************3. Checking a Condition*********************/
//Check if there is at least one program that is broadcast live (`isLive == true`).
        boolean isLiveExists = tvProgramList.stream().anyMatch(TVProgram::isLive);
        System.out.println("Live exists? " + isLiveExists);

        /*************** 4. Finding the Maximum Value**********************/
//Determine the longest program (maximum value of the `duration` field).

        Optional<TVProgram> longestProgram1 = tvProgramList.stream()
                .max(Comparator.comparingInt(TVProgram::getDuration));
        System.out.println(longestProgram1.get());

        longestProgram1.ifPresent(tv -> {
            String formattedOutput = String.format("Program: %s | Duration: %d",
                    tv.getProgramName(),
                     tv.getDuration());
            System.out.println(formattedOutput);
        });

        /**************5. Calculating the Average Rating***********************/

       OptionalDouble averageRating = tvProgramList.stream()
                .mapToDouble(TVProgram::getRating)
                .peek(System.out::println)
                .average();

        averageRating.ifPresent(avg -> System.out.println("Average rating: " + avg));// 7.9125

        /*************6. Grouping by Channel************************/
//Use `Collectors.groupingBy(TVProgram::getChannel)` to create a map where the key
// is the channel name and the value is a list of programs on that channel.

        Map<String, List<TVProgram>> programsByChannel = tvProgramList.stream()
                .collect(Collectors.groupingBy(TVProgram::getChannel));

        programsByChannel.forEach((channel, programList) -> {
            System.out.println("Channel: " + channel);
            programList.forEach(program -> System.out.println("  " + program));
        });

        /*Output:
        Channel: Channel One
  TVProgram{channel='Channel One', programName='Morning News', duration=30, isLive=true, rating=7,8}
  TVProgram{channel='Channel One', programName='Late Show', duration=45, isLive=false, rating=8,1}
Channel: ComedyFun
  TVProgram{channel='ComedyFun', programName='Stand-up Special', duration=90, isLive=false, rating=8,2}
  TVProgram{channel='ComedyFun', programName='Improv Show', duration=25, isLive=true, rating=7,3}
Channel: SportsTV
  TVProgram{channel='SportsTV', programName='Football Match', duration=120, isLive=true, rating=9,0}
Channel: MovieMax
  TVProgram{channel='MovieMax', programName='Action Movie', duration=110, isLive=false, rating=8,5}
  TVProgram{channel='MovieMax', programName='Romantic Comedy', duration=100, isLive=false, rating=6,9}
Channel: EduChannel
  TVProgram{channel='EduChannel', programName='Science Doc', duration=60, isLive=false, rating=7,5}*/

        /*************7. Sorting************************/
        //Sort the list of programs by channel name or by rating (e.g., in descending order).
        System.out.println("sortedByChannleNameReverced");
        List<TVProgram> sortedByChannleNameReverced = tvProgramList.stream()
                .sorted(Comparator.comparing(TVProgram::getChannel, Comparator.reverseOrder()))
                .collect(Collectors.toList());

        sortedByChannleNameReverced.forEach(System.out::println);
        System.out.println("*****************************");

        System.out.println("sortedByRatingReverced");
        List<TVProgram> sortedByRatingReverced = tvProgramList.stream()
                .sorted(Comparator.comparing(TVProgram::getRating, Comparator.reverseOrder()))
                .collect(Collectors.toList());

        sortedByChannleNameReverced.forEach(System.out::println);
        System.out.println("**********************");

        System.out.println("sortedByChannelThenByRatingReverced");
List<TVProgram> sortedPrograms = tvProgramList.stream()
                .sorted(Comparator.comparing(TVProgram::getChannel)
                        .thenComparing(TVProgram::getRating, Comparator.reverseOrder()))
                .collect(Collectors.toList());

        sortedPrograms.forEach(System.out::println);

    }
}

