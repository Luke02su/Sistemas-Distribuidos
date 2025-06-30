package com.sd.prj_controle_equipamentos_ti_gestor.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // Indica que esta é uma classe de configuração do Spring
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Aplica as configurações CORS a todos os caminhos da sua API (ex: /gestor/lojas, /gestor/equipamentos)
                .allowedOrigins("http://localhost:8091") // **MUITO IMPORTANTE:** Especifique a URL exata do seu frontend
                                                        // Se você tiver múltiplos frontends, pode adicionar mais:
                                                        // .allowedOrigins("http://localhost:8091", "http://outro-frontend.com")
                                                        // Para desenvolvimento, pode usar ".allowedOrigins("*")", mas NUNCA em produção!
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Permite todos os métodos HTTP que você usa
                .allowedHeaders("*") // Permite todos os cabeçalhos na requisição
                .allowCredentials(true) // Permite o envio de cookies ou cabeçalhos de autorização
                .maxAge(3600); // Define por quanto tempo (em segundos) as respostas OPTIONS podem ser armazenadas em cache pelo navegador
    }
}
