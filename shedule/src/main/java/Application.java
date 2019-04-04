import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

public class Application {
    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        Bot bod = new Bot();
        try {

            telegramBotsApi.registerBot(new Bot());
            System.out.println("sad");
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
}
