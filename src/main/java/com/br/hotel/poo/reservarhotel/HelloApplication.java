package com.br.hotel.poo.reservarhotel;

import com.br.hotel.poo.reservarhotel.DAO.UsuarioDAO;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {

    private List<Usuario> usuarios = new ArrayList<>(); // Lista para armazenar usuários

    @Override
    public void start(Stage primaryStage) {
        // GridPane para organizar os campos
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(15);  // Espaçamento vertical
        grid.setHgap(20);  // Espaçamento horizontal
        grid.setAlignment(Pos.CENTER);

        // Carregar logo a partir dos recursos
        Image logo = new Image(getClass().getResourceAsStream("/com/br/hotel/poo/reservarhotel/imagens/logoNova.png"));
        ImageView logoView = new ImageView(logo);
        logoView.setFitHeight(100);
        logoView.setPreserveRatio(true);

        // Caixa Vertical (VBox) para logo e texto
        VBox vbox = new VBox(20);
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setPadding(new Insets(40, 0, 0, 0));

        // Texto abaixo da logo
        Label lblTitulo = new Label("Crie a sua conta");
        lblTitulo.getStyleClass().add("label-title"); // Adicionando a classe CSS para o título

        // Adiciona a logo e o título ao VBox
        vbox.getChildren().addAll(logoView, lblTitulo);

        // Campos do formulário
        Label lblNome = new Label("Nome:");
        lblNome.getStyleClass().add("label");
        TextField txtNome = new TextField();
        txtNome.getStyleClass().add("text-field");

        Label lblSobrenome = new Label("Sobrenome:");
        lblSobrenome.getStyleClass().add("label");
        TextField txtSobrenome = new TextField();
        txtSobrenome.getStyleClass().add("text-field");

        Label lblCPF = new Label("CPF:");
        lblCPF.getStyleClass().add("label");
        TextField txtCPF = new TextField();
        txtCPF.getStyleClass().add("text-field");

        Label lblEmail = new Label("Email:");
        lblEmail.getStyleClass().add("label");
        TextField txtEmail = new TextField();
        txtEmail.getStyleClass().add("text-field");

        Label lblSenha = new Label("Senha:");
        lblSenha.getStyleClass().add("label");
        PasswordField txtSenha = new PasswordField();
        txtSenha.getStyleClass().add("text-field");

        Label lblConfirmarSenha = new Label("Confirmar senha:");
        lblConfirmarSenha.getStyleClass().add("label");
        PasswordField txtConfirmarSenha = new PasswordField();
        txtConfirmarSenha.getStyleClass().add("text-field");

        // Adiciona os campos ao GridPane
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
        btnCadastrar.getStyleClass().add("button");
        btnCadastrar.setId("btn-cadastrar");

        // Evento ao clicar no botão "Criar conta"
        btnCadastrar.setOnAction(e -> {
            // Dados coletados do cadastro
            String nome = txtNome.getText();
            String sobrenome = txtSobrenome.getText();
            String cpf = txtCPF.getText();
            String email = txtEmail.getText();
            String senha = txtSenha.getText();
            String confirmarSenha = txtConfirmarSenha.getText();

            // Validar os campos
            if (nome.isEmpty() || sobrenome.isEmpty() || cpf.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Erro");
                alert.setHeaderText(null);
                alert.setContentText("Por favor, preencha todos os campos.");
                alert.showAndWait();
                return;
            }

            // Validação de senha
            if (!senha.equals(confirmarSenha)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText(null);
                alert.setContentText("As senhas não coincidem!");
                alert.showAndWait();
                return;
            }

            // Cria o objeto Usuario
            Usuario usuario = new Usuario(nome, sobrenome, cpf, email, senha);

            // Chama o DAO para cadastrar o usuário no banco
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.cadastrarUsuario(usuario);

            // Define o usuário na sessão atual
            SessaoUsuario.setUsuarioLogado(usuario);

            // Exibe pop-up de sucesso
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sucesso");
            alert.setHeaderText(null);
            alert.setContentText("Cadastro realizado com sucesso!");
            alert.showAndWait();

            // Limpar campos após o cadastro
            txtNome.clear();
            txtSobrenome.clear();
            txtCPF.clear();
            txtEmail.clear();
            txtSenha.clear();
            txtConfirmarSenha.clear();
        });

        // Adiciona o botão ao GridPane
        grid.add(btnCadastrar, 0, 4, 4, 1); // Centraliza e ocupa 4 colunas

        // Adiciona o GridPane ao VBox
        vbox.getChildren().add(grid);

        // Configurando a cena e o palco
        Scene scene = new Scene(vbox, 800, 600);
        scene.getStylesheets().add(getClass().getResource("/com/br/hotel/poo/reservarhotel/CSS/style.css").toExternalForm());
        primaryStage.setTitle("Rancho Fundo - Crie sua conta");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
