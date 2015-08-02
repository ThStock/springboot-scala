package springscala;

import org.springframework.stereotype.Component;

@Component
public class StreetTrimmerJava {

  public ExampleController.Street trim(ExampleController.Street s) {
    return new ExampleController.Street(s.getName().replaceAll("[_+]+", " ").trim(), s.getNumber());
  }
}
