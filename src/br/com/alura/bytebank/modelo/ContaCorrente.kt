package br.com.alura.bytebank.modelo

class ContaCorrente(
    titular: Cliente,
    numero: Int
) : br.com.alura.bytebank.modelo.Conta(
    titular = titular,
    numero = numero
) {
    override fun saca(valor: Double) {
        val valorComTaxa = valor + 0.1
        if(this.saldo >= valorComTaxa){
            this.saldo -= valorComTaxa
        }
    }
}