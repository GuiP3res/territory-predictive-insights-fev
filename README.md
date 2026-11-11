# Territory Predictive Insights — FloodGuard

**Global Solution 2026.2 — CitizenTech / Programação Orientada a Objetos (FIAP)**

**Aluno:** Guilherme Verrillo Peres • **RM:** 563981 • **Turma:** 2CCPH

## Problema e público beneficiado
Associações comunitárias e equipes de apoio precisam acompanhar chuvas intensas e possíveis alagamentos em regiões vulneráveis. Este protótipo registra dados simulados, calcula estimativas simples e sinaliza quando um valor supera os limiares configurados. O sistema apoia decisões humanas: não substitui medição oficial nem alertas da Defesa Civil.

## Tecnologias
Java 21, Maven, Spring Boot 3.5.7, Spring Data JPA, H2 em arquivo, JUnit 5 e Mockito. Aplicação apenas de console; não exige API REST ou interface gráfica.

## Executar
Instale Java 21 e Maven 3.9+. Na raiz do repositório:
- Testes e build: `mvn clean verify`
- Iniciar menu: `mvn spring-boot:run`
- Para executar o JAR empacotado: `java -jar target/territory-predictive-insights-1.0.0.jar`

Menu: verificar sensores, registrar leituras, executar previsões, consultar histórico, listar alertas, modificar limiares, ver status e exportar CSV. São necessárias 5 medições por sensor para média móvel e 10 para comparação. Repita a opção 2 antes de prever.

Os dados persistem localmente no banco H2 em `dados/territory.mv.db` e os CSVs ficam em `exportacoes/`. Reinicie o programa para conferir que o histórico permanece. As pastas de dados e exportação não são versionadas.

## Modelagem e decisões técnicas
`ComponenteTerritorial` é uma classe abstrata; `EstacaoMonitoramento` a estende. A interface `Sensor` possui três implementações: chuva (mm), nível do rio (m) e umidade do solo (%). `DadosTerritorio` encapsula os limiares e permite alterá-los mediante código de acesso de demonstração. Configure a variável de ambiente `TERRITORY_ACCESS_CODE` antes de usar; a senha padrão 1234 é apenas didática e inadequada para produção.

`EstrategiaPrevisao` é abstrata e define duas subclasses: `PrevisaoMediaMovel`, que usa as últimas N medições, e `PrevisaoComparacao`, que estima o próximo valor por atual + (atual - anterior), usando duas janelas consecutivas. A `FabricaPrevisao` implementa Factory para centralizar a criação e evitar lógica duplicada. Os dados são consultados pelos repositórios JPA, e o serviço de monitoramento armazena os alertas com nível ATENCAO, ALERTA ou CRITICO.

`DadosInvalidosException` sinaliza leituras e limiares inválidos; `FonteIndisponivelException` indica falhas simuladas nos sensores. No console, os erros são apresentados com mensagens úteis.

## Testes
`mvn clean verify` executa testes de domínio, exceções (assertThrows), estratégias, Factory, serviços com mocks Mockito, exportação CSV e integração com H2 após reinicialização do contexto. O workflow do GitHub Actions usa Java 21 para a mesma verificação. Confira a aba Actions para o resultado real dos testes; a existência dos testes não significa aprovação automática.

## Reflexão crítica
A plataforma deve se calar quando faltam leituras suficientes ou quando o sensor falha. Cinco amostras representam uma janela pequena e, isoladamente, não sustentam uma previsão meteorológica confiável. Um sensor por região não representa a heterogeneidade de relevo, drenagem, bueiros, ocupação urbana ou microclima. Falhas, leituras extremas e dados desatualizados podem alterar dramaticamente médias simples. O sinal emitido deve ser interpretado junto com dados de outras fontes e experiência local. A decisão de evacuar, fechar uma via ou mobilizar recursos cabe às equipes responsáveis e não ao algoritmo. O software é um auxílio educacional à decisão, jamais um sistema público certificado de alerta.

## Uso de inteligência artificial e participação
A IA auxiliou na elaboração de exemplos de código, arquitetura, organização dos testes e documentação. A revisão, a compreensão da implementação e a validação final devem ser realizadas por Guilherme Verrillo Peres, único integrante. Não foram usados dados pessoais reais.
