package api.example.utils;
import io.github.cdimascio.dotenv.Dotenv;

public class Helper {
    
    // public static Dotenv dotenv;

    // public static Dotenv loaDotenv(){
    //     if (dotenv == null) {
    //         dotenv = Dotenv.load();
    //     }
    //     return dotenv;
    // }

    // public static String getKey(String key){
    //     return loaDotenv().get(key.toUpperCase());
    // }

    private static final Dotenv dotenv = Dotenv.load();

    public static String getKey(String key) {
        return dotenv.get(key); // jangan upperCase lagi kalau di .env sudah kapital
    }
}
