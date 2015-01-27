
import nonemptystring.{EmptyString, FullString, MaybeString}
import org.specs2.mutable.Specification
import play.api.libs.json._
import play.api.libs.json.JsString

class NonEmptyStringSpec extends Specification {

  "MaybeString" should {

    "return FullString or EmptyString" in {
      MaybeString("apple") must_== FullString("apple")
      MaybeString("") must_== EmptyString
    }

    "empty fullString throw exception" in {
      FullString("") must throwA[Exception]
    }

    "concancate with string" in {
      s"${MaybeString("apple")} juice" must_== "apple juice"
      s"${MaybeString("")} orange" must_== " orange"
    }

    "be serialized from string" in {

      Json.fromJson[MaybeString](JsString("apple")).get must_== FullString("apple")
      Json.fromJson[MaybeString](JsString("")).get must_== EmptyString

    }

    "be desrialized into JsString" in {
      Json.toJson(MaybeString("apple")) must_== JsString("apple")
      Json.toJson(MaybeString("")) must_== JsNull
    }

  }

}
