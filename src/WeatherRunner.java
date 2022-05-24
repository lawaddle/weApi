import java.util.Scanner;

public class WeatherRunner {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter zip code for weather: ");
        String zip = s.nextLine();
        System.out.print("How many days? ");
        int days = s.nextInt();
        s.nextLine();

        WeatherAPI api = new WeatherAPI();
        api.getForecast(zip, days);

        System.out.print("Enter zip code for space: ");
        String loc = s.nextLine();
        System.out.print("When (give date)? ");
        String date = s.nextLine();
        api.getSpacecast(loc, date);
    }
}