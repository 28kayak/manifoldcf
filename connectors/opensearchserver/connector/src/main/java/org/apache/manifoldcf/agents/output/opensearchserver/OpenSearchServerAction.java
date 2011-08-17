package org.apache.manifoldcf.agents.output.opensearchserver;

import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.manifoldcf.core.interfaces.ManifoldCFException;

public class OpenSearchServerAction extends OpenSearchServerConnection {

  public enum CommandEnum {
    optimize, reload;
  }

  public OpenSearchServerAction(CommandEnum cmd, OpenSearchServerConfig config)
      throws ManifoldCFException {
    super(config);
    StringBuffer url = getApiUrl("action");
    url.append("&action=");
    url.append(cmd.name());
    GetMethod method = new GetMethod(url.toString());
    call(method);
    if ("OK".equals(checkXPath(xPathStatus)))
      return;
    setResult(Result.ERROR, checkXPath(xPathException));
  }
}
