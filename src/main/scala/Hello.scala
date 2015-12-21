import com.twitter.finatra.http.routing.HttpRouter
import com.twitter.finatra.http.HttpServer

object HomeServerMain extends HomeServer

class HomeServer extends HttpServer {
  override val modules = Nil

  override def configureHttp(router: HttpRouter) = { }
}
