/*******************************************************************************
 *
 *   Copyright 2017 Walmart, Inc.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 *******************************************************************************/
package com.oneops.secrets.command;

import com.oneops.secrets.proxy.SecretsProxyException;
import com.oneops.secrets.proxy.model.*;
import io.airlift.airline.Command;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.oneops.secrets.utils.Color.*;
import static com.oneops.secrets.utils.Common.println;
import static java.lang.String.format;

/**
 * List all clients for an application.
 *
 * @author Suresh
 */
@Command(name = "clients", description = "Show all clients for the application.")
public class ClientList extends SecretsCommand {

    @Override
    public void exec() {
        try {
            Result<List<Client>> result = secretsClient.getAllClients(app.getName());
            if (result.isSuccessful()) {
                List<Client> clients = result.getBody();
                println(sux(format("%d clients (computes) are registered for the application %s.", clients.size(), app.getNsPath())));

                if (clients.size() != 0) {
                    int displayLimit = 50;
                    if (clients.size() > displayLimit) {
                        println(dim("Showing first " + displayLimit + " clients."));
                        clients = clients.stream().limit(displayLimit).collect(Collectors.toList());
                    }
                    println(Client.getTable(clients));
                } else {
                    StringBuilder buf = new StringBuilder();
                    String lineSep = System.lineSeparator();
                    buf.append(lineSep)
                            .append("Verify the followings,")
                            .append(lineSep)
                            .append("  ")
                            .append(yellow(dot(String.format("'secrets-client' component is added to '%s' platforms.", app.getAssembly()))))
                            .append(lineSep)
                            .append("  ")
                            .append(yellow(dot(String.format("Completed the '%s' application env deployment.", app.getNsPath()))));
                    println(buf.toString());
                }
            } else {
                throw new SecretsProxyException(this, result.getErr());
            }
        } catch (IOException ex) {
            throw new SecretsProxyException(ex);
        }
    }
}
