grammar T;

@header {
import java.util.ArrayList;
import java.util.List;
import org.antlr.runtime.*;
}

@members{   
    List <Tabla> tablas = new ArrayList<Tabla>();  
    Tabla tablaActual = null;
    boolean hayErrores = false;
    
    // Método para detectar y reportar errores semánticos
    public void reportarError(String mensaje) {
        hayErrores = true;
        System.err.println(mensaje);
        // Escribir como comentario en el SQL para que sea visible en la UI
        System.out.println("-- Error: " + mensaje);
    }
    
    // Método para verificar si una tabla existe
    public boolean tablaExiste(String nombreTabla) {
        for (Tabla t : tablas) {
            if (t.nombre.equals(nombreTabla)) {
                return true;
            }
        }
        return false;
    }
    
    // Método para obtener una tabla por nombre
    public Tabla obtenerTabla(String nombreTabla) {
        for (Tabla t : tablas) {
            if (t.nombre.equals(nombreTabla)) {
                return t;
            }
        }
        return null;
    }
}

inicio : creacion usar tabla+ cerrar;

creacion : CREAR ID {
    System.out.println("CREATE DATABASE "+$ID.text+";"); 
}; 

usar : USAR ID {
    System.out.println("\\c "+$ID.text+";"); 
};

tabla : TABLA ID INICIO { 
    // Verificar que el nombre de la tabla no esté duplicado
    if (tablaExiste($ID.text)) {
        reportarError("La tabla '"+$ID.text+"' ya ha sido definida");
    }
    
    // código para generar SQL
    System.out.println("CREATE TABLE "+$ID.text); 
    System.out.println(" ("+$ID.text+"_key SERIAL PRIMARY KEY"); 
    // código para crear estructura de datos
    Tabla t = new Tabla();
    t.nombre = $ID.text;
    tablas.add(t);
    tablaActual = t;
}
campo+ foranea*
FIN {
    System.out.println("  ); "); 
};

campo : ID (t=NUMERICO | t=ALFABETICO | t=FECHA) { 
    // Verificar que el nombre del campo no esté duplicado
    for (Atributo a : tablaActual.atributos) {
        if (a.nombreAtributo.equals($ID.text)) {
            reportarError("El atributo '"+$ID.text+"' ya está definido en la tabla '"+tablaActual.nombre+"'");
            break;
        }
    }
    
    // código para generar SQL
    if(($t.text).compareTo("letras")==0) 
        System.out.println(", "+$ID.text + " VARCHAR(300)");
    else if(($t.text).compareTo("fecha")==0)
        System.out.println(", "+$ID.text + " DATE"); 
    else if(($t.text).compareTo("numeros")==0)
        System.out.println(", "+$ID.text + " INTEGER");
    else  
        System.out.println(", "+$ID.text + " " +$t.text);    

    // código para crear estructura de datos
    Atributo a = new Atributo();
    a.nombreAtributo = $ID.text;
    a.tipoAtributo = $t.text;
    tablaActual.atributos.add(a);
};

foranea : FORANEA ID { 
    boolean tablaEncontrada = false;
    
    // Verificar si la tabla referenciada existe
    if ($ID.text.equals(tablaActual.nombre)) {
        reportarError("No puede ser foranea la misma tabla "+$ID.text);
    } 
    else if (!tablaExiste($ID.text)) {
        reportarError("No existe la tabla '"+$ID.text+"' referenciada como foránea");
    } 
    else {
        // La tabla existe y no es la misma
        System.out.println(", "+$ID.text+"_key INTEGER");
        // Agregar la restricción de clave foránea
        System.out.println(", CONSTRAINT fk_"+tablaActual.nombre+"_"+$ID.text);
        System.out.println("  FOREIGN KEY ("+$ID.text+"_key)");
        System.out.println("  REFERENCES "+$ID.text+" ("+$ID.text+"_key)");
        
        // código para crear estructura de datos
        Atributo a = new Atributo();
        a.nombreAtributo = $ID.text+"_key";
        a.tipoAtributo = "INTEGER";
        tablaActual.atributos.add(a);
    }
};

cerrar : CERRAR {
    // Si hay errores, lanzar una excepción para detener la compilación
    if (hayErrores) {
        throw new RuntimeException("Se encontraron errores semánticos en la definición de las tablas.");
    }
    
    // Generación de información sobre las tablas
    System.out.println("\n-- Resumen de las tablas:");
    for (int i=0; i<tablas.size(); i++){ 
        System.out.println("-- Tabla: "+tablas.get(i).nombre);
        List <Atributo> atribs = tablas.get(i).atributos;
        for (int j=0; j<atribs.size(); j++){  
            System.out.print("--   <Atributo>  "+atribs.get(j).nombreAtributo);
            System.out.println(" \t<TipoAtrib> "+atribs.get(j).tipoAtributo);
        }
    }
};

CERRAR : 'cerrar';
NUMERICO : 'numeros';
ALFABETICO : 'letras';
FECHA : 'fecha';
TABLA : 'tabla';
FORANEA : 'depende_de';
INICIO : 'inicio';
FIN : 'fin';
USAR : 'usar';
CREAR : 'crear';
ID : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;  
WS : (' ' | '\n' | '\t' | '\r')+ {$channel=HIDDEN;};

