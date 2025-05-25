import javax.swing.filechooser.FileSystemView;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.antlr.runtime.*;
import java.io.*;
import java.sql.*;

public class IDEGeneradorBD extends JFrame {

    private JTextArea areaEntrada;
    private JTextArea areaSQL;
    private JTextArea areaErrores;
    private JButton btnCompilar;
    private JButton btnEjecutar;
    private JButton btnGenerarPython;
    private TParser parser;


    private String dbUrl = "jdbc:postgresql://localhost:5432/";
    private String dbUsuario = "postgres";
    private String dbPassword = "";
    private String dbName = "mi_base_de_datos";


    public IDEGeneradorBD() {
        try {
          Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
          JOptionPane.showMessageDialog(this, 
          "No se pudo cargar el driver de PostgreSQL. Verifica que el JAR esté en el classpath.",
          "Error de Driver", JOptionPane.ERROR_MESSAGE);
        }

        setTitle("Generador de BD - Lenguaje de Alto Nivel");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel principal
        JPanel panelCentro = new JPanel(new BorderLayout());    

        // Área de entrada
        areaEntrada = new JTextArea();
        JScrollPane scrollEntrada = new JScrollPane(areaEntrada);
        scrollEntrada.setBorder(BorderFactory.createTitledBorder("Entrada (Lenguaje de alto nivel)"));

        // Área de SQL generado
        areaSQL = new JTextArea();
        areaSQL.setEditable(false);
        JScrollPane scrollSQL = new JScrollPane(areaSQL);
        scrollSQL.setBorder(BorderFactory.createTitledBorder("SQL generado"));

        // Panel de los dos cuadros
        JPanel panelAreas = new JPanel(new GridLayout(1, 2, 10, 10));
        panelAreas.add(scrollEntrada);
        panelAreas.add(scrollSQL);

        // Panel de botones
        btnCompilar = new JButton("Compilar");
        btnEjecutar = new JButton("Ejecutar");
        btnEjecutar.setEnabled(false);
        btnGenerarPython = new JButton("Generar código Python");

        // Acción del botón Generar código Python
        btnGenerarPython.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                fileChooser.setDialogTitle("Guardar archivo Python");
                fileChooser.setSelectedFile(new java.io.File("crud_generado.py"));
                int userSelection = fileChooser.showSaveDialog(null);
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    try (FileWriter writer = new FileWriter(fileChooser.getSelectedFile())) {

                        writer.write("import psycopg2\n");
                        writer.write("from tkinter import *\n\n");
                        writer.write("conn = psycopg2.connect(\n");
                        writer.write("    host='localhost',\n");
                        writer.write("    database='" + dbName + "',\n");
                        writer.write("    user='postgres',\n");
                        writer.write("    password='" + dbPassword + "'\n");
                        writer.write(")\n");
                        writer.write("cursor = conn.cursor()\n\n");

                        writer.write("root = Tk()\n");
                        writer.write("root.title('CRUD generado')\n\n");

                        int row = 0;
                        for (Tabla tabla : parser.getTablas()) {
                            String nombreTabla = tabla.getNombre();
                            writer.write("# Tabla: " + nombreTabla + "\n");

                            StringBuilder paramList = new StringBuilder();
                            StringBuilder fieldList = new StringBuilder();
                            StringBuilder placeholders = new StringBuilder();

                            writer.write("Label(root, text='ID').grid(row=" + row + ", column=0)\n");
                            writer.write("entry_id = Entry(root)\n");
                            writer.write("entry_id.grid(row=" + row + ", column=1)\n");
                            row++;

                            for (Atributo attr : tabla.getAtributos()) {
                                String nombreAttr = attr.getNombre();
                                String tipoAttr = attr.getTipo();
                                boolean esRef = tipoAttr.startsWith("ref(");
                                String campoBD = esRef ? nombreAttr + "_id" : nombreAttr;

                                writer.write("Label(root, text=\"" + nombreAttr + "\").grid(row=" + row + ", column=0)\n");
                                writer.write("entry_" + nombreAttr + " = Entry(root)\n");
                                writer.write("entry_" + nombreAttr + ".grid(row=" + row + ", column=1)\n");

                                paramList.append(nombreAttr).append(", ");
                                fieldList.append(campoBD).append(", ");
                                placeholders.append("%s, ");
                                row++;
                            }

                            String paramStr = paramList.toString().replaceAll(", $", "");
                            String fieldStr = fieldList.toString().replaceAll(", $", "");
                            String placeStr = placeholders.toString().replaceAll(", $", "");

                            // INSERT
                            writer.write("\ndef insertar():\n");
                            for (Atributo attr : tabla.getAtributos()) {
                                String nombreAttr = attr.getNombre();
                                writer.write("    " + nombreAttr + " = entry_" + nombreAttr + ".get()\n");
                            }
                            writer.write("    cursor.execute(\"INSERT INTO " + nombreTabla + " (" + fieldStr + ") VALUES (" + placeStr + ")\", (" + paramStr + ",))\n");
                            writer.write("    conn.commit()\n\n");

                            // UPDATE
                            writer.write("def actualizar():\n");
                            writer.write("    id_val = entry_id.get()\n");
                            for (Atributo attr : tabla.getAtributos()) {
                                writer.write("    " + attr.getNombre() + " = entry_" + attr.getNombre() + ".get()\n");
                            }
                            StringBuilder setFields = new StringBuilder();
                            for (Atributo attr : tabla.getAtributos()) {
                                String campoBD = attr.getTipo().startsWith("ref(") ? attr.getNombre() + "_id" : attr.getNombre();
                                setFields.append(campoBD).append(" = %s, ");
                            }
                            String setStr = setFields.toString().replaceAll(", $", "");
                            writer.write("    cursor.execute(\"UPDATE " + nombreTabla + " SET " + setStr + " WHERE id = %s\", (" + paramStr + ", id_val))\n");
                            writer.write("    conn.commit()\n\n");

                            // DELETE
                            writer.write("def eliminar():\n");
                            writer.write("    id_val = entry_id.get()\n");
                            writer.write("    cursor.execute(\"DELETE FROM " + nombreTabla + " WHERE id = %s\", (id_val,))\n");
                            writer.write("    conn.commit()\n\n");

                            // Botones
                            writer.write("Button(root, text='Agregar', command=insertar).grid(row=" + row + ", column=0)\n");
                            writer.write("Button(root, text='Actualizar', command=actualizar).grid(row=" + row + ", column=1)\n");
                            row++;
                            writer.write("Button(root, text='Eliminar', command=eliminar).grid(row=" + row + ", column=0, columnspan=2)\n\n");
                            row += 2;
                        }

                        writer.write("root.mainloop()\n");

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
        panelBotones.add(Box.createVerticalGlue());
        panelBotones.add(btnCompilar);
        panelBotones.add(Box.createRigidArea(new Dimension(0, 10)));
        panelBotones.add(btnEjecutar);
        panelBotones.add(Box.createRigidArea(new Dimension(0, 10)));
        panelBotones.add(btnGenerarPython);
        panelBotones.add(Box.createVerticalGlue());

        // Añadir botones al centro entre las áreas
        JPanel panelCentroConBotones = new JPanel(new BorderLayout());
        panelCentroConBotones.add(panelAreas, BorderLayout.CENTER);
        panelCentroConBotones.add(panelBotones, BorderLayout.WEST);

        // Área de errores
        areaErrores = new JTextArea(4, 80);
        areaErrores.setEditable(false);
        JScrollPane scrollErrores = new JScrollPane(areaErrores);
        scrollErrores.setBorder(BorderFactory.createTitledBorder("Errores"));

        // Eventos
        btnCompilar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                compilar();
            }
        });

        btnEjecutar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ejecutar();
            }
        });


        // Agregar al frame
        add(panelCentroConBotones, BorderLayout.CENTER);
        add(scrollErrores, BorderLayout.SOUTH);
        agregarMenuConexion();
        
        // Cargar ejemplo por defecto
        cargarEjemplo();
    }

  private void compilar() {
    String entrada = areaEntrada.getText();
    areaErrores.setText("");
    areaSQL.setText("");

    if (entrada.isEmpty()) {
        areaErrores.setText("Error: No se ha escrito ninguna entrada.");
        return;
    }

    PrintStream originalErr = System.err;
    ByteArrayOutputStream errBuffer = new ByteArrayOutputStream();
    System.setErr(new PrintStream(errBuffer));

    boolean hayErrores = false;
    String resultadoSQL = "";

    try {
        ANTLRInputStream input = new ANTLRInputStream(new ByteArrayInputStream(entrada.getBytes("UTF-8")));
        TLexer lexer = new TLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        parser = new TParser(tokens);

        ByteArrayOutputStream bufferSalida = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(bufferSalida));

        parser.inicio();

        System.setOut(originalOut);
        resultadoSQL = bufferSalida.toString();
        areaSQL.setText(resultadoSQL);

    } catch (Exception e) {
        hayErrores = true;
        if (e instanceof RecognitionException) {
            RecognitionException re = (RecognitionException) e;
            areaErrores.setText("Error de sintaxis en línea " + re.line + ", columna " + re.charPositionInLine + "\n");
        } else if (e instanceof RuntimeException && e.getMessage() != null &&
                   e.getMessage().contains("errores semánticos")) {
            areaErrores.setText(e.getMessage() + "\n");

            String errores = errBuffer.toString();
            if (!errores.isEmpty()) {
                String[] lineas = errores.split("\n");
                for (String linea : lineas) {
                    if (!linea.contains("at ") && !linea.contains("Exception")) {
                        areaErrores.append(linea.trim() + "\n");
                    }
                }
            }
        } else {
            areaErrores.setText("Error: " + e.getMessage() + "\n");
        }

    } finally {
        System.setErr(originalErr);

        // Si no se detectaron errores, mostrar resumen y activar botón
        if (!hayErrores) {
            StringBuilder resumen = new StringBuilder("== RESUMEN DE TABLAS ==\n");
            for (Tabla tabla : parser.getTablas()) {
                resumen.append("Tabla: ").append(tabla.getNombre()).append("\n");
                for (Atributo atrib : tabla.getAtributos()) {
                    resumen.append("<Atributo>  ").append(atrib.getNombre())
                           .append(" \t<TipoAtrib> ").append(atrib.getTipo()).append("\n");
                }
            }
            areaErrores.setText(resumen.toString());
            btnEjecutar.setEnabled(true);
        }
    }
}

    private void ejecutar() {
    String sql = areaSQL.getText();

    try {
        String[] lineas = sql.split("\n");
        String nombreBD = null;
        for (String linea : lineas) {
            if (linea.toLowerCase().startsWith("create database")) {
                String[] partes = linea.split(" ");
                if (partes.length >= 3) {
                    nombreBD = partes[2].trim().replace(";", "");
                    nombreBD = nombreBD.replaceAll("[^a-zA-Z0-9_]", "_"); // Sanitizar
                }
                break;
            }
        }

        if (nombreBD == null) {
            areaErrores.setText("No se encontró el nombre de la base de datos en el SQL.");
            return;
        }

        dbName = nombreBD; // ✅ GUARDAR NOMBRE PARA USO EN PYTHON

        int confirmacion = JOptionPane.showConfirmDialog(this,
                "Se creará la base de datos '" + nombreBD + "'. ¿Desea continuar?",
                "Confirmar creación de BD", JOptionPane.YES_NO_OPTION);

        if (confirmacion != JOptionPane.YES_OPTION) {
            return;
        }

        String password = dbPassword;
        if (password.isEmpty()) {
            JPasswordField passwordField = new JPasswordField();
            int option = JOptionPane.showConfirmDialog(this, passwordField,
                    "Ingrese la contraseña de PostgreSQL", JOptionPane.OK_CANCEL_OPTION);

            if (option == JOptionPane.OK_OPTION) {
                password = new String(passwordField.getPassword());
                dbPassword = password; // ✅ GUARDAR CONTRASEÑA PARA USO EN PYTHON
            } else {
                return;
            }
        }

        areaErrores.setText("Conectando a PostgreSQL...\n");
        Connection conn = DriverManager.getConnection(dbUrl + "postgres", dbUsuario, password);
        Statement stmt = conn.createStatement();

        try {
            stmt.execute("SELECT pg_terminate_backend(pid) FROM pg_stat_activity WHERE datname='" + nombreBD + "'");
            stmt.executeUpdate("DROP DATABASE IF EXISTS " + nombreBD);
            stmt.executeUpdate("CREATE DATABASE " + nombreBD);
        } catch (SQLException e) {
            areaErrores.setText("Error al crear la base de datos: " + e.getMessage() + "\n");
            stmt.close();
            conn.close();
            return;
        }

        stmt.close();
        conn.close();

        areaErrores.setText("Base de datos '" + nombreBD + "' creada. Ejecutando sentencias SQL...\n");

        conn = DriverManager.getConnection(dbUrl + nombreBD, dbUsuario, password);
        conn.setAutoCommit(false);
        stmt = conn.createStatement();

        StringBuilder currentStatement = new StringBuilder();
        for (String linea : lineas) {
            linea = linea.trim();

            if (linea.isEmpty() || linea.startsWith("--") || linea.startsWith("\\")) {
                continue;
            }

            if (linea.toLowerCase().startsWith("create database")) {
                continue;
            }

            currentStatement.append(linea);

            if (linea.endsWith(";")) {
                String sentencia = currentStatement.toString();
                try {
                    stmt.execute(sentencia);
                    areaErrores.append("Sentencia ejecutada: " +
                            sentencia.substring(0, Math.min(50, sentencia.length())) +
                            (sentencia.length() > 50 ? "...\n" : "\n"));
                } catch (SQLException e) {
                    areaErrores.append("Error en sentencia: " + sentencia + "\n" + e.getMessage() + "\n");
                }
                currentStatement = new StringBuilder();
            } else {
                currentStatement.append(" ");
            }
        }

        if (currentStatement.length() > 0) {
            String sentencia = currentStatement.toString().trim();
            if (!sentencia.isEmpty()) {
                try {
                    stmt.execute(sentencia);
                    areaErrores.append("Sentencia ejecutada: " +
                            sentencia.substring(0, Math.min(50, sentencia.length())) +
                            (sentencia.length() > 50 ? "...\n" : "\n"));
                } catch (SQLException e) {
                    areaErrores.append("Error en sentencia: " + sentencia + "\n" + e.getMessage() + "\n");
                }
            }
        }

        conn.commit();
        stmt.close();
        conn.close();

        JOptionPane.showMessageDialog(this, "¡Base de datos creada con éxito en PostgreSQL!");

    } catch (SQLException e) {
        areaErrores.setText("Error SQL:\n" + e.getMessage() + "\n");
        e.printStackTrace();

        JTextArea textArea = new JTextArea(10, 50);
        textArea.setText(e.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        JOptionPane.showMessageDialog(this, scrollPane, "Error Detallado", JOptionPane.ERROR_MESSAGE);
    }
}


    private void agregarMenuConexion() {
    JMenuBar menuBar = new JMenuBar();
    
    // Menú Archivo
    JMenu menuArchivo = new JMenu("Archivo");
    JMenuItem itemAbrir = new JMenuItem("Abrir...");
    itemAbrir.addActionListener(e -> abrirArchivo());
    menuArchivo.add(itemAbrir);
    
    // Menú Conexión
    JMenu menuConexion = new JMenu("Conexión");
    JMenuItem itemConfigurar = new JMenuItem("Configurar PostgreSQL");
    itemConfigurar.addActionListener(e -> configurarConexion());
    
    JMenuItem itemProbar = new JMenuItem("Probar conexión");
    itemProbar.addActionListener(e -> probarConexion());
    
    menuConexion.add(itemConfigurar);
    menuConexion.add(itemProbar);
    
    // Menú Ejemplos
    JMenu menuEjemplos = new JMenu("Ejemplos");
    JMenuItem itemCargarEjemplo = new JMenuItem("Cargar ejemplo básico");
    itemCargarEjemplo.addActionListener(e -> cargarEjemplo());
    
    menuEjemplos.add(itemCargarEjemplo);
    
    menuBar.add(menuArchivo);
    menuBar.add(menuConexion);
    menuBar.add(menuEjemplos);
    
    setJMenuBar(menuBar);
}

    private void configurarConexion() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        
        JTextField txtHost = new JTextField("localhost");
        JTextField txtPuerto = new JTextField("5432");
        JTextField txtUsuario = new JTextField("postgres");
        
        panel.add(new JLabel("Host:"));
        panel.add(txtHost);
        panel.add(new JLabel("Puerto:"));
        panel.add(txtPuerto);
        panel.add(new JLabel("Usuario:"));
        panel.add(txtUsuario);
        
        int result = JOptionPane.showConfirmDialog(this, panel, "Configurar conexión", 
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        if (result == JOptionPane.OK_OPTION) {
            // Guardar la configuración
            dbUrl = "jdbc:postgresql://" + txtHost.getText() + ":" + txtPuerto.getText() + "/";
            dbUsuario = txtUsuario.getText();
        }
    }

    private void cargarEjemplo() {
        String ejemplo = "crear mi_base_de_datos\n" +
                        "usar mi_base_de_datos\n" +
                        "tabla Estudiante inicio\n" +
                        "  nombre letras\n" +
                        "  edad numeros\n" +
                        "  fecha_nacimiento fecha\n" +
                        "fin\n" +
                        "tabla Curso inicio\n" +
                        "  nombre_curso letras\n" +
                        "  creditos numeros\n" +
                        "  depende_de Estudiante\n" +
                        "fin\n" +
                        "cerrar";
        
        areaEntrada.setText(ejemplo);
    }

    private void probarConexion() {
        JPasswordField passwordField = new JPasswordField();
        int option = JOptionPane.showConfirmDialog(this, passwordField, 
                "Ingrese la contraseña de PostgreSQL", JOptionPane.OK_CANCEL_OPTION);
        
        String password = "";
        if (option == JOptionPane.OK_OPTION) {
            password = new String(passwordField.getPassword());
        } else {
            return;
        }
        
        try {
            // Intentar conectarse a la base de datos postgres (que siempre existe)
            Connection conn = DriverManager.getConnection(dbUrl + "postgres", dbUsuario, password);
            conn.close();
            JOptionPane.showMessageDialog(this, "¡Conexión exitosa a PostgreSQL!", 
                    "Prueba de conexión", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al conectar: " + e.getMessage(), 
                    "Error de conexión", JOptionPane.ERROR_MESSAGE);
        }
    }

private void abrirArchivo() {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Seleccionar archivo de código");
    fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
        @Override
        public boolean accept(File f) {
            return f.isDirectory() || f.getName().toLowerCase().endsWith(".txt");
        }

        @Override
        public String getDescription() {
            return "Archivos de texto (*.txt)";
        }
    });

    int resultado = fileChooser.showOpenDialog(this);
    if (resultado == JFileChooser.APPROVE_OPTION) {
        File archivoSeleccionado = fileChooser.getSelectedFile();
        try {
            StringBuilder contenido = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader(archivoSeleccionado))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    contenido.append(linea).append("\n");
                }
            }
            
            areaEntrada.setText(contenido.toString());
            areaErrores.setText("Archivo cargado: " + archivoSeleccionado.getAbsolutePath());
            
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                    "Error al leer el archivo: " + ex.getMessage(),
                    "Error de lectura", JOptionPane.ERROR_MESSAGE);
        }
    }
}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new IDEGeneradorBD().setVisible(true);
        });
    }
}