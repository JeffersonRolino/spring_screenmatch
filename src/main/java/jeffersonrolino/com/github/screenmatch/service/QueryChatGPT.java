package jeffersonrolino.com.github.screenmatch.service;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Value;

public class QueryChatGPT {
    public static String getTranslation(String text, String key){
        OpenAiService service = new OpenAiService(key);
        CompletionRequest request = CompletionRequest.builder()
                .model("text-davinci-003")
                .prompt("traduza para o português o texto: " + text)
                .maxTokens(1000)
                .temperature(0.7)
                .build();

        var answer = service.createCompletion(request);
        return answer.getChoices().get(0).getText();
    }
}
