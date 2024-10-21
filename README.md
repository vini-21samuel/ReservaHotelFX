![Captura de tela de 2024-10-20 21-29-17](https://github.com/user-attachments/assets/d4acf8bf-5486-474c-9e33-00b6daff6af8)

# Sistema de Reserva de Hotéis

## Descrição do Projeto

Este projeto é um sistema de reserva de hotéis desenvolvido em JavaFX, com o objetivo de facilitar o gerenciamento de reservas de clientes em hotéis. O sistema permite que os usuários se cadastrem, realizem login e façam reservas de forma simples e eficiente. Os dados são armazenados em um banco de dados MySQL.

## Funcionalidades

### 1. Cadastro de Usuário
- Os usuários podem se cadastrar no sistema fornecendo informações como nome, email e senha.
- O sistema valida os dados inseridos e armazena as informações no banco de dados.

### 2. Login
- Os usuários podem realizar login utilizando seu email e senha cadastrados.
- O sistema verifica as credenciais e permite o acesso ao sistema caso estejam corretas.

### 3. Reserva de Hóteis
- Após o login, os usuários podem visualizar uma lista de hotéis disponíveis.
- Os usuários podem realizar reservas selecionando um hotel e fornecendo detalhes como data de check-in e check-out, número de hóspedes, etc.
- As reservas são salvas no banco de dados e podem ser gerenciadas pelo usuário.

### 4. Gerenciamento de Hotéis
- O sistema permite o cadastro de novos hotéis, incluindo informações como nome, descrição, preço e disponibilidade.
- Os hotéis podem ser editados ou removidos conforme necessário.

## Tecnologias Utilizadas
- **JavaFX**: Para a criação da interface gráfica do usuário.
- **MySQL**: Para armazenamento de dados, incluindo informações de usuários e reservas.
- **JDBC**: Para conexão entre o Java e o banco de dados MySQL.

## Estrutura do Banco de Dados

O banco de dados contém as seguintes tabelas:

- **Usuarios**
  - `id`: Identificador único do usuário.
  - `nome`: Nome do usuário.
  - `email`: Email do usuário.
  - `senha`: Senha do usuário.

- **Hoteis**
  - `id`: Identificador único do hotel.
  - `nome`: Nome do hotel.
  - `descricao`: Descrição do hotel.
  - `preco`: Preço da diária.

- **Reservas**
  - `id`: Identificador único da reserva.
  - `usuario_id`: Identificador do usuário que fez a reserva.
  - `hotel_id`: Identificador do hotel reservado.
  - `data_checkin`: Data de check-in.
  - `data_checkout`: Data de check-out.

## Como Executar o Projeto

1. Clone o repositório:
   ```bash
   git clone <URL_DO_REPOSITORIO>
