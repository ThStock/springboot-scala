package springscala

import org.springframework.stereotype.Component

@Component
class StreetTrimmer {

  // XXX shows java similarity
  def trim(s: ExampleController.Street): ExampleController.Street = {
    return new ExampleController.Street(s.name.replaceAll("[_+]+", " ").trim(), s.number);
  }
}
