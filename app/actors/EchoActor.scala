package actors

import akka.actor.{Actor, ActorRef}
import org.slf4j.LoggerFactory
import akka.event.LoggingReceive

/**
  * Created by lam.nm on 5/29/2017.
  */
class EchoActor(out: ActorRef) extends Actor{
  private val logger = LoggerFactory.getLogger("controllers.HomeController")
  def receive = LoggingReceive{
    case whatever =>
      logger.info("Receive: {}", whatever)
      out ! whatever
      logger.info("Receive: {}", whatever)
  }
}
