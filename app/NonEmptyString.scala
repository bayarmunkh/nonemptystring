package nonemptystring

import play.api.libs.json._

object NonEmptyString {

  def apply(str: String) = {
    if(str.nonEmpty)
      new NonEmptyString  {
        val value = Some(str)
      }
    else
      new NonEmptyString {
        val value = None
      }
  }

  implicit val reads: Reads[NonEmptyString] = {
    (__).read[String].map(NonEmptyString(_))
  }

  implicit val writes: Writes[NonEmptyString] = new Writes[NonEmptyString] {
    override def writes(o: NonEmptyString): JsValue = o.value match {
      case Some(str) => JsString(str)
      case _         => JsNull
    }
  }

}

sealed trait NonEmptyString {
  // container
  val value: Option[String]

  override def toString = value.getOrElse("")
}
