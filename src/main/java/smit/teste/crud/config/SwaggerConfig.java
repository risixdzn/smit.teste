package smit.teste.crud.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Gestão Produtos/Pedidos")
                        .version("1.0")
                        .description("""
                                ### 📦 Teste Técnico Spring Boot | SMIT
                                
                                Esta API REST foi desenvolvida como parte do **teste técnico da SMIT**.
                                
                                O objetivo do sistema é gerenciar produtos e pedidos de uma loja virtual, com as seguintes responsabilidades:
                                
                                - CRUD completo de produtos
                                - Criação e leitura de pedidos
                                - Controle automático de estoque com base nas quantidades dos pedido.
                                
                                #### Regras de Negócio
                                - Um pedido **não pode ser criado** se algum produto solicitado tiver **estoque insuficiente**.
                                - Ao criar um pedido, o sistema **reduz automaticamente** a quantidade em estoque dos produtos envolvidos.
                                - Produtos não podem ser cadastrados com **preço ou quantidade negativa**.
                                
                                **Contato:** Ricardo Amorim  
                                📧 me@ricardo.gg
                                """)
                        .contact(new Contact()
                                .name("Ricardo Amorim")
                                .email("me@ricardo.gg")
                        )
                )
                .tags(List.of(
                        new Tag().name("Produtos")
                                .description("Operações de CRUD para gerenciamento de produtos."),
                        new Tag().name("Pedidos")
                                .description("Criação, listagem e remoção de pedidos com validação de estoque.")
                ));
    }
}
