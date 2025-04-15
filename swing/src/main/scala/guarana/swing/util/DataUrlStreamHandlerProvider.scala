package guarana
package swing.util

import java.io.{ByteArrayInputStream, InputStream}
import java.net.spi.URLStreamHandlerProvider
import java.net.{MalformedURLException, URL, URLConnection, URLDecoder, URLStreamHandler}
import java.util.Base64

class DataUrlStreamHandlerProvider extends URLStreamHandlerProvider {

  override def createURLStreamHandler(protocol: String): URLStreamHandler | Null = protocol match {
    case "data" => DataStreamHandler
    case _ => null
  }

  object DataStreamHandler extends URLStreamHandler {

    val DataProtoRegex = "((.*?/.*?)?(?:;(.*?)=(.*?))?)(?:;(base64)?)?,(.*)".r
    val DefaultMimeType = "text/plain;charset=US-ASCII"

    override protected def openConnection(u: URL): URLConnection = {
      val matcher = DataProtoRegex.pattern.matcher(u.getPath())
      matcher.matches()
      val mimeType = matcher.group(1).nullFold(identity, DefaultMimeType)
      val tpe = matcher.group(2)
      // val attribute = matcher.group(3)
      val charset = matcher.group(4)
      val b64 = matcher.group(5)
      val data = matcher.group(6)
      val bytes =
        if ("base64" == b64) Base64.getDecoder().decode(data)
        else {
          if (tpe == "text/") parseAsText(charset.nullFold(identity, "US-ASCII"), data)
          else throw new MalformedURLException("Can't handle data protocol which isn't base64 encoded binary or text mimetype")
        }
      new URLConnection(u) {
        override def connect(): Unit = {}
        override def getContentType() = mimeType
        override def getInputStream(): InputStream = ByteArrayInputStream(bytes)
      }
    }

    def parseAsText(charset: String, data: String): Array[Byte] = URLDecoder.decode(data, charset).getBytes()
  }
}
