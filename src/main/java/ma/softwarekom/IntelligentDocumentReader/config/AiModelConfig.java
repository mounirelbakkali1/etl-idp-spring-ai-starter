package ma.softwarekom.IntelligentDocumentReader.config;

import ai.djl.Model;
import ai.djl.translate.Translator;
import org.apache.tika.parser.microsoft.ooxml.xwpf.ml2006.Word2006MLParser;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.*;
import org.springframework.ai.openai.OpenAiEmbeddingModel;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.ai.transformers.TransformersEmbeddingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Configuration
public class AiModelConfig {

    @Value("${spring.ai.openai.api-key}")
    private String openAiApiKey;


    /*@Bean
    public EmbeddingModel embeddingModel() {
        // OpenAI Implementation
        return new OpenAiEmbeddingModel(new OpenAiApi(openAiApiKey));
    }*/

    @Bean
    @Primary
    public EmbeddingModel transformersEmbeddingModel() {
        // Transformers (ONNX) Embeddings Implementation
        return new TransformersEmbeddingModel();
    }

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }


}
