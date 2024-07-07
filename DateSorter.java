public class DateComparator implements Comparator<String> {
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public int compare(String s1, String s2) {
        try {
            // Extract the dates from the strings
            String dateString1 = s1.substring(s1.lastIndexOf('~') + 1);
            String dateString2 = s2.substring(s2.lastIndexOf('~') + 1);

            // Parse the dates into LocalDate objects
            LocalDate date1 = LocalDate.parse(dateString1, dateFormatter);
            LocalDate date2 = LocalDate.parse(dateString2, dateFormatter);

            // Compare the LocalDate objects
            return date1.compareTo(date2);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format", e);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        strings.add("info1~info2~2023-07-01");
        strings.add("info1~info3~2023-06-30");
        strings.add("info1~info4~2023-07-03");

        // Create an instance of the comparator
        Comparator<String> comparator = new DateComparator();

        // Sort the list
        Collections.sort(strings, comparator);

        // Print the sorted list
        for (String s : strings) {
            System.out.println(s);
        }
    }
}
