import com.twitter.finatra.http.routing.HttpRouter
import com.twitter.finatra.http.HttpServer

object PlayingServerMain extends PlayingServer

class PlayingServer extends HttpServer {
  override val modules = Nil

  override def configureHttp(router: HttpRouter) = {
    router.add[PlayingController]
  }
}
