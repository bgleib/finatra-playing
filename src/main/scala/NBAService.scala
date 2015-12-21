import java.util.concurrent.TimeUnit

import com.google.inject.Inject
import com.twitter.finatra.json.FinatraObjectMapper
import com.twitter.util.Future
import dispatch.Defaults._
import dispatch.{Http, url}

import scala.concurrent.Await
import scala.concurrent.duration.Duration

class NBAService @Inject()(mapper: FinatraObjectMapper) {
  def get(): Future[Int] = {
    val request = url("http://stats.nba.com/stats/commonallplayers")
      .addParameter("LeagueId", "00")
      .addParameter("Season", "2015-16")
      .addParameter("IsOnlyCurrentSeason", "1")
    //Http(request)

    Future.value(10)
  }
}
