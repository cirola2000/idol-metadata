package com.github.cirola2000.idol.metadata.services;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.cirola2000.idol.metadata.models.DistributionModel;
import com.github.cirola2000.idol.metadata.services.DistributionServices;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
@RunWith(SpringRunner.class)
public class DistributionServicesTest {

	final String DISTRIBUTION_LABEL = "Distribution 12";

	final String DISTRIBUTION_URI = "Distribution uri";

	@Autowired
	MongoTemplate template;

	@Autowired
	DistributionServices distributionServices;

	DistributionModel distributionModel = new DistributionModel();

	@Before
	public void init() {
		distributionModel.setLabel(DISTRIBUTION_LABEL);
		distributionModel.setUri(DISTRIBUTION_URI);
	}

	@Test
	public void test1SavedistributionTest() {
		distributionServices.saveDistribution(distributionModel);
		assertThat(distributionServices.countAll(), is(new Long(1)));
	}

	@Test
	public void test2GetdistributionByURITest() {
		assertThat(distributionServices.getDistributionByURL(DISTRIBUTION_URI).getLabel(), is(DISTRIBUTION_LABEL));
	}

	@Test
	public void test3GetdistributionByIDTest() {
		assertThat(distributionServices.getDistributionByURL(DISTRIBUTION_URI).getId(), notNullValue());
	}

	@Test
	public void test4DropDatabase() {
		template.getDb().dropDatabase();
	}

}
