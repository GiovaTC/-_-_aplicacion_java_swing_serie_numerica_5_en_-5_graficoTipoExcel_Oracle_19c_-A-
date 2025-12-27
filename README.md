# -_-_aplicacion_java_swing_serie_numerica_5_en_-5_graficoTipoExcel_Oracle_19c_-A- :.
# 📊 Aplicación Java Swing – Serie Numérica 5 en 5 + Gráfico Tipo Excel + Oracle 19c .

<img width="350" height="120" alt="image" src="https://github.com/user-attachments/assets/8a9e53b4-6c70-4088-885b-74ddc71f19b9" />    

<img width="2554" height="1079" alt="image" src="https://github.com/user-attachments/assets/b350fd17-1a56-41b3-8e8a-92f697b20eda" />    

**Solución completa, profesional y lista para ejecutar en IntelliJ IDEA**

---

## ✔️ Descripción General :

Aplicación Java con interfaz gráfica (Swing) que :

- Genera una serie numérica de **5 en 5** → `0, 5, 10, 15…`
- Dibuja un **gráfico estadístico tipo Excel (barras)**.
- Registra los valores en **Oracle Database 19c** mediante **JDBC**.
- La operación se ejecuta mediante **botón**.

---

## ✔️ Requisitos :

- IntelliJ IDEA  
- JDK 8 o superior  
- Oracle Database 19c  
- Driver JDBC `ojdbc8.jar` (o dependencia Maven)

---

## ✔️ Script Oracle 19c :

Ejecutar en SQL Developer o en consola Oracle :

```sql
CREATE TABLE SERIE_ESTADISTICA (
    ID NUMBER GENERATED ALWAYS AS IDENTITY,
    VALOR NUMBER NOT NULL,
    FECHA_REGISTRO DATE DEFAULT SYSDATE
);
```

## ✔️ Dependencia Maven (opcional) :
```
<dependency>
    <groupId>com.oracle.database.jdbc</groupId>
    <artifactId>ojdbc8</artifactId>
    <version>21.5.0.0</version>
</dependency>
```

Si no usa Maven, agregue manualmente ojdbc8.jar al proyecto.

## ✔️ Código Completo Java (Swing + Oracle) :

Cree un proyecto Java estándar en IntelliJ IDEA y agregue esta clase :
```
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

        JButton generarBtn = new JButton("Generar Serie y Registrar en Oracle");
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
        String user = "YOUR_USER";      
        String password = "YOUR_PASSWORD";

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

        if (datos.isEmpty())
            return;

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLUE);

        int x = 80;
        int base = 400;

        for (Integer valor : datos) {
            int altura = valor * 3;
            g2.fillRect(x, base - altura, 30, altura);
            g2.drawString(String.valueOf(valor), x, base + 20);
            x += 40;
        }

        g2.drawString("Gráfico Tipo Excel - Serie 5 en 5", 230, 80);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GraficoEstadistico().setVisible(true));
    }
}
```

## ✔️ Configuración en IntelliJ IDEA :

Crear proyecto Java .

Agregar la clase anterior .

Configurar credenciales Oracle :

user = "usuario_oracle";
password = "clave_oracle";

Asegurar que Oracle esté levantado .

Ejecutar main .

## ✔️ Resultado Esperado:

Ventana gráfica Swing .

Botón para generar serie .

Se dibuja gráfico tipo Excel .

Se insertan automáticamente los datos en Oracle .

```
© 2025 Giovanny Alejandro Tapiero Cataño & chatGpt :. . 
Todos los derechos reservados.

Este software y su contenido están protegidos por las leyes internacionales de derechos de autor. 
No se permite su reproducción, distribución, modificación ni uso con fines comerciales sin 
autorización expresa del titular.
```
--- :. .
