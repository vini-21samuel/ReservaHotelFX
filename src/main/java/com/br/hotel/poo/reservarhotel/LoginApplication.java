package com.br.hotel.poo.reservarhotel;

import com.br.hotel.poo.reservarhotel.DataBaseconnection.DBconnection;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginApplication extends Application {

    private List<Usuario> usuarios = new ArrayList<>(); // Lista de usuários

    @Override
    public void start(Stage primaryStage) {
        // Adicionando um usuário de exemplo (Você deve preencher a lista de usuários ao cadastrar novos)
        usuarios.add(new Usuario("admin@example.com", "admin", "Nome", "Sobrenome", "12345678900")); // Exemplo

        mostrarTelaLogin(primaryStage); // Iniciar pela tela de login
    }

    // Método para exibir a tela de login
    private void mostrarTelaLogin(Stage stage) {
        // GridPane para organizar os campos
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(15);  // Espaçamento vertical
        grid.setHgap(20);  // Espaçamento horizontal
        grid.setAlignment(Pos.CENTER);

        // Carregar logo
        Image logo = new Image(getClass().getResourceAsStream("/com/br/hotel/poo/reservarhotel/imagens/logoNova.png"));
        ImageView logoView = new ImageView(logo);
        logoView.setFitHeight(100);
        logoView.setPreserveRatio(true);

        // Caixa Vertical (VBox) para logo e texto
        VBox vbox = new VBox(20);
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setPadding(new Insets(40, 0, 0, 0));

        // Texto "Login"
        Label lblTitulo = new Label("Login");
        lblTitulo.getStyleClass().add("label-title");

        // Adicionar logo e título ao VBox
        vbox.getChildren().addAll(logoView, lblTitulo);

        // Campos do formulário
        Label lblEmail = new Label("Email:");
        lblEmail.getStyleClass().add("label");
        TextField txtEmail = new TextField();
        txtEmail.getStyleClass().add("text-field");

        Label lblSenha = new Label("Senha:");
        lblSenha.getStyleClass().add("label");
        PasswordField txtSenha = new PasswordField();
        txtSenha.getStyleClass().add("password-field");

        // Adiciona os campos ao GridPane
        grid.add(lblEmail, 0, 0);  // Rótulo "Email"
        grid.add(txtEmail, 1, 0);  // Campo de email

        grid.add(lblSenha, 0, 1);  // Rótulo "Senha"
        grid.add(txtSenha, 1, 1);  // Campo de senha

        // Botão de login
        Button btnEntrar = new Button("Entrar");
        btnEntrar.getStyleClass().add("button");

        // Ação ao clicar no botão "Entrar"
        btnEntrar.setOnAction(e -> {
            String email = txtEmail.getText();
            String senha = txtSenha.getText();

            if (verificarCredenciais(email, senha)) {
                // Exibir mensagem de sucesso
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Login Feito com Sucesso");
                alert.setHeaderText(null);
                alert.setContentText("Bem-vindo ao sistema de reserva de hotéis!");
                alert.showAndWait();

                // Fechar a tela de login
                stage.close();

                // Abrir a tela principal
                abrirTelaPrincipal();
            } else {
                // Exibir mensagem de erro
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro de Login");
                alert.setHeaderText(null);
                alert.setContentText("Email ou senha incorretos.");
                alert.showAndWait();
            }
        });
        // Adicionar o botão ao GridPane (centralizando)
        grid.add(btnEntrar, 1, 2);  // Coloca o botão na segunda coluna, terceira linha

        // Adiciona o GridPane ao VBox
        vbox.getChildren().add(grid);

        // Configurando a cena e o palco
        Scene scene = new Scene(vbox, 800, 600);
        scene.getStylesheets().add(getClass().getResource("/com/br/hotel/poo/reservarhotel/CSS/style.css").toExternalForm());
        stage.setTitle("Rancho Fundo - Login");
        stage.setScene(scene);
        stage.show();
    }

    // Método para exibir a tela de cadastro
    private void mostrarTelaCadastro(Stage stage) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(15);
        grid.setHgap(20);
        grid.setAlignment(Pos.CENTER);

        // Logo e título
        Image logo = new Image(getClass().getResourceAsStream("/com/br/hotel/poo/reservarhotel/imagens/logoNova.png"));
        ImageView logoView = new ImageView(logo);
        logoView.setFitHeight(100);
        logoView.setPreserveRatio(true);

        VBox vbox = new VBox(20);
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setPadding(new Insets(40, 0, 0, 0));
        Label lblTitulo = new Label("Crie a sua conta");
        lblTitulo.getStyleClass().add("label-title");
        vbox.getChildren().addAll(logoView, lblTitulo);

        // Campos de entrada
        Label lblNome = new Label("Nome:");
        TextField txtNome = new TextField();

        Label lblSobrenome = new Label("Sobrenome:");
        TextField txtSobrenome = new TextField();

        Label lblCPF = new Label("CPF:");
        TextField txtCPF = new TextField();

        Label lblEmail = new Label("Email:");
        TextField txtEmail = new TextField();

        Label lblSenha = new Label("Senha:");
        PasswordField txtSenha = new PasswordField();

        Label lblConfirmarSenha = new Label("Confirmar senha:");
        PasswordField txtConfirmarSenha = new PasswordField();

        // Adicionando campos ao grid
        grid.add(lblNome, 0, 0);
        grid.add(txtNome, 1, 0);
        grid.add(lblSobrenome, 2, 0);
        grid.add(txtSobrenome, 3, 0);

        grid.add(lblCPF, 0, 1);
        grid.add(txtCPF, 1, 1);
        grid.add(lblEmail, 2, 1);
        grid.add(txtEmail, 3, 1);

        grid.add(lblSenha, 0, 2);
        grid.add(txtSenha, 1, 2);
        grid.add(lblConfirmarSenha, 2, 2);
        grid.add(txtConfirmarSenha, 3, 2);

        // Botão de cadastro
        Button btnCadastrar = new Button("Criar conta");
        btnCadastrar.setOnAction(e -> {
            String nome = txtNome.getText();
            String sobrenome = txtSobrenome.getText();
            String email = txtEmail.getText();
            String senha = txtSenha.getText();
            String confirmarSenha = txtConfirmarSenha.getText();

            if (senha.equals(confirmarSenha)) {
                Usuario usuario = new Usuario(email, senha, nome, sobrenome, txtCPF.getText());
                usuarios.add(usuario);  // Adiciona o usuário à lista

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucesso");
                alert.setHeaderText(null);
                alert.setContentText("Cadastro realizado com sucesso!");
                alert.showAndWait();

                // Após o cadastro, redireciona para a tela de login
                mostrarTelaLogin(stage);  // Volta para a tela de login
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText(null);
                alert.setContentText("As senhas não coincidem!");
                alert.showAndWait();
            }
        });

        // Adicionar o botão de cadastro ao grid
        grid.add(btnCadastrar, 0, 4, 4, 1);
        vbox.getChildren().add(grid);

        Scene scene = new Scene(vbox, 800, 600);
        scene.getStylesheets().add(getClass().getResource("/com/br/hotel/poo/reservarhotel/CSS/style.css").toExternalForm());
        stage.setTitle("Rancho Fundo - Crie sua conta");
        stage.setScene(scene);
        stage.show();
    }

    // Método para verificar as credenciais de login
    private boolean verificarCredenciais(String email, String senha) {
        try (Connection conn = DBconnection.getConnection()) {
            String sql = "SELECT * FROM usuarios WHERE email = ? AND senha = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Credenciais corretas
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    // Método para abrir a Tela Principal
    private void abrirTelaPrincipal() {
        TelaPrincipal telaPrincipal = new TelaPrincipal();
        Stage stage = new Stage();
        try {
            telaPrincipal.start(stage); // Iniciar a tela principal
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
