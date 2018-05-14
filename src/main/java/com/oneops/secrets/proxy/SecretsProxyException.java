/**
 * *****************************************************************************
 *
 * <p>Copyright 2017 Walmart, Inc.
 *
 * <p>Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 * <p>http://www.apache.org/licenses/LICENSE-2.0
 *
 * <p>Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * <p>*****************************************************************************
 */
package com.oneops.secrets.proxy;

import com.oneops.secrets.command.SecretsCommand;
import com.oneops.secrets.proxy.model.ErrorRes;
import javax.annotation.*;

/**
 * Exception thrown for any proxy server errors.
 *
 * @author Suresh
 */
public class SecretsProxyException extends RuntimeException {

  private SecretsCommand cmd;

  private ErrorRes err;

  public SecretsProxyException(@Nonnull SecretsCommand cmd, @Nonnull ErrorRes err) {
    super();
    this.cmd = cmd;
    this.err = err;
  }

  public SecretsProxyException(Throwable cause) {
    super(cause);
  }

  public SecretsProxyException(String message, Throwable cause) {
    super(message, cause);
  }

  /** Returns the {@link SecretsCommand} which caused the exception. */
  public @Nullable SecretsCommand getCmd() {
    return cmd;
  }

  /** Returns the {@link ErrorRes} which caused the exception. */
  public @Nullable ErrorRes getErr() {
    return err;
  }
}
