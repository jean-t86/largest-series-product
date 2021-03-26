class Series(private val input: String) {

    init {
        if (!input.all { char -> char.toInt() in 48..57 }) {
            throw IllegalArgumentException()
        }
    }

    fun getLargestProduct(span: Int): Long {
        if ((input.isEmpty() && span != 0) ||
            input.length < span ||
            span < 0
        ) {
            throw IllegalArgumentException()
        }

        if (input.isEmpty() && span == 0) return 1L

        val numbers = input
            .chunked(1)
            .map { it.toInt() }

        if (numbers.size - span <= 2 && numbers[numbers.size / 2] == 0) return 0L

        var lsp = 0L
        for (i in numbers.indices) {
            var currentProduct = 1L
            for (j in i until span + i) {
                if (j > numbers.lastIndex) {
                    break
                }
                currentProduct *= numbers[j]
            }
            if (currentProduct > lsp) lsp = currentProduct
        }

        return lsp
    }
}
