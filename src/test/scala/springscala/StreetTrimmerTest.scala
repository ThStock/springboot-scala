package springscala

import org.junit.Assert.assertEquals
import org.junit.Test
import springscala.ExampleController.Street

class StreetTrimmerTest {

  @Test
  def testTrim() {
    // GIVEN
    val testee = new StreetTrimmer()
    val street = Street("ab++c _ ", "67")

    // WHEN
    val result = testee.trim(street)

    // THEN
    assertEquals(Street("ab c", "67"), result)
  }
}
