import java.util.*
import kotlin.collections.ArrayList

val listaDeContas: MutableList<Conta> = ArrayList()

fun main() {
    Engine().run()
}

fun sacarConta(console: Console) {

    print("Digite o número da conta: ")
    val numeroConta = console.nextLine().toInt()
    print("Digite o valor a ser sacado: ")
    val saldo = console.nextLine().toDouble()

    val contaEncontrada = getContaByNumero(numeroConta)
    contaEncontrada?.sacar(saldo)

    println(contaEncontrada)
}

fun getContaByNumero(numeroConta: Int): Conta? {
    return listaDeContas.find { conta -> conta.numero == numeroConta }
}

private fun criarConta(console: Console): Conta {
    var conta = Conta()
    print("Digite o titulo: ")
    conta.titular = console.nextLine()
    print("Digite o numero: ")
    conta.numero = console.nextLine().toInt()
    print("Digite o saldo: ")
    conta.saldo = console.nextLine().toDouble()
    println("")
    print(conta)

    listaDeContas.add(conta)

    return conta
}

enum class Opcao(valor: Int) {
    CRIAR_CONTA(1),
    SACAR(2),
    DEPOSITAR(3),
    TRANSFERIR(4)
}

private fun apresentarOpcoes(console: Console): String {
    println("""
        Selecione uma das seguintes opções:
        Criar conta - 1
        Sacar - 2
        Depositar - 3 
        Transferir - 4
    """.trimIndent())

    return console.nextLine()
}

class Console {
    private val scanner = Scanner(System.`in`)

    fun nextLine(): String {
        return scanner.nextLine()
    }
}

class Conta(var titular: String = "", var numero: Int = 0, var saldo: Double = 0.0) {

    var teste: String = "Teste"
        private set

    override fun toString(): String {
        return "Conta(titular='$titular', numero=$numero, saldo=$saldo)"
    }

    fun sacar(valorSaque: Double) {
        if (this.saldo > 0 && this.saldo >= valorSaque) {
            this.saldo -= valorSaque
        }
    }

    fun depositar(valorDeposito: Double) {
        if (valorDeposito > 0) {
            this.saldo += valorDeposito
        }
    }

    fun transferir(contaDestinoEncontrada: Conta, valorTransferencia: Double) {
        this.sacar(valorTransferencia)
        contaDestinoEncontrada.depositar(valorTransferencia)
    }
}

class Engine {
    private val console = Console()
    private var running = true

    fun run() {
        cabecalho()

        while (running) {
            when (apresentarOpcoes(console)) {
                "1" -> criarConta(console)
                "2" -> sacarConta(console)
                "3" -> depositar(console)
                "4" -> transferir(console)
                else -> this.stop()
            }
        }
    }

    private fun stop() {
        println("Stopping...")
        this.running = false
    }
}

private fun depositar(console: Console) {
    print("Digite o número da conta: ")
    val numeroConta = console.nextLine().toInt()
    print("Digite o valor a ser depositado: ")
    val saldo = console.nextLine().toDouble()

    val contaEncontrada = getContaByNumero(numeroConta)
    contaEncontrada?.depositar(saldo)

    println(contaEncontrada)
}

private fun transferir(console: Console) {
    print("Digite o número da conta origem: ")
    val numeroContaOrigem = console.nextLine().toInt()
    print("Digite o número da conta destino: ")
    val numeroContaDestino = console.nextLine().toInt()

    print("Digite o valor a ser transferido: ")
    val valorTransferencia = console.nextLine().toDouble()

    val contaOrigemEncontrada = getContaByNumero(numeroContaOrigem)
    val contaDestinoEncontrada = getContaByNumero(numeroContaDestino)

    if (contaDestinoEncontrada != null && contaOrigemEncontrada != null) {
        contaOrigemEncontrada.transferir(contaDestinoEncontrada, valorTransferencia)

        println(contaOrigemEncontrada)
        println(contaDestinoEncontrada)
    }
}


private fun cabecalho() {
    println("#############################################################");
    println("################# bem vindo ao Bytebank #####################");
    println("#############################################################");
}