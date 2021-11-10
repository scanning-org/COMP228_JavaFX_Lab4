module exercise1.brunomorgado_comp228lab4 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens exercise1.brunomorgado_comp228lab4 to javafx.fxml;
    exports exercise1.brunomorgado_comp228lab4;
}