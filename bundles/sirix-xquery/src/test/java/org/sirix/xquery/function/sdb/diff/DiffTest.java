/**
 * Copyright (c) 2018, Sirix
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the <organization> nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.sirix.xquery.function.sdb.diff;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import org.brackit.xquery.QueryContext;
import org.brackit.xquery.QueryException;
import org.brackit.xquery.XQuery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sirix.Holder;
import org.sirix.TestHelper;
import org.sirix.TestHelper.PATHS;
import org.sirix.exception.SirixException;
import org.sirix.utils.DocumentCreator;
import org.sirix.xquery.SirixCompileChain;
import org.sirix.xquery.SirixQueryContext;
import org.sirix.xquery.node.DBStore;
import junit.framework.TestCase;

/**
 * @author Johannes Lichtenberger <lichtenberger.johannes@gmail.com>
 *
 */
public final class DiffTest extends TestCase {
  /** The {@link Holder} instance. */
  private Holder holder;

  @Override
  @Before
  public void setUp() throws SirixException {
    TestHelper.deleteEverything();
    holder = Holder.generateWtx();
    DocumentCreator.createVersionedWithUpdatesAndDeletes(holder.getXdmNodeWriteTrx());
    holder.getXdmNodeWriteTrx().close();
  }

  @Override
  @After
  public void tearDown() throws SirixException {
    holder.close();
    TestHelper.closeEverything();
  }

  @Test
  public void test() throws IOException, QueryException {
    final Path database = PATHS.PATH1.getFile();

    // Initialize query context and store.
    try (final DBStore store = DBStore.newBuilder().location(database.getParent()).build()) {
      final QueryContext ctx = new SirixQueryContext(store);

      final String dbName = database.toString();
      final String resName = TestHelper.RESOURCE;

      final String xq = "sdb:diff('" + dbName + "', '" + resName + "', 1, 5)";

      final XQuery query = new XQuery(new SirixCompileChain(store), xq);

      try (final ByteArrayOutputStream out = new ByteArrayOutputStream()) {
        query.serialize(ctx, new PrintStream(out));

        final String content = new String(out.toByteArray(), StandardCharsets.UTF_8);

        new XQuery(new SirixCompileChain(store), content).execute(ctx);
      }
    }
  }
}
