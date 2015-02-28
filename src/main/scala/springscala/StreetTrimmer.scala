package springscala

import org.springframework.stereotype.Component

@Component
class StreetTrimmer {
  def trim(s: String): String = s.replaceAll("[_+]", " ").trim

}
