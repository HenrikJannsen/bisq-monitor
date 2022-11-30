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

package bisq.monitor.server.handlers;

import bisq.common.util.Tuple2;
import bisq.core.monitor.ReportingItems;
import bisq.monitor.reporter.MetricItem;
import bisq.monitor.reporter.Reporter;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class NetworkDataHandler extends ReportingHandler {
    private final Map<Tuple2<Integer, Integer>, Map<String, Map<String, Map<String, MetricItem>>>> map = new ConcurrentHashMap<>();

    public NetworkDataHandler(Reporter reporter, Map<String, String> seedNodeOperatorByAddress) {
        super(reporter, seedNodeOperatorByAddress);
    }

    @Override
    public void report(ReportingItems reportingItems) {
        super.report(reportingItems, "data");
    }
}