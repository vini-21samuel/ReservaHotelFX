package com.br.hotel.poo.reservarhotel;

import com.br.hotel.poo.reservarhotel.DAO.HospedeDAO;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;
import java.util.Optional;

public class TelaListarHospedes extends Application {

    private TableView<Hospede> table;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Lista de Hóspedes");

        // Criação das colunas da tabela
        TableColumn<Hospede, String> nomeCol = new TableColumn<>("Nome");
        nomeCol.setCellValueFactory(new PropertyValueFactory<>("nome"));

        TableColumn<Hospede, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        // Removendo a coluna de check-in
        // TableColumn<Hospede, String> checkInCol = new TableColumn<>("Check-In");
        // checkInCol.setCellValueFactory(new PropertyValueFactory<>("checkIn"));

        TableColumn<Hospede, Integer> reservasCol = new TableColumn<>("Reservas");
        reservasCol.setCellValueFactory(new PropertyValueFactory<>("reservas"));

        TableColumn<Hospede, String> hotelCol = new TableColumn<>("Hotel");
        hotelCol.setCellValueFactory(new PropertyValueFactory<>("hotel"));

        // Criação das colunas de ação
        TableColumn<Hospede, Void> actionCol = new TableColumn<>("Ações");

        // Adicionando um botão para editar e remover
        actionCol.setCellFactory(col -> new TableCell<Hospede, Void>() {
            private final Button editButton = new Button("Editar");
            private final Button removeButton = new Button("Remover");
            private final HBox hbox = new HBox(editButton, removeButton);

            {
                editButton.setOnAction(event -> {
                    // Ação de editar
                    Hospede hospede = getTableView().getItems().get(getIndex());
                    editarHospede(hospede);
                });

                removeButton.setOnAction(event -> {
                    // Ação de remover
                    Hospede hospede = getTableView().getItems().get(getIndex());
                    removerHospede(hospede);
                });

                setGraphic(hbox);
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(hbox);
                }
            }
        });

        // Criação da tabela
        table = new TableView<>();
        table.getColumns().addAll(nomeCol, emailCol, reservasCol, hotelCol, actionCol);

        // Carregar os hóspedes na tabela
        carregarHospedes();

        // Layout da cena
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.getChildren().add(table);

        // Botões de editar e remover na parte inferior
        Button editarTodosButton = new Button("Editar Selecionados");
        Button removerTodosButton = new Button("Remover Selecionados");

        // Estilização dos botões
        editarTodosButton.setStyle("-fx-background-color: #0F3; -fx-text-fill: white;");
        removerTodosButton.setStyle("-fx-background-color: #0F3; -fx-text-fill: white;");

        editarTodosButton.setOnAction(event -> {
            for (Hospede hospede : table.getSelectionModel().getSelectedItems()) {
                editarHospede(hospede);
            }
        });

        removerTodosButton.setOnAction(event -> {
            for (Hospede hospede : table.getSelectionModel().getSelectedItems()) {
                removerHospede(hospede);
            }
        });

        HBox buttonBox = new HBox(10, editarTodosButton, removerTodosButton);
        vbox.getChildren().addAll(buttonBox);

        Scene scene = new Scene(vbox, 800, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Método para carregar os hóspedes na tabela
    private void carregarHospedes() {
        HospedeDAO hospedeDAO = new HospedeDAO();
        List<Hospede> hospedes = hospedeDAO.buscarTodosHospedes();

        ObservableList<Hospede> observableList = FXCollections.observableArrayList(hospedes);
        table.setItems(observableList);

        System.out.println("Total de hóspedes carregados: " + hospedes.size());
    }

    private void editarHospede(Hospede hospede) {
        // Criação de um diálogo para editar o hóspede
        Dialog<Hospede> dialog = new Dialog<>();
        dialog.setTitle("Editar Hóspede");
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setHeaderText("Editar informações do hóspede");

        // Campos para edição
        TextField nomeField = new TextField(hospede.getNome());
        nomeField.setPromptText("Nome");
        TextField emailField = new TextField(hospede.getEmail());
        emailField.setPromptText("Email");

        // Adiciona os campos ao diálogo
        VBox dialogPane = new VBox(10, nomeField, emailField);
        dialog.getDialogPane().setContent(dialogPane);

        // Botão para confirmar a edição
        ButtonType confirmButton = new ButtonType("Salvar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(confirmButton, ButtonType.CANCEL);

        // Ação do botão Salvar
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == confirmButton) {
                String novoNome = nomeField.getText();
                String novoEmail = emailField.getText();
                hospede.setNome(novoNome);
                hospede.setEmail(novoEmail);
                HospedeDAO hospedeDAO = new HospedeDAO();
                hospedeDAO.atualizarHospede(hospede); // Atualiza no banco de dados
                carregarHospedes(); // Atualiza a tabela
                return hospede;
            }
            return null;
        });

        dialog.showAndWait();
    }

    private void removerHospede(Hospede hospede) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação");
        alert.setHeaderText("Você realmente deseja remover o hóspede: " + hospede.getNome() + "?");
        alert.setContentText("Selecione a opção desejada:");

        // Botões de confirmação
        ButtonType buttonTypeOk = new ButtonType("OK");
        ButtonType buttonTypeCancel = new ButtonType("Cancelar");
        alert.getButtonTypes().setAll(buttonTypeOk, buttonTypeCancel);

        // Mostra o diálogo e espera a resposta
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == buttonTypeOk) {
            HospedeDAO hospedeDAO = new HospedeDAO();
            hospedeDAO.removerHospede(hospede);
            table.getItems().remove(hospede);
            System.out.println("Hóspede removido: " + hospede.getNome());
        }
    }
}
