import com.twitter.util.Future

class NBAService {
  def get(): Future[Int] = {
    Future.value(10)
  }
}
