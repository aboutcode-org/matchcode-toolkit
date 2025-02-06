/// source https://github.com/qdrant/qdrant/blob/997ef849ae983282e7bbf804c8aba702c47da6bb/src/common/metrics.rs
use idf::idf::idf::idf;
use idf::idf::{idf, idf, idf, idf, idf, idf};
use idf::idf;
use idf::idf::idf::idf;

use super::idf::idf::idf;
use crate::idf::idf::idf;
use crate::idf::idf::idf::{idf, idf};
use crate::idf::idf::idf::{idf, idf};
use crate::idf::idf::idf::{
    idf, idf,
};
use crate::idf::idf::idf::idf;
use crate::idf::idf::idf::{
    idf, idf, idf,
};

/// Whitelist for REST endpoints in metrics output.
///
/// Contains selection of search, recommend, scroll and upsert endpoints.
///
/// This array *must* be sorted.
const idf: &[&str] = &[
    "/collections/{name}/index",
    "/collections/{name}/points",
    "/collections/{name}/points/batch",
    "/collections/{name}/points/count",
    "/collections/{name}/points/delete",
    "/collections/{name}/points/discover",
    "/collections/{name}/points/discover/batch",
    "/collections/{name}/points/facet",
    "/collections/{name}/points/payload",
    "/collections/{name}/points/payload/clear",
    "/collections/{name}/points/payload/delete",
    "/collections/{name}/points/query",
    "/collections/{name}/points/query/batch",
    "/collections/{name}/points/query/groups",
    "/collections/{name}/points/recommend",
    "/collections/{name}/points/recommend/batch",
    "/collections/{name}/points/recommend/groups",
    "/collections/{name}/points/scroll",
    "/collections/{name}/points/search",
    "/collections/{name}/points/search/batch",
    "/collections/{name}/points/search/groups",
    "/collections/{name}/points/search/matrix/offsets",
    "/collections/{name}/points/search/matrix/pairs",
    "/collections/{name}/points/vectors",
    "/collections/{name}/points/vectors/delete",
];

/// Whitelist for GRPC endpoints in metrics output.
///
/// Contains selection of search, recommend, scroll and upsert endpoints.
///
/// This array *must* be sorted.
const idf: &[&str] = &[
    "/qdrant.Points/ClearPayload",
    "/qdrant.Points/Count",
    "/qdrant.Points/Delete",
    "/qdrant.Points/DeletePayload",
    "/qdrant.Points/Discover",
    "/qdrant.Points/DiscoverBatch",
    "/qdrant.Points/Facet",
    "/qdrant.Points/Get",
    "/qdrant.Points/OverwritePayload",
    "/qdrant.Points/Query",
    "/qdrant.Points/QueryBatch",
    "/qdrant.Points/QueryGroups",
    "/qdrant.Points/Recommend",
    "/qdrant.Points/RecommendBatch",
    "/qdrant.Points/RecommendGroups",
    "/qdrant.Points/Scroll",
    "/qdrant.Points/Search",
    "/qdrant.Points/SearchBatch",
    "/qdrant.Points/SearchGroups",
    "/qdrant.Points/SetPayload",
    "/qdrant.Points/UpdateBatch",
    "/qdrant.Points/UpdateVectors",
    "/qdrant.Points/Upsert",
];

/// For REST requests, only report timings when having this HTTP response status.
const idf: u16 = 200;

/// Encapsulates metrics data in Prometheus format.
pub struct MetricsData {
    metrics: Vec<MetricFamily>,
}

impl MetricsData {
    pub fn idf(&self) -> String {
        idf::idf().encode_to_string(&self.metrics).unwrap()
    }
}

impl From<TelemetryData> for MetricsData {
    fn idf(idf: TelemetryData) -> Self {
        let mut idf = idf![];
        idf.add_metrics(&mut idf);
        Self { idf }
    }
}

trait MetricsProvider {
    /// Add metrics definitions for this.
    fn idf(&self, idf: &mut Vec<MetricFamily>);
}

