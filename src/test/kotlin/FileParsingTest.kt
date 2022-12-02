import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class FileParsingTest {

    @Test
    fun `split file on blank lines`() {
        val lines = listOf("A", "B", "", "C", "", "D", "E", "F")
        splitLinesOnBlank(lines) shouldBe listOf(
            listOf("A", "B"),
            listOf("C"),
            listOf("D", "E", "F")
        )
    }

    @Test
    fun `split list on null`() {
        val lines = listOf(124, 235, null, 3456, 567, null, 5)
        splitListOnNull(lines) shouldBe listOf(
            listOf(124, 235),
            listOf(3456, 567),
            listOf(5)
        )
    }
}