def count_a(text):
    """
    Cuenta el número de veces que aparece la letra 'a' en el texto dado.

    :param text: El texto en el que contar las 'a'.
    :return: El número de veces que aparece la letra 'a'.
    """
    return text.count('a')

# Ejemplo de uso
if __name__ == "__main__":
    sample_text = "Esta es una cadena de ejemplo para contar las letras a."
    print(f"El número de 'a' en el texto es: {count_a(sample_text)}")
