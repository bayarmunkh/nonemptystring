
import nonemptystring.NonEmptyString
import org.specs2.mutable.Specification
import play.api.libs.json._
import play.api.libs.json.JsString

class NonEmptyStringSpec extends Specification {

  "NonEmptyString" should {

    "concancate with string" in {
      s"${NonEmptyString("apple")} juice" must_== "apple juice"
    }

    "be serialized from string" in {

      Json.fromJson[NonEmptyString](JsString("apple")).get.value must_== Some("apple")
      Json.fromJson[NonEmptyString](JsString("")).get.value must_== None

    }

  }

}
