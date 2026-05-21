package guarana.gtk;

import org.gnome.gobject.GObject;
import org.gnome.gobject.ParamSpec;
import scala.Function1;

class NotifyBridge implements GObject.NotifyCallback {
  private Function1<ParamSpec, scala.Unit> f;
  public NotifyBridge(Function1<ParamSpec, scala.Unit> f) {
    this.f = f;
  }

  @Override
  public void run(ParamSpec param) {
    f.apply(param);
  }
}