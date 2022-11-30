/*
 * This file is part of Bisq.
 *
 * Bisq is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at
 * your option) any later version.
 *
 * Bisq is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Bisq. If not, see <http://www.gnu.org/licenses/>.
 */

package bisq.monitor;

import bisq.monitor.clearnet.metric.MarketStats;
import bisq.monitor.reporter.Reporter;
import lombok.extern.slf4j.Slf4j;
import org.berndpruenster.netlayer.tor.Tor;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;

/**
 * Monitor executable for the Bisq network.
 *
 * @author Florian Reimair
 */
@Slf4j
public class Monitor {

    public Monitor(Properties properties, Reporter reporter) {
        List<Metric> metrics = new ArrayList<>();
        metrics.add(new MarketStats(reporter));

        // configure triggers execution if enabled
        metrics.forEach(metric -> metric.configure(properties));
    }

    public CompletableFuture<Void> shutDown() {
        return CompletableFuture.runAsync(() -> {
            Metric.haltAllMetrics();

            log.info("shutting down tor...");
            Tor tor = Tor.getDefault();
            if (tor != null) {
                tor.shutdown();
            }
        });
    }
}
