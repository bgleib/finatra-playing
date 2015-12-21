import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
import com.twitter.finatra.response.Mustache

class PlayingController extends Controller {
  private[this] val nbaService = new NBAService()

  @Mustache("home")
  case class HomeView(name: String, points: String)

  get("/") { request: Request =>
    nbaService.get().map { points =>
      HomeView("Steph Curry", points.toString)
    }
  }
}
