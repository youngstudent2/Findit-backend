package FindItTestSimulation;

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class StudentManagerTestSimulation extends Simulation {
    val baseUrl = "http://localhost:8089/api/"

	var headers_default =                                       // 设置默认的请求头
    Map(
      "User-Agent" -> "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0",
      "Content-Type" -> "application/json; charset=UTF-8",
	  "Accept-Language" -> "zh-CN,zh;q=0.5",
	  "Accept-Encoding" -> "gzip, deflate",
	  "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
	  "Authorization" -> "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxODE4NjAxMDYiLCJpYXQiOjE2MjMxMzYwNDIsImV4cCI6MTYyMzEzOTY0Mn0.h2BAOitykojXgjgBdZ6sZiRfGX58PPu4tVX2F6ZPjyY"
    )

    val httpProtocol = http
        .baseUrl(baseUrl)
		.headers(headers_default)

    val scn = scenario("item list")
    .exec( 
        http("item list get all").get("/items")
    )
    setUp(scn.inject(atOnceUsers(100)).protocols(httpProtocol))
}