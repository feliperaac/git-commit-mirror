package com.feliperaac.gitcommitmirror.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

@Service
public class WhatsAppService {

    @Value("${evolution.api.url}")
    private String apiUrl;

    @Value("${evolution.api.key}")
    private String apiKey;

    @Value("${evolution.api.instance}")
    private String instance;

    @Value("${evolution.api.group.id}")
    private String groupId;

    private final RestTemplate restTemplate = new RestTemplate();

public void enviarMensagemComImagem(String texto, String urlImagem) {
        // Endpoint correto para envio de mídia na EvolutionAPI
        String url = apiUrl + "/message/sendMedia/" + instance;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("apikey", apiKey);

        // Montando o JSON exatamente como a EvolutionAPI exige
        Map<String, Object> body = new HashMap<>();
        body.put("number", groupId);
        body.put("media", urlImagem);       // A URL da imagem que a Evolution vai baixar
        body.put("mediatype", "image");     // Tipo precisa ser 'image' em minúsculo
        body.put("caption", texto);         // O texto que acompanha a foto

        try {
            HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);
            // Executa a chamada POST
            restTemplate.postForEntity(url, request, String.class);
            System.out.println("📢 Mensagem enviada com sucesso para o WhatsApp!");
        } catch (Exception e) {
            System.err.println("❌ Erro ao enviar mensagem para a EvolutionAPI: " + e.getMessage());
        }
    }
}