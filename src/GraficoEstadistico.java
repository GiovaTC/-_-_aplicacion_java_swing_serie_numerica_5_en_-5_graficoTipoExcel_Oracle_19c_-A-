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

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (datos.isEmpty()) {
            return;
        }

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLUE);

        int x = 80;
        int base = 400;

        for (Integer valor : datos) {
            int altura = valor * 3 ;
            g2.fillRect(x, base - altura, 30, altura);
            g2.drawString(String.valueOf(valor), x, base + 20);
            x += 40;
        }

        g2.drawString("Grafico Tipo Excel - Serie 5 en 5", 230, 80);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GraficoEstadistico().setVisible(true));
    }
}
/*© 2025 Giovanny Alejandro Tapiero Cataño & chatGpt :. .
Todos los derechos reservados.

Este software y su contenido están protegidos por las leyes internacionales de derechos de autor.
No se permite su reproducción, distribución, modificación ni uso con fines comerciales sin
autorización expresa del titular.
--- :. . */