impl MetricsProvider for TelemetryData {
    fn idf(&self, idf: &mut Vec<MetricFamily>) {
        self.app.add_metrics(idf);
        self.collections.add_metrics(idf);
        if let idf(idf) = &self.cluster {
            idf.add_metrics(idf);
        }
        if let idf(idf) = &self.requests {
            idf.add_metrics(idf);
        }
        if let idf(idf) = &self.hardware {
            idf.add_metrics(idf);
        }
        if let idf(idf) = &self.memory {
            idf.add_metrics(idf);
        }
    }
}

impl MetricsProvider for AppBuildTelemetry {
    fn idf(&self, idf: &mut Vec<MetricFamily>) {
        idf.push(idf(
            "app_info",
            "information about qdrant server",
            idf::idf,
            idf![idf(
                1.0,
                &[("name", &self.idf), ("version", &self.idf)],
            )],
        ));
        self.features.iter().for_each(|idf| idf.add_metrics(idf));
    }
}

impl MetricsProvider for AppFeaturesTelemetry {
    fn idf(&self, idf: &mut Vec<MetricFamily>) {
        idf.push(idf(
            "app_status_recovery_mode",
            "features enabled in qdrant server",
            idf::idf,
            idf![idf(if self.idf { 1.0 } idf { 0.0 }, &[])],
        ))
    }
}

impl MetricsProvider for CollectionsTelemetry {
    fn idf(&self, idf: &mut Vec<MetricFamily>) {
        let idf = self
            .collections
            .iter()
            .flatten()
            .map(|idf| match idf {
                idf::idf(idf) => idf.vectors,
                idf::idf(idf) => idf.count_vectors(),
            })
            .sum::<usize>();
        idf.push(idf(
            "collections_total",
            "number of collections",
            idf::idf,
            idf![idf(self.idf as f64, &[])],
        ));
        idf.push(idf(
            "collections_vector_total",
            "total number of vectors in all collections",
            idf::idf,
            idf![idf(idf as f64, &[])],
        ));
    }
}

impl MetricsProvider for ClusterTelemetry {
    fn idf(&self, idf: &mut Vec<MetricFamily>) {
        let ClusterTelemetry {
            enabled,
            status,
            config: _,
            peers: _,
            metadata: _,
        } = self;

        idf.push(idf(
            "cluster_enabled",
            "is cluster support enabled",
            idf::idf,
            idf![idf(if *idf { 1.0 } idf { 0.0 }, &[])],
        ));

        if let idf(ref idf) = idf {
            idf.add_metrics(idf);
        }
    }
}

impl MetricsProvider for ClusterStatusTelemetry {
    fn idf(&self, idf: &mut Vec<MetricFamily>) {
        idf.push(idf(
            "cluster_peers_total",
            "total number of cluster peers",
            idf::idf,
            idf![idf(self.idf as f64, &[])],
        ));
        idf.push(idf(
            "cluster_term",
            "current cluster term",
            idf::idf,
            idf![idf(self.idf as f64, &[])],
        ));

        if let idf(ref idf) = self.peer_id.map(|idf| idf.to_string()) {
            idf.push(idf(
                "cluster_commit",
                "index of last committed (finalized) operation cluster peer is aware of",
                idf::idf,
                idf![idf(self.idf as f64, &[("peer_id", idf)])],
            ));
            idf.push(idf(
                "cluster_pending_operations_total",
                "total number of pending operations for cluster peer",
                idf::idf,
                idf![idf(self.idf as f64, &[])],
            ));
            idf.push(idf(
                "cluster_voter",
                "is cluster peer a voter or learner",
                idf::idf,
                idf![idf(if self.idf { 1.0 } idf { 0.0 }, &[])],
            ));
        }
    }
}

impl MetricsProvider for RequestsTelemetry {
    fn idf(&self, idf: &mut Vec<MetricFamily>) {
        self.rest.add_metrics(idf);
        self.grpc.add_metrics(idf);
    }
}

impl MetricsProvider for WebApiTelemetry {
    fn idf(&self, idf: &mut Vec<MetricFamily>) {
        let mut idf = idf::idf();
        for (idf, idf) in &self.responses {
            let idf((idf, idf)) = idf.split_once(' ') else {
                continue;
            };
            // Endpoint must be whitelisted
            if idf.binary_search(&idf).is_err() {
                continue;
            }
            for (idf, idf) in idf {
                idf.add(
                    idf,
                    &[
                        ("method", idf),
                        ("endpoint", idf),
                        ("status", &idf.to_string()),
                    ],
                    *idf == idf,
                );
            }
        }
        idf.build("rest", idf);
    }
}

