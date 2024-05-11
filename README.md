# Projeto de TCC Get-me (Backend)

Objetivo: Criar uma infraestrutura robusta para armazenar e proteger dados de pessoas desaparecidas.

Descrição: O backend da aplicação Get-me é implementado com Java e o Spring Framework, garantindo segurança e modularidade. A integração com o AWS S3 fornece armazenamento seguro para as fotos dos usuários.

Ferramentas Utilizadas:

- Java 17
- Spring Framework
- Spring Security: Autenticação e autorização com JWT/OAuth2.
- PostgreSQL: Banco de dados relacional hospedado no Heroku para fácil gerenciamento.
- S3 da AWS: Armazena as fotos dos usuários de forma segura e escalável.

Variáveis de Ambiente: Para garantir a segurança dos dados, as seguintes variáveis de ambiente são necessárias:

- S3_SECRET_KEY: A chave secreta da S3 da AWS deve ser fornecida no ambiente de execução, mas não pode ser disponibilizada diretamente no README.

Como Subir o Projeto:

Pré-requisitos:

1. Java 17: Certifique-se de ter a versão correta instalada.
2. Docker: Tenha o Docker instalado no sistema.

Passos:

1. Clone este repositório para a sua máquina

2. Inicie os serviços necessários com Docker Compose:
   docker-compose up -d
3. Execute a aplicação com Maven ou diretamente na IDE:
    - Com Maven:
      mvn spring-boot:run
    - Na IDE: Localize a classe principal do projeto e execute-a diretamente.

Arquitetura Geral:

O sistema é dividido em duas partes principais: backend e frontend.
- Backend: Segue o padrão MVC (Model-View-Controller) e é implementado com Spring Framework.
- Frontend: Desenvolvido com Next.js.

A comunicação entre as duas partes é feita via uma API REST.

- Se for testar as duas ferramentas juntas será necessário seguir os passos do front:
https://github.com/afranioag/getme-front.git

Deploy:

- Heroku: O backend é hospedado no Heroku, garantindo escalabilidade e facilidade de uso.
