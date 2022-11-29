module fr.gsb.gsbrvdr {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens fr.gsb.gsbrvdr to javafx.fxml;
    exports fr.gsb.gsbrvdr;
}