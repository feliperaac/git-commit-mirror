package com.feliperaac.gitcommitmirror.runner;

import com.feliperaac.gitcommitmirror.model.Promocao;
import com.feliperaac.gitcommitmirror.repository.PromocaoRepository;
import com.feliperaac.gitcommitmirror.service.WhatsAppService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TesteEnvioRunner implements CommandLineRunner {

    private final PromocaoRepository promocaoRepository;
    private final WhatsAppService whatsAppService;

    // O Spring injeta automaticamente o repositório e o serviço aqui
    public TesteEnvioRunner(PromocaoRepository promocaoRepository, WhatsAppService whatsAppService) {
        this.promocaoRepository = promocaoRepository;
        this.whatsAppService = whatsAppService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("🧪 [TESTE] Iniciando teste de fluxo do Bot...");

        String fakeProductId = "AMZN-TESTE-123";

        // 1. Testa o Banco de Dados (Postgres)
        if (!promocaoRepository.existsByProductId(fakeProductId)) {
            System.out.println("💾 [TESTE] Produto não encontrado no banco. Salvando histórico...");
            
            Promocao testePromo = new Promocao(
                fakeProductId, 
                "Produto de Teste do Bot", 
                "https://amzn.to/testeAfiliado"
            );
            promocaoRepository.save(testePromo);
            
            System.out.println("✅ [TESTE] Gravado com sucesso no Postgres!");
        } else {
            System.out.println("⚠️ [TESTE] O ID do produto já existe no banco. Ignorando envio para não duplicar.");
            return; // Corta o fluxo aqui se já foi enviado antes
        }

        // 2. Testa o envio para o WhatsApp através da EvolutionAPI
        String mensagemFormatada = """
            🔥 *PROMOÇÃO DE TESTE* 🔥
            
            🛍️ *Produto de Teste do Bot*
            
            🔗 Compre aqui: https://amzn.to/testeAfiliado
            
            _Enviado automaticamente pelo GitCommitMirror Bot_
            """;
            
        // URL de uma imagem pública qualquer apenas para testar o envio de mídia
       String urlImagemTeste = "https://github.com/fluidicon.png";

        System.out.println("🚀 [TESTE] Disparando requisição para a EvolutionAPI...");
        whatsAppService.enviarMensagemComImagem(mensagemFormatada, urlImagemTeste);
    }
}