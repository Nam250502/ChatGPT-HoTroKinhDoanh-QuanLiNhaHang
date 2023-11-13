package nam.nguyen.store.controller;

import nam.nguyen.store.model.OpenAiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class ChatController {

    @Value("${openai.api.key}") // Đặt giá trị trong file application.properties hoặc application.yml
    private String openaiApiKey;

    @GetMapping("/chat")
    public String getChatResult(Model model) {
        // Gửi request đến API OpenAI
        String apiUrl = "https://api.openai.com/v1/chat/completions";
        String gptModel = "gpt-3.5-turbo";

        String content = "chào bạn";
        // Tạo payload theo định dạng JSON
//        String payload = "{ \"model\": \"" + gptModel + "\", " +
//                "\"messages\": [ {\"role\": \"system\", \"content\": \"You are a helpful assistant.\"}, " +
//                "{\"role\": \"user\", \"content\": \"hãy trả lời bằng tiếng việt bạn là ai\"} ] }";
        String payload = "{ \"model\": \"" + gptModel + "\", " +
                "\"messages\": [ {\"role\": \"system\", \"content\": \"You are a helpful assistant.\"}, " +
                "{\"role\": \"user\", \"content\": \""+content+"\"} ] }";


        // Gửi request
        RestTemplate restTemplate = new RestTemplate();
        OpenAiResponse response = restTemplate.postForObject(apiUrl, createHttpEntity(payload), OpenAiResponse.class);

        // Lưu kết quả vào model để hiển thị trên giao diện
        model.addAttribute("assistantReply", response.getChoices().get(0).getMessage().getContent());

        return "chat";
    }

    private HttpEntity<String> createHttpEntity(String payload) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + openaiApiKey);

        return new HttpEntity<>(payload, headers);
    }
}
