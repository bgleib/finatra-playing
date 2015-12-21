import com.google.inject.Inject
import com.ning.http.client.Response
import com.twitter.finatra.json.FinatraObjectMapper
import dispatch.{Http, url}
import com.twitter.util.Future
import com.twitter.bijection.twitter_util.UtilBijections.twitter2ScalaFuture

class NBAService(@Inject mapper: FinatraObjectMapper) {
  private[this] val futureConverter = twitter2ScalaFuture[Response].inverse

  def get(): Future[Int] = {
    val request = url("http://stats.nba.com/stats/commonallplayers")
      .addParameter("LeagueId", "00")
      .addParameter("Season", "2015-16")
      .addParameter("IsOnlyCurrentSeason", "1")
    futureConverter(Http(request)).map { response =>
      response.getResponseBody
    }

    Future.value(10)
  }
}
