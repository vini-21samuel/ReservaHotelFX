package com.br.hotel.poo.reservarhotel;

import com.br.hotel.poo.reservarhotel.DAO.reservaDAO;
import com.br.hotel.poo.reservarhotel.DAO.lugaresDAO;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.List;

public class TelaPrincipal extends Application {
    private lugaresDAO lugaresDAO = new lugaresDAO();
    private reservaDAO reservarDAO = new reservaDAO(); // Instância do ReservarDAO

    private ListView<String> listViewSugestoes = new ListView<>();

    @Override
    public void start(Stage primaryStage) {
        Label lblBemVindo = new Label();
        Usuario usuarioAtual = SessaoUsuario.getUsuarioLogado(); // Recupera o usuário logado
        if (usuarioAtual != null) {
            lblBemVindo.setText("Bem-vindo, " + usuarioAtual.getNome() + "!");
        } else {
            lblBemVindo.setText("Bem-vindo!");
        }
        lblBemVindo.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #0F3D6E;");

        // Logo do hotel
        Image logoImage = new Image(getClass().getResourceAsStream("/com/br/hotel/poo/reservarhotel/imagens/logoNova.png"));
        ImageView logoView = new ImageView(logoImage);
        logoView.setFitHeight(100);
        logoView.setFitWidth(100);

        // Ícone de perfil no canto superior direito
        Image perfilImage = new Image(getClass().getResourceAsStream("/com/br/hotel/poo/reservarhotel/imagens/perfil.png"));
        ImageView perfilView = new ImageView(perfilImage);
        perfilView.setFitHeight(50);
        perfilView.setFitWidth(50);
        perfilView.setPreserveRatio(true);
        perfilView.setStyle("-fx-cursor: hand;");

        perfilView.setOnMouseClicked(e -> {
            System.out.println("Clique no ícone de perfil");
        });

        // Botão para exibir popup de hóspedes
        Button btnReservar = new Button("Reservar");
        btnReservar.setStyle("-fx-background-color: #0F3D6E; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 8px 16px;");

        Popup popupHospedes = new Popup();
        popupHospedes.setAutoHide(true);

        Spinner<Integer> adultosSpinner = new Spinner<>(1, 10, 2);
        Spinner<Integer> criancasSpinner = new Spinner<>(0, 10, 0);
        Spinner<Integer> quartosSpinner = new Spinner<>(1, 5, 1);

        VBox popupContent = new VBox(10);
        popupContent.setPadding(new Insets(20));
        popupContent.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-background-color: white;");

        Button confirmarBtn = new Button("Confirmar");
        confirmarBtn.setOnAction(e -> {
            int adultos = adultosSpinner.getValue();
            int criancas = criancasSpinner.getValue();
            int quartos = quartosSpinner.getValue();
            int totalHospedes = adultos + criancas;

            btnReservar.setText("Reservar: " + totalHospedes + " hóspedes e " + quartos + " quarto(s)");
            popupHospedes.hide();
        });

        popupContent.getChildren().addAll(
                criarSpinnerComRotulo("Adultos", adultosSpinner),
                criarSpinnerComRotulo("Crianças", criancasSpinner),
                criarSpinnerComRotulo("Quartos", quartosSpinner),
                confirmarBtn
        );
        popupHospedes.getContent().add(popupContent);

        btnReservar.setOnAction(e -> {
            popupHospedes.setX(btnReservar.getScene().getWindow().getX() + btnReservar.getLayoutX());
            popupHospedes.setY(btnReservar.getScene().getWindow().getY() + btnReservar.getLayoutY() + btnReservar.getHeight());
            popupHospedes.show(primaryStage);
        });

        TextField campoDestino = new TextField();
        campoDestino.setPromptText(" Vai pra onde?");
        campoDestino.setMinWidth(200);
        campoDestino.setStyle("-fx-font-size: 16px; -fx-padding: 8px;");

        campoDestino.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && !newValue.trim().isEmpty()) {
                List<String> resultados = lugaresDAO.buscarEstados(newValue);
                mostrarSugestoes(resultados);
            } else {
                listViewSugestoes.getItems().clear();
                listViewSugestoes.setVisible(false);
            }
        });

        // Define a posição do ListView com base no campo de texto
        listViewSugestoes.setPrefHeight(100);
        listViewSugestoes.setVisible(false);
        listViewSugestoes.setOnMouseClicked(event -> {
            String estadoSelecionado = listViewSugestoes.getSelectionModel().getSelectedItem();
            if (estadoSelecionado != null) {
                campoDestino.setText(estadoSelecionado);
                listViewSugestoes.setVisible(false);
            }
        });

        // Configurando a aparência dos itens do ListView
        listViewSugestoes.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item);
                    setStyle("-fx-text-fill: #0F3D6E; -fx-font-weight: bold;");
                }
            }
        });

        DatePicker checkInPicker = new DatePicker();
        checkInPicker.setPromptText("Check-in");
        checkInPicker.setValue(LocalDate.now());
        checkInPicker.setStyle("-fx-background-color: #FFFFFF; -fx-font-size: 16px; -fx-padding: 8px;");

        DatePicker checkOutPicker = new DatePicker();
        checkOutPicker.setPromptText("Check-out");
        checkOutPicker.setStyle("-fx-background-color: #FFFFFF; -fx-font-size: 16px; -fx-padding: 8px;");

        Button btnPesquisar = new Button("Pesquisar");
        btnPesquisar.setStyle("-fx-background-color: #0F3D6E; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 8px 16px;");

        btnPesquisar.setOnAction(e -> {
            String destinoEscolhido = campoDestino.getText(); // Capturando o destino
            LocalDate checkIn = checkInPicker.getValue(); // Capturando a data de check-in
            LocalDate checkOut = checkOutPicker.getValue(); // Capturando a data de check-out

            // Fazer reserva (ou outra lógica relacionada) com os dados capturados
            reservarDAO.fazerReserva(destinoEscolhido, checkIn, checkOut, adultosSpinner.getValue(), criancasSpinner.getValue(), quartosSpinner.getValue());

            TelaListarHoteis telaListarHoteis = new TelaListarHoteis(destinoEscolhido);
            try {
                telaListarHoteis.start(new Stage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        HBox hboxBusca = new HBox(10);
        hboxBusca.getChildren().addAll(campoDestino, checkInPicker, checkOutPicker, btnReservar, btnPesquisar);
        hboxBusca.setAlignment(Pos.CENTER);
        hboxBusca.setPadding(new Insets(10));
        hboxBusca.setStyle("-fx-border-color: #0F3D6E; -fx-border-width: 1; -fx-border-radius: 5px; -fx-background-color: #FFFFFF;");

        // Título e subtítulo com fontes menores
        Label titulo = new Label("Conheça o Brasil");
        Label subTitulo = new Label("Estes destinos incríveis têm muito a oferecer");

        titulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #0F3D6E; -fx-alignment: center;");
        subTitulo.setStyle("-fx-font-size: 14px; -fx-text-fill: #555555; -fx-alignment: center;");

        // Grid para as imagens
        GridPane gridDestinos = new GridPane();
        gridDestinos.setHgap(20);
        gridDestinos.setVgap(10);
        gridDestinos.setPadding(new Insets(20));
        gridDestinos.setAlignment(Pos.BOTTOM_CENTER); // Alinhando ao fundo da tela

        // Adicionar as imagens dos destinos
        ImageView imgSaoPaulo = new ImageView(new Image(getClass().getResourceAsStream("/com/br/hotel/poo/reservarhotel/imagens/spo.jpg")));
        imgSaoPaulo.setFitWidth(200);
        imgSaoPaulo.setFitHeight(150);
        imgSaoPaulo.setPreserveRatio(true);

        Label labelSaoPaulo = new Label("São Paulo");
        labelSaoPaulo.setStyle("-fx-font-weight: bold; -fx-alignment: center;");

        ImageView imgRio = new ImageView(new Image(getClass().getResourceAsStream("/com/br/hotel/poo/reservarhotel/imagens/rio.jpg")));
        imgRio.setFitWidth(200);
        imgRio.setFitHeight(150);
        imgRio.setPreserveRatio(true);

        Label labelRio = new Label("Rio de Janeiro");
        labelRio.setStyle("-fx-font-weight: bold; -fx-alignment: center;");

        ImageView imgGramado = new ImageView(new Image(getClass().getResourceAsStream("/com/br/hotel/poo/reservarhotel/imagens/gramado.jpg")));
        imgGramado.setFitWidth(200);
        imgGramado.setFitHeight(150);
        imgGramado.setPreserveRatio(true);

        Label labelGramado = new Label("Gramado");
        labelGramado.setStyle("-fx-font-weight: bold; -fx-alignment: center;");

        gridDestinos.add(imgSaoPaulo, 0, 0);
        gridDestinos.add(labelSaoPaulo, 0, 1);
        gridDestinos.add(imgRio, 1, 0);
        gridDestinos.add(labelRio, 1, 1);
        gridDestinos.add(imgGramado, 2, 0);
        gridDestinos.add(labelGramado, 2, 1);

        // Criação do HBox para a logo e perfil
        HBox hboxTopo = new HBox(550, logoView, new Region(), perfilView);
        hboxTopo.setAlignment(Pos.CENTER); // Alinha no centro verticalmente
        hboxTopo.setPadding(new Insets(20));
        hboxTopo.setStyle("-fx-background-color: #FFFFFF;");

        VBox mainLayout = new VBox(10);
        mainLayout.setPadding(new Insets(20));
        mainLayout.getChildren().addAll(hboxTopo, hboxBusca, listViewSugestoes, titulo, subTitulo, gridDestinos);

        Scene scene = new Scene(mainLayout, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sistema de Reserva de Hotéis");
        primaryStage.show();
    }

    private VBox criarSpinnerComRotulo(String rotulo, Spinner<Integer> spinner) {
        VBox vbox = new VBox();
        Label label = new Label(rotulo);
        label.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        vbox.getChildren().addAll(label, spinner);
        return vbox;
    }

    private void mostrarSugestoes(List<String> sugestoes) {
        listViewSugestoes.getItems().clear();
        listViewSugestoes.getItems().addAll(sugestoes);
        listViewSugestoes.setVisible(true);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
