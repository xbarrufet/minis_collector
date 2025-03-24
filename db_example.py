import sqlite3

# Conectar a la base de datos (o crearla si no existe)
conn = sqlite3.connect('personas.db')
cursor = conn.cursor()

# Crear la tabla de personas
cursor.execute('''
CREATE TABLE IF NOT EXISTS persona (
    id INTEGER PRIMARY KEY,
    nombre TEXT NOT NULL,
    edad INTEGER NOT NULL
)
''')

# Función para insertar una persona en la base de datos
def insertar_persona(nombre: str, edad: int):
    cursor.execute('''
    INSERT INTO persona (nombre, edad) VALUES (?, ?)
    ''', (nombre, edad))
    conn.commit()

# Ejemplo de uso
insertar_persona('Juan Perez', 30)

# Cerrar la conexión
conn.close()