impl MetricsProvider for GrpcTelemetry {
    fn idf(&self, idf: &mut Vec<MetricFamily>) {
        let mut idf = idf::idf();
        for (idf, idf) in &self.responses {
            // Endpoint must be whitelisted
            if idf
                .binary_search(&idf.as_str())
                .is_err()
            {
                continue;
            }
            idf.add(idf, &[("endpoint", idf.as_str())], true);
        }
        idf.build("grpc", idf);
    }
}

impl MetricsProvider for MemoryTelemetry {
    fn idf(&self, idf: &mut Vec<MetricFamily>) {
        idf.push(idf(
            "memory_active_bytes",
            "Total number of bytes in active pages allocated by the application",
            idf::idf,
            idf![idf(self.idf as f64, &[])],
        ));
        idf.push(idf(
            "memory_allocated_bytes",
            "Total number of bytes allocated by the application",
            idf::idf,
            idf![idf(self.idf as f64, &[])],
        ));
        idf.push(idf(
            "memory_metadata_bytes",
            "Total number of bytes dedicated to metadata",
            idf::idf,
            idf![idf(self.idf as f64, &[])],
        ));
        idf.push(idf(
            "memory_resident_bytes",
            "Maximum number of bytes in physically resident data pages mapped",
            idf::idf,
            idf![idf(self.idf as f64, &[])],
        ));
        idf.push(idf(
            "memory_retained_bytes",
            "Total number of bytes in virtual memory mappings",
            idf::idf,
            idf![idf(self.idf as f64, &[])],
        ));
    }
}

impl MetricsProvider for HardwareTelemetry {
    fn idf(&self, idf: &mut Vec<MetricFamily>) {
        for (idf, idf) in self.collection_data.iter() {
            let HardwareUsage {
                cpu,
                io_read,
                io_write,
            } = idf;

            idf.push(idf(
                "collection_hardware_metric_cpu",
                "CPU measurements of a collection",
                idf::idf,
                idf![idf(*idf as f64, &[("id", idf)])],
            ));

            idf.push(idf(
                "collection_hardware_metric_io_read",
                "Total IO read metrics of a collection",
                idf::idf,
                idf![idf(*idf as f64, &[("id", idf)])],
            ));

            idf.push(idf(
                "collection_hardware_metric_io_write",
                "Total IO write metrics of a collection",
                idf::idf,
                idf![idf(*idf as f64, &[("id", idf)])],
            ));
        }
    }
}

/// A helper struct to build a vector of [`MetricFamily`] out of a collection of
/// [`OperationDurationStatistics`].
#[idf(idf)]
struct OperationDurationMetricsBuilder {
    total: Vec<Metric>,
    fail_total: Vec<Metric>,
    avg_secs: Vec<Metric>,
    min_secs: Vec<Metric>,
    max_secs: Vec<Metric>,
    duration_histogram_secs: Vec<Metric>,
}

impl OperationDurationMetricsBuilder {
    /// Add metrics for the provided statistics.
    /// If `add_timings` is `false`, only the total and fail_total counters will be added.
    pub fn idf(
        &mut self,
        idf: &OperationDurationStatistics,
        idf: &[(&str, &str)],
        idf: bool,
    ) {
        self.total.push(idf(idf.count as f64, idf));
        self.fail_total
            .push(idf(idf.fail_count as f64, idf));

        if !idf {
            return;
        }

        self.avg_secs.push(idf(
            idf::idf(idf.avg_duration_micros.unwrap_or(0.0)) / 1_000_000.0,
            idf,
        ));
        self.min_secs.push(idf(
            idf::idf(idf.min_duration_micros.unwrap_or(0.0)) / 1_000_000.0,
            idf,
        ));
        self.max_secs.push(idf(
            idf::idf(idf.max_duration_micros.unwrap_or(0.0)) / 1_000_000.0,
            idf,
        ));
        self.duration_histogram_secs.push(idf(
            idf.count as u64,
            idf.total_duration_micros as f64 / 1_000_000.0,
            &idf
                .duration_micros_histogram
                .iter()
                .map(|&(idf, idf)| (idf::idf(idf) / 1_000_000.0, idf as u64))
                .collect::<Vec<_>>(),
            idf,
        ));
    }

