/*
 * Copyright (c) 2015. Qubole Inc
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.qubole.quark.catalog.db.pojo;

import com.qubole.quark.catalog.db.RelSchema;
import com.qubole.quark.catalog.db.dao.DimensionDAO;
import org.junit.BeforeClass;
import org.junit.Test;
import org.skife.jdbi.v2.DBI;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by adeshr on 2/17/16.
 */
public class DimensionTest extends DbUtility {

  private static final String dbSchemaUrl = "jdbc:h2:mem:DimensionTest;DB_CLOSE_DELAY=-1";
  private static DimensionDAO dimensionDAO;
  @BeforeClass
  public static void setUpClass() throws ClassNotFoundException, SQLException,
      IOException, URISyntaxException {

    setUpDb(dbSchemaUrl, "sa", "", "tpcds.sql");

    DBI dbi = new DBI(dbSchemaUrl, "sa", "");
    dimensionDAO = dbi.onDemand(DimensionDAO.class);
  }

  @Test
  public void testGet() {
    List<RelSchema.DbDimension> dimensions= dimensionDAO.findByCubeId(1);
    assertThat(dimensions.size(), equalTo(8));
  }
}
