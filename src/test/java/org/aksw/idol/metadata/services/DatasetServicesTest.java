package org.aksw.idol.metadata.services;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.aksw.idol.metadata.models.DatasetModel;
import org.aksw.idol.metadata.services.DatasetServices;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
@RunWith(SpringRunner.class)
public class DatasetServicesTest {

	final String DATASET_LABEL = "Dataset 12";
	
	final String DATASET_URI = "Dataset uri";
	
	
	
	@Autowired
	MongoTemplate template;
	
	@Autowired
	DatasetServices datasetServices;
	
	DatasetModel datasetModel = new DatasetModel();
	
	
	@Before
	public void init() {
		datasetModel.setLabel(DATASET_LABEL);
		datasetModel.setUri(DATASET_URI);
	}
	
	@Test
	public void test1SaveDatasetTest() {
		datasetServices.saveDataset(datasetModel);
		assertThat(datasetServices.countAll(), is(new Long(1))); 
	}
	
	
	@Test
	public void test2GetDatasetByURITest() {
		assertThat(datasetServices.getDatasetByURL(DATASET_URI).getLabel(), is(DATASET_LABEL));
	}
	
	@Test
	public void test3GetDatasetByIDTest() {
		assertThat(datasetServices.getDatasetByURL(DATASET_URI).getId(), notNullValue());
	}

	@Test
	public void test4DropDatabase() {
		template.getDb().dropDatabase();
	}
	
}
