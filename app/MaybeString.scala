package nonemptystring

import play.api.libs.json._

object MaybeString {

  def apply(str: String): MaybeString = {
    if(str.nonEmpty)
      new FullString(str)
    else
      EmptyString
  }

  implicit val reads: Reads[MaybeString] = {
    (__).read[String].map(MaybeString(_))
  }

  implicit val writes: Writes[MaybeString] = new Writes[MaybeString] {
    override def writes(o: MaybeString): JsValue = o match {
      case FullString(str) => JsString(str)
      case EmptyString => JsNull
    }
  }

}

case class FullString(value: String) extends MaybeString {
  require(value.nonEmpty)

  def isEmpty = false
  def nonEmpty = true
  override def toString = value
}
case object EmptyString extends MaybeString {
  def isEmpty = true
  def nonEmpty = false
  override def toString = ""
}

sealed trait MaybeString {
  def isEmpty: Boolean
  def nonEmpty: Boolean
}
