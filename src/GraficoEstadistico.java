import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class GraficoEstadistico extends JFrame {

    private List<Integer> datos = new ArrayList<>();

    public GraficoEstadistico() {
        setTitle("Gráfico Estadístico - Serie de 5 en 5");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton generarBtn = new JButton("Generar serie y registrar en oracle");
        generarBtn.addActionListener(e -> {
            generarSerie();
            insertarEnOracle();
            repaint();
            JOptionPane.showMessageDialog(this, "Datos registrados en Oracle y gráfico actualizado");
        });

        add(generarBtn, BorderLayout.SOUTH);
    }

    private void generarSerie() {
        datos.clear();
        for (int i = 0; i <= 100; i += 5) {
            datos.add(i);
        }
    }

    private void insertarEnOracle() {
        String url = "jdbc:oracle:thin:@//localhost:1521/orcl";
        String user = "system";
        String password = "Tapiero123";

        String sql = "INSERT INTO SERIE_ESTADISTICA (VALOR) VALUES (?)";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            PreparedStatement ps = conn.prepareStatement(sql);

            for (Integer valor : datos) {
                ps.setInt(1, valor);
                ps.executeUpdate();
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error Oracle: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            System.out.println("i = " + i);
        }
    }
}