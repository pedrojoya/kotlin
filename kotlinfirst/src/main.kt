// Las constantes NO se pueden definir como locales y deben ser val.
const val LEMA = "Quillo que"

fun main(args: Array<String>) {

    // Con var definimos variables modificables.
    var mensaje: String

    // El tipo es estático, pero se puede inferir del valor inicial.
    var mensaje2 = "hola"
    // Pero que se pueda inferir, NO significa que sea dinámico. No podemos "cambiarle" el tipo.
    // mensaje2 = 5

    // Con val es una variable final (sólo se le puede dar un valor en su vida).
    val x: Int
    // Nos da error porque x no puede ser null y por tanto ha debido ser inicializado
    // x = x + 5
    x = 8
    // Nos da error porque x sólo puede asignarse una única vez.
    // x = 15

    // Se puede usar interpolación de variables en cadenas (para no tener que andar concatenando)
    println("Te digo: $mensaje2")
    println("El lema es ${LEMA.toUpperCase()}")

}