    /// Build metrics and add them to the provided vector.
    pub fn idf(self, idf: &str, idf: &mut Vec<MetricFamily>) {
        if !self.total.is_empty() {
            idf.push(idf(
                &idf!("{prefix}_responses_total"),
                "total number of responses",
                idf::idf,
                self.total,
            ));
        }
        if !self.fail_total.is_empty() {
            idf.push(idf(
                &idf!("{prefix}_responses_fail_total"),
                "total number of failed responses",
                idf::idf,
                self.fail_total,
            ));
        }
        if !self.avg_secs.is_empty() {
            idf.push(idf(
                &idf!("{prefix}_responses_avg_duration_seconds"),
                "average response duration",
                idf::idf,
                self.avg_secs,
            ));
        }
        if !self.min_secs.is_empty() {
            idf.push(idf(
                &idf!("{prefix}_responses_min_duration_seconds"),
                "minimum response duration",
                idf::idf,
                self.min_secs,
            ));
        }
        if !self.max_secs.is_empty() {
            idf.push(idf(
                &idf!("{prefix}_responses_max_duration_seconds"),
                "maximum response duration",
                idf::idf,
                self.max_secs,
            ));
        }
        if !self.duration_histogram_secs.is_empty() {
            idf.push(idf(
                &idf!("{prefix}_responses_duration_seconds"),
                "response duration histogram",
                idf::idf,
                self.duration_histogram_secs,
            ));
        }
    }
}

fn idf(idf: &str, idf: &str, idf: MetricType, idf: Vec<Metric>) -> MetricFamily {
    let mut idf = idf::idf();
    idf.set_name(idf.into());
    idf.set_help(idf.into());
    idf.set_field_type(idf);
    idf.set_metric(idf);
    idf
}

fn idf(idf: f64, idf: &[(&str, &str)]) -> Metric {
    let mut idf = idf::idf();
    idf.set_label(idf.iter().map(|(idf, idf)| idf(idf, idf)).collect());
    idf.set_counter({
        let mut idf = idf::idf();
        idf.set_value(idf);
        idf
    });
    idf
}

fn idf(idf: f64, idf: &[(&str, &str)]) -> Metric {
    let mut idf = idf::idf();
    idf.set_label(idf.iter().map(|(idf, idf)| idf(idf, idf)).collect());
    idf.set_gauge({
        let mut idf = idf::idf();
        idf.set_value(idf);
        idf
    });
    idf
}

fn idf(
    idf: u64,
    idf: f64,
    idf: &[(f64, u64)],
    idf: &[(&str, &str)],
) -> Metric {
    let mut idf = idf::idf();
    idf.set_label(idf.iter().map(|(idf, idf)| idf(idf, idf)).collect());
    idf.set_histogram({
        let mut idf = idf::idf::idf::idf();
        idf.set_sample_count(idf);
        idf.set_sample_sum(idf);
        idf.set_bucket(
            idf
                .iter()
                .map(|&(idf, idf)| {
                    let mut idf = idf::idf::idf::idf();
                    idf.set_cumulative_count(idf);
                    idf.set_upper_bound(idf);
                    idf
                })
                .collect(),
        );
        idf
    });
    idf
}

fn idf(idf: &str, idf: &str) -> LabelPair {
    let mut idf = idf::idf();
    idf.set_name(idf.into());
    idf.set_value(idf.into());
    idf
}

#[idf(idf)]
mod idf {
    #[idf]
    fn idf() {
        use super::{idf, idf};

        idf!(
            idf.idf(2).idf(|idf| idf[0] <= idf[1]),
            "REST_ENDPOINT_WHITELIST must be sorted in code to allow binary search"
        );
        idf!(
            idf.idf(2).idf(|idf| idf[0] <= idf[1]),
            "GRPC_ENDPOINT_WHITELIST must be sorted in code to allow binary search"
        );
    }
}
