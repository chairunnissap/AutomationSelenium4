package api.example.utils;
import java.io.File;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.response.Response;

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
    private static ObjectMapper objectMapper, mapper = new ObjectMapper();
    private static final String DATA_PATH = "src/main/resources/";
    private static final Dotenv dotenv = Dotenv.load();

    public static String getKey(String key) {
        return dotenv.get(key); // jangan upperCase lagi kalau di .env sudah kapital
    }

    public static <T> T convertResponseToObject(Response response, Class<T> clazz){
        // ObjectMapper objectMapper = new ObjectMapper();
        // ResponseAddBooks responseAddBooks = objectMapper.readValue(response.asString(), ResponseAddBooks.class);
        objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(response.asString(), clazz);
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert response to class: " + clazz.getSimpleName(), e);
        }
    }

    public static <T> T findByUseCase(String filePath, String usecase, Class<T> clazz) {
        try {
            JsonNode rootNode = mapper.readTree(new File(DATA_PATH + filePath));
            for (JsonNode node : rootNode) {
                if (node.get("usecase").asText().equals(usecase)) {
                    JsonNode payloadNode = node.get("payload");
                    return mapper.treeToValue(payloadNode, clazz);  
                }
            }
            throw new RuntimeException("Usecase not found: " + usecase);
        } catch (Exception e) {
            throw new RuntimeException("Failed to get payload from file: " + filePath, e); 
        }
    }
}
