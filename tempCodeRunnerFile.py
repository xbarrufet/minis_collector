def contar_a_en_don_quijote(url_libro: str) -> int:
    """Cuenta las letras 'a' en el libro 'Don Quijote' desde una URL."""
    response = requests.get(url_libro)
    texto = response.text
    return texto.count('a')

# Ejemplo de uso
url_libro = 'https://www.gutenberg.org/files/2000/2000-0.txt'
numero_de_as = contar_a_en_don_quijote(url_libro)
print(f"El número de letras 'a' en el libro 'Don Quijote' es: {numero_de_as}")
