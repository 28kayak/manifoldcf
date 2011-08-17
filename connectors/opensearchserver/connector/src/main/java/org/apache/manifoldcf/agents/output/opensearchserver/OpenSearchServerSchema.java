package org.apache.manifoldcf.agents.output.opensearchserver;

import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.manifoldcf.core.interfaces.ManifoldCFException;

public class OpenSearchServerSchema extends OpenSearchServerConnection {

  public OpenSearchServerSchema(OpenSearchServerConfig config)
      throws ManifoldCFException {
    super(config);
    String indexName = config.getIndexName();
    StringBuffer url = getApiUrl("schema");
    url.append("&cmd=indexList");
    GetMethod method = new GetMethod(url.toString());
    String xpath = "count(/response/index[@name='" + indexName + "'])";
    call(method);
    if ("1".equals(checkXPath(xpath)))
      return;
    setResult(Result.ERROR, "Index not found");
  }
}
