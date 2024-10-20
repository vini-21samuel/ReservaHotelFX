package com.br.hotel.poo.reservarhotel;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TelaListarHoteis extends Application {

    private String destinoEscolhido;

    // Construtor para receber o destino
    public TelaListarHoteis(String destinoEscolhido) {
        this.destinoEscolhido = destinoEscolhido;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Lista de Hotéis");

        // Criando o layout principal (BorderPane)
        BorderPane root = new BorderPane();

        // Definindo o fundo da tela
        root.setStyle("-fx-background-color: #F0F8FF;"); // Cor de fundo

        // Criando a logo do hotel
        ImageView logo = new ImageView(new Image(getClass().getResourceAsStream("/com/br/hotel/poo/reservarhotel/imagens/logoNova.png")));
        logo.setFitHeight(100);
        logo.setFitWidth(100);

        // Criando o ícone de perfil
        ImageView iconPerfil = new ImageView(new Image(getClass().getResourceAsStream("/com/br/hotel/poo/reservarhotel/imagens/perfil.png")));
        iconPerfil.setFitHeight(50);
        iconPerfil.setFitWidth(50);

        // Criação do HBox para a logo e perfil
        HBox hboxTopo = new HBox(500, logo, new Region(), iconPerfil);
        hboxTopo.setAlignment(Pos.CENTER); // Alinha no centro verticalmente
        hboxTopo.setPadding(new Insets(20));
        hboxTopo.setStyle("-fx-background-color: #FFFFFF;");

        // Criando o layout para exibir os hotéis (VBox)
        VBox vbox = new VBox();
        vbox.setSpacing(20);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.TOP_CENTER); // Alinha os elementos no topo

        // Adicionando 4 hotéis com descrições detalhadas
        vbox.getChildren().add(criarHotel("Castelo dos Sonhos", 350, "/com/br/hotel/poo/reservarhotel/imagens/castelo_sonhos.jpg", 5,
                "Um hotel majestoso em meio à natureza, perfeito para famílias e casais. " +
                        "Oferece diversas atividades ao ar livre e um spa completo para relaxamento."));
        vbox.getChildren().add(criarHotel("Paraíso Tropical", 280, "/com/br/hotel/poo/reservarhotel/imagens/paraiso_tropical.jpg", 4,
                "Localizado à beira-mar, este resort é ideal para quem busca descanso e diversão. " +
                        "Contamos com piscinas, restaurantes e um acesso exclusivo à praia."));
        vbox.getChildren().add(criarHotel("Montanha Mística", 320, "/com/br/hotel/poo/reservarhotel/imagens/montanha_mistica.jpg", 5,
                "Situado nas montanhas, oferece uma vista deslumbrante e atividades de aventura. " +
                        "Perfeito para quem ama trilhas e esportes radicais."));
        vbox.getChildren().add(criarHotel("Praia Azul Resort", 400, "/com/br/hotel/poo/reservarhotel/imagens/praia_azul_resort.jpg", 4,
                "Um resort luxuoso com serviços personalizados, ideal para lua de mel ou viagens em família. " +
                        "Inclui acesso a áreas exclusivas e tratamentos de spa."));

        // Adicionando o VBox com hotéis em um ScrollPane
        ScrollPane scrollPane = new ScrollPane(vbox);
        scrollPane.setFitToWidth(true); // Ajusta a largura do ScrollPane
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS); // Sempre exibe a barra de rolagem vertical

        // Adicionando o cabeçalho e o ScrollPane ao layout principal
        root.setTop(hboxTopo);
        root.setCenter(scrollPane);

        // Definindo a cena e exibindo a janela
        Scene scene = new Scene(root, 1000, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Método para criar o box de um hotel com imagem, nome, estrelas, descrição e botão de reserva
    private HBox criarHotel(String nome, double preco, String imagemPath, int estrelas, String descricao) {
        HBox hbox = new HBox();
        hbox.setSpacing(20);
        hbox.setAlignment(Pos.CENTER_LEFT); // Alinha os elementos à esquerda
        hbox.setPadding(new Insets(10)); // Espaçamento em torno do hotel

        // Imagem do hotel
        ImageView imagemHotel = new ImageView(new Image(getClass().getResourceAsStream(imagemPath)));
        imagemHotel.setFitHeight(150);
        imagemHotel.setFitWidth(200);

        // VBox para as informações do hotel
        VBox infoBox = new VBox();
        infoBox.setSpacing(10);
        infoBox.setAlignment(Pos.TOP_LEFT); // Alinha as informações à esquerda

        // Nome do hotel
        Label nomeHotel = new Label(nome);
        nomeHotel.setFont(new Font(18)); // Aumenta o tamanho da fonte

        // Avaliação em estrelas
        Label avaliacao = new Label();
        for (int i = 0; i < estrelas; i++) {
            avaliacao.setText(avaliacao.getText() + "★");
        }

        // Descrição do hotel
        Label descricaoHotel = new Label(descricao);
        descricaoHotel.setWrapText(true); // Permite quebra de linha
        descricaoHotel.setMaxWidth(400); // Largura máxima para a descrição

        // Adicionando o destino escolhido
        Label destinoLabel = new Label("Destino: " + destinoEscolhido);
        destinoLabel.setFont(new Font(14)); // Tamanho da fonte do destino
        destinoLabel.setWrapText(true);
        destinoLabel.setMaxWidth(400); // Largura máxima para o destino

        // HBox para o preço e botão
        VBox precoEBotaoBox = new VBox();
        precoEBotaoBox.setSpacing(10);
        precoEBotaoBox.setAlignment(Pos.CENTER_RIGHT); // Alinha à direita

        // Preço
        Label precoHotel = new Label("R$" + preco);
        precoHotel.setFont(new Font(16)); // Aumenta o tamanho da fonte

        // Botão "Reservar Hotel"
        Button reservarBtn = new Button("Reservar Hotel");
        reservarBtn.setStyle("-fx-background-color: #0F3D6E; -fx-text-fill: white; "
                + "-fx-border-radius: 10px; -fx-background-radius: 10px; "
                + "-fx-border-color: #0F3D6E; -fx-border-width: 3px;"); // Borda radius grossa azul

        // Adicionando evento ao botão de reserva
        reservarBtn.setOnAction(e -> {
            Alert alerta = new Alert(AlertType.CONFIRMATION);
            alerta.setTitle("Confirmação de Reserva");
            alerta.setHeaderText("Tem certeza que deseja reservar o hotel: " + nome + "?");
            alerta.setContentText("Preço: R$" + preco);
            alerta.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    Alert sucessoAlert = new Alert(AlertType.INFORMATION);
                    sucessoAlert.setTitle("Reserva Confirmada");
                    sucessoAlert.setHeaderText("Reserva realizada com sucesso!");
                    sucessoAlert.setContentText("Você reservou o hotel: " + nome + " por R$" + preco);
                    sucessoAlert.showAndWait();
                }
            });
        });

        // Adicionando preço e botão no VBox
        precoEBotaoBox.getChildren().addAll(precoHotel, reservarBtn);

        // Adicionando componentes ao infoBox
        infoBox.getChildren().addAll(nomeHotel, avaliacao, descricaoHotel, destinoLabel);

        // Adicionando a imagem e as informações no HBox
        hbox.getChildren().addAll(imagemHotel, infoBox); // Adiciona imagem e informações no HBox

        // Adiciona um espaço entre o infoBox e o preço e botão
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS); // Faz o espaço crescer, empurrando o preço e botão para a direita
        hbox.getChildren().add(spacer);
        hbox.getChildren().add(precoEBotaoBox); // Adiciona preço e botão no HBox

        return hbox;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
