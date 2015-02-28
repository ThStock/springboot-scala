package springscala

import org.springframework.web.servlet.ModelAndView

import scala.collection.JavaConverters._

trait ScalaShortcuts {

  def view(name: String, model: Map[String, AnyRef]): ModelAndView = {

    def convertToJavaValues(entry: (String, AnyRef)): (String, AnyRef) = {
      entry._2 match {
        case in: Seq[AnyRef] => (entry._1, in.asJava)
        case _ => entry
      }
    }

    new ModelAndView(name, model.map(convertToJavaValues).asJava)
  }

}
