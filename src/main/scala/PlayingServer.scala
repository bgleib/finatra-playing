package com.bleib.finatra_playing

import com.twitter.finagle.http.{Response, Request}
import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.filters.CommonFilters
import com.twitter.finatra.http.routing.HttpRouter
import com.twitter.finatra.httpclient.modules.HttpClientModule
import com.twitter.finatra.json.modules.FinatraJacksonModule
import com.twitter.finatra.logging.filter.{LoggingMDCFilter, TraceIdMDCFilter}
import com.twitter.finatra.logging.modules.Slf4jBridgeModule

object PlayingServerMain extends PlayingServer

class PlayingServer extends HttpServer {

  val httpClientModule = new HttpClientModule {
    def dest: String = "stats.nba.com:80"
  }

  override val disableAdminHttpServer = true
  override val modules = Seq(Slf4jBridgeModule, FinatraJacksonModule, httpClientModule)

  override def configureHttp(router: HttpRouter) = {
    router
      .filter[LoggingMDCFilter[Request, Response]]
      .filter[TraceIdMDCFilter[Request, Response]]
      .filter[CommonFilters]
      .add[PlayingController]
  }
}