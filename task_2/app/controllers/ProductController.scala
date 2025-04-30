package controllers

import play.api.mvc._
import play.api.libs.json._

case class Product(id: Long, name: String, price: Double)
object Product {
  implicit val format: OFormat[Product] = Json.format[Product]
}

@javax.inject.Singleton
class ProductController @javax.inject.Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  private var products = List(
    Product(1, "Młotek", 25.0),
    Product(2, "Śrubokręt", 15.5)
  )

  def list() = Action {
    Ok(Json.toJson(products))
  }

  def show(id: Long) = Action {
    products.find(_.id == id) match {
      case Some(p) => Ok(Json.toJson(p))
      case None    => NotFound(Json.obj("error" -> "Produkt nie znaleziony"))
    }
  }

  def create() = Action(parse.json) { request =>
    request.body.validate[Product].fold(
      errors => BadRequest(Json.obj("error" -> "Niepoprawne dane")),
      prod => {
        products = products :+ prod
        Created(Json.toJson(prod))
      }
    )
  }
}
