module com.br.hotel.poo.reservarhotel {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.br.hotel.poo.reservarhotel to javafx.fxml;
    exports com.br.hotel.poo.reservarhotel;
    exports com.br.hotel.poo.reservarhotel.DAO;
    opens com.br.hotel.poo.reservarhotel.DAO to javafx.fxml;
}