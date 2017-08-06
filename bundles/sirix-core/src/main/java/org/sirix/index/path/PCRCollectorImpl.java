package org.sirix.index.path;

import java.util.Objects;
import java.util.Set;

import org.brackit.xquery.atomic.QNm;
import org.brackit.xquery.util.path.Path;
import org.brackit.xquery.util.path.PathException;
import org.sirix.api.NodeReadTrx;
import org.sirix.api.NodeWriteTrx;
import org.sirix.index.path.summary.PathSummaryReader;
import org.sirix.utils.LogWrapper;
import org.slf4j.LoggerFactory;

public final class PCRCollectorImpl implements PCRCollector {

	/** Logger. */
	private static final LogWrapper LOGGER = new LogWrapper(
			LoggerFactory.getLogger(PCRCollectorImpl.class));

	private final NodeReadTrx mRtx;

	public PCRCollectorImpl(final NodeReadTrx rtx) {
		mRtx = Objects.requireNonNull(rtx, "The transaction must not be null.");
	}

	@Override
	public PCRValue getPCRsForPaths(Set<Path<QNm>> paths) {
		try (final PathSummaryReader reader = mRtx instanceof NodeWriteTrx ? ((NodeWriteTrx) mRtx)
				.getPathSummary() : mRtx.getSession().openPathSummary(
				mRtx.getRevisionNumber())) {
			final long maxPCR = reader.getMaxNodeKey();
			final Set<Long> pathClassRecords = reader.getPCRsForPaths(paths);
			return PCRValue.getInstance(maxPCR, pathClassRecords);
		} catch (final PathException e) {
			LOGGER.error(e.getMessage(), e);
		}

		return PCRValue.getEmptyInstance();
	}
}