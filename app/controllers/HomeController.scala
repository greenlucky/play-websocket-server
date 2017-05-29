package controllers

import javax.inject.Inject

import actors.EchoActor
import akka.actor.{ActorSystem, Props}
import akka.stream.Materializer
import akka.util.ByteString
import org.slf4j.LoggerFactory
import play.api.libs.streams.ActorFlow
import play.api.mvc.{Controller, WebSocket}

import scala.concurrent.ExecutionContext

/**
  * Created by lam.nm on 5/29/2017.
  */
class HomeController @Inject() (implicit actorSystem: ActorSystem,
                                mat: Materializer,
                                cc: ExecutionContext) extends Controller{
  private val logger = LoggerFactory.getLogger(this.getClass)
    def api: WebSocket = WebSocket.accept[ByteString, ByteString] {
      implicit request =>
        ActorFlow.actorRef(out => {
          logger.debug("Income {}", out)
          Props(new EchoActor(out))
        })
    }
}
